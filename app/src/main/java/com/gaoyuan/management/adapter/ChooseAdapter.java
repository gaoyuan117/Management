package com.gaoyuan.management.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.gaoyuan.management.R;

import java.util.List;

/**
 * Created by gaoyuan on 2017/7/11.
 */

public class ChooseAdapter extends BaseAdapter {
    private Context context;
    private List<String> mList;

    public ChooseAdapter(Context context, List<String> mList) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_text, parent,false);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        title.setText(mList.get(position));
        return convertView;
    }
}
