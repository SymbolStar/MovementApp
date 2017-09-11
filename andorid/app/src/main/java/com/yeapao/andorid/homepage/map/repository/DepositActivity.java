package com.yeapao.andorid.homepage.map.repository;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/9/11.
 */

public class DepositActivity extends BaseActivity {


    private static final String TAG = "DepositActivity";
    @BindView(R.id.cb_wechat_pay)
    CheckBox cbWechatPay;
    @BindView(R.id.cb_alipay)
    CheckBox cbAlipay;
    @BindView(R.id.tv_pay)
    TextView tvPay;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DepositActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        ButterKnife.bind(this);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("押金支付");
        initBack();
        initRightText("退款说明");
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.cb_wechat_pay, R.id.cb_alipay, R.id.tv_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_wechat_pay:
                if (cbWechatPay.isChecked()) {
                    cbAlipay.setChecked(false);
                }
                break;
            case R.id.cb_alipay:
                if (cbAlipay.isChecked()) {
                    cbWechatPay.setChecked(false);
                }
                break;
            case R.id.tv_pay:
                depositPay();
                break;
        }
    }

    private void depositPay() {

        if (cbAlipay.isChecked() || cbWechatPay.isChecked()) {
            if (cbAlipay.isChecked()) {
                LogUtil.e(TAG,"alipay");
            } else {

            }

        } else {
            ToastManager.showToast(getContext(),"请选择支付方式");
            return;
        }



    }
}
