package com.yeapao.andorid.homepage.lesson;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/7/26.
 */

public class ScanQrcodeActivity extends BaseActivity {
    private static final String TAG = "ScanQrcodeActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopBar();

    }

    @Override
    protected void initTopBar() {
         initTitle("");
    }

    @Override
    protected Context getContext() {
        return null;
    }
}
