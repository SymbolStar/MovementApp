package com.yeapao.andorid.homepage.lesson;

import android.content.Context;

import com.android.volley.VolleyError;
import com.google.common.net.InetAddresses;
import com.google.gson.Gson;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.NetImpl;
import com.yeapao.andorid.homepage.circle.CircleContract;
import com.yeapao.andorid.model.HomeList;
import com.yeapao.andorid.model.MessageResult;
import com.yeapao.andorid.model.SelectHomeList;

/**
 * Created by fujindong on 2017/7/11.
 */

public class LessonPresenter implements LessonContract.Presenter {

    private static final String TAG = "LessonPresenter";
    private Context mContext;
    private LessonContract.View mView;
    private Gson gson = new Gson();
    private HomeList mHomeList;


    public LessonPresenter(Context context, LessonContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);

    }


    @Override
    public void start() {
        mView.startLoading();
        getData();
    }



    @Override
    public void getData() {

        CloudClient.doHttpRequest(mContext, ConstantYeaPao.GET_HOME_LIST, NetImpl.getInstance().getHomeData("0"), null, new JSONResultHandler() {
            @Override
            public void onSuccess(String jsonString) {
                LogUtil.e(TAG, jsonString);
                MessageResult result = gson.fromJson(jsonString, MessageResult.class);
                if (result.getErrmsg().equals("ok")) {
                    mHomeList = result.getData();
                    mView.showResult(result.getData());
                } else {

                }

                mView.stopLoading();
            }

            @Override
            public void onError(VolleyError errorMessage) {
                LogUtil.e(TAG, errorMessage.toString());
            }
        });

    }

    @Override
    public void getLessonScreeningData(String time, String status, final String region) {
        LogUtil.e(TAG,time+" "+status+" "+region);
        CloudClient.doHttpRequest(mContext, ConstantYeaPao.GET_HOME_SELECT, NetImpl.getInstance().setLessonScreening(time, status, region), null, new JSONResultHandler() {
            @Override
            public void onSuccess(String jsonString) {
                LogUtil.e(TAG, jsonString);
                SelectHomeList result = gson.fromJson(jsonString, SelectHomeList.class);
                if (result.getErrmsg().equals("ok")) {
                    mView.showSelectResult(result);
                } else {

                }
                mView.stopLoading();
            }

            @Override
            public void onError(VolleyError errorMessage) {
                LogUtil.e(TAG,errorMessage.toString());
            }
        });
    }
}
