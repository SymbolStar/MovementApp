package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/7/27.
 */

public class MyselfChangePasswordActivity extends BaseActivity {

    private static final String TAG = "MyselfChangePasswordActivity";


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfChangePasswordActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("修改密码");
        initBack();
        initRightText("完成");
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
