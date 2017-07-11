package com.yeapao.andorid.homepage.lesson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.myself.MyselfPresenter;

/**
 * Created by fujindong on 2017/7/11.
 */

public class LessonFragmentView extends Fragment implements LessonContract.View {

    private LessonContract.Presenter mPresenter;


    public LessonFragmentView() {

    }


    public static LessonFragmentView newInstance() {
        return new LessonFragmentView();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_lesson, null);
        return view;

    }

    @Override
    public void setPresenter(LessonContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {

    }
}
