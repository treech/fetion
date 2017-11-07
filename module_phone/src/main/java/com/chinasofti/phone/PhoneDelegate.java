package com.chinasofti.phone;

import com.chinasofti.common.base.ApplicationDelegate;
import com.chinasofti.common.manager.ViewManager;
import com.chinasofti.common.utils.log.YLog;
import com.chinasofti.phone.ui.fragment.PhoneFragment;

import static com.chinasofti.common.base.AppConst.TAB_PHONE_INDEX;


public class PhoneDelegate implements ApplicationDelegate {

    private static final String TAG = "PhoneDelegate";

    @Override
    public void onCreate() {
        YLog.e(TAG,"init PhoneFragment");
        ViewManager.getInstance().addFragment(TAB_PHONE_INDEX, new PhoneFragment());
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
