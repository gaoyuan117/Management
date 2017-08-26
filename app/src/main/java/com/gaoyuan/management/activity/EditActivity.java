package com.gaoyuan.management.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.bean.UserBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.RxUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class EditActivity extends BaseActivity {

    @BindView(R.id.et_edit_input)
    EditText mEditInput;
    private String type;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        type = getIntent().getStringExtra("type");
        if (type.equals("phone")) {
            setCenterTitle("修改手机号");
            mEditInput.setHint("请输入手机号");
            mEditInput.setInputType(InputType.TYPE_CLASS_PHONE);
        }

        setRightTitle("确定").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modPhone();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    private void modPhone() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("修改中");
        final String s = mEditInput.getText().toString();
        if (TextUtils.isEmpty(s)) {
            showToastMsg("请输入手机号");
            return;
        }
        progressDialog.show();
        RetrofitClient.getInstance().createApi().modPhone(Config.token, s)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        progressDialog.dismiss();
                        if (registerBean.getStatus().equals("ok")) {
                            Intent intent = new Intent();

                            intent.putExtra("phone", s);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            showToastMsg(registerBean.getErrorMessage());
                        }
                    }
                });
    }
}
