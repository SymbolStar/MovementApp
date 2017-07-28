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

public class MyselfFeedBackActivity extends BaseActivity {

    private static final String TAG = "MyselfFeedBackActivity";


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfFeedBackActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initTopBar();
        initView();

    }

    private void initView() {

    }

    @Override
    protected void initTopBar() {
        initTitle("建议反馈");
        initBack();
        initRightText("发送");
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
