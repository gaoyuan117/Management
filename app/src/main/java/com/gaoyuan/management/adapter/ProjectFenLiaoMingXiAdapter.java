package com.gaoyuan.management.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gaoyuan.management.R;
import com.gaoyuan.management.bean.FenLiaoMingXiBean;
import com.gaoyuan.management.bean.ProjectFenLiaoMingXiBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.view.MyListView;

import java.util.List;

/**
 * Created by gaoyuan on 2017/7/25.
 */

public class ProjectFenLiaoMingXiAdapter extends BaseQuickAdapter<ProjectFenLiaoMingXiBean.FenliaodanListBean, BaseViewHolder> {
    private String projectName,gonghuoshangUser;

    public ProjectFenLiaoMingXiAdapter(int layoutResId, List<ProjectFenLiaoMingXiBean.FenliaodanListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectFenLiaoMingXiBean.FenliaodanListBean item) {
        Context context = helper.getConvertView().getContext();
        ImageView imgStatus = helper.getView(R.id.img_fenliaomingxi_state);
        MyListView listView = helper.getView(R.id.lv_fenliaomingxi_list);
        ProjectFenLiaoMingXiItemAdapter adapter = new ProjectFenLiaoMingXiItemAdapter(item.getVoFenliaodanItemList(), context, item.getSendDateStr(), item.getVoFenbaoshangUser().getName());
        listView.setAdapter(adapter);

        helper.setText(R.id.tv_fenliaomingxi_name, projectName)
                .setText(R.id.tv_fenliaomingxi_gonghuoshang, "供货商：" + gonghuoshangUser)
                .setText(R.id.tv_femliaomingxi_lingliaoren, "领料人：" + item.getVoFenbaoshangUser().getName())
                .addOnClickListener(R.id.img_fenliaomingxi_ling);

        Glide.with(context).load(Config.PIC_URL + item.getSignFenbaoshang()).into((ImageView) helper.getView(R.id.img_fenliaomingxi_ling));

        if (item.getStatus().equals("waitRecive")) {
            imgStatus.setImageResource(R.mipmap.weiling);
        } else {
            imgStatus.setImageResource(R.mipmap.yiling);
        }
    }

    public void setProjectName(String projectName,String gonghuoshangUser) {
        this.projectName = projectName;
        this.gonghuoshangUser = gonghuoshangUser;
    }
}
