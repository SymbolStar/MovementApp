package com.yeapao.andorid.homepage.shopping;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/16.
 */

public class ShoppingOrderActivity extends BaseActivity {


    @BindView(R.id.cb_wechat_pay)
    CheckBox cbWechatPay;
    @BindView(R.id.cb_alipay)
    CheckBox cbAlipay;
    @BindView(R.id.btn_subnit_order)
    Button btnSubnitOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
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
                break;
            case R.id.cb_alipay:
                break;
            case R.id.btn_subnit_order:
                break;
        }
    }
}
