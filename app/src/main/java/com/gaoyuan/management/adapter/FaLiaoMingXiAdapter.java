package com.gaoyuan.management.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gaoyuan.management.R;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoyuan on 2017/7/16.
 */

public class FaLiaoMingXiAdapter extends BaseQuickAdapter<FaLiaoMingXiBean.WuliaodanListBean, BaseViewHolder> {

    public FaLiaoMingXiAdapter(int layoutResId, List<FaLiaoMingXiBean.WuliaodanListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FaLiaoMingXiBean.WuliaodanListBean item) {
        Context context = helper.getConvertView().getContext();
        ListView listView = helper.getView(R.id.lv_faliaomingxi_list);

        FaLiaoMingXiItemAdapter adapter = new FaLiaoMingXiItemAdapter(item.getVoWuliaodanItemList(), context, item.getCreateDateStr());
        listView.setAdapter(adapter);
        String shouLiaoRen = "";
        if (item.getVoWuziguanliUser() == null) {
            shouLiaoRen = "";
        } else {
            shouLiaoRen = item.getVoWuziguanliUser().getName();
        }

        String signSend = item.getSignSend();//发料人签名
        String receiveSign = item.getSignRecive();//收料人签名

        if (signSend.contains("http")) {
            Glide.with(context).load(signSend)
                    .into((ImageView) helper.getView(R.id.img_faliaomingxi_fa));
        } else {
            Glide.with(context).load(Config.PIC_URL + signSend)
                    .into((ImageView) helper.getView(R.id.img_faliaomingxi_fa));
        }

        if (receiveSign.contains("http")) {
            Glide.with(context).load(receiveSign)
                    .into((ImageView) helper.getView(R.id.img_faliaomingxi_shou));
        } else {
            Glide.with(context).load(Config.PIC_URL + receiveSign)
                    .into((ImageView) helper.getView(R.id.img_faliaomingxi_shou));
        }

        helper.setText(R.id.tv_faliaomingxi_faliaono, "发料编号：" + item.getSendMarkedNum())
                .setText(R.id.tv_faliaomingxi_shouliaono, "收料编号：" + item.getReciveMarkedNum())
                .setText(R.id.tv_faliaomingxi_name, item.getVoCsProject().getName())
                .setText(R.id.tv_faliaomingxi_shouliaoren, "收料人：" + shouLiaoRen)
                .setText(R.id.tv_faliaomingxi_faliaoren, "发料人：" + item.getVoGonghuoshangUser().getName())
                .addOnClickListener(R.id.img_faliaomingxi_fa)
                .addOnClickListener(R.id.img_faliaomingxi_shou);

        ImageView stateImg = helper.getView(R.id.img_faliaomingxi_state);
        if (item.getStatus().equals("sendedWaitRecive")) {
            stateImg.setImageResource(R.mipmap.weishouliao);
        } else {
            stateImg.setImageResource(R.mipmap.yishouliao);
        }
    }

}