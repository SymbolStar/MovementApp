package com.yeapao.andorid.homepage.myself.tab.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/8/20.
 */

public class YeaPaoInfoActivity extends BaseActivity {

    private static final String TAG = "YeaPaoInfoActivity";


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, YeaPaoInfoActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeapao_info);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("关于我们");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
