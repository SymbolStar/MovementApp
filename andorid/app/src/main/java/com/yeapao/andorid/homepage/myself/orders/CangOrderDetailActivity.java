package com.yeapao.andorid.homepage.myself.orders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.ActualOrderDetailModel;
import com.yeapao.andorid.model.NormalDataModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/9/20.
 * 舱的使用订单  预约订单  使用同一个页面
 */

public class CangOrderDetailActivity extends BaseActivity {

    private static final String TAG = "CangOrderDetailActivity";
    public static final String CangOrder = "CangOrder";
    public static final String CangReservation = "CangReservation";
    @BindView(R.id.tv_cang_order_1)
    TextView tvCangOrder1;
    @BindView(R.id.tv_cang_order_2)
    TextView tvCangOrder2;
    @BindView(R.id.tv_cang_order_3)
    TextView tvCangOrder3;
    @BindView(R.id.tv_cang_order_4)
    TextView tvCangOrder4;
    @BindView(R.id.tv_cang_order_5)
    TextView tvCangOrder5;
    @BindView(R.id.tv_cang_order_pay)
    TextView tvCangOrderPay;
    @BindView(R.id.iv_cang_order_6)
    ImageView ivCangOrder6;
    @BindView(R.id.tv_pay_way)
    TextView tvPayWay;
    @BindView(R.id.iv_line_6)
    ImageView ivLinePayWay;
    @BindView(R.id.tv_cang_order_title)
    TextView tvCangOrderTitle;
    @BindView(R.id.tv_cang_order_time)
    TextView tvCangOrderTime;

    private String mActualOrderId;

    private ActualOrderDetailModel mActualModel = new ActualOrderDetailModel();

    private String type;

    public static void start(Context context, String actualOrderId, String type) {
        Intent intent = new Intent();
        intent.putExtra("actualOrderId", actualOrderId);
        intent.putExtra("type", type);
        intent.setClass(context, CangOrderDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cang_order_detail);
        ButterKnife.bind(this);
        initTopBar();
        Intent intent = getIntent();
        mActualOrderId = intent.getStringExtra("actualOrderId");
        type = intent.getStringExtra("type");
        if (type.equals(CangOrder)) {
            getNetWork(mActualOrderId);
        } else {
            getNetWorkForCangReservationOrder(mActualOrderId);
        }


    }

    private void initView() {
        tvCangOrderTitle.setText("健身舱使用");
        tvCangOrderTime.setText(getContext().getResources().getString(R.string.cang_pay1));
        tvCangOrder1.setText(mActualModel.getData().getActualOrdersCode());
        tvCangOrder2.setText(mActualModel.getData().getStartDate());
        tvCangOrder3.setText(String.valueOf(mActualModel.getData().getDuration()) + "分钟");
        tvCangOrder4.setText(mActualModel.getData().getWarehouseName());
        tvCangOrder5.setText(getContext().getResources().getString(R.string.RMB) + String.valueOf(mActualModel.getData().getPrice()));
        if (mActualModel.getData().getTypes() == null) {
            tvCangOrderPay.setVisibility(View.GONE);
            ivCangOrder6.setVisibility(View.GONE);
            tvCangOrderPay.setText("继续支付");
            tvCangOrderPay.setTextColor(getContext().getResources().getColor(R.color.yellow_text_color));
            tvCangOrderPay.setBackground(getContext().getResources().getDrawable(R.drawable.my_order_button_shape));
            tvCangOrderPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CangOrderPayActivity.start(getContext(),String.valueOf(mActualModel.getData().getActualOrdersId()));
                }
            });
        } else if (mActualModel.getData().getTypes().equals("1")) {
            tvCangOrderPay.setVisibility(View.VISIBLE);
            ivCangOrder6.setVisibility(View.VISIBLE);
            ivCangOrder6.setImageDrawable(getContext().getDrawable(R.drawable.buy_alipay));
            tvCangOrderPay.setText("删除订单");
            tvCangOrderPay.setTextColor(getContext().getResources().getColor(R.color.text_hint_color));
            tvCangOrderPay.setBackground(getContext().getResources().getDrawable(R.drawable.my_order_button_n_shape));
            tvCangOrderPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getNetWorkDeleteCangOrder(String.valueOf(mActualModel.getData().getActualOrdersId()));
                }
            });
        } else {
            tvCangOrderPay.setVisibility(View.VISIBLE);
            ivCangOrder6.setVisibility(View.VISIBLE);
            ivCangOrder6.setImageDrawable(getContext().getDrawable(R.drawable.buy_wechat));
            tvCangOrderPay.setText("删除订单");
            tvCangOrderPay.setTextColor(getContext().getResources().getColor(R.color.text_hint_color));
            tvCangOrderPay.setBackground(getContext().getResources().getDrawable(R.drawable.my_order_button_n_shape));
            tvCangOrderPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getNetWorkDeleteCangOrder(String.valueOf(mActualModel.getData().getActualOrdersId()));
                }
            });
        }
    }


    private void initViewV2() {
        tvCangOrderTitle.setText("健身舱预约");
        tvCangOrderTime.setText(getContext().getResources().getString(R.string.cang_reservation_pay1));
        tvCangOrder1.setText(mActualModel.getData().getReservaOrdersCode());
        tvCangOrder2.setText(mActualModel.getData().getStartDate());
        tvCangOrder3.setText(String.valueOf(mActualModel.getData().getDuration()) + "分钟");
        tvCangOrder4.setText(mActualModel.getData().getWarehouseName());
        tvCangOrder5.setText(getContext().getResources().getString(R.string.RMB) + String.valueOf(mActualModel.getData().getPrice()));
        if (mActualModel.getData().getTypes() == null) {
            tvCangOrderPay.setVisibility(View.GONE);
            ivCangOrder6.setVisibility(View.GONE);
            tvCangOrderPay.setText("继续支付");
            tvCangOrderPay.setTextColor(getContext().getResources().getColor(R.color.yellow_text_color));
            tvCangOrderPay.setBackground(getContext().getResources().getDrawable(R.drawable.my_order_button_shape));
        } else if (mActualModel.getData().getTypes().equals("1")) {
            tvCangOrderPay.setVisibility(View.VISIBLE);
            ivCangOrder6.setVisibility(View.VISIBLE);
            ivCangOrder6.setImageDrawable(getContext().getDrawable(R.drawable.buy_alipay));
            tvCangOrderPay.setText("删除订单");
            tvCangOrderPay.setTextColor(getContext().getResources().getColor(R.color.text_hint_color));
            tvCangOrderPay.setBackground(getContext().getResources().getDrawable(R.drawable.my_order_button_n_shape));
        } else {
            tvCangOrderPay.setVisibility(View.VISIBLE);
            ivCangOrder6.setVisibility(View.VISIBLE);
            ivCangOrder6.setImageDrawable(getContext().getDrawable(R.drawable.buy_wechat));
            tvCangOrderPay.setText("删除订单");
            tvCangOrderPay.setTextColor(getContext().getResources().getColor(R.color.text_hint_color));
            tvCangOrderPay.setBackground(getContext().getResources().getDrawable(R.drawable.my_order_button_n_shape));
        }

        tvCangOrderPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNetWorkDeleteReservationOrders(mActualModel.getData().getReservaOrdersId());
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

    private void getNetWork(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestActualOrderDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<ActualOrderDetailModel> modelObserver = new Observer<ActualOrderDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(ActualOrderDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                mActualModel = model;
                initView();
            }
        }
    };

    @OnClick(R.id.tv_cang_order_pay)
    public void onViewClicked() {
    }


    private void getNetWorkForCangReservationOrder(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestCangReservationDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverCangReservation);
    }

    Observer<ActualOrderDetailModel> modelObserverCangReservation = new Observer<ActualOrderDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(ActualOrderDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                mActualModel = model;
                initViewV2();
            }
        }
    };

//舱预约订单删除
    private void getNetWorkDeleteReservationOrders(String id) {
        LogUtil.e(TAG,id);
        subscription = Network.getYeapaoApi()
                .requestDeleteCangReservationOrders(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( modelObserverDeleteCangReservationOrders);
    }

    Observer<NormalDataModel> modelObserverDeleteCangReservationOrders = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {
            DialogUtils.cancelProgressDialog();
            finish();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());
            DialogUtils.cancelProgressDialog();
        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                DialogUtils.cancelProgressDialog();
            }
        }
    };

//    舱运动订单删除
    private void getNetWorkDeleteCangOrder(String id) {
        LogUtil.e(TAG,id);
        subscription = Network.getYeapaoApi()
                .requestDeleteActualOrders(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( modelObserverDeleteCangOrder);
    }

    Observer<NormalDataModel> modelObserverDeleteCangOrder = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {
            DialogUtils.cancelProgressDialog();
            finish();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());
            DialogUtils.cancelProgressDialog();
        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                DialogUtils.cancelProgressDialog();
            }
        }
    };

}
