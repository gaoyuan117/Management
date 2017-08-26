package com.gaoyuan.management;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.gaoyuan.management.bean.LoginBean;
import com.gaoyuan.management.bean.UserBean;

/**
 * Created by gaoyuan on 2017/7/5.
 */

public class MyApplication extends Application{

    public static Context app;
    public static UserBean userBean;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

    }
}
