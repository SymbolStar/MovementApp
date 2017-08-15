package com.yeapao.andorid.api;

/**
 * Created by fujindong on 2017/7/18.
 */

public class ConstantYeaPao {

    public static final String APPID = "2017072707914806";
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDSL8IDi+IOt5te7EWlvzW/2OoRfpw7JVooQtiq88f12BgWgwFrDcwLekgUesHQS/AU4nPvziSvbJnzRt4Db5eyX9FujrBsrOIT5QS1/qs4UTJFlikBr/16knfsjR8+fC7vyNqR5HLzzWmnW2VV15Mc1aletOf7AtLgv9UPTv28mk43Uhtmh3nDkNTUOgBtoHD6idzZ7zsATtmMLZ7l8EpPANvKfA6qucL/A6oWbSwDwiGa2gBTNMXTaDaDVbxGtX0p574biMsicuV+eF1IF4K0YI/wNlgjR/rzLzBBgqpn9/B3EHPfB8/tV7tI/bkVcKpsxTFZX+rZXQz/SxvVViOXAgMBAAECggEACrspeqjfp8S0CAqkG6xBgEQA31fJfjOQANI44bGEmgaoZC3YGrEfo85gc7s4vfvCjC5roOizcslmqscJer9tzUubE7vxQfspp75fmTMhZsSufmMGQPHNTT7BJLCn0bbhZpiooB47yDrKlCLUb6eL+ULnXNB7r5MbVSI2LIvp5IEcoWk7BZa7Efs8vx3JlwHpCpwOhmdAgVRXVbZkicKhrAwiGDGVVryq/WloVVAxO4+dN4cWVJIYeNA3OrVD+UOSP6/D/amaJe2CMKiHtpl51q5ZMGzUnH8iSH7kT2R2o6+Pe5hYEv+hVVtaNew4dwylUL83OH5+dxrKVNO1HV6NoQKBgQDpIS25QKKYaYR2f/beGgQ2x+ItBhPMtoniT2lp243YJVkSFeAjfIf4F0Ql89moTb7GWh87ifm/Gb/ufZAaryKkHbuEqyyP061H5/OkG3Bea3NYjpQiBR07qNoXaxYVWW7GQr0SghGEqNb8KlcRZOehrapaIc+NnVGaphcfyP7/LwKBgQDmzmMWfoK+tw9fhreuH+2hI7pr2iXhnqFC+jlmdC8TPUG2tMlmpFMEvXwN8xmrg8IcbYps+sanBOPNYk9y4YQyAhKKvEniSXFLGSQj5Rotm2OpoIXS3DxI4eJFf0wjz8hs1QInHQXgYphYMZehKmf0Sdodx+HZhCxjiPEes3ZIGQKBgQDIvbkRe4aGltVo9GcueFiIkzU+b6086TFxnc+3R0ash/xahluqMuEhz2bvBzq0ob1ULuYV7okO9K1APv6G+6doifGeG4cTkcHz2NcCyF3J79wytRL4+E5jwNGKuLsbRxcLwQvtk/JhuMDmAc9XOvv5TAET+2ktF/ncoIfo5rosIQKBgBV44+0GzgESJML9VH9FhyTznvdDrzyDNnt1rftjGbIPg9qMjSQYa4TIYA8oK54yPs/IPRWQawTExrCn0b5dTqAcYz827yS3MmwLJbqVKM9PYoE4ZtrMFGuvYA6ZHitCvGGrm2yqHY7XfVbPHk0nFc67RCh7HP7QdhS4e74gcgOZAoGARtRildwK8KI65B1XN+SfLsISgMtioHb6/sNtzmCQ7kk/lij8eco3TFa1sHe4qfymcoxRItwfCPE0PkGnJBSMNUYIs2Z73JvjrDUKi1ni/6ZaTy5D9EkqdwcB35F2JV+JS6QZ8QahnD9HptHTEkThjDVa5okP4KzGmxQTUke4bf0=";

    public static String APP_ID = "wx6e86b10e6860b2be";//微信支付

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
