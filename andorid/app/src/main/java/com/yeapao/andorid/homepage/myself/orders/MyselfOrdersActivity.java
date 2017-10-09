package com.yeapao.andorid.homepage.myself.orders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/9/18.
 */

public class MyselfOrdersActivity extends BaseActivity {


    private static final String TAG = "MyselfOrdersActivity";

    private TabLayout tabLayout;
    private CangOrderFragment cangOrderFragment;
    private CangReservationFragment cangReservationFragment;
    private OrderPagerAdapter orderPagerAdapter;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfOrdersActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_order);
        initTopBar();
        if (savedInstanceState != null) {
            FragmentManager manager = getSupportFragmentManager();
            cangOrderFragment = (CangOrderFragment) manager.getFragment(savedInstanceState, "order");
            cangReservationFragment = (CangReservationFragment) manager.getFragment(savedInstanceState, "reservation");
        } else {
            cangOrderFragment = new CangOrderFragment();
            cangReservationFragment = new CangReservationFragment();
        }

        initView();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_orders);
        orderPagerAdapter = new OrderPagerAdapter(getSupportFragmentManager(), getContext(), cangOrderFragment, cangReservationFragment);
        viewPager.setAdapter(orderPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void initTopBar() {
        initTitle("我的订单");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
