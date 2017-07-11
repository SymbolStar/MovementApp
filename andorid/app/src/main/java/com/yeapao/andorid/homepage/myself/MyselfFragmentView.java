package com.yeapao.andorid.homepage.myself;

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

public class MyselfFragmentView extends Fragment implements MyselfContract.View {


    private MyselfContract.Presenter mPresenter;

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
        View view = View.inflate(getActivity(), R.layout.fragment_myself, null);
        return view;
    }

    @Override
    public void setPresenter(MyselfContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {

    }
}
