package com.scottfu.sflibrary.springindicator;

/**
 * Created by fujindong on 2017/7/28.
 */

public interface TabClickListener {

    /**
     * Handle click event when tab is clicked.
     * @param position ViewPager item position.
     * @return Call setCurrentItem if return true.
     */
    public boolean onTabClick(int position);

}