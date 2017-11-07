package com.chinasofti.message;

import com.chinasofti.common.base.ApplicationDelegate;
import com.chinasofti.common.manager.ViewManager;
import com.chinasofti.common.utils.log.YLog;
import com.chinasofti.message.ui.fragment.MessageFragment;

import static com.chinasofti.common.base.AppConst.TAB_MESSAGE_INDEX;


public class MessageDelegate implements ApplicationDelegate {

    private static final String TAG = "MessageDelegate";

    @Override
    public void onCreate() {
        YLog.e(TAG,"init MessageFragment");
        ViewManager.getInstance().addFragment(TAB_MESSAGE_INDEX, new MessageFragment());
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
