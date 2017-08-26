package com.gaoyuan.management.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gaoyuan.management.R;

import java.util.List;

/**
 * Created by gaoyuan on 2017/8/9.
 */

public class ImgAdapter extends BaseAdapter {
    private Context context;
    private List<String> mList;

    public ImgAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_img,null);
        ImageView img = (ImageView) convertView.findViewById(R.id.img_gv_item);
        Glide.with(context).load(mList.get(position)).into(img);
        return convertView;
    }
}

