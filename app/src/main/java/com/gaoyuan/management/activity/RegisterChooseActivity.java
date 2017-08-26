package com.gaoyuan.management.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.PicBean;
import com.gaoyuan.management.bean.RegisterListBean;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.BaseObjObserver;
import com.gaoyuan.management.rxjava.RxUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class RegisterChooseActivity extends BaseActivity {

    @BindView(R.id.rg_register_choose_supply)
    RadioButton mRgSupply;
    @BindView(R.id.rg_register_choose_manager)
    RadioButton mRgManager;
    @BindView(R.id.rg_register_choose_subcontractor)
    RadioButton mRgSubcontractor;
    private String v_path;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_register_choose);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.video, R.id.rg_register_choose_supply, R.id.rg_register_choose_manager, R.id.rg_register_choose_subcontractor, R.id.rg_register_choose_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rg_register_choose_supply://供应商注册
                startActivity(new Intent(this, RegisterSupplyActivity.class));
                break;
            case R.id.rg_register_choose_manager://物资管理人员注册
                startActivity(new Intent(this, RegisterManagementActivity.class));
                break;
            case R.id.rg_register_choose_subcontractor://分包商注册
                startActivity(new Intent(this, RegisterDistributeActivity.class));
                break;
            case R.id.rg_register_choose_back:
                finish();
                break;
        }
    }




}
