package com.yeapao.andorid.util;

import android.content.Context;

import com.scottfu.sflibrary.cache.ACache;
import com.yeapao.andorid.model.UserData;

/**
 * Created by fujindong on 2017/7/25.
 */

public class GlobalDataYepao {


    private static boolean IS_LOGIN = false;

    public static boolean isLogin() {
        return IS_LOGIN;
    }

    public static void setIsLogin(boolean isLogin) {
        IS_LOGIN = isLogin;
    }

    private static ACache cache;

    public static ACache getCache(Context context) {
        if (cache == null) {
            cache = ACache.get(context, "YepaoCache");
        }
        return cache;
    }

    public static void setCache(ACache aCache) {
        cache = aCache;
    }
    public static void setUser(Context context, UserData userData) {
        getCache(context).put(GlobalDataConstant.USER_DATA, userData);
    }

    public static UserData getUser(Context context) {
        return (UserData) getCache(context).getAsObject(GlobalDataConstant.USER_DATA);
    }

    public static void clearUser(Context context) {
        getCache(context).remove(GlobalDataConstant.USER_DATA);
    }

}
