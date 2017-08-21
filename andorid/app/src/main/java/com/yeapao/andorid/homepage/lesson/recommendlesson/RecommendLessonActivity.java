package com.yeapao.andorid.homepage.lesson.recommendlesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.scottfu.sflibrary.springindicator.ScrollerViewPager;
import com.scottfu.sflibrary.springindicator.SpringIndicator;
import com.scottfu.sflibrary.springindicator.TabClickListener;
import com.scottfu.sflibrary.util.SystemDateUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.homepage.myself.tab.shopkeeper.GuideFragment;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;

/**
 * Created by fujindong on 2017/8/19.
 */

public class RecommendLessonActivity extends BaseActivity {
    private static final String TAG = "RecommendLessonActivity";

    ScrollerViewPager viewPager;

    public static String shopId;

    private int currentDay = 0;


    public static void start(Context context,String shopId) {
        Intent intent = new Intent();
        intent.putExtra("shopId", shopId);
        intent.setClass(context, RecommendLessonActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_lesson);
        initTopBar();
        Intent intent = getIntent();
        shopId = intent.getStringExtra("shopId");

        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(RecommendLessonFragment.class, SystemDateUtil.getCurrentWeekYMD(), getTitles());
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();
        viewPager.setCurrentItem(currentDay);

        // just set viewPager
        springIndicator.setViewPager(viewPager);
        springIndicator.setOnTabClickListener(new TabClickListener() {
            @Override
            public boolean onTabClick(int position) {
//                TODO 暂不做任何处理
                return false;
            }
        });


    }

    private List<String> getTitles(){

        ArrayList<String> days = new ArrayList<>();

//        TODO 计算时间

        String date = SystemDateUtil.getCurrentYYYYMMDD();
        String[] day = date.split("-");
        String[] w = SystemDateUtil.getCurrentWeek();
        for (int i = 0; i < w.length; i++) {

            if (w[i].equals(day[2])) {
                currentDay = i;
                days.add("今");
            } else {
                days.add(w[i]);
            }
        }
        return days;
    }

    @Override
    protected void initTopBar() {
        initTitle("课程预约");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
