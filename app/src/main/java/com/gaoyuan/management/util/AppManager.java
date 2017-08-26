package com.gaoyuan.management.util;

import android.app.Activity;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * Created by ljl on 2016/12/11.
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    //private static AppManager instance = null; // 懒汉式 有缺陷
    private static AppManager instance = new AppManager();// 饿汉式 安全 简单

    private static Stack<Activity> activityList;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        // (懒汉式)
        //if (instance == null) {
        //    instance = new AppManager();
        //}

        // 锁 多线程 (面试 考 懒汉式)
        //if (instance == null) {
        //    synchronized(AppManager.class){
        //		if(instance == null){
        //			instance = new AppManager();
        //		}
        //	}
        //}
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 添加Activity到堆栈
     */
    public void AaddActivityList(Activity activity) {
        if (activityList == null) {
            activityList = new Stack<>();
        }
        activityList.add(activity);
    }


    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        if (activityStack != null && activityStack.size() > 0) {
            Activity activity = activityStack.lastElement();
            finishActivity(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (activityStack != null && activityStack.size() > 0) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack != null && activityStack.size() > 0) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

    /**
     * 结束所有Activity
     */
    public void AfinishListActivity() {
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0, size = activityList.size(); i < size; i++) {
                if (null != activityList.get(i)) {
                    activityList.get(i).finish();
                }
            }
            activityList.clear();
        }
    }
}