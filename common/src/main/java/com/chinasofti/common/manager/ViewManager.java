package com.chinasofti.common.manager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.chinasofti.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>管理组件的fragment和activity</p>
 */
public class ViewManager {

    private static Stack<Activity> sActivityStack;
    private static List<BaseFragment> sFragmentList;

    public static ViewManager getInstance() {
        return ViewManagerHolder.sInstance;
    }

    private static class ViewManagerHolder {
        private static final ViewManager sInstance = new ViewManager();
    }

    private ViewManager() {
    }

    public void addFragment(int index, BaseFragment fragment) {
        if (sFragmentList == null) {
            sFragmentList = new ArrayList<>();
        }
        sFragmentList.add(index, fragment);
    }


    public BaseFragment getFragment(int index) {
        if (sFragmentList != null) {
            return sFragmentList.get(index);
        }
        return null;
    }


    public List<BaseFragment> getAllFragment() {
        if (sFragmentList != null) {
            return sFragmentList;
        }
        return null;
    }


    /**
     * 添加指定Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (sActivityStack == null) {
            sActivityStack = new Stack<Activity>();
        }
        sActivityStack.add(activity);
    }


    /**
     * 获取当前Activity
     */
    public Activity currentActivity() {
        Activity activity = sActivityStack.lastElement();
        return activity;
    }


    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        Activity activity = sActivityStack.lastElement();
        finishActivity(activity);
    }


    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            sActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }


    /**
     * 结束指定Class的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : sActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }


    /**
     * 结束全部的Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = sActivityStack.size(); i < size; i++) {
            if (null != sActivityStack.get(i)) {
                sActivityStack.get(i).finish();
            }
        }
        sActivityStack.clear();
    }


    /**
     * 退出应用程序
     */
    public void exitApp(Context context) {
        try {
            finishAllActivity();
            //杀死后台进程需要在AndroidManifest中声明android.permission.KILL_BACKGROUND_PROCESSES；
            android.app.ActivityManager activityManager = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());
            //System.exit(0);
        } catch (Exception e) {
            Log.e("ActivityManager", "app exit" + e.getMessage());
        }
    }
}
