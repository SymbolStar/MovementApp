package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/7/27.
 */

public class MyselfFoodActivity extends BaseActivity {

    private static final String TAG = "MyselfFoodActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_food);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("一周食谱");
        initBack();
    }

    @Override
    protected Context getContext() {
        return null;
    }
}
