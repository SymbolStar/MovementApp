package com.yeapao.andorid.homepage.map.repository;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.scottfu.sflibrary.alipay.AuthResult;
import com.scottfu.sflibrary.alipay.PayResult;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.ActialOrderDetailModel;
import com.yeapao.andorid.model.CallPaymentModel;
import com.yeapao.andorid.model.CangDeviceNoData;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.util.Map;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/9/16.
 */

public class SportFinishActivity extends BaseActivity {

    private static final String TAG = "SportFinishActivity";
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_fit_pay_price)
    TextView tvFitPayPrice;
    @BindView(R.id.tv_fit_time)
    TextView tvFitTime;
    @BindView(R.id.tv_fit_order_code)
    TextView tvFitOrderCode;
    @BindView(R.id.tv_fit_cang_id)
    TextView tvFitCangId;
    @BindView(R.id.cb_wechat_pay)
    CheckBox cbWechatPay;
    @BindView(R.id.cb_alipay)
    CheckBox cbAlipay;


    private ActialOrderDetailModel actialOrderDetailModel = new ActialOrderDetailModel();
    private String payMentType;
    private String actualOrderId;
    private String totalTime;


    //    支付
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private MessageSendReceiver numberReceiver;
    IWXAPI api;


    public static void start(Context context, String actualOrdersId, String totalTime) {
        Intent intent = new Intent();
        intent.putExtra("actualOrderId", actualOrdersId);
        intent.putExtra("totalTime", totalTime);
        intent.setClass(context, SportFinishActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_pay);
        ButterKnife.bind(this);
        initTopBar();

        Intent intent = getIntent();
        actualOrderId = intent.getStringExtra("actualOrderId");
        totalTime = intent.getStringExtra("totalTime");

        CangDeviceNoData cangDevice = new CangDeviceNoData();
        cangDevice = GlobalDataYepao.getCangDeviceData(getContext());

        getNetWork(actualOrderId,totalTime,cangDevice.getDeviceNo());

        if (numberReceiver == null) {
            numberReceiver = new MessageSendReceiver();
        }
        getContext().registerReceiver(numberReceiver, new IntentFilter("wxPay.action"));
        api = WXAPIFactory.createWXAPI(getContext(), ConstantYeaPao.APP_ID, true);
        api.registerApp(ConstantYeaPao.APP_ID);

    }

    @Override
    protected void initTopBar() {
        initTitle("支付");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private void getNetWork(String actualOrdersId, String totalTime,String deviceNo) {
        LogUtil.e(TAG, actualOrdersId + "___" + totalTime);
        subscription = Network.getYeapaoApi()
                .requestActualOrderDetail(actualOrdersId, totalTime, GlobalDataYepao.getUser(getContext()).getId(),deviceNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<ActialOrderDetailModel> modelObserver = new Observer<ActialOrderDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(ActialOrderDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                actialOrderDetailModel = model;
                initView();
            }
        }
    };

    private void initView() {
        tvFitPayPrice.setText(getContext().getResources().getString(R.string.RMB)+actialOrderDetailModel.getData().getPrice());
        tvFitTime.setText(actialOrderDetailModel.getData().getTime()+"min");
        tvFitOrderCode.setText(actialOrderDetailModel.getData().getActualOrdersCode());
        tvFitCangId.setText(actialOrderDetailModel.getData().getWarehouseName());
    }

    @OnClick({R.id.tv_pay, R.id.cb_wechat_pay, R.id.cb_alipay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pay:
                if (cbWechatPay.isChecked() || cbAlipay.isChecked()) {
                    getPayment(actialOrderDetailModel.getData().getPrice(), actialOrderDetailModel.getData().getActualOrdersCode(), payMentType);
                } else {
                    ToastManager.showToast(getContext(),"请选择支付方式");
                }

                break;
            case R.id.cb_wechat_pay:
                if (cbWechatPay.isChecked()) {
                    cbAlipay.setChecked(false);
                    payMentType = "2";
                }
                break;
            case R.id.cb_alipay:
                if (cbAlipay.isChecked()) {
                    cbWechatPay.setChecked(false);
                    payMentType = "1";
                }
                break;
        }
    }









    private void getPayment(String price, String orderCode, String paymentType) {

        LogUtil.e(TAG, price+"==="+orderCode+"+++"+paymentType);
        subscription = Network.getYeapaoApi()
                .requestPayment(price,orderCode,paymentType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverPayment);
    }

    Observer<CallPaymentModel> modelObserverPayment = new Observer<CallPaymentModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());

        }

        @Override
        public void onNext(CallPaymentModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {

                if (payMentType.equals("1")) {


//                            final String orderInfo = model.getData().getAliPayInfo();   // 订单信息
//                            LogUtil.e("orderInfo",orderInfo);

//
//                            OrderCommitted result = new OrderCommitted();
//                            result.setOrderID(model.getData().getOrderCode());
////                            result.setOrderName("课程订单");
////                            String mmm = String.format("%.2f", content.getAmount());
//                            result.setFinalPrice(model.getData().getPrice());
////                            result.setNameList("111");
//
//                            Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(ConstantYeaPao.APPID, true,result);
//                            String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
//                            LogUtil.e("+++++++", orderParam);
//                            String privateKey = ConstantYeaPao.RSA2_PRIVATE;
//                            String sign = OrderInfoUtil2_0.getSign(params, privateKey, true);
//                            final String orderInfo = orderParam + "&" + sign;
                    final String orderInfo = model.getData().getAliPayInfo();

                    LogUtil.e("+++++++", orderInfo);

                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(SportFinishActivity.this);
                            Map<String, String> result = alipay.payV2(orderInfo,true);
                            Log.e("msp", result.toString());
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                }else{

                    PayReq request = new PayReq();
                    request.appId = ConstantYeaPao.APP_ID;
                    request.partnerId = "1486707182";
                    request.prepayId = model.getData().getPrepayid();
                    request.nonceStr = model.getData().getNoncestr();
                    request.timeStamp = model.getData().getTimeStamp();
                    request.packageValue = "Sign=WXPay";
                    request.sign = model.getData().getWxPayReq();
                    api.sendReq(request);

                }



            }
        }
    };



    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        ((Activity)getContext()).finish();
                        PaySuccessActivity.start(getContext(),totalTime);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getContext(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(getContext(),
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(getContext(),
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };








    class MessageSendReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("wxPay.action")) {
                ((Activity)getContext()).finish();
                PaySuccessActivity.start(getContext(), totalTime);
            }
        }
    }

}
