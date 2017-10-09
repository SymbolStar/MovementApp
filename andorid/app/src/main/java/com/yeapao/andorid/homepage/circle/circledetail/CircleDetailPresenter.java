package com.yeapao.andorid.homepage.circle.circledetail;

import android.content.Context;
import android.nfc.NdefRecord;
import android.support.annotation.NonNull;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.model.CommunityDetailModel;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/18.
 */

public class CircleDetailPresenter implements CircleDetailContract.Presenter {

    private static final String TAG = "CircleDetailPresenter";

    private Context mContext;
    private CircleDetailContract.View mView;
    private String communityId;
    private String fabulous;
    private Subscription subscription;
    private CommunityDetailModel mCommunityDetailModel;

    public CircleDetailPresenter(@NonNull Subscription subscription, @NonNull Context context, @NonNull CircleDetailContract.View view) {
        mContext = context;
        mView = view;
        this.subscription = subscription;
        view.setPresenter(this);
    }

    public void setCommunityId(String id) {
        LogUtil.e(TAG + "++++++ id", id);
        communityId = id;
    }

    public void setFabulous(String fabulous) {
        LogUtil.e(TAG + "++++++ fabulous", fabulous);
        this.fabulous = fabulous;
    }


    @Override
    public void start() {
        mView.startLoading();
        getData();
//        if (fabulous.equals("0")) {
//            mView.refreshPraise(false);
//        } else {
//            mView.refreshPraise(true);
//        }

    }

    @Override
    public void getData() {

        if (GlobalDataYepao.isLogin()) {
            subscription = Network.getYeapaoApi()
                    .requestCommunityDetail(communityId, GlobalDataYepao.getUser(mContext).getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(modelObserver);
        } else {
            subscription = Network.getYeapaoApi()
                    .requestCommunityDetail(communityId,"0")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(modelObserver);
        }



    }

    Observer<CommunityDetailModel> modelObserver = new Observer<CommunityDetailModel>() {
        @Override
        public void onCompleted() {
            mView.stopLoading();
        }

        @Override
        public void onError(Throwable e) {
            mView.stopLoading();
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(CommunityDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                mCommunityDetailModel = model;
                mView.showResult(model);

            }
        }
    };

    @Override
    public void getPraise() {
        LogUtil.e(TAG + "------Praise", communityId);
        subscription = Network.getYeapaoApi()
                .requestFinger(communityId, GlobalDataYepao.getUser(mContext).getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(FingerModelObserver);
    }


    Observer<NormalDataModel> FingerModelObserver = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                mView.refreshPraise(true);
                getData();
            }
        }
    };


    @Override
    public void deletePraise() {
        LogUtil.e(TAG + "------deletePraise", communityId);
        subscription = Network.getYeapaoApi()
                .requestDeleteFinger(communityId, GlobalDataYepao.getUser(mContext).getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(DeleteFingerModelObserver);
    }

    Observer<NormalDataModel> DeleteFingerModelObserver = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                mView.refreshPraise(false);
                getData();
            }
        }
    };

    @Override
    public void getComment(String content) {
        LogUtil.e(TAG + "------getComment", content + "   " + communityId);
        subscription = Network.getYeapaoApi()
                .requestComment(content, communityId, GlobalDataYepao.getUser(mContext).getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CommentModelObserver);
    }


    Observer<NormalDataModel> CommentModelObserver = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                getData();
            }
        }
    };


    @Override
    public void deleteComment(String communityCommentId) {
        LogUtil.e(TAG + "------deleteComment", communityCommentId);
        subscription = Network.getYeapaoApi()
                .requestDeleteComment(communityCommentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(DeleteCommentModelObserver);
    }


    Observer<NormalDataModel> DeleteCommentModelObserver = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                getData();
            }
        }
    };


    @Override
    public void getFromToComment(int position, String content) {

        LogUtil.e(TAG, "getFormToComment 回复评论" + position + "  " + content);
        subscription = Network.getYeapaoApi()
                .requestFromToComment(content, String.valueOf(mCommunityDetailModel.getData().getCommunityId()),
                        String.valueOf(mCommunityDetailModel.getData().getComments().get(position).getId()),
                        GlobalDataYepao.getUser(mContext).getId(),
                        String.valueOf(mCommunityDetailModel.getData().getComments().get(position).getCustomerId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(FromToModelObserver);
    }

    @Override
    public void getFromToChildComment(int pos, int childPos, String content) {
        LogUtil.e(TAG, "getFormToComment 回复评论里的评论" + pos + "  " + childPos + "  " + content);
        subscription = Network.getYeapaoApi()
                .requestFromToComment(content, String.valueOf(mCommunityDetailModel.getData().getCommunityId()),
                        String.valueOf(mCommunityDetailModel.getData().getComments().get(pos).getCommunityCommentsOuts().get(childPos).getId()),
                        GlobalDataYepao.getUser(mContext).getId(),
                        String.valueOf(mCommunityDetailModel.getData().getComments().get(pos).getCommunityCommentsOuts().get(childPos).getCustomerId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(FromToModelObserver);
    }


    Observer<NormalDataModel> FromToModelObserver = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                getData();
            }
        }
    };


    @Override
    public void deleteCommunity() {
        LogUtil.e(TAG, String.valueOf(mCommunityDetailModel.getData().getCommunityId()));
        subscription = Network.getYeapaoApi()
                .requestDeleteCommunity(String.valueOf(mCommunityDetailModel.getData().getCommunityId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(deleteCommunityModelObserver);
    }


    Observer<NormalDataModel> deleteCommunityModelObserver = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                    mView.CircleDetailFinish();
            }
        }
    };

}
