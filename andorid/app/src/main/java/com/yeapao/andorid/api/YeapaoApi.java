package com.yeapao.andorid.api;

import com.yeapao.andorid.model.BodySideListModel;
import com.yeapao.andorid.model.BodySideOneGetModel;
import com.yeapao.andorid.model.BodySideOneModel;
import com.yeapao.andorid.model.FoodInfoModel;
import com.yeapao.andorid.model.IsAmShopModel;
import com.yeapao.andorid.model.Myfiles;
import com.yeapao.andorid.model.RegisterModel;
import com.yeapao.andorid.model.TestData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fujindong on 2017/8/3.
 */

public interface YeapaoApi {

    @POST("user/userDetails")
    Observable<TestData> createUser(@Query("id") String id);
//
//    @POST("user/loginApp")
//    Observable<UserMessage> getUserData(@Query("phone") String phone, @Query("password") String password);
//
//    @POST("user/verificationCode")
//    Observable<VerificationCode> getVerification(@Query("phone") String phone);
//
//    @POST("user/register")
//    Observable<UserData> loginAccount(@Query("phone") String phone, @Query("password") String password, @Query("name") String name,
//                                      @Query("verificationCode") String ver);

    //    注册
    @POST("user/register")
    Observable<RegisterModel> loginAccount(@Query("phone") String phone, @Query("password") String password, @Query("name") String name,
                                           @Query("verificationCode") String ver);

    //    体测第一节
    @Multipart
    @POST("curriculum/saveBodySideOne")
    Observable<BodySideOneModel> uploadFile(@Query("quietHeartRate") String rate, @Query("bloodPressure") String blood, @Query("heights") String height,
                                            @Query("weight") String weight, @Query("inBody") String inbody, @Query("scheduleId") String sid,
                                            @Query("customerId") String cid, @Query("bodySideOne") String bid, @Part("files\";filename=\"image.jpeg") RequestBody file);


    //   上传图片单张
    @Multipart
    @POST("user/filesUpload")
    Observable<Myfiles> myFiles(@Part() MultipartBody.Part file);

    //    我是店长
    @POST("user/iAmShop")
    Observable<IsAmShopModel> getIsAnShop(@Query("id") String id);

    //    体测预约
    @POST("curriculum/bodySideList")
    Observable<BodySideListModel> getBodySide(@Query("id") String id);

//    课程预约
//    @POST("curriculum/courseReservation")
//    Observable

    //    食谱
    @POST("cookbook/infos")
    Observable<FoodInfoModel> getFoodInfos(@Query("dateStr") String date);


    //    体测第一节返回数据
    @POST("curriculum/selectBodySideOne")
    Observable<BodySideOneGetModel> getBodySideOne(@Query("scheduleId") String id);

}
