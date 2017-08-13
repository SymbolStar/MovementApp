package com.yeapao.andorid.homepage.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.model.VideoDataModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import rx.Subscription;

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
    private VideoContract.Presenter mPresenter;

    private LinearLayoutManager linearLayoutManager;

    private VideoMessageAdapter videoMessageAdapter;

    protected Subscription subscription;
    private String type="1";

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public VideoFragmentView() {

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
        mPresenter.start();
        mPresenter.getData(subscription,type);
        srlVideo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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
    }

    @Override
    public void showResult(VideoDataModel videoDataModel) {

        if (videoMessageAdapter == null) {
            videoMessageAdapter = new VideoMessageAdapter(getContext(),videoDataModel);
            rvVideoList.setAdapter(videoMessageAdapter);
        } else {
            rvVideoList.setAdapter(videoMessageAdapter);
            videoMessageAdapter.notifyDataSetChanged();
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
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
