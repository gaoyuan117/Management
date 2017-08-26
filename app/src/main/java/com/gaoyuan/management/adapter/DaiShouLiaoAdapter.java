package com.gaoyuan.management.adapter;

import android.content.Context;
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
 * Created by gaoyuan on 2017/7/21.
 */

public class DaiShouLiaoAdapter extends BaseQuickAdapter<FaLiaoMingXiBean.WuliaodanListBean, BaseViewHolder> {

    public DaiShouLiaoAdapter(int layoutResId, List<FaLiaoMingXiBean.WuliaodanListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FaLiaoMingXiBean.WuliaodanListBean item) {
        Context context = helper.getConvertView().getContext();
        ListView listView = helper.getView(R.id.lv_daishouliao_list);
        ImageView signImg = helper.getView(R.id.img_daishouliao_fa);

        helper.setText(R.id.tv_daishouliao_name, item.getVoCsProject().getName())
                .setText(R.id.tv_daishouliao_faliaono, "发料编号：" + item.getSendMarkedNum())
                .setText(R.id.tv_daishouliao_company, "发料单位：" + item.getVoGonghuoshangUser().getCompany())
                .setText(R.id.tv_daishouliao_faliaoren, "发料人：" + item.getVoGonghuoshangUser().getName())
                .addOnClickListener(R.id.img_daishouliao_fa)
                .addOnClickListener(R.id.tv_daishouliao_toshouliao);

        FaLiaoMingXiItemAdapter adapter = new FaLiaoMingXiItemAdapter(item.getVoWuliaodanItemList(), context, item.getCreateDateStr());
        listView.setAdapter(adapter);

        if (item.getSignSend().contains("http")) {
            Glide.with(context).load(item.getSignSend()).into(signImg);
        } else {
            Glide.with(context).load(Config.PIC_URL + item.getSignSend()).into(signImg);
        }


    }
}
