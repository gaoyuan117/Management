package com.gaoyuan.management.rxjava;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gaoyuan.management.MyApplication;
import com.gaoyuan.management.base.HttpResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by admin on 2017/3/27.
 */

public abstract class BaseObjObserver<T> implements Observer<HttpResult<T>> {

    private static final String TAG = "BaseObjObserver";
    private boolean isToast = true;//是否toast
    private Context mContext;
    private String message;//加载框提示内容
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout refreshLayout;//下拉刷新
    private BaseQuickAdapter adapter;

    protected BaseObjObserver(Context context) {
        mContext = context;
    }

    protected BaseObjObserver(Context context, SwipeRefreshLayout refreshLayout) {
        this(context);
        this.refreshLayout = refreshLayout;
    }

    protected BaseObjObserver(Context context, boolean isToast) {
        mContext = context;
        this.isToast = isToast;
    }

    protected BaseObjObserver(Context context, String message) {
        this.mContext = context;
        this.message = message;
    }

    protected BaseObjObserver(Context context, String message, SwipeRefreshLayout refreshLayout) {
        this.mContext = context;
        this.message = message;
        this.refreshLayout = refreshLayout;
    }

    protected BaseObjObserver(Context context, String message, boolean isToast) {
        this(context, message);
        this.isToast = isToast;
    }

    protected BaseObjObserver(Context context, SwipeRefreshLayout refreshLayout, BaseQuickAdapter adapter) {
        this(context);
        this.adapter = adapter;
        this.refreshLayout = refreshLayout;
    }


    @Override
    public void onNext(HttpResult<T> value) {
        if (value.status.equals("ok")) {
            T t = value.results;
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
        if (adapter != null) {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onComplete() {

        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setEnabled(false);
        }
    }

    protected abstract void onHandleSuccess(T t);

    protected void onHandleError(String code, String msg) {
        //根据code处理

        //是否Toast，默认true
        if (isToast) {
            Toast.makeText(MyApplication.app, msg, Toast.LENGTH_SHORT).show();
        }

        if (adapter != null) {
            if (code.equals("ok")) {
                adapter.loadMoreEnd();
            }
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

