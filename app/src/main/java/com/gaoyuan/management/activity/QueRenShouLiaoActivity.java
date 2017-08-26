package com.gaoyuan.management.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gaoyuan.management.MyApplication;
import com.gaoyuan.management.R;
import com.gaoyuan.management.adapter.ImgAdapter;
import com.gaoyuan.management.adapter.QueRenShouLiaoAdapter;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;
import com.gaoyuan.management.bean.PicBean;
import com.gaoyuan.management.bean.PicBean2;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.RxUtils;
import com.gaoyuan.management.util.BitmapCompress;
import com.gaoyuan.management.util.CommonFunction;
import com.gaoyuan.management.util.GlideUtil;
import com.gaoyuan.management.util.LogUtil;
import com.gaoyuan.management.view.MyGridView;
import com.gaoyuan.management.view.MyListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class QueRenShouLiaoActivity extends BaseActivity {

    @BindView(R.id.ll_querenshouliao)
    LinearLayout mLayout;
    @BindView(R.id.tv_querenshouliao_addpic)
    TextView mTvAddpic;
    @BindView(R.id.tv_querenshouliao_pic)
    ImageView mImgQuerenshouliaoPic;
    @BindView(R.id.img_querenfaliao_qianming)
    ImageView mQianming;
    @BindView(R.id.img_querenfaliao_qianzi)
    ImageView mQianzi;
    @BindView(R.id.tv_querenfaliao_certain)
    TextView mCertain;
    @BindView(R.id.gv_querenshouliao)
    MyGridView myGridView;

    private ImgAdapter mAdapter;

    private List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean> mList;

    private String time, id;
    private String signPath;
    private String picPath;//上传图片返回的图片地址
    private List<String> picQrPath;
    private ArrayList<String> picList;
    private String items, imgList;

    private List<EditText> editTList;
    private Map<Integer, String> statusMap;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_que_ren_shou_liao);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        picQrPath = new ArrayList<>();
        editTList = new ArrayList<>();
        statusMap = new HashMap<>();
        mList = new ArrayList<>();
        picList = new ArrayList<>();
        List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean> list = (List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean>) getIntent().getSerializableExtra("list");
        time = getIntent().getStringExtra("time");
        id = getIntent().getStringExtra("id");
        mList.addAll(list);
        creatLayout();

    }


    @Override
    public void initData() {
        setCenterTitle("确认收料");
    }

    @Override
    public void initEvent() {
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (picList != null && picList.size() > 0) {
                    GlideUtil.imageBrower(QueRenShouLiaoActivity.this, 0, picList, "local");
                }
            }
        });
    }

    @OnClick({R.id.tv_querenshouliao_addpic, R.id.tv_querenshouliao_pic, R.id.img_querenfaliao_qianming, R.id.img_querenfaliao_qianzi, R.id.tv_querenfaliao_certain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_querenshouliao_addpic://选择图片
                CommonFunction.selectPic(this, false, 9, picList);
                break;
            case R.id.tv_querenshouliao_pic://收料图片
                try {
                    if (!TextUtils.isEmpty(picList.get(0))) {
                        List<String> list = new ArrayList<>();
                        list.add(picList.get(0));
                        GlideUtil.imageBrower(this, 0, list, "local");
                    }
                } catch (Exception e) {

                }
                break;
            case R.id.img_querenfaliao_qianming://签字图片
                if (!TextUtils.isEmpty(signPath)) {
                    List<String> list = new ArrayList<>();
                    list.add(signPath);
                    GlideUtil.imageBrower(this, 0, list, "local");
                }
                break;
            case R.id.img_querenfaliao_qianzi://签字
                Intent intent = new Intent(this, HandwrittenActivity.class);
                startActivityForResult(intent, 110);
                break;
            case R.id.tv_querenfaliao_certain://确认
                if (checkInput()) {
                    if (picList.size() == 0) {
                        showToastMsg("请上传确认收料图片");
                        return;
                    }
                    compress(this);
                }
                break;
        }
    }

    /**
     * 创建物料单
     */
    private void creatLayout() {
        for (int i = 0; i < mList.size(); i++) {
            View view = View.inflate(this, R.layout.item_querenshouliao, null);
            TextView mWuziName = (TextView) view.findViewById(R.id.tv_faliaomingxi_wuzi_name);
            TextView mWuziTime = (TextView) view.findViewById(R.id.tv_faliaomingxi_wuzi_time);
            TextView mWuziPinpai = (TextView) view.findViewById(R.id.tv_faliaomingxi_wuzi_pinpai);
            TextView mWuziXinghao = (TextView) view.findViewById(R.id.tv_faliaomingxi_wuzi_xinghao);
            TextView mWuziDanwei = (TextView) view.findViewById(R.id.tv_faliaomingxi_wuzi_danwei);
            TextView mWuziNum = (TextView) view.findViewById(R.id.tv_faliaomingxi_wuzi_num);
            final TextView mWuziStatus = (TextView) view.findViewById(R.id.tv_faliaomingxi_wuzi_status);
            final EditText mWuziHeshounum = (EditText) view.findViewById(R.id.et_querenfaliao_heshou);
            final ImageView mQueRenFaLiao = (ImageView) view.findViewById(R.id.img_querenshouliao_queren);
            final ImageView mJuJueFaLiao = (ImageView) view.findViewById(R.id.img_querenshouliao_jujue);

            editTList.add(mWuziHeshounum);

            FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean bean = mList.get(i);

            mWuziName.setText(bean.getName());
            mWuziTime.setText("发料时间：" + time);
            mWuziDanwei.setText("计算单位：" + bean.getUnitName());
            mWuziPinpai.setText("品牌：" + bean.getBrand());
            mWuziXinghao.setText("型号规格：" + bean.getSpecifications());
            mWuziNum.setText("数量：" + bean.getSendCount());

            final int finalI = i;
            mQueRenFaLiao.setOnClickListener(new View.OnClickListener() {//确认发料
                @Override
                public void onClick(View v) {
                    mWuziStatus.setText("确认收料");
                    mWuziStatus.setTextColor(Color.GREEN);
                    statusMap.put(finalI, "confirm");
                }
            });

            mJuJueFaLiao.setOnClickListener(new View.OnClickListener() {//拒绝发料
                @Override
                public void onClick(View v) {
                    mWuziStatus.setText("拒绝收料");
                    mWuziStatus.setTextColor(Color.RED);
                    statusMap.put(finalI, "retreated");
                    mWuziHeshounum.setText("0");
                }
            });

            mLayout.addView(view);
        }
    }

    /**
     * 检查输入，生成JSONArray
     *
     * @return
     */
    private boolean checkInput() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;

        for (int i = 0; i < mList.size(); i++) {
            jsonObject = new JSONObject();
            String status = statusMap.get(i);

            String s = editTList.get(i).getText().toString();
            LogUtil.e("核收数量：" + s + "\n" + "状态：" + status);


            if (TextUtils.isEmpty(s)) {
                showToastMsg("请输入第" + (i + 1) + "个物料单的核收数量");
                return false;
            }

            if (Integer.valueOf(s) > mList.get(i).getSendCount()) {
                showToastMsg("第" + (i + 1) + "个物料单的核收数量大于发货数量，请重新输入");
                return false;
            }

            if (TextUtils.isEmpty(status)) {
                showToastMsg("是否接收第" + (i + 1) + "个物料单中的物料");
                return false;
            }
            try {
                jsonObject.put("wuliaodanItemId", mList.get(i).getId());
                jsonObject.put("reciveStatus", status);
                jsonObject.put("reciveCount", s);

                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        items = jsonArray.toString();

        LogUtil.e("items：" + items);

        return true;
    }

    /**
     * 把图片集合转换成JSONArray
     *
     * @return
     */
    private String toJSONArrayPic() {

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            for (int i = 0; i < picQrPath.size(); i++) {
                jsonObject.put("imgSrc", picQrPath.get(i));
            }
            jsonArray.put(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("gy", "JSONArray:" + jsonArray.toString());
        return jsonArray.toString();
    }

    /**
     * 确认收料
     */
    private void receive() {

        if (TextUtils.isEmpty(picPath)) {
            showToastMsg("请前往签名");
            return;
        }
        imgList = toJSONArrayPic();
        map.put("token", Config.token);
        map.put("wuliaodanId", id);
        map.put("signRecive", picPath);
        map.put("items", items);
        map.put("imgList", imgList);

        RetrofitClient.getInstance().createApi().recive(map)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        dismissProgressDialog();
                        if (registerBean.getStatus().equals("ok")) {
                            showToastMsg("确认收料成功");
                            finish();
                        } else {
                            showToastMsg(registerBean.getErrorMessage());
                        }
                    }
                });
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

    /**
     * 上传收料图片
     */
    private void uploadPic2(List<String> list) {

        for (int i = 0; i < list.size(); i++) {
            File file = new File(list.get(i));
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            RetrofitClient.getInstance().createApi().uploadQueRen(imageBody)
                    .compose(RxUtils.<PicBean>io_main())
                    .subscribe(new Consumer<PicBean>() {
                        @Override
                        public void accept(PicBean picBean) throws Exception {
                            picQrPath.add(picBean.getUrl());
                            handler.sendEmptyMessage(1);
                        }
                    });
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (picQrPath.size() == picList.size()) {
                receive();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 110) {
                signPath = data.getStringExtra("path");
                Glide.with(this).load(signPath).into(mQianming);
                uploadPic(signPath);
            }

            if (requestCode == Config.SELECT_IMAGE) {
                picList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                picQrPath.clear();
                Log.e("gy", "图片数量：" + picList.size());
                mAdapter = new ImgAdapter(this, picList);
                myGridView.setAdapter(mAdapter);

                myGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertDialog alertDialog = new AlertDialog.Builder(QueRenShouLiaoActivity.this)
                                .setMessage("是否移除该图片?")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        picList.remove(position);
                                        mAdapter.notifyDataSetChanged();
                                        dialog.dismiss();
                                    }
                                }).create();
                        alertDialog.show();
                        return true;
                    }
                });
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
                        showProgressDialog("确认中");
                    }

                    @Override
                    public void onSuccess(List<String> files) {
                        GlideUtil.glideLocalImg(activity, mImgQuerenshouliaoPic, picList.get(0));
                        uploadPic2(files);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                    }
                }).launch();
    }
}
