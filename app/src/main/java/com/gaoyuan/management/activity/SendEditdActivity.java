package com.gaoyuan.management.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.EditBean;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.BaseObjObserver;
import com.gaoyuan.management.rxjava.RxUtils;
import com.gaoyuan.management.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class SendEditdActivity extends BaseActivity {

    @BindView(R.id.ll_edit)
    LinearLayout mLayout;

    private List<EditText> nameList;
    private List<EditText> xingHaoList;
    private List<EditText> danWeiList;
    private List<EditText> numList;
    private List<EditText> pinPaiList;
    private EditBean mEditBean;


    @Override
    public void setContent() {
        setContentView(R.layout.activity_send_editd);
        ButterKnife.bind(this);
        setCenterTitle("编辑");
    }

    @Override
    public void init() {
        nameList = new ArrayList<>();
        xingHaoList = new ArrayList<>();
        danWeiList = new ArrayList<>();
        numList = new ArrayList<>();
        pinPaiList = new ArrayList<>();
    }

    @Override
    public void initData() {
        String id = getIntent().getStringExtra("id");
        toEdit(id);
    }

    @Override
    public void initEvent() {

    }

    @OnClick(R.id.bt_edit_sure)
    public void onViewClicked() {
        if(checkInput()){
            edit();
        }
    }

    /**
     * 添加发料信息
     */
    private void addFaLiao(EditBean.WuliaodanBean wuliaodanBean) {

        List<EditBean.WuliaodanBean.VoWuliaodanItemListBean> itemList = wuliaodanBean.getVoWuliaodanItemList();

        for (int i = 0; i < itemList.size(); i++) {

            EditBean.WuliaodanBean.VoWuliaodanItemListBean bean = itemList.get(i);
            View view = View.inflate(this, R.layout.layout_fawuliao, null);
            EditText etName = (EditText) view.findViewById(R.id.et_faliao_info_name);
            EditText etXingHao = (EditText) view.findViewById(R.id.et_faliao_info_xinghao);
            EditText etDanWei = (EditText) view.findViewById(R.id.et_faliao_info_danwei);
            EditText etNum = (EditText) view.findViewById(R.id.et_faliao_info_num);
            EditText etPinPai = (EditText) view.findViewById(R.id.et_faliao_info_pinpai);

            mLayout.addView(view);

            nameList.add(etName);
            xingHaoList.add(etXingHao);
            danWeiList.add(etDanWei);
            numList.add(etNum);
            pinPaiList.add(etPinPai);

            etName.setText(bean.getName());
            etXingHao.setText(bean.getSpecifications());
            etDanWei.setText(bean.getUnitName());
            etNum.setText(bean.getSendCount()+"");
            etPinPai.setText(bean.getBrand());
        }
    }

    /**
     * 获取修改数据
     *
     * @param id
     */
    private void toEdit(String id) {
        RetrofitClient.getInstance().createApi().toMod(Config.token, id)
                .compose(RxUtils.<HttpResult<EditBean>>io_main())
                .subscribe(new BaseObjObserver<EditBean>(this, "加载中") {

                    @Override
                    protected void onHandleSuccess(EditBean editBean) {
                        mEditBean = editBean;
                        addFaLiao(editBean.getWuliaodan());
                    }
                });
    }

    /**
     * 修改
     */
    private void edit() {
        map.put("token",Config.token);
        map.put("wuliaodanId",mEditBean.getWuliaodan().getId());
        map.put("projectId",mEditBean.getWuliaodan().getCsProjectId());
        map.put("sendDateStr",mEditBean.getWuliaodan().getSendDateStr());
        map.put("items",toJSONArray());
        RetrofitClient.getInstance().createApi().edit(map)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean.getStatus().equals("ok")) {
                            showToastMsg("修改成功");
                            finish();
                        } else {
                            showToastMsg(registerBean.getErrorMessage());
                        }
                    }
                });
    }


    /**
     * 检查表单内容
     */
    private boolean checkInput() {

        LogUtil.e("发料表单数量：" + nameList.size());

        for (int i = 0; i < nameList.size(); i++) {
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

        for (int i = 0; i < nameList.size(); i++) {
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


}
