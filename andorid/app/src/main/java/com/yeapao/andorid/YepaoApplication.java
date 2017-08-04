package com.yeapao.andorid;

import android.app.Application;

import com.scottfu.sflibrary.cache.ACache;
import com.scottfu.sflibrary.util.FileUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.zxy.tiny.Tiny;

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

//        异步图片压缩
        Tiny.getInstance().init(this);


        //初始化文件路径
        FileUtil.initFilePath(this);
        LogUtil.e("image",FileUtil.IMAGE_PATH);

    }
}
