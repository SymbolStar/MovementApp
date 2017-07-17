package com.yeapao.andorid.homepage.circle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.common.base.MoreObjects;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by fujindong on 2017/7/11.
 */

public class CircleFragmentView extends Fragment implements CircleContract.View {

    @BindView(R.id.iv_circle_write)
    ImageView ivCircleWrite;
    @BindView(R.id.rv_circle_list)
    RecyclerView rvCircleList;
    Unbinder unbinder;
    private CircleContract.Presenter mPresenter;
    private LinearLayoutManager llm;
    private CircleMessageAdapter circleMessageAdapter;

    public CircleFragmentView() {

    }

    public static CircleFragmentView newInstance() {
        return new CircleFragmentView();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews(view);
        return view;
    }

    @Override
    public void setPresenter(CircleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvCircleList.setLayoutManager(llm);
        showResult();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_circle_write)
    public void onViewClicked() {
        ToastManager.showToast(getContext(),"circle_write");
    }

    @Override
    public void showResult() {
        if (circleMessageAdapter == null) {
            circleMessageAdapter = new CircleMessageAdapter(getContext());
            rvCircleList.setAdapter(circleMessageAdapter);
        } else {
            rvCircleList.setAdapter(circleMessageAdapter);
            circleMessageAdapter.notifyDataSetChanged();
        }


    }
}
