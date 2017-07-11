package com.yeapao.andorid.homepage.circle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeapao.andorid.R;

/**
 * Created by fujindong on 2017/7/11.
 */

public class CircleFragmentView extends Fragment implements CircleContract.View {

    private CircleContract.Presenter mPresenter;

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

        View view = View.inflate(getActivity(), R.layout.fragment_circle, null);

        return view;
    }

    @Override
    public void setPresenter(CircleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {

    }
}
