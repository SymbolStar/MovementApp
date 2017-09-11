package com.yeapao.andorid.homepage.map.repository;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/9/11.
 */

public class DepositActivity extends BaseActivity {


    private static final String TAG = "DepositActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
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
}
