package com.yeapao.andorid.homepage.myself;

import android.content.Context;

import com.google.gson.Gson;
import com.yeapao.andorid.homepage.circle.CircleContract;

/**
 * Created by fujindong on 2017/7/11.
 */

public class MyselfPresenter implements MyselfContract.Presenter {


    private Context mContext;
    private MyselfContract.View mView;
    private Gson gson = new Gson();


    public MyselfPresenter(Context context, MyselfContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);

    }


    @Override
    public void start() {

    }
}
