package com.yeapao.repository.api;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fujindong on 2017/8/3.
 */

public interface YeapaoApi {

    @POST("order/obtainRequest")
    Observable<NormalDataModel> requestDoorStatus(@Query("customerId") String id);
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

}