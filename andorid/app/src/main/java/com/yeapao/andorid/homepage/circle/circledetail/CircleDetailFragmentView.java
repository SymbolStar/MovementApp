package com.yeapao.andorid.homepage.circle.circledetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by fujindong on 2017/7/18.
 */

public class CircleDetailFragmentView extends Fragment implements CircleDetailContract.View {


    @BindView(R.id.rv_circle_detail_list)
    RecyclerView rvCircleDetailList;
    @BindView(R.id.srl_circle_detail)
    SwipeRefreshLayout srlCircleDetail;
    @BindView(R.id.iv_circle_finger)
    ImageView ivCircleFinger;
    @BindView(R.id.et_comment)
    EditText etComment;
    Unbinder unbinder;
    private CircleDetailContract.Presenter mPresenter;
    private LinearLayoutManager llm;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_detail, container, false);
        initViews(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(CircleDetailContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvCircleDetailList.setLayoutManager(llm);

        showResult();
    }

    @Override
    public void showResult() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_circle_finger, R.id.et_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_circle_finger:
                break;
            case R.id.et_comment:
                break;
        }
    }
}
