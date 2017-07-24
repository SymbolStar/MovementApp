package com.yeapao.andorid.homepage.myself;

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
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.myself.tab.MyselfLessonActivity;
import com.yeapao.andorid.homepage.myself.tab.MyselfOrderActivity;
import com.yeapao.andorid.homepage.myself.tab.MyselfPostActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by fujindong on 2017/7/11.
 */

public class MyselfFragmentView extends Fragment implements MyselfContract.View {


    @BindView(R.id.iv_myself_setting)
    ImageView ivMyselfSetting;
    @BindView(R.id.rv_mySelf_list)
    RecyclerView rvMySelfList;
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
    public void initViews(View view) {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMySelfList.setLayoutManager(llm);

        showResult();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.iv_myself_setting)
    void onSettingClick(View view) {
        ToastManager.showToast(getContext(),"onClick");
        LoginActivity.start(getContext());
    }

    @Override
    public void showResult() {
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
                    }

                }
            });
        } else {
            rvMySelfList.setAdapter(myselfMessageAdapter);
            myselfMessageAdapter.notifyDataSetChanged();
        }
    }





}
