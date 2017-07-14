package com.yeapao.andorid.homepage.lesson;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeapao.andorid.R;

import java.util.Random;

/**
 * Created by fujindong on 2017/7/12.
 */

public class LessonViewPager extends PagerAdapter {


    private final Random random = new Random();
    private int mSize;


    public LessonViewPager() {
        mSize = 2;
    }


    public LessonViewPager(int count) {
        mSize = count;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        TextView textView = new TextView(container.getContext());
//        textView.setText(String.valueOf(position + 1));
//        textView.setBackgroundColor(0xff000000 | random.nextInt(0x00ffffff));
////         textView.setBackground(container.getContext().getResources().getDrawable(R.drawable.home_banner1));
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.WHITE);
//        textView.setTextSize(48);
//        container.addView(textView, ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT);


        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageDrawable(container.getResources().getDrawable(R.drawable.home_banner1));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        return imageView;
    }



    @Override
    public int getCount() {
        return mSize;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
