package com.gaoyuan.management.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaoyuan.management.R;
import com.gaoyuan.management.bean.FenLiaoMingXiBean;
import com.gaoyuan.management.bean.ProjectFenLiaoMingXiBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaoyuan on 2017/7/16.
 */

public class ProjectFenLiaoMingXiItemAdapter extends BaseAdapter {
    private List<ProjectFenLiaoMingXiBean.FenliaodanListBean.VoFenliaodanItemListBean> mList;
    private Context context;
    private String time, fenUser;

    public ProjectFenLiaoMingXiItemAdapter(List<ProjectFenLiaoMingXiBean.FenliaodanListBean.VoFenliaodanItemListBean> mList, Context context, String time, String fenUser) {
        this.mList = mList;
        this.context = context;
        this.time = time;
        this.fenUser = fenUser;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_fenliaomingxi_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ProjectFenLiaoMingXiBean.FenliaodanListBean.VoFenliaodanItemListBean bean = mList.get(position);

        viewHolder.mWuziName.setText(bean.getName());
        viewHolder.mWuziTime.setText("分料时间：" + time);
        viewHolder.mWuziDanwei.setText("计算单位：" + bean.getUnitName());
        viewHolder.mWuziXinghao.setText("型号规格：" + bean.getSpecifications());
        viewHolder.mWuziFenuser.setText("分包商：" + fenUser);
        viewHolder.mWuziFennum.setText("分料数量：" + bean.getFenliaoCount());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_fenliaomingxi_wuzi_name)
        TextView mWuziName;
        @BindView(R.id.tv_fenliaomingxi_wuzi_time)
        TextView mWuziTime;
        @BindView(R.id.tv_faliaomingxi_wuzi_xinghao)
        TextView mWuziXinghao;
        @BindView(R.id.tv_faliaomingxi_wuzi_danwei)
        TextView mWuziDanwei;
        @BindView(R.id.tv_fenliaomingxi_wuzi_fenuser)
        TextView mWuziFenuser;
        @BindView(R.id.tv_fenliaomingxi_wuzi_fennum)
        TextView mWuziFennum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
