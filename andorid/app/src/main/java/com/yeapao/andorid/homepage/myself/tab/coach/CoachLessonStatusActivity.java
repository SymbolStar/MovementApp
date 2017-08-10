package com.yeapao.andorid.homepage.myself.tab.coach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.ClassBeginsModel;
import com.yeapao.andorid.model.NormalDataModel;

import java.util.jar.Attributes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/31.
 */

public class CoachLessonStatusActivity extends BaseActivity {

    private static final String TAG = "CoachLessonStatusActivity";
    @BindView(R.id.rv_coach_lesson_status_list)
    RecyclerView rvCoachLessonStatusList;
    @BindView(R.id.tv_class_over)
    TextView tvClassOver;

    private CoachLessonStatusMessageAdapter messageAdapter;

    private String scheduleId;
    private String scheduleName;


    public static void start(Context context, String id, String scheduleName) {

        Intent intent = new Intent();
        intent.putExtra("scheduleId", id);
        intent.putExtra("scheduleName", scheduleName);
        intent.setClass(context, CoachLessonStatusActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_lesson_status);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        scheduleId = intent.getStringExtra("scheduleId");
        scheduleName = intent.getStringExtra("scheduleName");
        initTopBar();
        initView();


    }

    private void initView() {
        rvCoachLessonStatusList.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();
    }

    private void getData() {


        getNetWork(scheduleId);


    }

    private void showResult(ClassBeginsModel model) {

        if (messageAdapter == null) {
            messageAdapter = new CoachLessonStatusMessageAdapter(getContext(), model);
            messageAdapter.setLeaveEarlyListener(new LeaveEarlyListener() {
                @Override
                public void leaveListener(String id) {
                    requesetLeave(id);
                    ToastManager.showToast(getContext(), "leave" + id);
                }
            });
            messageAdapter.setRollCallListener(new RollCallListener() {
                @Override
                public void rollCall(String ids) {
                    requesetNormal(ids);
                    ToastManager.showToast(getContext(), "rollcall" + ids);
                }
            });
            rvCoachLessonStatusList.setAdapter(messageAdapter);
        } else {
            rvCoachLessonStatusList.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();

        }

    }


    @Override
    protected void initTopBar() {
        initTitle(scheduleName);
        initBack();

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick(R.id.tv_class_over)
    public void setTvClassOver() {
        requesetClassIsOver(scheduleId);
    }


    private void getNetWork(String id) {
        subscription = Network.getYeapaoApi()
                .getClassBegins(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<ClassBeginsModel> modelObserver = new Observer<ClassBeginsModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(ClassBeginsModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                showResult(model);
            }
        }
    };


    private void requesetLeave(String id) {
        subscription = Network.getYeapaoApi()
                .requestLeaveEarly(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverLeave);
    }

    Observer<NormalDataModel> modelObserverLeave = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {

            }
        }
    };


    private void requesetNormal(String id) {
        subscription = Network.getYeapaoApi()
                .requestNormal(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverNormal);
    }

    Observer<NormalDataModel> modelObserverNormal = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {

            }
        }
    };


    private void requesetClassIsOver(String id) {
        subscription = Network.getYeapaoApi()
                .requestClassIsOver(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverClassIsOver);
    }

    Observer<NormalDataModel> modelObserverClassIsOver = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {
            finish();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {

            }
        }
    };

}
