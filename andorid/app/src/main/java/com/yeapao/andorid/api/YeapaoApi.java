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
import com.yeapao.andorid.model.MessageListModel;
import com.yeapao.andorid.model.MyOrderDataModel;
import com.yeapao.andorid.model.Myfiles;
import com.yeapao.andorid.model.MyselfLessonModel;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.OrderDetailModel;
import com.yeapao.andorid.model.PunchTheClockModel;
import com.yeapao.andorid.model.RecommendLessonModel;
import com.yeapao.andorid.model.RegisterModel;
import com.yeapao.andorid.model.RollCallListModel;
import com.yeapao.andorid.model.SaveReservation;
import com.yeapao.andorid.model.TestData;
import com.yeapao.andorid.model.UserDetailsModel;
import com.yeapao.andorid.model.VideoDataModel;
import com.yeapao.andorid.model.VideoTypeModel;
import com.yeapao.andorid.model.WareHouseListModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
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

//支付
    @POST("payment/callPayment")
    Observable<CallPaymentModel> requestPayment(@Query("price") String price, @Query("orderCode") String orderCode,
                                                @Query("paymentType") String paymentType);
//打卡
    @POST("community/savePunch")
    Observable<NormalDataModel> requestClockOut(@Query("customerId") String id, @Query("weight") String weight);

//提交疼痛度
    @POST("curriculum/savePainDegree")
    Observable<NormalDataModel> requestPainData(@Query("reservationDetailsId") String reservationId, @Query("position") String position, @Query("degree") String degree);

    //   预约
    @POST("curriculum/saveReservation")
    Observable<SaveReservation> requestSaveReservation(@Query("scheduleId") String scheduleId, @Query("curriculumId") String curriculumId, @Query("id") String id);

    //    课程预约列表
    @POST("curriculum/findRecommendList")
    Observable<RecommendLessonModel> requesetRecommendLesson(@Query("shopId") String shopId, @Query("id") String id,
                                                             @Query("time") String time);

//    视频类型
    @GET("video/types")
    Observable<VideoTypeModel> requestVideoType();
//  消息列表
    @POST("user/messageList")
    Observable<MessageListModel> requestMessageList(@Query("customerId") String id);
//消息详情列表
    @POST("user/punchTheClocks")
    Observable<PunchTheClockModel> requestPunchTheClock(@Query("customerId") String customerId, @Query("type") String type);


    //   意见反馈
    @POST("user/feedbackSave")
    Observable<NormalDataModel> requestFeedBackSave(@Query("customerId") String customerId, @Query("content") String content);

    //获取用户资料
    @POST("user/userDetails")
    Observable<UserDetailsModel> requesetUserDetail(@Query("id") String Id);

    //    修改资料
    @Multipart
    @POST("user/updateCustomerById")
    Observable<NormalDataModel> requestChangeUserData(@Query("customerId") String customerId,
                                                       @Query("gender") String gender,
                                                       @Query("name") String name,
                                                        @Query("age") String age,
                                                       @Part("files\";filename=\"image.jpeg") RequestBody file);
    //    修改资料2
    @POST("user/updateCustomerById")
    Observable<NormalDataModel> requestChangeUserDataV2(@Query("customerId") String customerId,
                                                      @Query("gender") String gender,
                                                      @Query("name") String name,
                                                      @Query("age") String age);

    //完善资料
    @POST("user/updateCustomer")
    Observable<NormalDataModel> requestCostomerData(@Query("gender") String gender,
                                                    @Query("birthDate") String birthDte,
                                                    @Query("height") String height,
                                                    @Query("weight") String weight,
                                                    @Query("objective") String objective,
                                                    @Query("physicalCondition") String physical,
                                                    @Query("id") String id);
//订单列表
    @POST("order/findOrderList")
    Observable<MyOrderDataModel> getOrderInfo(@Query("customerId") String customerId);

    //   删除订单
    @POST("order/deleteOrderById")
    Observable<NormalDataModel> requestDeleteOrder(@Query("orderId") String orderId);

    @POST("order/OrderDetails")
    Observable<OrderDetailModel> requestOrderDetail(@Query("orderId") String orderId);

    @POST("curriculum/findMyCurriculumList")
    Observable<MyselfLessonModel> requestMyCurriculumList(@Query("id") String id, @Query("status") String status);

    @POST("user/loginApp")
    Observable<UserDetailsModel> requestLogin(@Query("phone") String phone, @Query("password") String passworid);

    //   地图首页
    @POST("home/selectWareHouseList")
    Observable<WareHouseListModel> requestWareHouseList(@Query("customerId") String customerId);
}
