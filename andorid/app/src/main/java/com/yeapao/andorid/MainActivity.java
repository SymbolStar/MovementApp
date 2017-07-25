package com.yeapao.andorid;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import android.databinding.DataBindingUtil;

import com.scottfu.sflibrary.permission.ActivityCollector;
import com.scottfu.sflibrary.permission.PermissionActivity;
import com.scottfu.sflibrary.permission.PermissionListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.databinding.ActivityMainBinding;
import com.yeapao.andorid.homepage.circle.CircleFragmentView;
import com.yeapao.andorid.homepage.circle.CirclePresenter;
import com.yeapao.andorid.homepage.lesson.LessonFragmentView;
import com.yeapao.andorid.homepage.lesson.LessonPresenter;
import com.yeapao.andorid.homepage.myself.MyselfFragmentView;
import com.yeapao.andorid.homepage.myself.MyselfPresenter;
import com.yeapao.andorid.homepage.shopping.ShoppingFragmentView;
import com.yeapao.andorid.homepage.shopping.ShoppingPresenter;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends PermissionActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding bind;

    private MainPagerAdapter mainPagerAdapter;

    private LessonFragmentView lessonFragmentView;
    private ShoppingFragmentView shoppingFragmentView;
    private CircleFragmentView circleFragmentView;
    private MyselfFragmentView myselfFragmentView;

    private LessonPresenter lessonPresenter;
    private ShoppingPresenter shoppingPresenter;
    private CirclePresenter circlePresenter;
    private MyselfPresenter myselfPresenter;


    private SparseIntArray items;// used for change ViewPager selected item
    private List<Fragment> fragments;// used for ViewPager adapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCollector.addActivity(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//初始隐藏键盘
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initView();
//        permissionCheck();

        fragments = new ArrayList<>(4);
        items = new SparseIntArray(4);

        if (savedInstanceState != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            lessonFragmentView = (LessonFragmentView) fragmentManager.getFragment(savedInstanceState, "lesson");
            shoppingFragmentView = (ShoppingFragmentView) fragmentManager.getFragment(savedInstanceState, "shopping");
            circleFragmentView = (CircleFragmentView) fragmentManager.getFragment(savedInstanceState, "circle");
            myselfFragmentView = (MyselfFragmentView) fragmentManager.getFragment(savedInstanceState, "myself");

            fragments.add(lessonFragmentView);
            fragments.add(shoppingFragmentView);
            fragments.add(circleFragmentView);
            fragments.add(myselfFragmentView);
        } else {
            lessonFragmentView = LessonFragmentView.newInstance();
            shoppingFragmentView = ShoppingFragmentView.newInstance();
            circleFragmentView = CircleFragmentView.newInstance();
            myselfFragmentView = MyselfFragmentView.newInstance();

            fragments.add(lessonFragmentView);
            fragments.add(shoppingFragmentView);
            fragments.add(circleFragmentView);
            fragments.add(myselfFragmentView);
        }

        lessonPresenter = new LessonPresenter(getContext(), lessonFragmentView);
        shoppingPresenter = new ShoppingPresenter(getContext(), shoppingFragmentView);
        circlePresenter = new CirclePresenter(getContext(), circleFragmentView);
        myselfPresenter = new MyselfPresenter(getContext(), myselfFragmentView);

//        fragments.add(lessonFragmentView);
//        fragments.add(shoppingFragmentView);
//        fragments.add(circleFragmentView);
//        fragments.add(myselfFragmentView);

        items.put(R.id.home_lesson, 0);
        items.put(R.id.home_shopping, 1);
        items.put(R.id.home_circle, 2);
        items.put(R.id.home_myself, 3);


        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        bind.vp.setAdapter(mainPagerAdapter);

        initEvent();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);

    }


    private void initView() {
        // disable all animations
        bind.bnvTab.enableAnimation(false);
        bind.bnvTab.enableShiftingMode(false);
        bind.bnvTab.enableItemShiftingMode(false);

        bind.bnvTab.setIconSize(24, 24);
        bind.bnvTab.setTextSize(10);


        // add badge
//        addBadgeAt(2, 1);
    }


    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to change the current item of view pager when click bottom nav item
        bind.bnvTab.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = 0;
//                switch (item.getItemId()) {
//                    case R.id.menu_music:
//                        id = 0;
//                        break;
//                    case R.id.menu_backup:
//                        id = 1;
//                        break;
//                    case R.id.menu_friends:
//                        id = 2;
//                        break;
//                }
//                if(previousPosition != id) {
//                  bind.vp.setCurrentItem(id, false);
//                  previousPosition = id;
//                }

                // you can write as above.
                // I recommend this method. You can change the item order or counts without update code here.
                int position = items.get(item.getItemId());
                if (previousPosition != position) {
                    // only set item when item changed
                    previousPosition = position;
                    Log.i(TAG, "-----bnve-------- previous item:" + bind.bnvTab.getCurrentItem() + " current item:" + position + " ------------------");
                    bind.vp.setCurrentItem(position);
                }
                return true;
            }
        });

        // set listener to change the current checked item of bottom nav when scroll view pager
        bind.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "-----ViewPager-------- previous item:" + bind.bnvTab.getCurrentItem() + " current item:" + position + " ------------------");
                bind.bnvTab.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /**
     * tab添加红色小标
     *
     * @param position
     * @param number
     * @return
     */
    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12, 2, true)
                .bindTarget(bind.bnvTab.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                            ToastManager.showToast(getContext(), "badge is removed");
                        }
                    }
                });
    }


    /**
     * 权限申请
     */
    private void permissionCheck() {
        requestRuntimePermission(new String[]{Manifest.permission.CALL_PHONE,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA}, new PermissionListener() {
            @Override
            public void onGranted() {
                Toast.makeText(getContext(), "所有权限已同意", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(List<String> deniedPermissions) {
                for (String deniedPermission : deniedPermissions) {
                    Toast.makeText(getContext(), "未同意的权限" + deniedPermission, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        FragmentManager manager = getSupportFragmentManager();
//        manager.putFragment(outState, "lesson", lessonFragmentView);
//        manager.putFragment(outState, "shopping", shoppingFragmentView);
//        manager.putFragment(outState, "circle", circleFragmentView);
//        manager.putFragment(outState, "myself", myselfFragmentView);
//    }

    private Context getContext() {
        return this;
    }

    /**
     * 主要是为了解决在首页 店铺少的时候 无法实现筛选栏的顶部悬浮。
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (bind.bnvTab.getCurrentItem() == 0) {
            lessonFragmentView.goneScreening();
        }
        return super.dispatchTouchEvent(ev);
    }
}
