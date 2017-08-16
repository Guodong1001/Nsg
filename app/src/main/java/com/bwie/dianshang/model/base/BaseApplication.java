package com.bwie.dianshang.model.base;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/19
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
