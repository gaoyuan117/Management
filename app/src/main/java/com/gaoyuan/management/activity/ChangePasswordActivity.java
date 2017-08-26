package com.gaoyuan.management.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.RxUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class ChangePasswordActivity extends BaseActivity {


    @BindView(R.id.et_change_oldpwd)
    EditText mEtOldpwd;
    @BindView(R.id.et_change_newpwd)
    EditText mEtNewpwd;
    @BindView(R.id.et_change_renewpwd)
    EditText mEtRenewpwd;
    @BindView(R.id.bt_change_certain)
    Button mBtCertain;


    @Override
    public void setContent() {
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        setCenterTitle("密码修改");

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @OnClick(R.id.bt_change_certain)
    public void onViewClicked() {
        changePassword();
    }

    /**
     * 修改密码
     */
    private void changePassword() {
        String oldPwd = mEtOldpwd.getText().toString();
        String newPwd = mEtNewpwd.getText().toString();
        String reNewPwd = mEtRenewpwd.getText().toString();

        if (TextUtils.isEmpty(oldPwd)) {
            showToastMsg("请输入老密码");
            return;
        }

        if (TextUtils.isEmpty(newPwd)) {
            showToastMsg("请输入新密码");
            return;
        }

        if (TextUtils.isEmpty(reNewPwd)) {
            showToastMsg("请输入确认密码");
            return;
        }

        if (!newPwd.equals(reNewPwd)) {
            showToastMsg("两次密码输入不一致");
            return;
        }
        map.put("token", Config.token);
        map.put("oldpwd", oldPwd);
        map.put("newPwd", newPwd);
        map.put("newPwdAgain", reNewPwd);
        RetrofitClient.getInstance().createApi().changePassword(map)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean.getStatus().equals("ok")) {
                            showToastMsg("密码修改成功");
                            finish();
                        } else {
                            showToastMsg(registerBean.getErrorMessage());
                        }
                    }
                });
    }

}
