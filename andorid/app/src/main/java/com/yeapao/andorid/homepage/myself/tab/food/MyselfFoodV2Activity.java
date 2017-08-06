package com.yeapao.andorid.homepage.myself.tab.food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.google.common.collect.Lists;
import com.scottfu.sflibrary.springindicator.ScrollerViewPager;
import com.scottfu.sflibrary.springindicator.SpringIndicator;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.SystemDateUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.ChooseFoodDialogFragment;
import com.yeapao.andorid.homepage.myself.tab.shopkeeper.GuideFragment;
import com.yeapao.andorid.homepage.myself.tab.shopkeeper.ShopKeeperLessonReservationActivity;
import com.yeapao.andorid.model.FoodInfoModel;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/8/1.
 */

public class MyselfFoodV2Activity extends BaseActivity {

    private static final String TAG = "MyselfFoodV2Activity";


    ScrollerViewPager viewPager;

    private int currentDay = 0;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfFoodV2Activity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_v2);
        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(FoodDetailFragment.class, SystemDateUtil.getCurrentWeekYMD(), getTitles());
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();
        viewPager.setCurrentItem(currentDay);

        // just set viewPager
        springIndicator.setViewPager(viewPager);

        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("一周食谱");
        initBack();
        getdata();
    }

    private void getdata() {
        getNetWork(SystemDateUtil.getCurrentYYYYMMDD());
    }


    private void getNetWork(String date) {
        LogUtil.e(TAG,date);
        subscription = Network.getYeapaoApi()
                .getFoodInfos(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( modelObserver);
    }

    Observer<FoodInfoModel> modelObserver = new Observer<FoodInfoModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());

        }

        @Override
        public void onNext(FoodInfoModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {

            }
        }
    };

    @Override
    protected Context getContext() {
        return this;
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

    private List<Integer> getBgRes(){
        return Lists.newArrayList(R.drawable.y_you, R.drawable.y_you, R.drawable.y_you, R.drawable.y_you
                , R.drawable.y_you, R.drawable.y_you, R.drawable.y_you);
    }



}
