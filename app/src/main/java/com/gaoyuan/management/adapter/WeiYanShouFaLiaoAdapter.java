package com.gaoyuan.management.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gaoyuan.management.R;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.util.SharedPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoyuan on 2017/7/16.
 */

public class WeiYanShouFaLiaoAdapter extends BaseQuickAdapter<FaLiaoMingXiBean.WuliaodanListBean, BaseViewHolder> {

    public WeiYanShouFaLiaoAdapter(int layoutResId, List<FaLiaoMingXiBean.WuliaodanListBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, FaLiaoMingXiBean.WuliaodanListBean item) {
        Context context = helper.getConvertView().getContext();


        final ListView listView = helper.getView(R.id.lv_weiyanshoufaliao_list);
        LinearLayout moreLayout = helper.getView(R.id.ll_weiyanshoufaliao_more);
        final ImageView moreImg = helper.getView(R.id.img_weiyanshoufaliao_more);
        ImageView imgSign = helper.getView(R.id.img_weiyanshoufaliao_fa);

        if (item.getSignSend().contains("http")) {
            Glide.with(context).load(item.getSignSend()).into(imgSign);
        } else {
            Glide.with(context).load(Config.PIC_URL + item.getSignSend()).into(imgSign);
        }


        FaLiaoMingXiItemAdapter adapter = new FaLiaoMingXiItemAdapter(item.getVoWuliaodanItemList(), context, item.getCreateDateStr());
        listView.setAdapter(adapter);

        helper.setText(R.id.tv_weiyanshoufaliao_no, "编号：" + item.getSendMarkedNum())
                .setText(R.id.tv_weiyanshoufaliao_faliaoren, "发料人：" + item.getVoGonghuoshangUser().getName())
                .addOnClickListener(R.id.img_weiyanshoufaliao_edit)
                .addOnClickListener(R.id.img_weiyanshoufaliao_delete);


        moreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.getVisibility() == View.GONE) {
                    listView.setVisibility(View.VISIBLE);
                    moreImg.setImageResource(R.mipmap.arrow_right);
                } else {
                    listView.setVisibility(View.GONE);
                    moreImg.setImageResource(R.mipmap.more);
                }
            }
        });


    }
}
