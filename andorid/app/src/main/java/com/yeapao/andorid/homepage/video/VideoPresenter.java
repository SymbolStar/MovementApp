package com.yeapao.andorid.homepage.video;

import android.content.Context;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.model.VideoDataModel;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/8/12.
 */

public class VideoPresenter implements VideoContract.Presenter{

    private static final String TAG = "VideoViewPresenter";

    private Context mContext;
    private VideoContract.View mView;
    private VideoDataModel videoDataModel;


    public VideoPresenter(Context context, VideoContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);

    }

    @Override
    public void start() {
            mView.startLoading();

    }

    @Override
    public void getData(Subscription subscription ,String type) {
        mView.startLoading();
        LogUtil.e(TAG,type);
        subscription = Network.getYeapaoApi()
                .getVideoData(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver );
    }

                  Observer<VideoDataModel> modelObserver = new Observer<VideoDataModel>() {
                    @Override
                    public void onCompleted() {
                        mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(VideoDataModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            mView.showResult(model);
                        }
                    }
                };

}
