package com.yeapao.andorid.lessondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.NetImpl;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogCallback;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.shopping.PainStatusActivity;
import com.yeapao.andorid.homepage.shopping.PainStatusWomenActivity;
import com.yeapao.andorid.homepage.shopping.ShoppingOrderActivity;
import com.yeapao.andorid.model.LessonDetailData;
import com.yeapao.andorid.model.ReservationLessonModel;
import com.yeapao.andorid.model.SaveReservation;
import com.yeapao.andorid.model.UserData;
import com.yeapao.andorid.storedetails.StoreDetailActivity;
import com.yeapao.andorid.util.GlobalDataYepao;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/14.
 */

public class LessonDetailActivity extends BaseActivity {


    private static final String TAG = "LessonDetailActivity";

    private static final String SCHEDULEID = "SCHEDULEID";
    
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_coach_name)
    TextView tvCoachName;
    @BindView(R.id.tv_good)
    TextView tvGood;
    @BindView(R.id.tv_lesson_content)
    TextView tvLessonContent;
    @BindView(R.id.tv_adress)
    TextView tvAdress;
    @BindView(R.id.tv_lesson_time)
    TextView tvLessonTime;
    @BindView(R.id.rv_lesson_content_list)
    RecyclerView rvLessonContentList;
    @BindView(R.id.tv_bespeak)
    TextView tvBespeak;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.rl_lesson_store)
    RelativeLayout rlLessonStore;
    @BindView(R.id.tv_lesson_name)
    TextView tvLessonName;

    private LessonDetailData lessonDetailData;

    private String scheduleId = "0";


    private Gson gson = new Gson();

    private LinearLayoutManager llm;

    private LessonDetailContentAdapter lessonDetailContentAdapter;


    public static void start(Context context, String scheduleId) {
        LogUtil.e(TAG+"ssss",scheduleId);
        Intent intent = new Intent();
        intent.setClass(context, LessonDetailActivity.class);
        intent.putExtra(SCHEDULEID, scheduleId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);
        ButterKnife.bind(this);
        scheduleId = getIntent().getStringExtra(SCHEDULEID);
        initView();
        initTopBar();
        getData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        getData();

    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvLessonContentList.setLayoutManager(llm);
    }

    @OnClick(R.id.tv_order)
    void setTvOrder(View view) {

        if (GlobalDataYepao.isLogin()) {


            if (lessonDetailData.getData().getCurriculum().getMySchedule().equals("1")) {

                getNetWorkReservation(String.valueOf(lessonDetailData.getData().getCurriculum().getScheduleId()),
                        String.valueOf(lessonDetailData.getData().getCurriculum().getCurriculumId()),
                        GlobalDataYepao.getUser(getContext()).getId());

            } else {
                if (lessonDetailData.getData().getCurriculum().getLinePrice() == 0) {
                    getNetWorkReservation(String.valueOf(lessonDetailData.getData().getCurriculum().getScheduleId()),
                            String.valueOf(lessonDetailData.getData().getCurriculum().getCurriculumId()),
                            GlobalDataYepao.getUser(getContext()).getId());
                } else {
                    hintPayLesson();
                }

            }

        } else {
            LoginActivity.start(getContext());
        }







    }

    private void hintPayLesson() {

        DialogUtils.showDialog(getContext(), "消息提示", "您还没有购买课程，请先购买",
                "线下课程"+" ￥"+lessonDetailData.getData().getCurriculum().getLinePrice(),
                "线上课程"+" ￥"+lessonDetailData.getData().getCurriculum().getOnLinePrice(), new DialogCallback() {
                    @Override
                    public void onItemClick(int position) {
                        LogUtil.e(TAG,"onitemclick");

                    }

                    @Override
                    public void onLeftClick() {
                        LogUtil.e(TAG,"leftClick");
                    }

                    @Override
                    public void onRightClick() {

                        ShoppingOrderActivity.start(getContext(),String.valueOf(lessonDetailData.getData().getCurriculum().getMap_curriculum_typesId()),
                                String.valueOf(lessonDetailData.getData().getCurriculum().getLinePrice()),GlobalDataYepao.getUser(getContext()).getId());

                    }
                });

    }


    @OnClick(R.id.rl_lesson_store)
    void setRlStoreClick(View view) {
        StoreDetailActivity.start(getContext(),String.valueOf(lessonDetailData.getData().getCurriculum().getCurriculumId()));
    }

    @Override
    protected void initTopBar() {

        initBack();
        initTitle("课程详情");
    }


    private void getData() {

        String id = "0";
        UserData userData = new UserData();
        userData = GlobalDataYepao.getUser(getContext());
        if (userData != null) {
            id = userData.getId();
        }
        LogUtil.e(TAG,scheduleId);
        CloudClient.doHttpRequest(getContext(), ConstantYeaPao.GET_LESSON_DETAIL, NetImpl.getInstance().getLessonDetail(scheduleId,id), null, new JSONResultHandler() {
            @Override
            public void onSuccess(String jsonString) {
                LogUtil.e(TAG, jsonString);
                lessonDetailData = gson.fromJson(jsonString, LessonDetailData.class);
                if (lessonDetailData.getErrmsg().equals("ok")) {
                    setData();
                } else {

                }
            }

            @Override
            public void onError(VolleyError errorMessage) {

            }
        });
    }

    private void setData() {
        LogUtil.e(TAG+"test",String.valueOf(lessonDetailData.getData().getCurriculum().getScheduleId()));
        tvLessonName.setText(lessonDetailData.getData().getCurriculum().getCurriculumName());
        tvShopName.setText(lessonDetailData.getData().getCurriculum().getShopName());
        tvCoachName.setText(lessonDetailData.getData().getCurriculum().getCoach());
        tvAdress.setText(lessonDetailData.getData().getCurriculum().getAddress());
        tvLessonTime.setText(lessonDetailData.getData().getCurriculum().getClassTimeStart()+
        "-"+lessonDetailData.getData().getCurriculum().getClassTimeEnd());
        tvGood.setText(lessonDetailData.getData().getCurriculum().getBeGoodAt());
        tvBespeak.setText("已预约  " + lessonDetailData.getData().getCurriculum().getBespeak() + "/" +
                lessonDetailData.getData().getCurriculum().getTotalNumber());

        GlideUtil glideUtil = new GlideUtil();
        glideUtil.glideLoadingImage(getContext(), ConstantYeaPao.HOST + lessonDetailData.getData().getCurriculum().getHead(),
                R.drawable.y_you, ivHead);

        if (lessonDetailContentAdapter == null) {
            lessonDetailContentAdapter = new LessonDetailContentAdapter(getContext(), lessonDetailData);
            rvLessonContentList.setAdapter(lessonDetailContentAdapter);
        }

        if (lessonDetailData.getData().getCurriculum().getIsBespeak().equals("1")) {
            tvOrder.setText("已预约");
            tvOrder.setBackgroundColor(getResources().getColor(R.color.bg_grey));
        } else {
            tvOrder.setText("预约");
            tvOrder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

    }

    @Override
    protected Context getContext() {
        return this;
    }


            private void getNetWorkReservation(String scheduleId,String curriculumId,String id) {
                    subscription = Network.getYeapaoApi()
                            .requestSaveReservation(scheduleId,curriculumId,id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserverReservation);
                }

                  Observer<SaveReservation> modelObserverReservation = new Observer<SaveReservation>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(SaveReservation model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            if (GlobalDataYepao.getUser(getContext()).getGender().equals("男")) {
                                PainStatusActivity.start(getContext(), String.valueOf(model.getData().getReservationDetailsId()));
                            } else {
                                PainStatusWomenActivity.start(getContext(), String.valueOf(model.getData().getReservationDetailsId()));
                            }

                        }
                    }
                };


}
