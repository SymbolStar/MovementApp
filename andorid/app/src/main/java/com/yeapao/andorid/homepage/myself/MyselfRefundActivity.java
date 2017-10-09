package com.yeapao.andorid.homepage.myself;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/9/12.
 */

public class MyselfRefundActivity extends BaseActivity {

    private static final String TAG = "MyselfRefundActivity";

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        initTopBar();
        mWebView = (WebView) findViewById(R.id.wv_refund);
        mWebView.loadUrl("http://47.92.113.97:8080/refund/explain.html");
    }

    @Override
    protected void initTopBar() {
        initTitle("退款说明");
        initBack();

    }

    @Override
    protected Context getContext() {
        return this;
    }
}
