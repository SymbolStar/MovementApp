package com.yeapao.andorid.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;

import org.w3c.dom.Text;

/**
 * Created by fujindong on 2017/7/14.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private ImageView ivLeftImage;
    protected ImageView ivRightImage;
    private TextView tvTitleText;

    protected abstract void initTopBar();

    protected abstract Context getContext();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initTab();
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
        ivRightImage = (ImageView) findViewById(R.id.iv_right);
        ivRightImage.setImageDrawable(drawable);
        ivRightImage.setVisibility(View.VISIBLE);
    }



    protected void initTitle(String title) {
        tvTitleText = (TextView) findViewById(R.id.tv_title);
        tvTitleText.setText(title);
    }

}
