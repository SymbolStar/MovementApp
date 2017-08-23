package com.yeapao.andorid.homepage.myself;

import android.content.Context;
import android.support.design.widget.TabLayout;

import com.google.gson.Gson;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.homepage.circle.CircleContract;
import com.yeapao.andorid.model.UserData;
import com.yeapao.andorid.model.UserDetailsModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        if (GlobalDataYepao.isLogin()) {
            mView.initViewClick(false);
            mView.refreshUserData();
        } else {
            mView.initViewClick(true);
            mView.showResult(userData);
        }

//        userData = GlobalDataYepao.getUser(mContext);
//        LogUtil.e("MyselfPresenter","start");
//        if (userData == null) {
//            LogUtil.e("====","null");
//            mView.initViewClick(true);
//        } else {
//            if (userData.isLogin()) {
//                mView.initViewClick(false);
//            } else {
//                LogUtil.e("===","isloginfalse");
//                mView.initViewClick(true);
//            }
//        }

//        mView.showResult(userData);

    }





}
