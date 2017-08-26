package com.yeapao.brake.api;

import java.util.HashMap;

/**
 * Created by fujindong on 2017/7/7.
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
    public  HashMap<String, String> setUserParams(String userId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", userId);
        params.put("cust_name", "yepao");
        params.put("gym", "yepao");
        return params;
    }


    //获取用户信息
    public  HashMap<String, String> getHomeData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("time", "1");
        params.put("status", "1");
        params.put("region", "高新区");
        return params;
    }
}
