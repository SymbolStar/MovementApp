package com.yeapao.andorid;

import android.app.Application;

import com.scottfu.sflibrary.cache.ACache;

/**
 * Created by fujindong on 2017/7/25.
 */

public class YepaoApplication extends Application {




    private static YepaoApplication application;
    public static ACache ACACHE;
    public static ACache YXTCACHE;




    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        ACACHE = ACache.get(getApplicationContext());
        YXTCACHE = ACache.get(getApplicationContext(), "YepaoCache");
    }
}
