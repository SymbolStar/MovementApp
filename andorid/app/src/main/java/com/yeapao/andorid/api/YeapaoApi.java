package com.yeapao.andorid.api;

import com.yeapao.andorid.model.BodySideDetailModel;
import com.yeapao.andorid.model.BodySideForthBackModel;
import com.yeapao.andorid.model.BodySideListModel;
import com.yeapao.andorid.model.BodySideOneGetModel;
import com.yeapao.andorid.model.BodySideOneModel;
import com.yeapao.andorid.model.BodySideThreeBackModel;
import com.yeapao.andorid.model.BodySideThreeGetDataModel;
import com.yeapao.andorid.model.BodySideTwoBackModel;
import com.yeapao.andorid.model.BodySideTwoGetBackModel;
import com.yeapao.andorid.model.CallPaymentModel;
import com.yeapao.andorid.model.ClassBeginsModel;
import com.yeapao.andorid.model.CookListDetailModel;
import com.yeapao.andorid.model.FoodInfoModel;
import com.yeapao.andorid.model.HealthDataModel;
import com.yeapao.andorid.model.IAmCoachListModel;
import com.yeapao.andorid.model.IsAmShopModel;
import com.yeapao.andorid.model.LessonOrderModel;
import com.yeapao.andorid.model.Myfiles;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.RegisterModel;
import com.yeapao.andorid.model.RollCallListModel;
import com.yeapao.andorid.model.TestData;
import com.yeapao.andorid.model.VideoDataModel;

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



    //    体测第一节
    @Multipart
    @POST("curriculum/saveBodySideOne")
    Observable<BodySideOneModel> uploadFile(@Query("quietHeartRate") String rate, @Query("bloodPressure") String blood, @Query("heights") String height,
                                            @Query("weight") String weight, @Query("inBody") String inbody, @Query("scheduleId") String sid,
                                            @Query("customerId") String cid, @Query("bodySideOne") String bid, @Part("files\";filename=\"image.jpeg") RequestBody file);



    //    体测第一节返回数据
    @POST("curriculum/selectBodySideOne")
    Observable<BodySideOneGetModel> getBodySideOne(@Query("scheduleId") String id);

    //    体测第二节
    @POST("curriculum/saveBodySideTwo")
    Observable<BodySideTwoBackModel> uploadDataSecond(@Query("upperRight") String upperRight, @Query("upperLeft") String upperLeft,
                                                @Query("abdomen") String abdomen, @Query("waist") String waist, @Query("hips") String hips,
                                                @Query("lowerRight") String lowerRight, @Query("lowerLeft") String lowerLeft,
                                                @Query("bodySideId") String bodySideId, @Query("bodySideTwo") String bodySideTwo);

    @POST("curriculum/selectBodySideTwo")
    Observable<BodySideTwoGetBackModel> getBodySideTwo(@Query("scheduleId") String id);


    //    体测第三节
    @POST("curriculum/saveBodySideThree")
    Observable<BodySideThreeBackModel> uploadDataThird(@Query("upperLimbStrength") String str1, @Query("lowerExtremityStrength") String str2,
                                                        @Query("precursor") String str3, @Query("heartRateOne") String str4, @Query("heartRateTwo") String str5,
                                                        @Query("heartRateThree") String str6,
                                                        @Query("bodySideId") String bodySideId, @Query("bodySideThree") String bodySideThree);

    @POST("curriculum/selectBodySideThree")
    Observable<BodySideThreeGetDataModel> getBodySideThree(@Query("scheduleId") String id);

//    //    第四节体测
    @Multipart
    @POST("curriculum/saveBodySideFour")
    Observable<BodySideForthBackModel> uploadDataForth(@Part("positive\";filename=\"image.jpg") RequestBody file1, @Part("side\";filename=\"image.jpg") RequestBody file2,
                                                       @Part("back\";filename=\"image.jpg") RequestBody file3, @Part("furredTongue\";filename=\"image.jpg") RequestBody file4,
                                                       @Query("bodySideId")String bodySideId);


    //    我是教练
    @POST("user/iAmCoach")
    Observable<IAmCoachListModel> getCoachList(@Query("customerId") String Id, @Query("time") String time);

    //    教练点名
    @POST("user/rollCallList")
    Observable<RollCallListModel> getRollCallList(@Query("scheduleId") String Id);

    //    开始上课
    @POST("user/rollCall")
    Observable<NormalDataModel> requestRollCall(@Query("scheduleId") String Id, @Query("ids") String ids);


    //    上课状态
    @POST("user/classBegins")
    Observable<ClassBeginsModel> getClassBegins(@Query("scheduleId") String Id);

//早退
    @POST("user/leaveEarly")
    Observable<NormalDataModel> requestLeaveEarly(@Query("ids") String ids);
//签到
    @POST("user/normal")
    Observable<NormalDataModel> requestNormal(@Query("ids") String ids);
//下课
    @POST("user/ClassIsOver")
    Observable<NormalDataModel> requestClassIsOver(@Query("scheduleId") String ids);

    //    我是店长课程预约 TODO 暂时没数据
    @POST("curriculum/courseReservation")
    Observable<IAmCoachListModel> getReservationLessonList(@Query("customerId") String Id, @Query("time") String time);

    @POST("user/healthDatabase")
    Observable<HealthDataModel> getHealthData(@Query("customerId") String id);

//食谱 食品库
    @POST("cookbook/detail")
    Observable<CookListDetailModel> getCookDetail(@Query("threeMeals") String meal, @Query("type") String type);

//体测记录详情
    @POST("user/testDetails")
    Observable<BodySideDetailModel> getBodySideDetail(@Query("bodySideId") String id);

    //获取视频
    @POST("video/list")
    Observable<VideoDataModel> getVideoData(@Query("type") String type);

    //创建订单
    @POST("order/crateOrder")
    Observable<LessonOrderModel> requestlessonOrder(@Query("mapCurriculumTypesId") String typeId,
                                                    @Query("price") String price, @Query("id") String id);


    @POST("payment/callPayment")
    Observable<CallPaymentModel> requestPayment(@Query("price") String price, @Query("orderCode") String orderCode,
                                                @Query("paymentType") String paymentType);

}
