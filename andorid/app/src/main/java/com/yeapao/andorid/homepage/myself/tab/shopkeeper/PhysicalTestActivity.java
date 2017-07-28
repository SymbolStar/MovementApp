package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/7/28.
 */

public class PhysicalTestActivity extends BaseActivity {

    private static final String TAG = "PhysicalTestActivity";


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PhysicalTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initBack();
        initTitle("体测第一节（共四节）");
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
