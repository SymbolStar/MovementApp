package com.yeapao.andorid.homepage.myself.tab.health;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by fujindong on 2017/8/10.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private final Context context;

    private BodySideScoreFragment scoreFragment;
    private BodySideRecordFragment recordFragment;

    public BodySideScoreFragment getScoreFragment() {
        return scoreFragment;
    }
    public BodySideRecordFragment getRecordFragment() {
        return recordFragment;
    }

    public MainPagerAdapter(FragmentManager fm, Context context, BodySideScoreFragment scoreFragment, BodySideRecordFragment recordFragment) {
        super(fm);
        this.context = context;
        titles = new String[]{"体测得分","体测记录"};

        this.scoreFragment = scoreFragment;
        this.recordFragment = recordFragment;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1){
            return recordFragment;
        }

        return scoreFragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
