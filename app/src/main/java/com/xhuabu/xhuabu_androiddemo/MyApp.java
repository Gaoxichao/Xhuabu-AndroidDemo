package com.xhuabu.xhuabu_androiddemo;

import android.app.Application;
import android.content.Context;

import com.xhuabu.xhuabu_androiddemo.base.ConfigUtil;

/**
 * Created by UPC on 2018/3/1.
 */

public class MyApp extends Application {

    public static MyApp mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Context getContext() {
        return mApp.getApplicationContext();
    }


    //保存一些常用的配置
    private static ConfigUtil configUtil = null;

    public static ConfigUtil getConfig() {
        if (configUtil == null) {
            configUtil = new ConfigUtil();
        }
        return configUtil;
    }
}
