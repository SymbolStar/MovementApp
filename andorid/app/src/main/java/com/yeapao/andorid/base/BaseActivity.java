package com.yeapao.andorid.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;

import rx.Subscription;

/**
 * Created by fujindong on 2017/7/14.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private ImageView ivLeftImage;
    protected ImageView ivRightImage;
    private TextView tvTitleText;
    private TextView tvRightTextView;

    protected abstract void initTopBar();

    protected abstract Context getContext();

    protected Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//初始隐藏键盘
//        initTab();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }

    private void initTab() {
        ivLeftImage = (ImageView) findViewById(R.id.iv_left);
        ivRightImage = (ImageView) findViewById(R.id.iv_right);

    }

    protected void initBack() {

        ivLeftImage = (ImageView) findViewById(R.id.iv_left);
        ivLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏软键盘
                View view = getWindow().peekDecorView();
                if (view != null) {
                    InputMethodManager inputmanger = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                LogUtil.e("initBack","initBack");
                finish();
            }
        });


    }

    /**
     * 初始化tab 右边的图标 点击事件等在上层的activity中设置 imageview类型为protected
     * @param drawable
     */
    protected void initRightIcon(Drawable drawable) {
        tvRightTextView = (TextView) findViewById(R.id.tv_right);
        tvRightTextView.setVisibility(View.INVISIBLE);
        ivRightImage = (ImageView) findViewById(R.id.iv_right);
        ivRightImage.setImageDrawable(drawable);
        ivRightImage.setVisibility(View.VISIBLE);
    }



    protected void initTitle(String title) {
        tvTitleText = (TextView) findViewById(R.id.tv_order_title);
        tvTitleText.setText(title);
    }


    protected void initRightText(String content) {
        ivRightImage = (ImageView) findViewById(R.id.iv_right);
        ivRightImage.setVisibility(View.INVISIBLE);
        tvRightTextView = (TextView) findViewById(R.id.tv_right);
        tvRightTextView.setText(content);
        tvRightTextView.setVisibility(View.VISIBLE);
    }
}
