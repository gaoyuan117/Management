package com.gaoyuan.management.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gaoyuan.management.R;
import com.gaoyuan.management.adapter.ChooseAdapter;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.PicBean;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.bean.ToSendBean;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WoYaoFaLiaoActivity extends BaseActivity {

    @BindView(R.id.tv_woyaofaliao_no)
    TextView mTvNo;
    @BindView(R.id.ll_woyaofaliao_list)
    LinearLayout mLayoutList;
    @BindView(R.id.tv_woyaofaliao_add)
    TextView mTvAdd;
    @BindView(R.id.tv_woyaofaliao_time)
    TextView mTvTime;
    @BindView(R.id.img_woyaofaliao_edit)
    ImageView mImgEdit;
    @BindView(R.id.img_woyaofaliao_qianming)
    ImageView mImgQianming;
    @BindView(R.id.img_woyaofaliao_qianzi)
    ImageView mImgQianzi;
    @BindView(R.id.tv_woyaofaliao_certain)
    TextView mTvCertain;
    @BindView(R.id.sp_woyaofaliao_project)
    Spinner mSpProject;

    private ToSendBean mToSendBean;

    private List<EditText> nameList;
    private List<EditText> xingHaoList;
    private List<EditText> danWeiList;
    private List<EditText> numList;
    private List<EditText> pinPaiList;
    private List<String> projectList;

    private ChooseAdapter projectAdapter;
    private int projectIndex;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private int lableNum;//发料表单的数量
    private String signPath;//本地的图片地址
    private String picPath;//上传图片返回的图片地址


    @Override
    public void setContent() {
        setContentView(R.layout.activity_wo_yao_fa_liao);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        setCenterTitle("我要发料");

        nameList = new ArrayList<>();
        xingHaoList = new ArrayList<>();
        danWeiList = new ArrayList<>();
        numList = new ArrayList<>();
        pinPaiList = new ArrayList<>();
        projectList = new ArrayList<>();
        projectAdapter = new ChooseAdapter(this, projectList);
        mSpProject.setAdapter(projectAdapter);

        String format = this.format.format(new Date().getTime());
        mTvTime.setText(format);//默认发料时间是当前时间

    }

    @Override
    public void initData() {
        toSend();
    }

    @Override
    public void initEvent() {
        mSpProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


    @OnClick({R.id.ll_woyaofaliao, R.id.tv_woyaofaliao_add, R.id.img_woyaofaliao_edit, R.id.img_woyaofaliao_qianming, R.id.img_woyaofaliao_qianzi, R.id.tv_woyaofaliao_certain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_woyaofaliao_add://增加发料表单
                addFaLiao();
                break;
            case R.id.img_woyaofaliao_edit://编辑发料日期
                dateDialog();
                break;
            case R.id.img_woyaofaliao_qianming://签名
                if (!TextUtils.isEmpty(signPath)) {
                    List<String> list = new ArrayList<>();
                    list.add(signPath);
                    GlideUtil.imageBrower(this, 0, list, "local");
                }
                break;
            case R.id.img_woyaofaliao_qianzi:
                Intent intent = new Intent(this, HandwrittenActivity.class);
                startActivityForResult(intent, 110);
                break;
            case R.id.tv_woyaofaliao_certain://确认发料
                if (checkInput()) {
                    send();
                }
                break;
            case R.id.ll_woyaofaliao://关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                break;

        }
    }

    /**
     * 添加发料信息
     */
    private void addFaLiao() {
        final View view = View.inflate(this, R.layout.layout_fawuliao, null);
        EditText etName = (EditText) view.findViewById(R.id.et_faliao_info_name);
        EditText etXingHao = (EditText) view.findViewById(R.id.et_faliao_info_xinghao);
        EditText etDanWei = (EditText) view.findViewById(R.id.et_faliao_info_danwei);
        EditText etNum = (EditText) view.findViewById(R.id.et_faliao_info_num);
        EditText etPinPai = (EditText) view.findViewById(R.id.et_faliao_info_pinpai);
        ImageView imgDelete = (ImageView) view.findViewById(R.id.img_woyaofaliao_delete);

        mLayoutList.addView(view);
        nameList.add(etName);
        xingHaoList.add(etXingHao);
        danWeiList.add(etDanWei);
        numList.add(etNum);
        pinPaiList.add(etPinPai);

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog dialog = new AlertDialog.Builder(WoYaoFaLiaoActivity.this)
                        .setMessage("是否删除该物料单")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                view.setVisibility(View.GONE);
                                EditText etName = (EditText) view.findViewById(R.id.et_faliao_info_name);
                                EditText etXingHao = (EditText) view.findViewById(R.id.et_faliao_info_xinghao);
                                EditText etDanWei = (EditText) view.findViewById(R.id.et_faliao_info_danwei);
                                EditText etNum = (EditText) view.findViewById(R.id.et_faliao_info_num);
                                EditText etPinPai = (EditText) view.findViewById(R.id.et_faliao_info_pinpai);

                                nameList.remove(etName);
                                xingHaoList.remove(etXingHao);
                                danWeiList.remove(etDanWei);
                                numList.remove(etNum);
                                pinPaiList.remove(etPinPai);
                            }
                        }).create();
                dialog.show();
            }
        });

        //长按隐藏该物料信息
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });

    }

    /**
     * 发料之前的准备
     */
    private void toSend() {
        RetrofitClient.getInstance().createApi().toSend(Config.token)
                .compose(RxUtils.<HttpResult<ToSendBean>>io_main())
                .subscribe(new BaseObjObserver<ToSendBean>(this, "加载中..") {

                    @Override
                    protected void onHandleSuccess(ToSendBean toSendBean) {
                        mToSendBean = toSendBean;
                        mTvNo.setText(toSendBean.getSendMarkedNum());
                        for (int i = 0; i < toSendBean.getProjectList().size(); i++) {
                            projectList.add(toSendBean.getProjectList().get(i).getName());
                        }
                        projectAdapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 检查表单内容
     */
    private boolean checkInput() {
        lableNum = 0;
        //根据可见状态判断表单数量
        for (int i = 0; i < mLayoutList.getChildCount(); i++) {
            if (mLayoutList.getChildAt(i).getVisibility() == View.VISIBLE) {
                lableNum += 1;
            }
        }
        LogUtil.e("发料表单数量：" + lableNum);

        for (int i = 0; i < lableNum; i++) {
            String s = nameList.get(i).getText().toString();
            String s1 = pinPaiList.get(i).getText().toString();
            String s2 = xingHaoList.get(i).getText().toString();
            String s3 = numList.get(i).getText().toString();
            String s4 = danWeiList.get(i).getText().toString();

            if (TextUtils.isEmpty(s) || TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2) || TextUtils.isEmpty(s3) || TextUtils.isEmpty(s4)) {
                showToastMsg("请检查第" + (i + 1) + "个表单的输入信息是否完整");
                return false;
            }
        }

        return true;
    }

    /**
     * 将表单信息转化成JSONArray
     */
    private String toJSONArray() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;

        for (int i = 0; i < lableNum; i++) {
            try {
                jsonObject = new JSONObject();
                jsonObject.put("name", nameList.get(i).getText().toString());
                jsonObject.put("brand", pinPaiList.get(i).getText().toString());
                jsonObject.put("sendCount", numList.get(i).getText().toString());
                jsonObject.put("specifications", xingHaoList.get(i).getText().toString());
                jsonObject.put("unitName", danWeiList.get(i).getText().toString());
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        LogUtil.e("JsonArray：" + jsonArray.toString());
        return jsonArray.toString();
    }

    /**
     * 发料
     */
    private void send() {
        if (lableNum == 0) {
            showToastMsg("请添加物料单");
            return;
        }
        if (TextUtils.isEmpty(picPath)) {
            showToastMsg("请签名");
            return;
        }
        map.put("token", Config.token);
        map.put("projectId", mToSendBean.getProjectList().get(projectIndex).getId());
        map.put("sendMarkedNum", mToSendBean.getSendMarkedNum());
        map.put("signSend", picPath);
        map.put("sendDateStr", mTvTime.getText().toString());

        map.put("items", toJSONArray());

        showProgressDialog("发料中");
        RetrofitClient.getInstance().createApi().send(map)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        dismissProgressDialog();
                        if (registerBean.getStatus().equals("ok")) {
                            showToastMsg("发料成功");
                            finish();
                        } else {
                            showToastMsg(registerBean.getErrorMessage());
                        }
                    }
                });
    }

    /**
     * 日期对话框
     */
    private void dateDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                mTvTime.setText(date);
                LogUtil.e("您选择了：" + date);
            }
        }, calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar
                .get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setCancelable(true);
        datePickerDialog.show();
    }


    /**
     * 上传图片
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
                Glide.with(this).load(signPath).into(mImgQianming);
                uploadPic(signPath);
            }
        }
    }


}
