package com.gaoyuan.management.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;
import com.gaoyuan.management.bean.FenBaoShangBean;
import com.gaoyuan.management.bean.PicBean;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.BaseObjObserver;
import com.gaoyuan.management.rxjava.RxUtils;
import com.gaoyuan.management.util.GlideUtil;
import com.gaoyuan.management.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class FenLiaoActivity extends BaseActivity {

    @BindView(R.id.ll_fenliao)
    LinearLayout mLayout;
    @BindView(R.id.sp_fenliao_choose)
    Spinner mSpChoose;
    @BindView(R.id.tv_fenliao_sure)
    TextView mQueRen;
    @BindView(R.id.img_querenfaliao_qianming)
    ImageView mQianming;
    @BindView(R.id.img_querenfaliao_qianzi)
    ImageView mQianzi;

    private String sendTime, recTime, id, gonghuoshang;
    private List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean> mList;
    private List<EditText> editTList;
    private List<String> projectList;
    private List<FenBaoShangBean.FenbaoshangListBean> listBean;
    private int index;
    private String items;
    private String signPath;
    private String picPath;//上传图片返回的图片地址

    @Override
    public void setContent() {
        setContentView(R.layout.activity_fen_liao);
        ButterKnife.bind(this);

        projectList = new ArrayList<>();
        editTList = new ArrayList<>();
        mList = new ArrayList<>();

        List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean> list = (List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean>) getIntent().getSerializableExtra("list");
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getFenliaoStatus().equals("fened")) {
                mList.add(list.get(i));
            }
        }
        sendTime = getIntent().getStringExtra("sedTime");
        recTime = getIntent().getStringExtra("recTime");
        id = getIntent().getStringExtra("id");
        gonghuoshang = getIntent().getStringExtra("gonghuoshang");
    }

    @Override
    public void init() {
        setCenterTitle("分料");
        creatLayout();
    }

    @Override
    public void initData() {
        getFenBaoShangList();
    }

    @Override
    public void initEvent() {
        mSpChoose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 创建物料单
     */
    private void creatLayout() {
        for (int i = 0; i < mList.size(); i++) {

            View view = View.inflate(this, R.layout.item_fen_liao, null);
            ViewHolder viewHolder = new ViewHolder(view);
            editTList.add(viewHolder.mInput);
            FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean bean = mList.get(i);

            viewHolder.mWuziName.setText(bean.getName());
            viewHolder.mWuziTime.setText("发料时间：" + sendTime);
            viewHolder.mWuziDanwei.setText("计算单位：" + bean.getUnitName());
            viewHolder.mWuziPinpai.setText("品牌：" + bean.getBrand());
            viewHolder.mWuziXinghao.setText("型号规格：" + bean.getSpecifications());
            viewHolder.mWuziNum.setText("数量：" + bean.getSendCount());
            viewHolder.mWuziHeshounum.setText("核收数量：" + bean.getReciveCount());
            viewHolder.mGongHuoshang.setText("供货商：" + gonghuoshang);
            viewHolder.mReceiveTime.setText("收料时间：" + recTime);
            viewHolder.mYiFaSong.setText("已发数量：" + bean.getFenliaoCount());
            viewHolder.mShengYu.setText("剩余数量：" + bean.getRestCount());

            String fenliaoStatus = bean.getFenliaoStatus();
            if (fenliaoStatus.equals("waitFen") || fenliaoStatus.equals("fning")) {
                viewHolder.mImgStatus.setImageResource(R.mipmap.weifenwan);
            } else if (fenliaoStatus.equals("fened")) {
                viewHolder.mImgStatus.setImageResource(R.mipmap.yifenwan);
            }

            mLayout.addView(view);
        }
    }

    /**
     * 获取分包商列表
     */
    private void getFenBaoShangList() {
        RetrofitClient.getInstance().createApi().findFenbaoshangUsersByProject(Config.token)
                .compose(RxUtils.<HttpResult<FenBaoShangBean>>io_main())
                .subscribe(new BaseObjObserver<FenBaoShangBean>(this, "获取中") {
                    @Override
                    protected void onHandleSuccess(FenBaoShangBean fenBaoShangBean) {

                        listBean = fenBaoShangBean.getFenbaoshangList();
                        if (listBean != null && listBean.size() > 0) {
                            for (int i = 0; i < listBean.size(); i++) {
                                projectList.add(listBean.get(i).getNickName());
                            }

                            ArrayAdapter adapter = new ArrayAdapter(FenLiaoActivity.this, android.R.layout.select_dialog_item, projectList);
                            mSpChoose.setAdapter(adapter);
                        }
                    }
                });
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
            String s = editTList.get(i).getText().toString();
            LogUtil.e("分料数量：" + s + "\n");
            if (TextUtils.isEmpty(s)) {
                showToastMsg("请输入第" + (i + 1) + "个物料单的分料数量,如果不进行分料请输入0");
                return false;
            }

            if (Integer.valueOf(s) > mList.get(i).getRestCount()) {
                showToastMsg("第" + (i + 1) + "个物料单的分料数量大于剩余数量，请重新输入");
                return false;
            }

            try {
                jsonObject.put("fromWuliaodanItemId", mList.get(i).getId());
                jsonObject.put("fenliaoCount", s);
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
     * 分料
     */
    private void fenliao() {
        if (TextUtils.isEmpty(picPath)) {
            showToastMsg("请进行签名");
            return;
        }
        map.put("token", Config.token);
        map.put("wuliaodanId", id);
        map.put("fenbaoshangUserId", listBean.get(index).getId());
        map.put("signWuziguanli", picPath);
        map.put("items", items);
        showProgressDialog("分料中");
        RetrofitClient.getInstance().createApi().fenliao(map)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        dismissProgressDialog();
                        if (registerBean.getStatus().equals("ok")) {
                            showToastMsg("分料成功");
                            finish();
                        } else {
                            showToastMsg(registerBean.getErrorMessage());
                        }
                    }
                });
    }


    @OnClick({R.id.tv_fenliao_sure, R.id.img_querenfaliao_qianming, R.id.img_querenfaliao_qianzi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_fenliao_sure:
                if (checkInput()) {
                    try {
                        fenliao();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    static class ViewHolder {
        @BindView(R.id.material_name)
        TextView mWuziName;
        @BindView(R.id.send_time)
        TextView mWuziTime;
        @BindView(R.id.vendor_name)
        TextView mGongHuoshang;
        @BindView(R.id.receive_time)
        TextView mReceiveTime;
        @BindView(R.id.mark_name)
        TextView mWuziPinpai;
        @BindView(R.id.size_model)
        TextView mWuziXinghao;
        @BindView(R.id.count_unit)
        TextView mWuziDanwei;
        @BindView(R.id.num_tv)
        TextView mWuziNum;
        @BindView(R.id.check_num)
        TextView mWuziHeshounum;
        @BindView(R.id.sended_num)
        TextView mYiFaSong;
        @BindView(R.id.shengyu_num)
        TextView mShengYu;
        @BindView(R.id.input_num)
        EditText mInput;
        @BindView(R.id.img_fenliao_status)
        ImageView mImgStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
