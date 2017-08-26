package com.gaoyuan.management.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class RegisterSupplyActivity extends BaseActivity {

    @BindView(R.id.et_register_supply_account)
    EditText mEtSupplyAccount;
    @BindView(R.id.et_register_supply_pwd)
    EditText mEtSupplyPwd;
    @BindView(R.id.et_register_supply_repwd)
    EditText mEtSupplyRepwd;
    @BindView(R.id.et_register_supply_unit)
    EditText mEtSupplyUnit;
    @BindView(R.id.et_register_supply_phone)
    EditText mEtSupplyPhone;
    @BindView(R.id.tv_register_supply_add)
    TextView mTvSupplyAdd;
    @BindView(R.id.tv_register_supply_pic)
    ImageView mImgSupplyPic;
    @BindView(R.id.bt_supply_register)
    Button mBtSupplyRegister;

    private ArrayList<String> picList;

    private String picPath;//上传后的图片地址

    @Override
    public void setContent() {
        setContentView(R.layout.activity_register_supply);
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

    @OnClick({R.id.tv_register_supply_add, R.id.bt_supply_register, R.id.rg_register_supply_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register_supply_add://选择图片
                CommonFunction.selectPic(this, false, 1, picList);
                break;
            case R.id.bt_supply_register://注册
                supplyRegister();
                break;
            case R.id.rg_register_supply_back:
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
                        showProgressDialog("图片处理中");
                    }

                    @Override
                    public void onSuccess(List<String> files) {
                        GlideUtil.glideLocalImg(activity, mImgSupplyPic, picList.get(0));
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
     * 供货商注册
     */
    private void supplyRegister() {
        String name = mEtSupplyAccount.getText().toString();
        String passWord = mEtSupplyPwd.getText().toString();
        final String rePassWord = mEtSupplyRepwd.getText().toString();
        String unit = mEtSupplyUnit.getText().toString();
        String phone = mEtSupplyPhone.getText().toString();
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
        if (TextUtils.isEmpty(unit)) {
            showToastMsg("请输入单位名称");
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
        if(TextUtils.isEmpty(picPath)){
            showToastMsg("请上传营业执照");
            return;
        }

        map.put("userType", "gonghuoshang");
        map.put("name", name);
        map.put("password", passWord);
        map.put("telPhone", phone);
        map.put("company", unit);
        map.put("yingyezhizhao", picPath);
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

}
