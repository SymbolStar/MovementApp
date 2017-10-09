package com.yeapao.andorid.homepage.map.repository;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.CangDeviceNoData;
import com.yeapao.andorid.model.CreateActualOrdersModel;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.SelectActualTimeModel;
import com.yeapao.andorid.util.GlobalDataYepao;
import com.yeapao.andorid.yeapaojpush.JpushMessageListener;
import com.yeapao.andorid.yeapaojpush.MyReceiver;

import java.util.Timer;
import java.util.TimerTask;

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
    private String cangDevice;

    private Timer timer = new Timer();
    private int countTime = 0;
    private boolean JpushMessageFlag = false;
    private int startFitFlag = 0;

    private SelectActualTimeModel mCangDetail = new SelectActualTimeModel();


    public static void start(Context context, String deviceId, String type,String cangDeviceId) {
        Intent intent = new Intent();
        intent.putExtra("deviceNo", deviceId);
        intent.putExtra("type", type);
        intent.putExtra("cangDevice", cangDeviceId);
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
        cangDevice = intent.getStringExtra("cangDevice");
        getNetWork(deviceNo, GlobalDataYepao.getUser(getContext()).getId(), type);

        MyReceiver.setJpushMessageListener(new JpushMessageListener() {
            @Override
            public void getMessage(String content) {
                if (content.equals("-1")) {
                    DialogUtils.cancelProgressDialog();
                    LogUtil.e(TAG+"jpushmessage",content);
                    JpushMessageFlag = true;
                    startFitFlag++;
                    if (startFitFlag == 1) {
                        LogUtil.e(TAG, deviceNo + "   " + type);
                        requestCreateCangOrdersV2(GlobalDataYepao.getUser(getContext()).getId(),mCangDetail.getData().getWarehouseId());

                    }


                }
            }
        });

    }





//
//    TimerTask task = new TimerTask() {
//        @Override
//        public void run() {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    countTime++;
//                    LogUtil.e(TAG,String.valueOf(countTime));
//                    if (JpushMessageFlag) {
//                        DialogUtils.cancelProgressDialog();
//                        timer.cancel();
//                        task.cancel();
//                        timer = null;
//                    } else if (countTime >= 10) {
//                        ToastManager.showToast(getContext(),"网络异常请稍后再试");
//                        DialogUtils.cancelProgressDialog();
//                        timer.cancel();
//                        task.cancel();
//                        timer = null;
//                    }
//                }
//            });
//        }
//    };



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
        DialogUtils.showProgressDialog(getContext(),true,true);
        countTime = 0;
        JpushMessageFlag = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!JpushMessageFlag) {
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                countTime++;
                                if (countTime >= 10) {
                                    JpushMessageFlag = true;
                                    DialogUtils.cancelProgressDialog();
                                    ToastManager.showToast(getContext(),"网络异常请稍后再试");
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }



            }
        }).start();


//      TODO 使用定时器存在一些问题  稍后写个类整理一下
//        if (timer == null) {
//            timer = new Timer();
//        }
//        timer.schedule(task,1000,1000);
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


    private void requestCreateCangOrders(String customerId, String wareHouseId, String type) {
        LogUtil.e(TAG, customerId + wareHouseId + type);
        subscription = Network.getYeapaoApi()
                .requestCreateActualOrders(customerId,wareHouseId,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createActualOrdersModelObserver);
    }
    private void requestCreateCangOrdersV2(String customerId, String wareHouseId) {
        LogUtil.e(TAG, customerId + wareHouseId );
        subscription = Network.getYeapaoApi()
                .requestCreateActualOrdersV2(customerId,wareHouseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createActualOrdersModelObserver);
    }

                  Observer<CreateActualOrdersModel> createActualOrdersModelObserver = new Observer<CreateActualOrdersModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(CreateActualOrdersModel model) {
                        LogUtil.e(TAG+"createActualOrdersModelObserver", model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            CangDeviceNoData cangDeviceNoData = new CangDeviceNoData();
                            cangDeviceNoData.setDeviceNo(cangDevice);
                            cangDeviceNoData.setId(model.getData().getActualOrdersId());
                            cangDeviceNoData.setStartTime(model.getData().getStartDate());
                            GlobalDataYepao.setCangDeviceData(getContext(),cangDeviceNoData);
                            StartSportActivity.start(getContext(),model.getData().getActualOrdersId(),model.getData().getStartDate());
                            finish();
                        }
                    }
                };


}
