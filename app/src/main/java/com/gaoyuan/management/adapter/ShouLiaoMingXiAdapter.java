package com.gaoyuan.management.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gaoyuan.management.R;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;
import com.gaoyuan.management.config.Config;

import java.util.List;

/**
 * Created by gaoyuan on 2017/7/24.
 */

public class ShouLiaoMingXiAdapter extends BaseQuickAdapter<FaLiaoMingXiBean.WuliaodanListBean, BaseViewHolder> {

    public ShouLiaoMingXiAdapter(int layoutResId, List<FaLiaoMingXiBean.WuliaodanListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FaLiaoMingXiBean.WuliaodanListBean item) {
        Context context = helper.getConvertView().getContext();
        ListView listView = helper.getView(R.id.lv_faliaomingxi_list);
        ImageView imgStatus = helper.getView(R.id.img_shouliao_fenliao);

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
                .addOnClickListener(R.id.img_faliaomingxi_shou)
                .addOnClickListener(R.id.img_shouliao_chakan)
                .addOnClickListener(R.id.img_shouliao_fenliao);

        ImageView fenliao = helper.getView(R.id.img_shouliao_fenliao);
        ImageView chakan = helper.getView(R.id.img_shouliao_chakan);

        boolean status = false;
        ImageView stateImg = helper.getView(R.id.img_faliaomingxi_state);
        if (item.getStatus().equals("recived")) {
            stateImg.setImageResource(R.mipmap.yishouliao);
            status = true;
        } else if (item.getStatus().equals("retreated")) {
            stateImg.setImageResource(R.mipmap.yituiliao);
            status = false;
            fenliao.setVisibility(View.GONE);
            chakan.setVisibility(View.GONE);
        }

        if (item.getFenliaoStatus().equals("fened")) {
            imgStatus.setVisibility(View.INVISIBLE);
        } else {
            imgStatus.setVisibility(View.VISIBLE);
        }


        ShouLiaoMingXiItemAdapter adapter = new ShouLiaoMingXiItemAdapter(item.getVoWuliaodanItemList(), status, item.getVoGonghuoshangUser().getName(), context, item.getCreateDateStr(), item.getReciveDateStr());
        listView.setAdapter(adapter);
    }
}
