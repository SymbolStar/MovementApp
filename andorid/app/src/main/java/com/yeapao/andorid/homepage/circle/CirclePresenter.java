package com.yeapao.andorid.homepage.circle;

import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by fujindong on 2017/7/11.
 */

public class CirclePresenter implements CircleContract.Presenter {

    private Context mContext;
    private CircleContract.View mView;
    private Gson gson = new Gson();


    public CirclePresenter(Context context, CircleContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);

    }


    @Override
    public void start() {

    }
}
