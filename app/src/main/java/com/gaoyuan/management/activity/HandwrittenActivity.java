package com.gaoyuan.management.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.view.View;

import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.view.LinePathView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HandwrittenActivity extends BaseActivity {
    //签名图片保存路径
    private String path = Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis() + ".png";
    @BindView(R.id.handwrite)
    LinePathView mHandwrite;


    @Override
    public void setContent() {
        setContentView(R.layout.activity_handwritten);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        setCenterTitle("签名");
    }

    @Override
    public void initData() {
        mHandwrite.setPenColor(getResources().getColor(R.color.blue));
        mHandwrite.setBackColor(Color.WHITE);

    }

    @Override
    public void initEvent() {

    }


    public void click(View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = new Intent();
                    intent.putExtra("path", mHandwrite.save(path));
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();

    }

    public void clear(View view) {
        mHandwrite.clear();
    }
}
