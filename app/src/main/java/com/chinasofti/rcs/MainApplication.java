package com.chinasofti.rcs;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chinasofti.common.base.BaseApplication;

import static com.chinasofti.common.base.AppConst.IS_DEBUG;

//@ReportsCrashes(
//        mailTo = "yeguoqiang6@outlook.com",
//        mode = ReportingInteractionMode.DIALOG,
//        customReportContent = {
//                ReportField.APP_VERSION_NAME,
//                ReportField.ANDROID_VERSION,
//                ReportField.PHONE_MODEL,
//                ReportField.CUSTOM_DATA,
//                ReportField.BRAND,
//                ReportField.STACK_TRACE,
//                ReportField.LOGCAT,
//                ReportField.USER_COMMENT},
//        resToastText = R.string.crash_toast_text,
//        resDialogText = R.string.crash_dialog_text,
//        resDialogTitle = R.string.crash_dialog_title)
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
//        ACRA.initWindowFeature(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // dex突破65535的限制
        MultiDex.install(this);
    }

}
