package com.yeapao.andorid.homepage.shopping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieRadarChartBase;
import com.scottfu.sflibrary.alipay.AlipayManager;
import com.scottfu.sflibrary.alipay.OrderCommitted;
import com.scottfu.sflibrary.alipay.PayResult;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.CallPaymentModel;
import com.yeapao.andorid.model.LessonOrderModel;

import org.w3c.dom.Text;

import java.security.PrivateKey;

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

    private LessonOrderModel lessonOrderModel;
    private String payMentType;


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

                            OrderCommitted result = new OrderCommitted();
                            result.setOrderID(model.getData().getOrderCode());
                            result.setOrderName("yepao");
                            String mmm = model.getData().getPrice();
                            result.setFinalPrice(mmm);
                            result.setNameList("yepao");
                            AlipayManager.payOrder(result,"http://47.92.113.97:8080/yepao/order/alipay",(Activity)getContext(),handler);
//                            AlipayManager.payOrderV2(result,model.getData().getAliPayInfo(),(Activity)getContext(),handler);
                        }
                    }
                };




    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            PayResult payResult = new PayResult((String) msg.obj);
            String resultInfo = payResult.getResult();
            String resultStatus = payResult.getResultStatus();
            if(TextUtils.equals(resultStatus, "9000")){
                Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                ((Activity)getContext()).finish();
            }else {
                if(TextUtils.equals(resultStatus, "8000")){
                    Toast.makeText(getContext(), "支付结果确认中", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
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
                        }
                    }
                };


}
