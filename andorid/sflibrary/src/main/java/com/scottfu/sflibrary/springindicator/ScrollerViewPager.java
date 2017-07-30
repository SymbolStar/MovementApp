package com.scottfu.sflibrary.springindicator;

/**
 * Created by fujindong on 2017/7/28.
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;

import java.lang.reflect.Field;

/**
 * Created by chenupt@gmail.com on 2015/3/7.
 * Description TODO
 */
public class ScrollerViewPager extends ViewPager {

    private static final String TAG = ScrollerViewPager.class.getSimpleName();

    private int duration = 1000;

    public ScrollerViewPager(Context context) {
        super(context);
    }

    public ScrollerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void fixScrollSpeed(){
        fixScrollSpeed(duration);
    }

    public void fixScrollSpeed(int duration){
        this.duration = duration;
        setScrollSpeedUsingRefection(duration);
    }


    private void setScrollSpeedUsingRefection(int duration) {
        try {
            Field localField = ViewPager.class.getDeclaredField("mScroller");
            localField.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(), new DecelerateInterpolator(1.5F));
            scroller.setDuration(duration);
            localField.set(this, scroller);
            return;
        } catch (IllegalAccessException localIllegalAccessException) {
        } catch (IllegalArgumentException localIllegalArgumentException) {
        } catch (NoSuchFieldException localNoSuchFieldException) {
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "onInterceptTouchEvent in IllegalArgumentException");
            return false;
        }
    }
}
