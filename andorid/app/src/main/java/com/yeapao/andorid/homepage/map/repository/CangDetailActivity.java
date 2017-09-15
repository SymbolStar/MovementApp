package com.yeapao.andorid.homepage.map.repository;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.SelectActualTimeModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/9/14.
 */

public class CangDetailActivity extends BaseActivity {

    private static final String TAG = "CangDetailActivity";
    @BindView(R.id.tv_reservation_cang_sure)
    TextView tvReservationCangSure;
    @BindView(R.id.tv_cang_id)
    TextView tvCangId;
    @BindView(R.id.tv_cang_status)
    TextView tvCangStatus;
    @BindView(R.id.tv_reservation_cang_price)
    TextView tvReservationCangPrice;


    private String deviceNo;
    private String type;

    private SelectActualTimeModel mCangDetail = new SelectActualTimeModel();


    public static void start(Context context, String deviceId, String type) {
        Intent intent = new Intent();
        intent.putExtra("deviceNo", deviceId);
        intent.putExtra("type", type);
        intent.setClass(context, CangDetailActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cang_detail);
        ButterKnife.bind(this);
        initTopBar();
        Intent intent = getIntent();
        deviceNo = intent.getStringExtra("deviceNo");
        type = intent.getStringExtra("type");
        getNetWork(deviceNo, GlobalDataYepao.getUser(getContext()).getId(), type);
    }

    @Override
    protected void initTopBar() {
        initTitle("开始使用");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private void getNetWork(String deviceNo, String customerId, String type) {

        LogUtil.e(TAG, deviceNo + customerId + type);
        subscription = Network.getYeapaoApi()
                .requestSelectActualTime(deviceNo, customerId, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<SelectActualTimeModel> modelObserver = new Observer<SelectActualTimeModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(SelectActualTimeModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                mCangDetail = model;
                initView();
            }
        }
    };

    private void initView() {
        tvCangId.setText(mCangDetail.getData().getWarehouseName());
        if (mCangDetail.getData().getStatus().equals("1")) {
            tvCangStatus.setText(getContext().getResources().getString(R.string.reservation_cang_status));
        } else if (mCangDetail.getData().getStatus().equals("2")) {
            tvCangStatus.setText(getContext().getResources().getString(R.string.reservation_cang_status1));
        } else if (mCangDetail.getData().getStatus().equals("3")) {
            tvCangStatus.setText(getContext().getResources().getString(R.string.reservation_cang_status2));
        } else if (mCangDetail.getData().getStatus().equals("4")) {
            tvCangStatus.setText(getContext().getResources().getString(R.string.reservation_cang_status3));
        } else {

        }
        tvReservationCangPrice.setText(mCangDetail.getData().getPrice() + "/1分钟");

    }

    @OnClick(R.id.tv_reservation_cang_sure)
    public void onViewClicked() {
        DialogUtils.showProgressDialog(getContext());
        requestOpenDoor(deviceNo, GlobalDataYepao.getUser(getContext()).getId(), type);

    }


    private void requestOpenDoor(String deviceNo, String customerId, String type) {
        LogUtil.e(TAG, deviceNo + customerId + type);
        subscription = Network.getYeapaoApi()
                .requestOpenDoor(deviceNo, customerId, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(DoorModelObserver);
    }

    Observer<NormalDataModel> DoorModelObserver = new Observer<NormalDataModel>() {
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

}
