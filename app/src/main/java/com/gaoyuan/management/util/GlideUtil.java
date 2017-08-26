package com.gaoyuan.management.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gaoyuan.management.activity.ImagePagerActivity;

import java.util.List;

/**
 * Created by gaoyuan on 2017/7/11.
 */

public class GlideUtil {

    public static void glideLocalImg(Activity activity, ImageView imageView,String url){
        Glide.with(activity)
                .load(url)
                .into(imageView);
    }

    /**
     * 这里跳转到图片的预览界面
     *
     * @param position
     * @param urls
     */
    public static void imageBrower(Context context, int position, List<String> urls,String type) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        int size = urls.size();
        //携带点击位置，和路径的集合进行跳转
        String[] arr = urls.toArray(new String[size]);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, arr);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }
}
