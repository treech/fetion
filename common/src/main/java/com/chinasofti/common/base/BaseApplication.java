package com.chinasofti.common.base;

import android.app.Application;
import android.content.Context;

import com.chinasofti.common.utils.CommonUtils;
import com.chinasofti.common.utils.log.YLog;

import java.util.ArrayList;
import java.util.List;

import static com.chinasofti.common.base.AppConst.APP_TAG;
import static com.chinasofti.common.base.AppConst.Delegate_CONTACTS;
import static com.chinasofti.common.base.AppConst.Delegate_ME;
import static com.chinasofti.common.base.AppConst.Delegate_MESSAGE;
import static com.chinasofti.common.base.AppConst.Delegate_PHONE;
import static com.chinasofti.common.base.AppConst.IS_DEBUG;

/**
 * 要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 * 组件中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 * 组件的Application需置于java/debug文件夹中，不得放于主代码；
 * 组件中获取Context的方法必须为:CommonUtils.getAppContext()，不允许其他写法；
 */
public class BaseApplication extends Application {

    private static BaseApplication sInstance;
    private static Context mContext;
    private List<ApplicationDelegate> sDelegate = new ArrayList<>();

    public static BaseApplication getInstance() {
        return sInstance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        YLog.init(IS_DEBUG, APP_TAG);
        CommonUtils.init(this);
        mContext = getApplicationContext();

        try {
            ApplicationDelegate fragment_message = (ApplicationDelegate) Class.forName(Delegate_MESSAGE).newInstance();
            ApplicationDelegate fragment_contacts = (ApplicationDelegate) Class.forName(Delegate_CONTACTS).newInstance();
            ApplicationDelegate fragment_phone = (ApplicationDelegate) Class.forName(Delegate_PHONE).newInstance();
            ApplicationDelegate fragment_me = (ApplicationDelegate) Class.forName(Delegate_ME).newInstance();
            sDelegate.add(fragment_message);
            sDelegate.add(fragment_contacts);
            sDelegate.add(fragment_phone);
            sDelegate.add(fragment_me);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (ApplicationDelegate delegate : sDelegate) {
            delegate.onCreate();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (ApplicationDelegate delegate : sDelegate) {
            delegate.onTerminate();
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (ApplicationDelegate delegate : sDelegate) {
            delegate.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (ApplicationDelegate delegate : sDelegate) {
            delegate.onTrimMemory(level);
        }
    }
}
