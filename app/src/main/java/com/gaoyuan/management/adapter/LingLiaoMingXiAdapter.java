package com.gaoyuan.management.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gaoyuan.management.R;
import com.gaoyuan.management.bean.DaiLingLiaoBean;
import com.gaoyuan.management.config.Config;

import java.util.List;

/**
 * Created by gaoyuan on 2017/7/25.
 */

public class LingLiaoMingXiAdapter extends BaseQuickAdapter<DaiLingLiaoBean.FenliaodanListBean, BaseViewHolder> {
    private String lingliaouser;


    public LingLiaoMingXiAdapter(int layoutResId, List<DaiLingLiaoBean.FenliaodanListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DaiLingLiaoBean.FenliaodanListBean item) {
        Context context = helper.getConvertView().getContext();
        ListView listView = helper.getView(R.id.lv_lingliaomingxi_list);
        ImageView imgFa = helper.getView(R.id.img_lingliaomingxi_fa);
        ImageView imgLing = helper.getView(R.id.img_lingliaomingxi_shou);

        helper.setText(R.id.tv_lingliaomingxi_name, item.getVoCsProject().getName())
                .setText(R.id.tv_lingliaomingxi_gonguser, "供料商：" + item.getVoGonghuoshangUser().getName())
                .setText(R.id.tv_lingliaomingxi_faliaoren, "发料人：" + item.getVoGonghuoshangUser().getName())
                .setText(R.id.tv_lingliaomingxi_shouliaoren, "领料人：" + lingliaouser)
                .addOnClickListener(R.id.img_lingliaomingxi_fa)
                .addOnClickListener(R.id.img_lingliaomingxi_shou);

        if (item.getSignFenbaoshang().contains("http")) {
            Glide.with(context).load(item.getSignFenbaoshang()).into(imgLing);
        } else {
            Glide.with(context).load(Config.PIC_URL + item.getSignFenbaoshang()).into(imgLing);
        }

        if (item.getVoWuliaodan().getSignSend().contains("http")) {
            Glide.with(context).load(item.getVoWuliaodan().getSignSend()).into(imgFa);
        } else {
            Glide.with(context).load(Config.PIC_URL + item.getVoWuliaodan().getSignSend()).into(imgFa);
        }

        LingLiaoMingXiItemAdapter adapter = new LingLiaoMingXiItemAdapter(item.getVoFenliaodanItemList(), context, item.getReciveDateStr());
        listView.setAdapter(adapter);


    }

    public void setLingliaouser(String user) {
        this.lingliaouser = user;
    }
}
