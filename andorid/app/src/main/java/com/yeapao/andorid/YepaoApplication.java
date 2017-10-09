package com.yeapao.andorid;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.scottfu.sflibrary.cache.ACache;
import com.scottfu.sflibrary.util.FileUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.zxy.tiny.Tiny;

import java.util.jar.JarOutputStream;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by fujindong on 2017/7/25.
 */

public class YepaoApplication extends MultiDexApplication {


    private static YepaoApplication application;
    public static ACache ACACHE;
    public static ACache YXTCACHE;
    private static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        application = this;
        ACACHE = ACache.get(getApplicationContext());
        YXTCACHE = ACache.get(getApplicationContext(), "YepaoCache");

//        异步图片压缩
        Tiny.getInstance().init(this);

//        JPush初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);



        //初始化文件路径
        FileUtil.initFilePath(this);
        LogUtil.e("image",FileUtil.IMAGE_PATH);


        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }


    public static Context getContextObject() {
        return context;
    }
}
