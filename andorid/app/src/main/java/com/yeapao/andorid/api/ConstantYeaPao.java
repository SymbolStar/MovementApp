package com.yeapao.andorid.api;

/**
 * Created by fujindong on 2017/7/18.
 */

public class ConstantYeaPao {
    public static final String HOST = "http://47.92.113.97:8080/yepao";
//    public static final String HOST = "http://192.168.2.128:8080";

    public static final String GET_HOME_LIST = HOST+"/home/homeList";
    public static final String GET_HOME_SELECT = HOST+"/home/selectHomeList";
    public static final String GET_LESSON_DETAIL = HOST+"/curriculum/findCurriculum";
    public static final String GET_STORE_DETAIL = HOST+"/shop/findShopDetails";
    public static final String GET_SHOPPING_LIST = HOST+"/curriculum/findCurriculumList";
    public static final String LOGIN = HOST+"/user/loginApp";
    public static final String RESERVATION = HOST+"/curriculum/saveReservation";
    public static final String  GET_VERIFICATION= HOST+"/user/verificationCode";
    public static final String  REGISTER= HOST+"/user/register";
    public static final String  GET_RESERVATION_LIST= HOST+"/curriculum/findMyScheduleList";
    public static final String  GET_LESSON_LIST= HOST+"/curriculum/findMyCurriculumList";

}
