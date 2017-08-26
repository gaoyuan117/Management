package com.gaoyuan.management.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.gaoyuan.management.R;
import com.gaoyuan.management.config.Config;

/**
 * 图片查看浏览界面，继承FragmentActivity
 */
public class ImagePagerActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";
    private ViewPager mPager;
    private TextView indexText;
    private String[] urls;
    private int index;
    private String type;//加载图片的类型  网络/本地

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager);
        urls = getIntent().getStringArrayExtra(EXTRA_IMAGE_URLS);
        index = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        type = getIntent().getStringExtra("type");
        mPager = (ViewPager) findViewById(R.id.image_detail_pager_viewpager);
        indexText = (TextView) findViewById(R.id.image_detail_pager_text);
        indexText.setText((index + 1) + "/" + urls.length);

        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        mPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return urls.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(ImagePagerActivity.this);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                view.setOnClickListener(ImagePagerActivity.this);

                //判断加载的是网络还是本地
                if (!TextUtils.isEmpty(type) && type.equals("net")) {
                    if (urls[position].contains("http")) {
                        Glide.with(ImagePagerActivity.this).load(urls[position]).into(view);

                    } else {
                        Glide.with(ImagePagerActivity.this).load(Config.PIC_URL + urls[position]).into(view);

                    }
                } else {
                    Glide.with(ImagePagerActivity.this).load(urls[position]).into(view);

                }
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        mPager.setCurrentItem(index);
        mPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        indexText.setText((position + 1) + "/" + urls.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}