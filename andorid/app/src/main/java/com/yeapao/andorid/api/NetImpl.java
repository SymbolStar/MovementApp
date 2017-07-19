package com.yeapao.andorid.api;

import java.util.HashMap;

/**
 * Created by fujindong on 2017/7/18.
 */

public class NetImpl {

    private static NetImpl mNet;


    private NetImpl() {
    }

    public static NetImpl getInstance() {
        if (null == mNet) {
            synchronized (NetImpl.class) {
                mNet = new NetImpl();
            }
        }
        return mNet;
    }


    //获取用户信息
    public  HashMap<String, String> getHomeData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("time", "1");
        params.put("status", "1");
        params.put("region", "高新区");
        return params;
    }

    //获取用户信息
    public HashMap<String, String> setLessonScreening(String time, String status, String region) {

        HashMap<String, String> params = new HashMap<>();
        params.put("time", time);
        params.put("status", status);
        params.put("region", region);
        return params;
    }
}
