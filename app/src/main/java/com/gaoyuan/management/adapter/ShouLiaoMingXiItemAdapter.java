package com.gaoyuan.management.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyuan.management.R;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaoyuan on 2017/7/16.
 */

public class ShouLiaoMingXiItemAdapter extends BaseAdapter {
    private List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean> mList;
    private Context context;
    private String time, recTime, gongHuoShang;
    private boolean status;//是否拒收

    public ShouLiaoMingXiItemAdapter(List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean> mList
            , boolean status, String gonghuoShang, Context context, String time, String recTime) {
        this.mList = mList;
        this.context = context;
        this.time = time;
        this.recTime = recTime;
        this.gongHuoShang = gonghuoShang;
        this.status = status;
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
            convertView = View.inflate(context, R.layout.item_shouliaomingxi, null);
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
        viewHolder.mGongHuoshang.setText("供货商：" + gongHuoShang);
        viewHolder.mReceiveTime.setText("收料时间：" + recTime);
        viewHolder.mYiFaSong.setText("已发数量：" + bean.getFenliaoCount());
        viewHolder.mShengYu.setText("剩余数量：" + bean.getRestCount());

        if (status) {
            String fenliaoStatus = bean.getFenliaoStatus();
            if (fenliaoStatus.equals("waitFen") || fenliaoStatus.equals("fening")) {
                viewHolder.mImgStatus.setImageResource(R.mipmap.weifenwan);
            } else if (fenliaoStatus.equals("fened")) {
                viewHolder.mImgStatus.setImageResource(R.mipmap.yifenwan);
            }
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.material_name)
        TextView mWuziName;
        @BindView(R.id.send_time)
        TextView mWuziTime;
        @BindView(R.id.vendor_name)
        TextView mGongHuoshang;
        @BindView(R.id.receive_time)
        TextView mReceiveTime;
        @BindView(R.id.mark_name)
        TextView mWuziPinpai;
        @BindView(R.id.size_model)
        TextView mWuziXinghao;
        @BindView(R.id.count_unit)
        TextView mWuziDanwei;
        @BindView(R.id.num_tv)
        TextView mWuziNum;
        @BindView(R.id.check_num)
        TextView mWuziHeshounum;
        @BindView(R.id.sended_num)
        TextView mYiFaSong;
        @BindView(R.id.shengyu_num)
        TextView mShengYu;
        @BindView(R.id.img_shouliaomimmgxi_status)
        ImageView mImgStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
