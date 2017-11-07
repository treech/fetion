package com.chinasofti.common.base;

/**
 * manage module fragments
 */
public interface ApplicationDelegate {

    void onCreate();

    void onTerminate();

    void onLowMemory();

    void onTrimMemory(int level);

}
