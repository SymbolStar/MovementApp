package com.yeapao.andorid.homepage.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.homepage.circle.*;
import com.yeapao.andorid.homepage.circle.circledetail.CircleDetailActivity;
import com.yeapao.andorid.model.CircleMessageModel;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.PunchTheClockModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/9/27.
 */

public class CircleMessageActivity extends BaseActivity {

    private static final String TAG = "CircleMessageActivity";
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_video_message_list)
    RecyclerView rvVideoMessageList;

    private CircleMessageAdapter messageAdapter;

    private CircleMessageModel mMessageModel;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CircleMessageActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_card);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        rvVideoMessageList.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();
    }

    private void getData() {
//        getNetWork(GlobalDataYepao.getUser(getContext()).getId(),"4");
        getNetWork(GlobalDataYepao.getUser(getContext()).getId(), "4");

    }

    private void showResult() {
        if (messageAdapter == null) {
            messageAdapter = new CircleMessageAdapter(getContext(), mMessageModel);
            rvVideoMessageList.setAdapter(messageAdapter);
            messageAdapter.setCardClickListener(new CircleMessageAdapter.gotoCardListener() {
                @Override
                public void gotoCard(int position) {
                    CircleDetailActivity.start(getContext(), mMessageModel.getData().get(position).getCommunityCommentId(), "1");
                }
            });

        } else {
            rvVideoMessageList.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initTopBar() {
        initTitle("圈子提醒");
        initBack();
        initRightText("清空");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TODO clear

                getNetWorkDeleteMessage("4");
            }
        });
    }

    @Override
    protected Context getContext() {
        return this;
    }


    private void getNetWork(String customerId, String type) {
        LogUtil.e(TAG, customerId + "---" + type);
        subscription = Network.getYeapaoApi()
                .requestCircleMessage(customerId, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<CircleMessageModel> modelObserver = new Observer<CircleMessageModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());
        }

        @Override
        public void onNext(CircleMessageModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                mMessageModel = model;
                showResult();
            }
        }

    };

    private void getNetWorkDeleteMessage(String type) {
        LogUtil.e(TAG, type);
        subscription = Network.getYeapaoApi()
                .requsetDeleteMessage(GlobalDataYepao.getUser(getContext()).getId(), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverDeleteMessage);
    }

    Observer<NormalDataModel> modelObserverDeleteMessage = new Observer<NormalDataModel>() {
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
                initView();
            }
        }
    };
}
