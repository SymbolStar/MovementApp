package com.yeapao.andorid.homepage.lesson;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.model.AdvertisementList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fujindong on 2017/7/12.
 */

public class LessonViewPager extends PagerAdapter {


    private int mSize;
    private Context mContext;
    private List<AdvertisementList> advertisementLists = new ArrayList<>();

    public LessonViewPager(Context context, List<AdvertisementList> lists) {
        mContext = context;
        advertisementLists = lists;
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
//        imageView.setImageDrawable(container.getResources().getDrawable(R.drawable.home_banner1));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext)
                .load(ConstantYeaPao.HOST+advertisementLists.get(position).getUrl())
                .asBitmap()
                .placeholder(R.drawable.home_banner_take_place)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.drawable.home_banner_take_place)
                .centerCrop()
                .into(imageView);
        container.addView(imageView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        return imageView;
    }


    //如果不继承这个方法在部分手机上会出现如下错误： java.lang.UnsupportedOperationException: Required method destroyItem was not overridden
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public int getCount() {
        return advertisementLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
