package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.OrderDetailModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/24.
 */

public class MyselfOrderDetailActivity extends BaseActivity {

    private static final String TAG = "MyselfOrderDetailActivity";
    @BindView(R.id.tv_order_title)
    TextView tvTitle;
    @BindView(R.id.tv_order_code)
    TextView tvOrderCode;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.tv_delete_order)
    TextView tvDeleteOrder;
    @BindView(R.id.iv_pay_type)
    ImageView ivPayType;
    private OrderDetailModel orderDetailModel;
    private String orderId;


    public static void start(Context context, String orderId) {
        Intent intent = new Intent();
        intent.putExtra("orderId", orderId);
        intent.setClass(context, MyselfOrderDetailActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_order_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        initTopBar();
        getNetWork(orderId);

    }

    private void initView() {
        tvTitle.setText(orderDetailModel.getData().getCurriculumName());
        tvEndDate.setText(orderDetailModel.getData().getEndDate());
        tvOrderCode.setText(orderDetailModel.getData().getOrderCode());
        tvOrderTime.setText(orderDetailModel.getData().getStartDate());
        tvOrderPrice.setText("¥"+orderDetailModel.getData().getPrice());
        if (orderDetailModel.getData().getTypes().equals("1")) {
            ivPayType.setImageDrawable(getResources().getDrawable(R.drawable.buy_alipay));
        } else {
            ivPayType.setImageDrawable(getResources().getDrawable(R.drawable.buy_wechat));
        }

        tvDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDeleteOrder(orderId);
            }
        });

    }

    @Override
    protected void initTopBar() {
        initTitle("订单详情");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }


    private void getNetWork(String orderId) {
        LogUtil.e(TAG, orderId);
        subscription = Network.getYeapaoApi()
                .requestOrderDetail(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<OrderDetailModel> modelObserver = new Observer<OrderDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());
        }

        @Override
        public void onNext(OrderDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                orderDetailModel = model;
                initView();
            }
        }
    };




    private void requestDeleteOrder(String id) {
        LogUtil.e(TAG,id);
        subscription = Network.getYeapaoApi()
                .requestDeleteOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverDeleteOrder );
    }

    Observer<NormalDataModel> modelObserverDeleteOrder = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                finish();
            }
        }
    };
}
