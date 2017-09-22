package com.yeapao.andorid.homepage.circle;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.homepage.message.MyMessageActivity;
import com.yeapao.andorid.model.CircleListModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static github.chenupt.multiplemodel.aa.AAModelFactory.TAG;

/**
 * Created by fujindong on 2017/7/11.
 */

public class CircleFragmentView extends BaseFragment implements CircleContract.View {

    @BindView(R.id.iv_circle_write)
    ImageView ivCircleWrite;
    @BindView(R.id.rv_circle_list)
    RecyclerView rvCircleList;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;


    private CircleContract.Presenter mPresenter;
    private LinearLayoutManager llm;
    private CircleMessageAdapter circleMessageAdapter;

    private CircleListModel mCircleListModel = new CircleListModel();

    private int currentPage = 0;
    private int totalPage;


    public CircleFragmentView() {

    }

    public static CircleFragmentView newInstance() {
        return new CircleFragmentView();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        currentPage = 0;
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNetWork(String.valueOf(currentPage) );
            }
        });
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        getNetWork(String.valueOf(currentPage));
        initViews(view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e(TAG,"onResume");
        getNetWork(String.valueOf(currentPage));
    }

    @OnClick(R.id.iv_circle_write)
    void setIvCircleWrite(View view) {
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

    @Override
    public void setPresenter(CircleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {

        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvCircleList.setLayoutManager(llm);
        rvCircleList.setOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                    判断是否滑动到底部 加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        if (currentPage < totalPage-1) {
                            getNetWork(String.valueOf(++currentPage));
                        } else {
                            ToastManager.showToast(getContext(),"没有更多");
                        }


                    }

                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy > 0;
                if (dy > 0) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getContext(),CirclePublishContentActivity.class));

                ToastManager.showToast(getContext(),"fab  onClick");
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void showResult(CircleListModel circleListModel) {
            circleMessageAdapter = new CircleMessageAdapter(getContext(), circleListModel);
            rvCircleList.setAdapter(circleMessageAdapter);
            circleMessageAdapter.setItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getActivity(), "onClick");
//                    startActivity(new Intent(getActivity(), CircleDetailActivity.class));
                }
            });
    }


            private void getNetWork(String page) {
                LogUtil.e(TAG,"onRefresh Circle--------"+String.valueOf(page));
                    subscription = Network.getYeapaoApi()
                            .requestCircleListPage(page)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserver);
                }

                  Observer<CircleListModel> modelObserver = new Observer<CircleListModel>() {
                    @Override
                    public void onCompleted() {
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());
                        refreshLayout.setRefreshing(false);

                    }

                    @Override
                    public void onNext(CircleListModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            if (currentPage == 0) {
                                mCircleListModel = model;
                                showResult(mCircleListModel);
                            } else {
                                showResultAdd(model);
                                mCircleListModel.getData().getCommunityList().addAll(model.getData().getCommunityList());
                            }
                            totalPage = model.getData().getTotalPage();
                        }
                    }
                };

    private void showResultAdd(CircleListModel model) {
        circleMessageAdapter.loadMore(model);

    }

}
