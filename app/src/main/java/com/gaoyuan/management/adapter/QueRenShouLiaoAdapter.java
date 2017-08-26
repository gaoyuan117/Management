package com.gaoyuan.management.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyuan.management.R;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;
import com.gaoyuan.management.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaoyuan on 2017/7/21.
 */

public class QueRenShouLiaoAdapter extends BaseAdapter {

    private Context context;
    private List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean> mList;
    private String time;

    public List<EditText> mEtList;
    public List<String> statusList;

    public QueRenShouLiaoAdapter(List<FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean> mList, Context context, String time) {
        this.mList = mList;
        this.context = context;
        this.time = time;

        mEtList = new ArrayList<>();
        statusList = new ArrayList<>();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
//        if (convertView == null) {
//            convertView = View.inflate(context, R.layout.item_querenshouliao, null);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }


        convertView = View.inflate(context, R.layout.item_querenshouliao, null);
        viewHolder = new ViewHolder(convertView);
        EditText editText = (EditText) convertView.findViewById(R.id.et_querenfaliao_heshou);

        FaLiaoMingXiBean.WuliaodanListBean.VoWuliaodanItemListBean bean = mList.get(position);

        viewHolder.mWuziName.setText(bean.getName());
        viewHolder.mWuziTime.setText("发料时间：" + time);
        viewHolder.mWuziDanwei.setText("计算单位：" + bean.getUnitName());
        viewHolder.mWuziPinpai.setText("品牌：" + bean.getBrand());
        viewHolder.mWuziXinghao.setText("型号规格：" + bean.getSpecifications());
        viewHolder.mWuziNum.setText("数量：" + bean.getSendCount());

        mEtList.add(editText);
        statusList.add("");

        viewHolder.mQueRenFaLiao.setOnClickListener(new View.OnClickListener() {//确认发料
            @Override
            public void onClick(View v) {
                viewHolder.mJuJueFaLiao.setVisibility(View.GONE);
                for (int i = 0; i < statusList.size(); i++) {
                    LogUtil.e("之前：" + statusList.get(i));

                }
                statusList.remove(position);
                statusList.add(position, "confirm");
                for (int i = 0; i < statusList.size(); i++) {
                    LogUtil.e("之后：" + statusList.get(i));
                }

            }
        });

        viewHolder.mJuJueFaLiao.setOnClickListener(new View.OnClickListener() {//拒绝发料
            @Override
            public void onClick(View v) {
                viewHolder.mQueRenFaLiao.setVisibility(View.GONE);
                statusList.add(position, "retreated");
            }
        });

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
//        @BindView(R.id.et_querenfaliao_heshou)
//        EditText mWuziHeshounum;
        @BindView(R.id.img_querenshouliao_queren)
        ImageView mQueRenFaLiao;
        @BindView(R.id.img_querenshouliao_jujue)
        ImageView mJuJueFaLiao;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
