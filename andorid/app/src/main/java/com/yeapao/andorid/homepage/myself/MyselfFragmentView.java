package com.yeapao.andorid.homepage.myself;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.myself.tab.MyselfHealthActivity;
import com.yeapao.andorid.homepage.myself.tab.MyselfLessonActivity;
import com.yeapao.andorid.homepage.myself.tab.MyselfOrderActivity;
import com.yeapao.andorid.homepage.myself.tab.MyselfPostActivity;
import com.yeapao.andorid.homepage.myself.tab.MyselfReservationActivity;
import com.yeapao.andorid.homepage.myself.tab.MyselfSettingActivity;
import com.yeapao.andorid.homepage.myself.tab.coach.MyselfCoachActivity;
import com.yeapao.andorid.homepage.myself.tab.health.HealthRepositoryActivity;
import com.yeapao.andorid.homepage.myself.tab.health.testCoordinatorLayoutActivity;
import com.yeapao.andorid.homepage.myself.tab.shopkeeper.MyselfShopOwnerActivity;
import com.yeapao.andorid.model.UserData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by fujindong on 2017/7/11.
 */

public class MyselfFragmentView extends Fragment implements MyselfContract.View {

    private static final String TAG = "MyselfFragmentView";


    @BindView(R.id.iv_myself_setting)
    ImageView ivMyselfSetting;
    @BindView(R.id.rv_mySelf_list)
    RecyclerView rvMySelfList;
    @BindView(R.id.v_myself_click)
    View vMyselfClick;
    Unbinder unbinder;

    private MyselfContract.Presenter mPresenter;
    private MyselfMessageAdapter myselfMessageAdapter;
    private LinearLayoutManager llm;

    public MyselfFragmentView() {

    }

    public static MyselfFragmentView newInstance() {
        return new MyselfFragmentView();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myself, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews(view);

        return view;
    }

    @Override
    public void setPresenter(MyselfContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e(TAG,"onresume");
        mPresenter.start();
    }

    @Override
    public void initViews(View view) {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMySelfList.setLayoutManager(llm);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.iv_myself_setting)
    void onSettingClick(View view) {
        ToastManager.showToast(getContext(),"onClick");
//        LoginActivity.start(getContext());
        MyselfSettingActivity.start(getContext());
    }

    @Override
    public void showResult(UserData userData) {
        if (myselfMessageAdapter == null) {
            myselfMessageAdapter = new MyselfMessageAdapter(getContext());
            rvMySelfList.setAdapter(myselfMessageAdapter);
            myselfMessageAdapter.setItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(), "header onclick");
                }
            });
            myselfMessageAdapter.setMyselfTabListener(new OnMyselfTabListener() {
                @Override
                public void onTabClick(View v, int position,String name) {
//            TODO 重新定义以枚举的形式
                    ToastManager.showToast(getContext(), "tab onclick"+name);
                    if (name.equals("我的帖子")) {
                        MyselfPostActivity.start(getContext());
                    } else if (name.equals("我的课程")) {
                        MyselfLessonActivity.start(getContext());
                    } else if (name.equals("我的订单")) {
                        MyselfOrderActivity.start(getContext());
                    } else if (name.equals("我的预约")) {
                        MyselfReservationActivity.start(getContext());
                    } else if (name.equals("我是店长")) {
                        MyselfShopOwnerActivity.start(getContext());
                    } else if (name.equals("我是教练")) {
                        MyselfCoachActivity.start(getContext());
                    } else if (name.equals("健康数据库")) {
                        MyselfHealthActivity.start(getContext());
//                        HealthRepositoryActivity.start(getContext());
//                        getContext().startActivity(new Intent(getContext(),testCoordinatorLayoutActivity.class));
                    }
                }
            });
        } else {
            rvMySelfList.setAdapter(myselfMessageAdapter);
            myselfMessageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initViewClick(boolean flag) {
        if (flag) {
            vMyselfClick.setVisibility(View.VISIBLE);
        } else {
            vMyselfClick.setVisibility(View.GONE);
        }

    }


    @OnClick(R.id.v_myself_click)
    void onViewClick(View view) {
        LogUtil.e(TAG,"onViewClick");
        LoginActivity.start(getContext());
    }



}
