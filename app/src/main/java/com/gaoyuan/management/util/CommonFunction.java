package com.gaoyuan.management.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import com.gaoyuan.management.config.Config;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;

import static io.reactivex.schedulers.Schedulers.single;

/**
 * Created by gaoyuan on 2017/7/11.
 */

public class CommonFunction {

    /**
     * 选择图片
     *
     * @param activity
     * @param showCamera
     * @param count
     * @param list
     */
    public static void selectPic(Activity activity, boolean showCamera, int count, ArrayList<String> list) {
        MultiImageSelector.create()
                .showCamera(showCamera)
                .count(count)
                .multi()
                .origin(list)
                .start(activity, Config.SELECT_IMAGE);
    }
}
