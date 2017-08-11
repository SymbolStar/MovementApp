package com.yeapao.andorid.homepage.myself.tab.health;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/8/10.
 */

public class HealthRepositoryActivity extends BaseActivity{

    private static final String TAG = "HealthRepositoryActivity";

    private TabLayout tabLayout;
    private MainPagerAdapter mPageAdapter;

    private BodySideScoreFragment scoreFragment;
    private BodySideRecordFragment recordFragment;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HealthRepositoryActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        initTopBar();
        initView();
    }

    private void initView() {
        scoreFragment = new BodySideScoreFragment();
        recordFragment = new BodySideRecordFragment();

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(2);

        mPageAdapter = new MainPagerAdapter(
                getSupportFragmentManager(),
                getContext(),
                scoreFragment,
                recordFragment);

        viewPager.setAdapter(mPageAdapter);
        tabLayout.setupWithViewPager(viewPager);


        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        setmCollapsingToolbarLayoutTitle("健康数据库");

    }

    @Override
    protected void initTopBar() {
        initTitle("健康数据库");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private void setmCollapsingToolbarLayoutTitle(String title) {
        mCollapsingToolbarLayout.setTitle(title);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
    }

}
