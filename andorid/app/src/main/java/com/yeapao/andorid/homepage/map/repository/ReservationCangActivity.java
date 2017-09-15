package com.yeapao.andorid.homepage.map.repository;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.CreateReservationTimeModel;
import com.yeapao.andorid.model.SelectReservationTimeModel;
import com.yeapao.andorid.util.CangReservationPickerDialog;
import com.yeapao.andorid.util.GlobalDataYepao;
import com.yeapao.andorid.util.PickerPainListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/9/12.
 */

public class ReservationCangActivity extends BaseActivity {

    private static final String TAG = "ReservationCangActivity";
    @BindView(R.id.tv_reservation_cang_sure)
    TextView tvReservationCangSure;
    @BindView(R.id.tv_reservation_time)
    TextView tvReservationTime;
    @BindView(R.id.tv_cang_status)
    TextView tvCangStatus;
    @BindView(R.id.tv_reservation_cang_price)
    TextView tvReservationCangPrice;
    @BindView(R.id.tv_cang_id)
    TextView tvCangId;

    private String warehouseId;
    private SelectReservationTimeModel selectReservationTimeModel = new SelectReservationTimeModel();
    private CangReservationPickerDialog cangReservationPickerDialog;
    private String reservationTime = "";
    private CreateReservationTimeModel createReservationTimeModel = new CreateReservationTimeModel();

    public static void start(Context context, String warehouseId) {
        Intent intent = new Intent();
        intent.putExtra("warehouseId", warehouseId);
        LogUtil.e(TAG, warehouseId);
        intent.setClass(context, ReservationCangActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cang_reservation);
        ButterKnife.bind(this);
        initTopBar();
        Intent intent = getIntent();
        warehouseId = intent.getStringExtra("warehouseId");
        LogUtil.e(TAG, warehouseId);
        getNetWork(warehouseId);

    }

    private void initView() {
        tvCangId.setText(selectReservationTimeModel.getData().getWarehouseName());
        if (selectReservationTimeModel.getData().getStatus().equals("1")) {
            tvCangStatus.setText(getContext().getResources().getString(R.string.reservation_cang_status));
        } else if (selectReservationTimeModel.getData().getStatus().equals("2")) {
            tvCangStatus.setText(getContext().getResources().getString(R.string.reservation_cang_status1));
        } else if (selectReservationTimeModel.getData().getStatus().equals("3")) {
            tvCangStatus.setText(getContext().getResources().getString(R.string.reservation_cang_status2));
        } else if (selectReservationTimeModel.getData().getStatus().equals("4")) {
            tvCangStatus.setText(getContext().getResources().getString(R.string.reservation_cang_status3));
        } else {

        }
        tvReservationCangPrice.setText(selectReservationTimeModel.getData().getPrice() + "元/5分钟");

        cangReservationPickerDialog = new CangReservationPickerDialog();
        cangReservationPickerDialog.setPickerPainListener(new PickerPainListener() {
            @Override
            public void getPainValue(String value) {

                reservationTime = value;
            }

            @Override
            public void cancel() {
                reservationTime = "";
                cangReservationPickerDialog.dismiss();
            }

            @Override
            public void determine() {
                if (reservationTime == null || reservationTime.equals("")) {
                    reservationTime = "5";
                }
                tvReservationTime.setText(reservationTime);
                cangReservationPickerDialog.dismiss();
            }
        });
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

    @OnClick({R.id.tv_reservation_cang_sure, R.id.tv_reservation_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reservation_cang_sure:
                String time = tvReservationTime.getText().toString();
                if (time == null || time.equals("")||time.length()>3) {
                    ToastManager.showToast(getContext(),"请选择预约时长");
                    return;
                } else {
                    DialogUtils.showProgressDialog(getContext());
                    requestReservationTime(selectReservationTimeModel.getData().getWarehouseId(), GlobalDataYepao.getUser(getContext()).getId(),
                            time,selectReservationTimeModel.getData().getWarehouseName());
                }

                break;
            case R.id.tv_reservation_time:
                if (cangReservationPickerDialog.isVisible()) {
                    cangReservationPickerDialog.dismiss();
                } else {
                    cangReservationPickerDialog.show(getSupportFragmentManager(), "time");
                }
                break;
        }
    }


    private void getNetWork(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestReservationTime(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<SelectReservationTimeModel> modelObserver = new Observer<SelectReservationTimeModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(SelectReservationTimeModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                selectReservationTimeModel = model;
                initView();
            }
        }
    };


    private void requestReservationTime(String warehouseId, String customerId, String time, String warehouseName) {

        LogUtil.e(TAG, warehouseId + customerId + time + warehouseName);
        subscription = Network.getYeapaoApi()
                .requestCreateReservationTime(warehouseId, customerId, time, warehouseName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createReservationTimeModelObserver);
    }

    Observer<CreateReservationTimeModel> createReservationTimeModelObserver = new Observer<CreateReservationTimeModel>() {
        @Override
        public void onCompleted() {
                DialogUtils.cancelProgressDialog();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());
            DialogUtils.cancelProgressDialog();

        }

        @Override
        public void onNext(CreateReservationTimeModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                createReservationTimeModel = model;
                DialogUtils.cancelProgressDialog();
                ReservationCangPayActivity.start(getContext(),String.valueOf(createReservationTimeModel.getData().getPrice()),String.valueOf(createReservationTimeModel.getData().getTime()),
                        createReservationTimeModel.getData().getReservaTimeOrderCode(),createReservationTimeModel.getData().getWarehouseName());
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            finish();
        } else {

        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
