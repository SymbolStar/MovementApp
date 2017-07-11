package com.yeapao.andorid.homepage.shopping;

import android.content.Context;

import com.google.gson.Gson;
import com.yeapao.andorid.homepage.circle.CircleContract;

/**
 * Created by fujindong on 2017/7/11.
 */

public class ShoppingPresenter implements ShoppingContract.Presenter {

    private Context mContext;
    private ShoppingContract.View mView;
    private Gson gson = new Gson();

    public ShoppingPresenter(Context context, ShoppingContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
