package com.yeapao.andorid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/7/24.
 */

public class RegisterActivity extends BaseActivity {

    private static final String TAG = "RegisterActivity";


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RegisterActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("注册");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
