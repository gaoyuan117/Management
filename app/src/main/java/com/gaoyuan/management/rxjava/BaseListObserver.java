package com.gaoyuan.management.rxjava;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.gaoyuan.management.MyApplication;
import com.gaoyuan.management.base.HttpArray;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/3/27.
 */

public abstract class BaseListObserver<T> implements Observer<HttpArray<T>> {
    private static final String TAG = "BaseObjObserver";
    private boolean isToast = true;//是否toast
    private Context mContext;
    private String message;//加载框提示内容
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout refreshLayout;//下拉刷新

    protected BaseListObserver(Context context) {
        mContext = context;
    }

    protected BaseListObserver(Context context, SwipeRefreshLayout refreshLayout) {
        this(context);
        this.refreshLayout = refreshLayout;
    }

    protected BaseListObserver(Context context, boolean isToast) {
        mContext = context;
        this.isToast = isToast;
    }

    protected BaseListObserver(Context context, String message) {
        this.mContext = context;
        this.message = message;
    }

    protected BaseListObserver(Context context, String message, SwipeRefreshLayout refreshLayout) {
        this.mContext = context;
        this.message = message;
        this.refreshLayout = refreshLayout;
    }

    protected BaseListObserver(Context context, String message, boolean isToast) {
        this(context, message);
        this.isToast = isToast;
    }


    @Override
    public void onNext(HttpArray<T> value) {
        if (value.status.equals("ok")) {
            List<T> t = value.results;
            onHandleSuccess(t);
            if (refreshLayout != null) {
                refreshLayout.setRefreshing(false);
            }
        } else {
            onHandleError(value.status, value.errorMessage);
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "error:" + e.toString());
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setEnabled(false);
        }

    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");

        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setEnabled(false);
        }
    }

    protected abstract void onHandleSuccess(List<T> list);

    protected void onHandleError(String code, String msg) {
        //根据code处理

        //是否Toast，默认true
        if (isToast) {
            Toast.makeText(MyApplication.app, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        //显示对话框
        if (!TextUtils.isEmpty(message)) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }
}
