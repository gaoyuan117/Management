package com.gaoyuan.management.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gaoyuan.management.MyApplication;
import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.LoginBean;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.BaseObjObserver;
import com.gaoyuan.management.rxjava.RxUtils;
import com.gaoyuan.management.util.LogUtil;
import com.gaoyuan.management.util.SharedPreference;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_user)
    EditText mEtUser;
    @BindView(R.id.et_login_pwd)
    EditText mEtPwd;
    @BindView(R.id.bt_login_login)
    Button mBtLogin;
    @BindView(R.id.tv_login_forget)
    TextView mTvForget;
    @BindView(R.id.tv_login_register)
    TextView mTvRegister;
    private TextView tvPhone;

    private String forgetPhone;

    private SharedPreference sharedPreference;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        sharedPreference = new SharedPreference("LoginBean");
    }

    @Override
    public void initData() {
        getForgetPhone();
    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.bt_login_login, R.id.tv_login_forget, R.id.tv_login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login_login://登陆
//                startActivity(new Intent(this, MainActivity.class));
                login();
                break;
            case R.id.tv_login_forget://忘记密码
                showForgetDialog();
                break;
            case R.id.tv_login_register://注册
                startActivity(new Intent(this, RegisterChooseActivity.class));
                break;
        }
    }


    /**
     * 弹出忘记密码的对话框
     */
    private void showForgetDialog() {
        Dialog dialog = new Dialog(this, R.style.dialog_type);
        View view = View.inflate(this, R.layout.dialog_prompt, null);
        tvPhone = (TextView) view.findViewById(R.id.tv_play_phone);
        final TextView tvPlayPhone = (TextView) view.findViewById(R.id.tv_play_phone);
        dialog.setContentView(view);
        dialog.show();

        if (!TextUtils.isEmpty(forgetPhone)) {
            tvPlayPhone.setText(forgetPhone);
        }

        tvPlayPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPhone(tvPlayPhone.getText().toString());
            }
        });
    }

    /**
     * 拨打电话
     *
     * @param phone
     */
    private void playPhone(String phone) {
        Uri uri = Uri.parse("tel:" + phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    /**
     * 登录
     */
    private void login() {
        String name = mEtUser.getText().toString();
        String passWord = mEtPwd.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToastMsg("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            showToastMsg("请输入密码");
            return;
        }
        RetrofitClient.getInstance().createApi().login(name, passWord)
                .compose(RxUtils.<LoginBean>io_main())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        if (loginBean.getStatus().equals("ok")) {
                            sharedPreference.edit()
                                    .putString("token", loginBean.getResults().getToken())
                                    .putString("userId", loginBean.getResults().getMyUser().getId() + "")
                                    .commit();
                            Config.token = loginBean.getResults().getToken();
                            Config.userId = loginBean.getResults().getMyUser().getId() + "";
                            startAct(MainActivity.class);
                            finish();
                        } else {
                            showToastMsg(loginBean.getErrorMessage());
                        }
                    }
                });
    }

    /**
     * 获取客服电话
     */
    private void getForgetPhone() {
        RetrofitClient.getInstance().createApi().forgetPassword(map)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {

                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean.getStatus().equals("ok")) {
                            forgetPhone = registerBean.getResults();
                        } else {
                            showToastMsg(registerBean.getErrorMessage());
                        }
                    }
                });
    }



}
