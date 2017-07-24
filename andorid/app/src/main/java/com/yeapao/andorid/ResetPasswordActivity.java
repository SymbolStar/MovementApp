package com.yeapao.andorid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/7/24.
 */

public class ResetPasswordActivity extends BaseActivity{




    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ResetPasswordActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("重置密码");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
