package com.chinasofti.me;

import com.chinasofti.common.base.ApplicationDelegate;
import com.chinasofti.common.manager.ViewManager;
import com.chinasofti.common.utils.log.YLog;
import com.chinasofti.me.ui.fragment.MeFragment;

import static com.chinasofti.common.base.AppConst.TAB_ME_INDEX;


public class MeDelegate implements ApplicationDelegate {

    private static final String TAG = "MeDelegate";

    @Override
    public void onCreate() {
        YLog.e(TAG,"init MeFragment");
        ViewManager.getInstance().addFragment(TAB_ME_INDEX, new MeFragment());
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }
}
