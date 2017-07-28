package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/27.
 */

public class MyselfShopOwnerActivity extends BaseActivity {

    private static final String TAG = "MyselfShopOwnerActivity";
    @BindView(R.id.rl_shopkeeper_edit)
    RelativeLayout rlShopkeeperEdit;
    @BindView(R.id.rl_shopkeeper_lesson)
    RelativeLayout rlShopkeeperLesson;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfShopOwnerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_shop_owner);
        ButterKnife.bind(this);
        initTopBar();
        initView();

    }

    private void initView() {

    }

    @Override
    protected void initTopBar() {
        initTitle("我是店长");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.rl_shopkeeper_edit, R.id.rl_shopkeeper_lesson})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_shopkeeper_edit:
                break;
            case R.id.rl_shopkeeper_lesson:
                ShopKeeperLessonReservationActivity.start(getContext());

                break;
        }
    }
}
