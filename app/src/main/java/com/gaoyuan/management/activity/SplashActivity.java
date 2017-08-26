package com.gaoyuan.management.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.gaoyuan.management.MyApplication;
import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.bean.LoginBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.util.LogUtil;
import com.gaoyuan.management.util.SharedPreference;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity {

    private SharedPreference sharedPreference;
    private String token;
    private String userId;


    @Override
    public void setContent() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void init() {
        sharedPreference = new SharedPreference("LoginBean");
        token = sharedPreference.get("token");
        userId = sharedPreference.get("userId");
        LogUtil.e("token：" + token + "\n" + "userId:" + userId);
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (TextUtils.isEmpty(token)) {
                            startAct(LoginActivity.class);
                            finish();
                        } else {
                            Config.token = token;
                            Config.userId = userId;
                            startAct(MainActivity.class);
                            finish();
                        }
                    }
                });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

}
