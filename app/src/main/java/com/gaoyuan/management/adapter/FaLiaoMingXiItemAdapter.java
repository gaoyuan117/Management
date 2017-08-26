package com.gaoyuan.management.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaoyuan.management.R;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaoyuan on 2017/7/16.
 */

public class FaLiaoMingXiItemAdapter extends BaseAdapter {
    private List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean> mList;
    private Context context;
    private String time;

    public FaLiaoMingXiItemAdapter(List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean> mList, Context context, String time) {
        this.mList = mList;
        this.context = context;
        this.time = time;
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
            convertView = View.inflate(context, R.layout.item_faliaomingxi_wuliao, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean bean = mList.get(position);

        viewHolder.mWuziName.setText(bean.getName());
        viewHolder.mWuziTime.setText("发料时间：" + time);
        viewHolder.mWuziDanwei.setText("计算单位：" + bean.getUnitName());
        viewHolder.mWuziPinpai.setText("品牌：" + bean.getBrand());
        viewHolder.mWuziXinghao.setText("型号规格：" + bean.getSpecifications());
        viewHolder.mWuziNum.setText("数量：" + bean.getSendCount());
        viewHolder.mWuziHeshounum.setText("核收数量：" + bean.getReciveCount());


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_faliaomingxi_wuzi_name)
        TextView mWuziName;
        @BindView(R.id.tv_faliaomingxi_wuzi_time)
        TextView mWuziTime;
        @BindView(R.id.tv_faliaomingxi_wuzi_pinpai)
        TextView mWuziPinpai;
        @BindView(R.id.tv_faliaomingxi_wuzi_xinghao)
        TextView mWuziXinghao;
        @BindView(R.id.tv_faliaomingxi_wuzi_danwei)
        TextView mWuziDanwei;
        @BindView(R.id.tv_faliaomingxi_wuzi_num)
        TextView mWuziNum;
        @BindView(R.id.tv_faliaomingxi_wuzi_heshounum)
        TextView mWuziHeshounum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
