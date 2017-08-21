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

    //获取验证码
    public HashMap<String, String> getVerification(String phone) {
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", phone);
        return params;
    }

    //用户注册
    public HashMap<String, String> registerRequest(String username, String password, String name, String verificationCode) {
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", username);
        params.put("password", password);
        params.put("name", name);
        params.put("verificationCode", verificationCode);
        return params;
    }
//    TODO 首页
    //获取首页数据
    public HashMap<String, String> loginRequest(String phone, String password) {
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", password);
        return params;
    }

    //获取首页数据
    public HashMap<String, String> getHomeData(String id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        return params;
    }


    //首页筛选
    public HashMap<String, String> setLessonScreening(String time, String status, String region) {
        HashMap<String, String> params = new HashMap<>();
        params.put("time", time);
        params.put("status", status);
        params.put("region", region);
        return params;
    }


    //课程详情
    public HashMap<String, String> getLessonDetail(String id,String customerId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("scheduleId", id);
        params.put("customerId", customerId);
        return params;
    }

    //预约课程
    public HashMap<String, String> reservationLesson(String scheduleId, String curriculumId, String id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("scheduleId", scheduleId);
        params.put("curriculumId", curriculumId);
        params.put("id", id);
        return params;
    }

    //店铺详情
    public HashMap<String, String> getStoreDetail(String id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("curriculumId", id);
        return params;
    }

    //    TODO 我的
//获取我的预约
    public HashMap<String, String> getReservationList(String id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        return params;
    }
    //获取我的课程
    public HashMap<String, String> getLessonList(String id,String status) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("status", id);
        return params;
    }

}
