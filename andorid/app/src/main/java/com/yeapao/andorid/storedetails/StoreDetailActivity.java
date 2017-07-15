package com.yeapao.andorid.storedetails;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/7/13.
 */

public class StoreDetailActivity extends BaseActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        initTopBar();
    }


    @Override
    protected void initTopBar() {
        initBack();
        initTitle("商店详情");
        initRightIcon(getResources().getDrawable(R.drawable.shop_share));
        ivRightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastManager.showToast(getContext(),"share");
            }
        });
    }

    @Override
    protected Context getContext() {
        return this;
    }



}
