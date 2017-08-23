package com.yeapao.andorid.homepage.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.homepage.myself.tab.MyselfReservationActivity;
import com.yeapao.andorid.model.PunchTheClockModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/8/20.
 */

public class ReservationActivity extends BaseActivity {

    private static final String TAG = "ReservationActivity";
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_reservation_message_list)
    RecyclerView rvReservationMessageList;

    private ReservationMessageAdapter messageAdapter;


    private PunchTheClockModel punchTheClockModel;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ReservationActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_card);
        ButterKnife.bind(this);
        initTopBar();
        initView();

    }

    private void initView() {
        rvReservationMessageList.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();
    }

    private void getData() {
//        TODO getnetwork
        getNetWork(GlobalDataYepao.getUser(getContext()).getId(),"2");
    }

    private void showResult() {
        if (messageAdapter == null) {
            messageAdapter = new ReservationMessageAdapter(getContext(),punchTheClockModel);
            rvReservationMessageList.setAdapter(messageAdapter);
            messageAdapter.setCardClickListener(new ReservationMessageAdapter.gotoCardListener() {
                @Override
                public void gotoCard() {
                    ToastManager.showToast(getContext(), "onClick");
                    MyselfReservationActivity.start(getContext());
                }
            });
        } else {
            rvReservationMessageList.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initTopBar() {
        initTitle("预约提醒");
        initBack();
        initRightText("清空");
    }

    @Override
    protected Context getContext() {
        return this;
    }


    private void getNetWork(String customerId, String type) {
        LogUtil.e(TAG, customerId + "---" + type);
        subscription = Network.getYeapaoApi()
                .requestPunchTheClock(customerId, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<PunchTheClockModel> modelObserver = new Observer<PunchTheClockModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(PunchTheClockModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                punchTheClockModel = model;
                showResult();
            }
        }

    };



}
