package com.gaoyuan.management.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gaoyuan.management.MyApplication;
import com.gaoyuan.management.R;


/**
 * 应用程序Activity的基类
 * Created by ljl on 2016/12/11.
 */
public abstract class BaseFragment extends Fragment {
    public View mView;
    protected MyApplication myApplication;
    protected Context context;
    protected Activity activity;
    public int width;
    public int height;
    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    public BaseFragment(MyApplication myApplication, Activity activity, Context context) {
        this.myApplication = myApplication;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("Create: " + this.getClass().getSimpleName());
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
        setContent();
        initData();
        initView();
        initEvent();
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);// 先移除
        }
        return mView;
    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 防止被T后，没点确定按钮然后按了home键，长期在后台又进app导致的crash
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
    }

    /**
     * 设置布局
     */
    public abstract void setContent();

    public abstract void initData();

    public abstract void initView();

    public abstract void initEvent();

    protected void showToastMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置title
     */
    protected void setCenterTitle(String title) {
        if (title == null) {
            return;
        }
        if (title.length() > 12) {
            title = title.substring(0, 11) + "...";
        }
        TextView tvTitle = (TextView) mView.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        goBack();
    }

    protected ImageView setImg_right(int id) {
        if (id == 0) {
            return null;
        }
        ImageView ImageView = (android.widget.ImageView) mView.findViewById(R.id.img_right);
        ImageView.setVisibility(View.VISIBLE);
        ImageView.setImageResource(id);
        return ImageView;
    }

    protected void visibilityExit() {
        mView.findViewById(R.id.exit_layout).setVisibility(View.GONE);
    }

    /**
     * 获取控件ID
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T getId(int id) {
        return (T) mView.findViewById(id);
    }

    /**
     * 设置布局
     */
    protected void goBack() {
        mView.findViewById(R.id.iv_back).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                });
    }

    /**
     * 监听数据变化 用于Fragment 与 Activity 通信
     *
     * @author Administrator
     */
    public interface DataChangeListener {
        void dataChange();

        void success(Object data);

        void fail(String msg);
    }

    protected DataChangeListener listener;

    public void setDataChangeListener(DataChangeListener listener) {
        this.listener = listener;
    }

    protected void onFragmentVisibleChange(boolean isVisible) {
        Log.w("BaseFragment", "onFragmentVisibleChange -> isVisible: " + isVisible);
    }

}
