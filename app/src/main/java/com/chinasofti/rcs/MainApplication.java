package com.chinasofti.rcs;

import android.content.Context;
import android.support.multidex.MultiDex;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chinasofti.common.base.BaseApplication;

import static com.chinasofti.common.base.AppConst.IS_DEBUG;

public class MainApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        if (IS_DEBUG) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
        //崩溃日志记录初始化
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // dex突破65535的限制
        MultiDex.install(this);
    }

}
