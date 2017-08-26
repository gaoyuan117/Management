package com.gaoyuan.management.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gaoyuan.management.MyApplication;
import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.bean.PicBean;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.bean.UserBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.RxUtils;
import com.gaoyuan.management.util.BitmapCompress;
import com.gaoyuan.management.util.CommonFunction;
import com.gaoyuan.management.util.GlideUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PersonalActivity extends BaseActivity {

    @BindView(R.id.img_personal_avatar)
    CircleImageView mPersonalAvatar;
    @BindView(R.id.tv_personal_account)
    TextView mPersonalAccount;
    @BindView(R.id.tv_personal_unit)
    TextView mPersonalUnit;
    @BindView(R.id.tv_personal_phone)
    TextView mPersonalPhone;
    @BindView(R.id.tv_personal_pic)
    TextView mPersonalPicName;
    @BindView(R.id.img_personal_yyzz)
    ImageView mPersonalYyzz;
    @BindView(R.id.img_personal_edit)
    ImageView mPersonalEdit;

    @BindView(R.id.ll_personal_conpany)
    LinearLayout mLayoutCompany;

    @BindView(R.id.ll_personal_pic)
    LinearLayout mLayoutPic;

    @BindView(R.id.ll_personal_job)
    LinearLayout mLayoutJob;
    @BindView(R.id.tv_personal_job)
    TextView mPersoanlJob;

    @BindView(R.id.ll_personal_project)
    LinearLayout mLayoutProject;
    @BindView(R.id.tv_personal_project)
    TextView mPersoanlProject;

    private String picPath;//上传后的图片地址
    private ArrayList<String> picList;
    private String userType;


    @Override
    public void setContent() {
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        setCenterTitle("个人信息");
        //根据登录角色 判断布局的显示隐藏

        userType = MyApplication.userBean.getResults().getUserType();
        if (userType.equals("gonghuoshang")) {
            mLayoutCompany.setVisibility(View.VISIBLE);
            mLayoutJob.setVisibility(View.GONE);
            mLayoutProject.setVisibility(View.GONE);
            mLayoutPic.setVisibility(View.VISIBLE);
            mPersonalPicName.setText("营业执照图片");

            //营业执照
            String yingyezhizhao = MyApplication.userBean.getResults().getYingyezhizhao();
            if (yingyezhizhao.contains("http")) {
                Glide.with(this).load(MyApplication.userBean.getResults().getYingyezhizhao())
                        .into(mPersonalYyzz);
            } else {
                Glide.with(this).load(Config.PIC_URL + MyApplication.userBean.getResults().getYingyezhizhao())
                        .into(mPersonalYyzz);
            }

        } else if (userType.equals("fenbaoshang")) {
            mLayoutCompany.setVisibility(View.VISIBLE);
            mLayoutJob.setVisibility(View.GONE);
            mLayoutProject.setVisibility(View.VISIBLE);
            mLayoutPic.setVisibility(View.VISIBLE);
            mPersonalPicName.setText("授权委托书图片");

            //营业执照
            String yingyezhizhao = MyApplication.userBean.getResults().getShouquanweituoshu();
            if (yingyezhizhao.contains("http")) {
                Glide.with(this).load(yingyezhizhao)
                        .into(mPersonalYyzz);
            } else {
                Glide.with(this).load(Config.PIC_URL + yingyezhizhao)
                        .into(mPersonalYyzz);
            }

        } else if (userType.equals("wuziguanliren")) {
            mLayoutCompany.setVisibility(View.GONE);
            mLayoutJob.setVisibility(View.VISIBLE);
            mLayoutProject.setVisibility(View.VISIBLE);
            mLayoutPic.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        setUserInfo();
    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.img_personal_avatar, R.id.img_personal_edit, R.id.img_personal_yyzz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_personal_avatar://头像
                CommonFunction.selectPic(this, false, 1, picList);
                break;
            case R.id.img_personal_edit://修改手机号
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("type", "phone");
                startActivityForResult(intent, 110);
                break;
            case R.id.img_personal_yyzz:
                String signSend = "";
                if (userType.equals("gonghuoshang")) {
                    signSend = MyApplication.userBean.getResults().getYingyezhizhao();
                } else {
                    signSend = MyApplication.userBean.getResults().getShouquanweituoshu();
                }
                if (!TextUtils.isEmpty(signSend)) {
                    List<String> list1 = new ArrayList<>();
                    list1.add(signSend);
                    GlideUtil.imageBrower(this, 0, list1, "net");
                }
                break;
        }
    }

    /**
     * 设置用户信息
     */
    private void setUserInfo() {
        Glide.with(this).load(MyApplication.userBean.getResults().getHeadportrait())
                .error(R.mipmap.default_avatar)
                .placeholder(R.mipmap.default_avatar)
                .into(mPersonalAvatar);

        mPersonalAccount.setText(MyApplication.userBean.getResults().getNickName());
        mPersonalUnit.setText(MyApplication.userBean.getResults().getCompany());
        mPersonalPhone.setText(MyApplication.userBean.getResults().getTelPhone());
        mPersoanlJob.setText(MyApplication.userBean.getResults().getVoJobName());
        mPersoanlProject.setText(MyApplication.userBean.getResults().getVoCsProjectName());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 110) {
                String phone = data.getStringExtra("phone");
                Log.e("gy", "回掉结果：" + phone);
                if (TextUtils.isEmpty(phone)) {
                    return;
                }
                MyApplication.userBean.getResults().setTelPhone(phone);
                setUserInfo();
            }

            if (requestCode == Config.SELECT_IMAGE) {
                picList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                compress();
            }
        }
    }

    /**
     * 压缩图片
     *
     * @param
     */
    private void compress() {
        BitmapCompress.get(this)
                .load(picList)
                .putGear(BitmapCompress.THIRD_GEAR)     //设定压缩档次，默认三挡
                .setCompressListener(new BitmapCompress.OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                        showProgressDialog("修改中");
                    }

                    @Override
                    public void onSuccess(List<String> files) {
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
        RetrofitClient.getInstance().createApi().uploadAvatar(imageBody)
                .compose(RxUtils.<PicBean>io_main())
                .subscribe(new Consumer<PicBean>() {
                    @Override
                    public void accept(PicBean picBean) throws Exception {
                        picPath = picBean.getUrl();
                        modAvatar(picPath);
                    }
                });
    }

    /**
     * 修改头像
     *
     * @param path
     */
    private void modAvatar(String path) {
        RetrofitClient.getInstance().createApi().modAvatar(Config.token, path)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        dismissProgressDialog();

                        if (registerBean.getStatus().equals("ok")) {
                            showToastMsg("修改成功");
                            Glide.with(PersonalActivity.this).load(registerBean.getResults()).error(R.mipmap.default_avatar)
                                    .into(mPersonalAvatar);
                        } else {
                            showToastMsg(registerBean.getErrorMessage());
                        }
                    }
                });
    }
}
