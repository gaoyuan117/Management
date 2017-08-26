package com.gaoyuan.management.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.gaoyuan.management.MyApplication;
import com.gaoyuan.management.R;
import com.gaoyuan.management.util.AppManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用程序Activity的基类
 * Created by ljl on 2016/12/11.
 */
public abstract class BaseActivity extends FragmentActivity {
    /**
     * 允许全屏
     */
    public Boolean FEATURE_NO_TITLE = true;
    public int width;
    public int height;
    private ProgressDialog progressDialog;
    public Map<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        if (FEATURE_NO_TITLE) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        AppManager.getAppManager().addActivity(this);
        map = new HashMap<>();
        progressDialog = new ProgressDialog(this);
        setContent();
        initData();
        init();
        initEvent();


    }

    public abstract void setContent();

    public abstract void init();

    public abstract void initData();

    public abstract void initEvent();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    protected BaseActivity getActivity() {
        return BaseActivity.this;
    }

    /**
     * 设置title
     */
    protected void setLeftTitle(String title) {
        if (title == null) {
            return;
        }
        if (title.length() > 12) {
            title = title.substring(0, 11) + "...";
        }
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(title);
        goBack();
    }

    protected void setCenterTitle(String title) {
        if (title == null) {
            return;
        }
        if (title.length() > 12) {
            title = title.substring(0, 11) + "...";
        }
        TextView tvTitle = (TextView) findViewById(R.id.center_title_tv);
        tvTitle.setText(title);
//		 goBack();
    }

    protected TextView setRightTitle(String title) {
        if (title == null) {
            return null;
        }
        TextView tvTitle = (TextView) findViewById(R.id.tv_right_text);
        tvTitle.setText(title);
        return tvTitle;
    }

    protected void setImg_right2(int id) {
        if (id == 0) {
            return;
        }
        ImageView ImageView = (android.widget.ImageView) findViewById(R.id.iv_right2);
        ImageView.setVisibility(View.VISIBLE);
        ImageView.setImageResource(id);
    }

    protected ImageView setImg_right(int id) {
        if (id == 0) {
            return null;
        }
        ImageView ImageView = (android.widget.ImageView) findViewById(R.id.img_right);
        ImageView.setVisibility(View.VISIBLE);
        ImageView.setImageResource(id);
        return ImageView;
    }

    /**
     * 设置布局
     */
    protected void goBack() {
        findViewById(R.id.iv_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void visibilityExit() {
        findViewById(R.id.exit_layout).setVisibility(View.GONE);
    }

    /**
     * 土司
     */
    protected void showToastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取控件ID
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T getId(int id) {
        return (T) findViewById(id);
    }

    public final String TAG = "BaseActivity";

    // 字体
    public void typeface(TextView txt) {
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/sv.ttf");
        txt.setTypeface(face);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void startAct(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    /**
     * 登录状态
     *
     * @return true登录 false未登录
     */
//    public boolean isLoginState() {
//        if (MyApplication.getInstance().UserInfo == null) {
//            return false;
//        }
//        return true;
//    }
    public void back(View view) {
        finish();
    }

    public void showProgressDialog(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


}
