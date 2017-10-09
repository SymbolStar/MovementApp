package com.yeapao.andorid.homepage.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.homepage.message.MyMessageActivity;
import com.yeapao.andorid.model.VideoDataModel;
import com.yeapao.andorid.model.VideoTypeModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static github.chenupt.multiplemodel.aa.AAModelFactory.TAG;

/**
 * Created by fujindong on 2017/8/12.
 */

public class VideoFragmentView extends Fragment implements VideoContract.View {


    @BindView(R.id.fl_info)
    FrameLayout flInfo;
    @BindView(R.id.tv_video_type)
    TextView tvVideoType;
    @BindView(R.id.tv_change_video_type)
    TextView tvChangeVideoType;
    @BindView(R.id.rv_video_list)
    RecyclerView rvVideoList;
    @BindView(R.id.srl_video)
    SwipeRefreshLayout srlVideo;
    @BindView(R.id.rv_video_tab_list)
    RecyclerView rvVideoTabList;
    @BindView(R.id.iv_message_icon)
    ImageView ivMessageIcon;


    private VideoContract.Presenter mPresenter;

    private LinearLayoutManager linearLayoutManager;

    private VideoMessageAdapter videoMessageAdapter;

    private VideoTabMessageAdapter videoTabMessageAdapter;

    private VideoTypeModel videoTypeModel;

    protected Subscription subscription;
    private String type="0";

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public VideoFragmentView() {

    }


    @OnClick(R.id.tv_change_video_type)
    void setTvVideoType(View v) {
        rvVideoTabList.setVisibility(View.VISIBLE);
        getVideoType();
    }

    @OnClick(R.id.iv_message_icon)
    void setIvMessageIcon(View view) {

        try {
            if (GlobalDataYepao.isLogin()) {
                MyMessageActivity.start(getContext());
            } else {
                ToastManager.showToast(getContext(),"请先登陆");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static VideoFragmentView newInstance() {
        return new VideoFragmentView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);
        LogUtil.e(TAG,"oncreateView");
        mPresenter.start();
        getVideoType();
//        mPresenter.getData(subscription,type);
        srlVideo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                JCVideoPlayer.releaseAllVideos();
                mPresenter.getData(subscription,type);
            }
        });
        srlVideo.setColorSchemeResources(R.color.colorPrimary);
        initViews(view);
        return view;
    }

    @Override
    public void setPresenter(VideoContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvVideoList.setLayoutManager(linearLayoutManager);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvVideoTabList.setLayoutManager(llm);

    }

    @Override
    public void showResult(VideoDataModel videoDataModel) {

        JCVideoPlayer.releaseAllVideos();
//        if (videoMessageAdapter == null) {
            videoMessageAdapter = new VideoMessageAdapter(getContext(),videoDataModel);
            rvVideoList.setAdapter(videoMessageAdapter);
//        }
//        else {
//            rvVideoList.setAdapter(videoMessageAdapter);
//            videoMessageAdapter.notifyDataSetChanged();
//
//        }




    }

    private void showVideoTab(final VideoTypeModel typeModel) {
        if (videoTabMessageAdapter == null) {
            tvVideoType.setText(typeModel.getData().get(0).getType_name());
            String currentType = tvVideoType.getText().toString();
            mPresenter.getData(subscription,type);
            videoTabMessageAdapter = new VideoTabMessageAdapter(getContext(),typeModel, currentType);
            rvVideoTabList.setAdapter(videoTabMessageAdapter);
            videoTabMessageAdapter.setOnItemCLickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    type = String.valueOf(videoTypeModel.getData().get(position).getType_index()) ;
                    tvVideoType.setText(videoTypeModel.getData().get(position).getType_name());
                    rvVideoTabList.setVisibility(View.GONE);
                    LogUtil.e("videoType",type);
                    mPresenter.getData(subscription,type);
                }
            });
        } else {
            videoTabMessageAdapter.refreshTab(tvVideoType.getText().toString());
            mPresenter.getData(subscription,type);
            rvVideoTabList.setAdapter(videoTabMessageAdapter);
            videoTabMessageAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void startLoading() {
        srlVideo.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        srlVideo.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e(TAG,"onresume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.e(TAG,"pause");
        JCVideoPlayer.releaseAllVideos();
    }



            private void getVideoType() {
                    subscription = Network.getYeapaoApi()
                            .requestVideoType()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserver );
                }

                  Observer<VideoTypeModel> modelObserver = new Observer<VideoTypeModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(VideoTypeModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            videoTypeModel = model;
                            showVideoTab(model);
                        }
                    }
                };

}
