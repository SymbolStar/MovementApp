package com.yeapao.andorid.homepage.shopping;

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
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.scottfu.sflibrary.alipay.AuthResult;
import com.scottfu.sflibrary.alipay.OrderCommitted;
import com.scottfu.sflibrary.alipay.OrderInfoUtil2_0;
import com.scottfu.sflibrary.alipay.PayResult;
import com.scottfu.sflibrary.util.LogUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.CallPaymentModel;
import com.yeapao.andorid.model.LessonOrderModel;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/16.
 */

public class ShoppingOrderActivity extends BaseActivity {

    private static final String TAG = "ShoppingOrderActivity";

    @BindView(R.id.cb_wechat_pay)
    CheckBox cbWechatPay;
    @BindView(R.id.cb_alipay)
    CheckBox cbAlipay;
    @BindView(R.id.btn_subnit_order)
    Button btnSubnitOrder;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.tv_order_code)
    TextView tvOrderCode;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    private LessonOrderModel lessonOrderModel;
    private String payMentType;

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private MessageSendReceiver numberReceiver;
    IWXAPI api;



    public static void start(Context context, String typeId, String price, String id) {
        Intent intent = new Intent();
        intent.putExtra("typeId", typeId);
        intent.putExtra("price", price);
        intent.putExtra("id", id);
        intent.setClass(context, ShoppingOrderActivity.class);
        context.startActivity(intent);

    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String typeId = intent.getStringExtra("typeId");
        String price = intent.getStringExtra("price");
        String id = intent.getStringExtra("id");
        getNetWork(typeId,price,id);


        initTopBar();


        if (numberReceiver == null) {
            numberReceiver = new MessageSendReceiver();
        }
        getContext().registerReceiver(numberReceiver, new IntentFilter("wxPay.action"));
        api = WXAPIFactory.createWXAPI(getContext(), ConstantYeaPao.APP_ID, true);
        api.registerApp(ConstantYeaPao.APP_ID);


    }


    @Override
    protected void initTopBar() {
        initBack();
        initTitle("订单详情");
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.cb_wechat_pay, R.id.cb_alipay, R.id.btn_subnit_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_wechat_pay:
                if (cbWechatPay.isChecked()) {
                    payMentType = "2";
                    cbAlipay.setChecked(false);

                } else {
                    payMentType = "";
                }
                break;
            case R.id.cb_alipay:
                if (cbAlipay.isChecked()) {
                    payMentType = "1";
                    cbWechatPay.setChecked(false);
                } else {
                    payMentType = "";
                }
                break;
            case R.id.btn_subnit_order:
                getPayment(String.valueOf(lessonOrderModel.getData().getPrice()),lessonOrderModel.getData().getOrderCode(),payMentType);
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
                                        PayTask alipay = new PayTask(ShoppingOrderActivity.this);
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






            private void getNetWork(String typeid,String price,String id) {
                    LogUtil.e(TAG,typeid+"---"+price+"---"+id);
                subscription = Network.getYeapaoApi()
                        .requestlessonOrder(typeid, price, id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(modelObserver);
                }

                  Observer<LessonOrderModel> modelObserver = new Observer<LessonOrderModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(LessonOrderModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            lessonOrderModel = model;
                            tvOrderCode.setText(lessonOrderModel.getData().getOrderCode());
                            tvOrderName.setText(lessonOrderModel.getData().getCurriculumName());
                            String content = tvPrice.getText() + " ￥" + lessonOrderModel.getData().getPrice();
                            int start = tvPrice.getText().length();
                            int end = content.length();
                            SpannableStringBuilder stringBuilder = new SpannableStringBuilder(content);
                            stringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_red)),start,end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                            tvPrice.setText(stringBuilder);
                        }
                    }
                };



    class MessageSendReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("wxPay.action")) {
                ((Activity)getContext()).finish();
            }
        }
    }





}
