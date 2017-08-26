package com.gaoyuan.management.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.gaoyuan.management.R;
import com.gaoyuan.management.adapter.ChooseAdapter;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.bean.RegisterListBean;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.BaseObjObserver;
import com.gaoyuan.management.rxjava.RxUtils;
import com.gaoyuan.management.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class RegisterManagementActivity extends BaseActivity {

    @BindView(R.id.et_register_manage_account)
    EditText mEtManageAccount;
    @BindView(R.id.et_register_manage_pwd)
    EditText mEtManagePwd;
    @BindView(R.id.et_register_manage_repwd)
    EditText mEtManageRepwd;
    @BindView(R.id.et_register_manage_phone)
    EditText mEtManagePhone;
    @BindView(R.id.sp_register_manage_position)
    Spinner mSpManagePosition;
    @BindView(R.id.sp_register_manage_project)
    Spinner mSpManageProject;
    @BindView(R.id.bt_manage_register)
    Button mBtRegister;

    private RegisterListBean mRegisterListBean;

    private ChooseAdapter projectAdapter;
    private ChooseAdapter positionAdapter;

    private List<String> projectList;
    private List<String> positionList;

    private int projectIndex, positionIndex;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_management);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        projectList = new ArrayList<>();
        positionList = new ArrayList<>();

        projectAdapter = new ChooseAdapter(this, projectList);
        positionAdapter = new ChooseAdapter(this, positionList);

        mSpManageProject.setAdapter(projectAdapter);
        mSpManagePosition.setAdapter(positionAdapter);

    }

    @Override
    public void initData() {
        getInfoList();
    }

    @Override
    public void initEvent() {
        selectProject();
        selectPosition();
    }

    /**
     * 选择职务
     */
    private void selectPosition() {
        mSpManagePosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionIndex = position;
                LogUtil.e("选中的职务：" + positionIndex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 选择项目
     */
    private void selectProject() {
        mSpManageProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                projectIndex = position;
                LogUtil.e("选中的项目：" + projectIndex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.bt_manage_register, R.id.rg_register_manage_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_manage_register://注册
                managerRegister();
                break;
            case R.id.rg_register_manage_back:
                finish();
                break;
        }
    }

    /**
     *  分包商注册
     */
    private void managerRegister() {
        String name = mEtManageAccount.getText().toString();
        String passWord = mEtManagePwd.getText().toString();
        String rePassWord = mEtManageRepwd.getText().toString();
        String phone = mEtManagePhone.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToastMsg("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            showToastMsg("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(rePassWord)) {
            showToastMsg("请输入确认密码");
            return;
        }
        if (passWord.length() < 6) {
            showToastMsg("密码最少6为位");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showToastMsg("请输入手机号");
            return;
        }
        if (!passWord.equals(rePassWord)) {
            showToastMsg("两次密码输入不一致");
            return;
        }

        if (mRegisterListBean == null) {
            showToastMsg("网络链接异常");
            return;
        }

        map.put("userType", "wuziguanliren");
        map.put("name", name);
        map.put("password", passWord);
        map.put("telPhone", phone);
        map.put("csProjectId", mRegisterListBean.getProjectList().get(projectIndex).getId());
        map.put("jobId", mRegisterListBean.getJobList().get(positionIndex).getId());
        map.put("shouquanweituoshu", "授权委托书图片");
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("注册中..");
        progressDialog.show();
        RetrofitClient.getInstance().createApi().register(map)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        progressDialog.dismiss();
                        if (registerBean.getStatus().equals("ok")) {
                            showToastMsg("注册成功");
                            finish();
                        } else {
                            showToastMsg(registerBean.getErrorMessage());
                        }
                    }
                });
    }

    /**
     * 获取公司 职位 项目集合
     */
    private void getInfoList() {
        RetrofitClient.getInstance().createApi().registerList("wuziguanliren")
                .compose(RxUtils.<HttpResult<RegisterListBean>>io_main())
                .subscribe(new BaseObjObserver<RegisterListBean>(this) {
                    @Override
                    protected void onHandleSuccess(RegisterListBean registerListBean) {
                        mRegisterListBean = registerListBean;
                        for (int i = 0; i < registerListBean.getJobList().size(); i++) {
                            positionList.add(registerListBean.getJobList().get(i).getName());
                        }

                        for (int i = 0; i < registerListBean.getProjectList().size(); i++) {
                            projectList.add(registerListBean.getProjectList().get(i).getName());
                        }

                        positionAdapter.notifyDataSetChanged();
                        projectAdapter.notifyDataSetChanged();
                    }
                });
    }
}
