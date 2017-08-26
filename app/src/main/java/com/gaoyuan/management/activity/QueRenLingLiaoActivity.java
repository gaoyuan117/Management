package com.gaoyuan.management.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.bean.PicBean;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.RxUtils;
import com.gaoyuan.management.util.GlideUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class QueRenLingLiaoActivity extends BaseActivity {

    @BindView(R.id.img_querenlingliao_qianming)
    ImageView mQianming;
    @BindView(R.id.img_querenlingliao_qianzi)
    ImageView mToQianzi;

    private String signPath;
    private String picPath;//上传图片返回的图片地址
    private String id;

    @Override
    public void setContent() {
        id = getIntent().getStringExtra("id");
        setContentView(R.layout.activity_que_ren_ling_liao);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        setCenterTitle("确认领料");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.img_querenlingliao_qianming, R.id.img_querenlingliao_qianzi, R.id.tv_querenlingliao_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_querenlingliao_qianming://签名图片
                if (!TextUtils.isEmpty(signPath)) {
                    List<String> list = new ArrayList<>();
                    list.add(signPath);
                    GlideUtil.imageBrower(this, 0, list, "local");
                }
                break;
            case R.id.img_querenlingliao_qianzi://签字
                Intent intent = new Intent(this, HandwrittenActivity.class);
                startActivityForResult(intent, 110);
                break;
            case R.id.tv_querenlingliao_sure://确定
                fenbaoshangRecive();
                break;
        }
    }

    /**
     * 上传签名图片
     */
    private void uploadPic(String path) {
        showProgressDialog("图片处理中");
        File file = new File(path);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RetrofitClient.getInstance().createApi().uploadSign(imageBody)
                .compose(RxUtils.<PicBean>io_main())
                .subscribe(new Consumer<PicBean>() {
                    @Override
                    public void accept(PicBean picBean) throws Exception {
                        picPath = picBean.getUrl();
                        dismissProgressDialog();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 110) {
                signPath = data.getStringExtra("path");
                Glide.with(this).load(signPath).into(mQianming);
                uploadPic(signPath);
            }
        }
    }

    private void fenbaoshangRecive() {
        if(TextUtils.isEmpty(picPath)){
            showToastMsg("请签字");
            return;
        }
        map.put("token", Config.token);
        map.put("fenliaodanId", id);
        map.put("signFenbaoshang", picPath);

        RetrofitClient.getInstance().createApi().fenbaoshangRecive(map)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean.getStatus().equals("ok")) {
                            showToastMsg("收料成功");
                            finish();
                        } else {
                            showToastMsg(registerBean.getErrorMessage());
                        }
                    }
                });
    }
}
