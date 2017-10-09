package com.yeapao.andorid.homepage.myself.orders;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by fujindong on 2017/9/18.
 */

public class OrderPagerAdapter extends FragmentPagerAdapter {


    private String[] titles;
    private Context context;

    private CangOrderFragment orderFragment;
    private CangReservationFragment cangReservationFragment;



    public OrderPagerAdapter(FragmentManager fragmentManager, Context context, CangOrderFragment cangOrderFragment,
                             CangReservationFragment cangReservationFragment) {
        super(fragmentManager);
        this.context = context;
        titles = new String[]{"健身舱使用","健身舱预约"};
        this.orderFragment = cangOrderFragment;
        this.cangReservationFragment = cangReservationFragment;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 1) {
            return cangReservationFragment;
        } else {
            return orderFragment;
        }

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
