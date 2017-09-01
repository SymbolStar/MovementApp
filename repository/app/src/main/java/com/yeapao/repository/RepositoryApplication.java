package com.yeapao.repository;

import android.app.Application;

import com.scottfu.sflibrary.cache.ACache;
import com.scottfu.sflibrary.util.FileUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.zxy.tiny.Tiny;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by fujindong on 2017/8/31.
 */

public class RepositoryApplication extends Application {

    private static RepositoryApplication application;


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;



//        JPush初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
//        ToastManager.showToast(this,getAndroidId(getContent())+"------AndroidId");

    }
}
