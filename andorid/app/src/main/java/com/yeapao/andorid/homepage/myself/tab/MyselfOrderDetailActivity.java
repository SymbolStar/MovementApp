package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/7/24.
 */

public class MyselfOrderDetailActivity extends BaseActivity {

    private static final String TAG = "MyselfOrderDetailActivity";



    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfOrderDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_order_detail);
        initTopBar();

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
}
