package com.yeapao.andorid.homepage.myself;

import android.content.Context;

import com.google.gson.Gson;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.homepage.circle.CircleContract;
import com.yeapao.andorid.model.UserData;
import com.yeapao.andorid.util.GlobalDataYepao;

/**
 * Created by fujindong on 2017/7/11.
 */

public class MyselfPresenter implements MyselfContract.Presenter {


    private Context mContext;
    private MyselfContract.View mView;
    private Gson gson = new Gson();
    private UserData userData;


    public MyselfPresenter(Context context, MyselfContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);

    }


    @Override
    public void start() {
        userData = GlobalDataYepao.getUser(mContext);
        LogUtil.e("MyselfPresenter","start");
        if (userData == null) {
            mView.initViewClick(true);
        } else {
            if (userData.isLogin()) {
                mView.initViewClick(false);
            } else {
                mView.initViewClick(true);
            }
        }

        mView.showResult(userData);

    }
}
