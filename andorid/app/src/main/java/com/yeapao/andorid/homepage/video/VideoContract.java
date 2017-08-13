package com.yeapao.andorid.homepage.video;

import com.scottfu.sflibrary.mvp.BasePresenter;
import com.scottfu.sflibrary.mvp.BaseView;
import com.yeapao.andorid.homepage.shopping.ShoppingContract;
import com.yeapao.andorid.model.VideoDataModel;

import rx.Subscription;

/**
 * Created by fujindong on 2017/8/12.
 */

public interface VideoContract {

    interface View extends BaseView<VideoContract.Presenter> {
        void showResult(VideoDataModel videoDataModel);

        void startLoading();

        void stopLoading();
    }


    interface Presenter extends BasePresenter {
        void getData(Subscription subscription,String type);
    }

}
