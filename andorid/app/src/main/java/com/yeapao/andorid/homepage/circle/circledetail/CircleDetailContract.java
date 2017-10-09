package com.yeapao.andorid.homepage.circle.circledetail;

import com.scottfu.sflibrary.mvp.BasePresenter;
import com.scottfu.sflibrary.mvp.BaseView;
import com.yeapao.andorid.model.CommunityDetailModel;

import rx.Subscription;

/**
 * Created by fujindong on 2017/7/18.
 */

public interface CircleDetailContract  {

    interface View extends BaseView<Presenter> {
        void showResult(CommunityDetailModel communityDetailModel);

        void startLoading();

        void stopLoading();

        void refreshPraise(boolean status);

        void refreshComment();

        void CircleDetailFinish();

    }


    interface Presenter extends BasePresenter {

        void getData();

        void getPraise();

        void deletePraise();

        void getComment(String content);

        void deleteComment(String communityCommentId);

        void getFromToComment(int position,String content);

        void getFromToChildComment(int pos, int childPos, String content);

        void deleteCommunity();
    }


}
