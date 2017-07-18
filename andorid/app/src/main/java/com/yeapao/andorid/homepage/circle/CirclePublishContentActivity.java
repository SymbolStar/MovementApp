package com.yeapao.andorid.homepage.circle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/7/17.
 */

public class CirclePublishContentActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_circle);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initBack();
        initTitle("发表主题");
        initRightText("发布");
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
