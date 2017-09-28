package com.yeapao.andorid.homepage.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.MessageListModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/8/19.
 */

public class MyMessageActivity extends BaseActivity {

    private static final String TAG = "MyMessageActivity";
    @BindView(R.id.rl_clock_out)
    RelativeLayout rlClockOut;
    @BindView(R.id.rl_order)
    RelativeLayout rlOrder;
    @BindView(R.id.rl_video)
    RelativeLayout rlVideo;
    @BindView(R.id.rl_circle)
    RelativeLayout rlCircle;


    private Badge clockBadge;
    private Badge reservationBadge;
    private Badge videoBadge;
    private Badge circleBadge;

    private MessageListModel messageListModel;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyMessageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        initTopBar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getNetWork(GlobalDataYepao.getUser(getContext()).getId());
    }

    private void setBadgeView() {

        clockBadge = new QBadgeView(this)
                .setBadgeNumber(messageListModel.getData().getPunchTheClocks())
                .setGravityOffset(15, 15, true)
                .bindTarget(rlClockOut)
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                            ToastManager.showToast(getContext(), "badge is removed");
                        }
                    }
                });

//        reservationBadge = new QBadgeView(this)
//                .setBadgeNumber(messageListModel.getData().getAppointments())
//                .setGravityOffset(15, 15, true)
//                .bindTarget(rlOrder)
//                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//                    @Override
//                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
//                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
//                            ToastManager.showToast(getContext(), "badge is removed");
//                        }
//                    }
//                });

        videoBadge = new QBadgeView(this)
                .setBadgeNumber(messageListModel.getData().getVideos())
                .setGravityOffset(15, 15, true)
                .bindTarget(rlVideo)
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                            ToastManager.showToast(getContext(), "badge is removed");
                        }
                    }
                });
        circleBadge = new QBadgeView(this)
                .setBadgeNumber(messageListModel.getData().getCommunityComments())
                .setGravityOffset(15, 15, true)
                .bindTarget(rlCircle)
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                            ToastManager.showToast(getContext(), "badge is removed");
                        }
                    }
                });


    }

    @Override
    protected void initTopBar() {
        initTitle("我的消息");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.rl_clock_out, R.id.rl_order, R.id.rl_video,R.id.rl_circle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_clock_out:
                ClockCardListActivity.start(getContext());
                break;
            case R.id.rl_order:
                ReservationActivity.start(getContext());
                break;
            case R.id.rl_video:
                VideoMessageActivity.start(getContext());
                break;
            case R.id.rl_circle:
                CircleMessageActivity.start(getContext());
                circleBadge.setBadgeNumber(0);
                break;
        }
    }




            private void getNetWork(String id) {
                    LogUtil.e(TAG,id);
                    subscription = Network.getYeapaoApi()
                            .requestMessageList(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserverMessagelist);
                }

                  Observer<MessageListModel> modelObserverMessagelist = new Observer<MessageListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(MessageListModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            messageListModel = model;
                            setBadgeView();
                        }
                    }
                };

}
