package com.gaoyuan.management.adapter;

import android.content.Context;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gaoyuan.management.R;
import com.gaoyuan.management.bean.DaiLingLiaoBean;

import java.util.List;

/**
 * Created by gaoyuan on 2017/7/25.
 */

public class DaiLingLiaoAdapter extends BaseQuickAdapter<DaiLingLiaoBean.FenliaodanListBean, BaseViewHolder> {

    public DaiLingLiaoAdapter(int layoutResId, List<DaiLingLiaoBean.FenliaodanListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DaiLingLiaoBean.FenliaodanListBean item) {
        Context context = helper.getConvertView().getContext();
        helper.setText(R.id.tv_dailingliao_name, item.getVoCsProject().getName())
                .setText(R.id.tv_dailingliao_gonguser, "供应商：" + item.getVoGonghuoshangUser().getName())
                .addOnClickListener(R.id.tv_dailingliao_tolingliao);

        ListView listView = helper.getView(R.id.lv_dailingliao_list);
        DaiShouLiaoItemAdapter adapter = new DaiShouLiaoItemAdapter(item.getVoFenliaodanItemList(), context, item.getSendDateStr());
        listView.setAdapter(adapter);

    }
}
