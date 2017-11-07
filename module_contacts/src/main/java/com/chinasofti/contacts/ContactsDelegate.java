package com.chinasofti.contacts;

import com.chinasofti.common.base.ApplicationDelegate;
import com.chinasofti.common.manager.ViewManager;
import com.chinasofti.common.utils.log.YLog;
import com.chinasofti.contacts.ui.fragment.ContactsFragment;

import static com.chinasofti.common.base.AppConst.TAB_CONTACTS_INDEX;


public class ContactsDelegate implements ApplicationDelegate {

    private static final String TAG = "ContactsDelegate";

    @Override
    public void onCreate() {
        YLog.e(TAG,"init ContactsFragment");
        ViewManager.getInstance().addFragment(TAB_CONTACTS_INDEX, new ContactsFragment());
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
