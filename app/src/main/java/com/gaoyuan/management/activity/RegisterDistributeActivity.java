package com.gaoyuan.management.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.gaoyuan.management.R;
import com.gaoyuan.management.adapter.ChooseAdapter;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.CommomBean;
import com.gaoyuan.management.bean.PicBean;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.bean.RegisterListBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.BaseObjObserver;
import com.gaoyuan.management.rxjava.RxUtils;
import com.gaoyuan.management.util.BitmapCompress;
import com.gaoyuan.management.util.CommonFunction;
import com.gaoyuan.management.util.GlideUtil;
import com.gaoyuan.management.util.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RegisterDistributeActivity extends BaseActivity {

    @BindView(R.id.et_register_distribute_account)
    EditText mDistributeAccount;
    @BindView(R.id.et_register_distribute_pwd)
    EditText mDistributePwd;
    @BindView(R.id.et_register_distribute_repwd)
    EditText mDistributeRepwd;
    @BindView(R.id.et_register_distribute_phone)
    EditText mDistributePhone;
    @BindView(R.id.tv_register_distribute_add)
    TextView mTvDistributeAdd;
    @BindView(R.id.tv_register_distribute_pic)
    ImageView mImgDistributePic;
    @BindView(R.id.sp_register_distribute_position)
    Spinner mSpinnerPosition;
    @BindView(R.id.sp_register_distribute_project)
    Spinner mSpinnerProject;
    @BindView(R.id.sp_register_distribute_gongsi)
    Spinner mSpinnerGongsi;
    @BindView(R.id.bt_distribute_register)
    Button mBtDistributeRegister;

    private ChooseAdapter mChooseAdapter;
    private ChooseAdapter mProjectAdapter;
    private ChooseAdapter mGongsiAdapter;

    private ArrayList<String> picList;
    private String picPath;//上传后的图片地址

    private List<String> positionList;
    private List<String> projectList;
    private List<String> gongsiList;

    private RegisterListBean mRegisterListBean;

    private int projectIndex, positionIndex, gongSiIndex;


    @Override
    public void setContent() {
        setContentView(R.layout.activity_register_distribute);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        positionList = new ArrayList<>();
        projectList = new ArrayList<>();
        gongsiList = new ArrayList<>();

        mChooseAdapter = new ChooseAdapter(this, positionList);
        mProjectAdapter = new ChooseAdapter(this, projectList);
        mGongsiAdapter = new ChooseAdapter(this, gongsiList);

        mSpinnerPosition.setAdapter(mChooseAdapter);
        mSpinnerProject.setAdapter(mProjectAdapter);
        mSpinnerGongsi.setAdapter(mGongsiAdapter);

    }

    @Override
    public void initData() {
        getInfoList();
    }

    @Override
    public void initEvent() {
        selectPosition();
        selectProject();
        selectGongsi();
    }

    @OnClick({R.id.tv_register_distribute_add, R.id.bt_distribute_register, R.id.rg_register_distribute_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register_distribute_add://选择图片
                CommonFunction.selectPic(this, false, 1, picList);
                break;
            case R.id.bt_distribute_register://注册
                distributeRegister();
                break;
            case R.id.rg_register_distribute_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                picList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                compress(this);
            }
        }
    }

    /**
     * 选择职务
     */
    private void selectPosition() {
        mSpinnerPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        mSpinnerProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    /**
     * 选择公司
     */
    private void selectGongsi() {
        mSpinnerProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gongSiIndex = position;
                LogUtil.e("选中的公司：" + gongSiIndex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * 压缩图片
     *
     * @param activity
     */
    private void compress(final Activity activity) {

        BitmapCompress.get(this)
                .load(picList)
                .putGear(BitmapCompress.THIRD_GEAR)     //设定压缩档次，默认三挡
                .setCompressListener(new BitmapCompress.OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                        showProgressDialog("图片处理中...");

                    }

                    @Override
                    public void onSuccess(List<String> files) {
                        GlideUtil.glideLocalImg(activity, mImgDistributePic, picList.get(0));
                        uploadPic(files.get(0));
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                    }
                }).launch();
    }

    /**
     * 上传图片
     */
    private void uploadPic(String path) {
        File file = new File(path);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RetrofitClient.getInstance().createApi().uploadPic(imageBody)
                .compose(RxUtils.<PicBean>io_main())
                .subscribe(new Consumer<PicBean>() {
                    @Override
                    public void accept(PicBean picBean) throws Exception {
                        picPath = picBean.getUrl();
                        dismissProgressDialog();
                    }
                });
    }

    /**
     * 分包商注册
     */
    private void distributeRegister() {
        String name = mDistributeAccount.getText().toString();
        String passWord = mDistributePwd.getText().toString();
        final String rePassWord = mDistributeRepwd.getText().toString();
        String phone = mDistributePhone.getText().toString();
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
        if (TextUtils.isEmpty(picPath)) {
            showToastMsg("请上传授权委托书");
            return;
        }

        if (mRegisterListBean == null) {
            showToastMsg("网络链接异常");
            return;
        }

        map.put("userType", "fenbaoshang");
        map.put("name", name);
        map.put("password", passWord);
        map.put("telPhone", phone);
        map.put("csProjectId", mRegisterListBean.getProjectList().get(projectIndex).getId());
        map.put("jobId", mRegisterListBean.getJobList().get(positionIndex).getId());
        map.put("companyId", mRegisterListBean.getCompanyList().get(gongSiIndex).getId());
        map.put("shouquanweituoshu", picPath);
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
        RetrofitClient.getInstance().createApi().registerList("fenbaoshang")
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

                        for (int i = 0; i < registerListBean.getCompanyList().size(); i++) {
                            gongsiList.add(registerListBean.getCompanyList().get(i).getName());
                        }

                        mChooseAdapter.notifyDataSetChanged();
                        mProjectAdapter.notifyDataSetChanged();
                        mGongsiAdapter.notifyDataSetChanged();
                    }
                });
    }
}
