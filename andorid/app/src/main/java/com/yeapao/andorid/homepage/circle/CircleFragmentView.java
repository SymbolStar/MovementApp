package com.yeapao.andorid.homepage.circle;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.yeapao.andorid.MainActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.circle.circledetail.CircleDetailActivity;
import com.yeapao.andorid.homepage.map.repository.PaySuccessActivity;
import com.yeapao.andorid.homepage.message.MyMessageActivity;
import com.yeapao.andorid.model.CircleListModel;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.SingleCommunityModel;
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

    private static final String TAG = "CircleFragment";

    @BindView(R.id.iv_circle_write)
    ImageView ivCircleWrite;
    @BindView(R.id.rv_circle_list)
    RecyclerView rvCircleList;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;

    private boolean singleCommunityFlag = false;

    private CircleContract.Presenter mPresenter;
    private LinearLayoutManager llm;
    private CircleMessageAdapter circleMessageAdapter;

    private CircleListModel mCircleListModel = new CircleListModel();

    private int currentPage = 0;
    private int totalPage;

    private int praisePosition = 0;

    private int singleCommunotyPosition = 0;

    private SingleCommunityModel mSingleCommunityModel = new SingleCommunityModel();

    public static boolean isPhotoPreView = true;

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
        LogUtil.e(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        currentPage = 0;
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 0;
                if (GlobalDataYepao.isLogin()) {
                    LogUtil.e(TAG,"onRefreshSwipe" +
                            "getnetWorkwithAccount");
                    getNetWorkWithAccount(GlobalDataYepao.getUser(getContext()).getId(), String.valueOf(currentPage));
                } else {
                    LogUtil.e(TAG,"onRefreshSwipe" +
                            "getnetWork");
                    getNetWork(String.valueOf(currentPage));
                }
            }
        });
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        if (GlobalDataYepao.isLogin()) {
//            getNetWorkWithAccount(GlobalDataYepao.getUser(getContext()).getId(), String.valueOf(currentPage));
//        } else {
//            getNetWork(String.valueOf(currentPage));
//        }
        initViews(view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e(TAG, "onResume");

        if (MainActivity.currentTab == 2) {
            DialogUtils.showProgressDialog(getContext(),true);
            if (GlobalDataYepao.isLogin() ) {
                if (isPhotoPreView) {
                    if (singleCommunityFlag) {
                        getNetWorkSingleCommunity(String.valueOf(mCircleListModel.getData().getCommunityList().get(singleCommunotyPosition).getCommunityId()),
                                GlobalDataYepao.getUser(getContext()).getId());
                        singleCommunityFlag = false;
                    } else {
                        currentPage = 0;
                        getNetWorkWithAccount(GlobalDataYepao.getUser(getContext()).getId(), String.valueOf(currentPage));
                    }
                } else {
                    DialogUtils.cancelProgressDialog();
                    isPhotoPreView = true;
                }



            } else {
                currentPage = 0;
                getNetWork(String.valueOf(currentPage));
            }
        }



    }

    @OnClick(R.id.iv_circle_write)
    void setIvCircleWrite(View view) {
        try {
            if (GlobalDataYepao.isLogin()) {
                MyMessageActivity.start(getContext());
            } else {
                ToastManager.showToast(getContext(), "请先登陆");
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
        LogUtil.e(TAG,"initViews");
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
                        if (currentPage < totalPage - 1) {
                            getNetWork(String.valueOf(++currentPage));
                        } else {
                            circleMessageAdapter.loadNothing();
                            ToastManager.showToast(getContext(), "没有更多");
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


                startActivity(new Intent(getContext(), CirclePublishContentActivity.class));

                ToastManager.showToast(getContext(), "fab  onClick");
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

        try {
            circleMessageAdapter = new CircleMessageAdapter(getContext(), mCircleListModel);

            rvCircleList.setAdapter(circleMessageAdapter);
            circleMessageAdapter.setItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    singleCommunityFlag = true;
                    singleCommunotyPosition = position;
                    CircleDetailActivity.start(getContext(), String.valueOf(mCircleListModel.getData().getCommunityList().get(position).getCommunityId()),
                            mCircleListModel.getData().getCommunityList().get(position).getFabulous());

//                CircleDetailActivity.start(getContext(), String.valueOf(mCircleListModel.getData().getCommunityList().get(position).getCommunityId()),
//                        mCircleListModel.getData().getCommunityList().get(position).getFabulous());
//                    startActivity(new Intent(getActivity(), CircleDetailActivity.class));
                }
            });

            circleMessageAdapter.setmPraiseListener(new CircleMessageAdapter.PraiseClickListener() {

                @Override
                public void onPraiseClicklistener(int position) {
                    praisePosition = position;
                    if (mCircleListModel.getData().getCommunityList().get(position).getFabulous().equals("1")) {

                        deletePraise(position);
                    } else {
                        getPraise(position);
                    }

                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }



    }

    private void getNetWorkWithAccount(String customerId, String page) {
        LogUtil.e(TAG, "onRefresh Circle--------" + customerId + "  " + page);
        subscription = Network.getYeapaoApi()
                .requestCircleListPageWithAccount(customerId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    private void getNetWork(String page) {
        LogUtil.e(TAG, "onRefresh Circle--------" + String.valueOf(page));
        subscription = Network.getYeapaoApi()
                .requestCircleListPage(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<CircleListModel> modelObserver = new Observer<CircleListModel>() {
        @Override
        public void onCompleted() {
            refreshLayout.setRefreshing(false);
            DialogUtils.cancelProgressDialog();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());
            refreshLayout.setRefreshing(false);

        }

        @Override
        public void onNext(CircleListModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                DialogUtils.cancelProgressDialog();
                if (currentPage == 0) {
                    LogUtil.e("---====--===", model.getData().getCommunityList().get(0).getFabulous());
                    mCircleListModel = model;
                    showResult(mCircleListModel);
                } else {
                    showResultAdd(model);
                    LogUtil.e(TAG + "  add model sum", String.valueOf(model.getData().getCommunityList().size()));
                    mCircleListModel.getData().getCommunityList().addAll(model.getData().getCommunityList());
                }
                totalPage = model.getData().getTotalPage();
            }
        }
    };

    private void showResultAdd(CircleListModel model) {
        circleMessageAdapter.loadMore(model);

    }

    public void getPraise(int position) {
        LogUtil.e(TAG + "------Praise", String.valueOf(mCircleListModel.getData().getCommunityList().get(position).getCommunityId()));
        subscription = Network.getYeapaoApi()
                .requestFinger(String.valueOf(mCircleListModel.getData().getCommunityList().get(position).getCommunityId()), GlobalDataYepao.getUser(getContext()).getId())
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
//                circleMessageAdapter.refreshItemPraise(praisePosition,"1");
                mCircleListModel.getData().getCommunityList().get(praisePosition).setFabulous("1");
                RecyclerView.ViewHolder viewHolder = rvCircleList.findViewHolderForAdapterPosition(praisePosition+1);//这边的praisePosition+1 为布局的位置
                if (viewHolder != null && viewHolder instanceof CircleMessageAdapter.CircleItemViewHolder) {
                    CircleMessageAdapter.CircleItemViewHolder itemHolder = (CircleMessageAdapter.CircleItemViewHolder) viewHolder;
                    Drawable img = getContext().getResources().getDrawable(R.drawable.circle_finger_s);
                    // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    itemHolder.tvFinger.setCompoundDrawables(img, null, null, null);
                    int sum = Integer.valueOf(itemHolder.tvFinger.getText().toString()) + 1;
                    itemHolder.tvFinger.setText(String.valueOf(sum));
                }

            }
        }
    };


    public void deletePraise(int position) {
        LogUtil.e(TAG + "------deletePraise", String.valueOf(mCircleListModel.getData().getCommunityList().get(position).getCommunityId()));
        subscription = Network.getYeapaoApi()
                .requestDeleteFinger(String.valueOf(mCircleListModel.getData().getCommunityList().get(position).getCommunityId()), GlobalDataYepao.getUser(getContext()).getId())
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
                mCircleListModel.getData().getCommunityList().get(praisePosition).setFabulous("0");
                RecyclerView.ViewHolder viewHolder = rvCircleList.findViewHolderForAdapterPosition(praisePosition+1);//这边的praisePosition+1 为布局的位置
                if (viewHolder != null && viewHolder instanceof CircleMessageAdapter.CircleItemViewHolder) {
                    CircleMessageAdapter.CircleItemViewHolder itemHolder = (CircleMessageAdapter.CircleItemViewHolder) viewHolder;
                    Drawable img = getContext().getResources().getDrawable(R.drawable.circle_finger_n);
                    // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    itemHolder.tvFinger.setCompoundDrawables(img, null, null, null);
                    int sum = Integer.valueOf(itemHolder.tvFinger.getText().toString()) - 1;
                    itemHolder.tvFinger.setText(String.valueOf(sum));
                }
            }
        }
    };


            private void getNetWorkSingleCommunity(String community,String customerId) {
                    LogUtil.e(TAG,community+customerId);
                    subscription = Network.getYeapaoApi()
                            .requestSingleCommunity(community,customerId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserverSingleCommunity );
                }

                  Observer<SingleCommunityModel> modelObserverSingleCommunity = new Observer<SingleCommunityModel>() {
                    @Override
                    public void onCompleted() {
                        DialogUtils.cancelProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());
                        currentPage = 0;
                        getNetWorkWithAccount(GlobalDataYepao.getUser(getContext()).getId(), String.valueOf(currentPage));
                    }

                    @Override
                    public void onNext(SingleCommunityModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            DialogUtils.cancelProgressDialog();
                            mSingleCommunityModel = model;
                            RecyclerView.ViewHolder viewHolder = rvCircleList.findViewHolderForAdapterPosition(singleCommunotyPosition + 1);//这边的praisePosition+1 为布局的位置
                            if (viewHolder != null && viewHolder instanceof CircleMessageAdapter.CircleItemViewHolder) {
                                CircleMessageAdapter.CircleItemViewHolder itemHolder = (CircleMessageAdapter.CircleItemViewHolder) viewHolder;
                                if (model.getData().getFabulous().equals("1")) {
                                    Drawable img = getContext().getResources().getDrawable(R.drawable.circle_finger_s);
                                    // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                                    itemHolder.tvFinger.setCompoundDrawables(img, null, null, null);
                                    int sum = Integer.valueOf(itemHolder.tvFinger.getText().toString()) - 1;
                                    itemHolder.tvFinger.setText(String.valueOf(sum));
                                } else {
                                    Drawable img = getContext().getResources().getDrawable(R.drawable.circle_finger_n);
                                    // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                                    itemHolder.tvFinger.setCompoundDrawables(img, null, null, null);
                                    int sum = Integer.valueOf(itemHolder.tvFinger.getText().toString()) - 1;
                                    itemHolder.tvFinger.setText(String.valueOf(sum));
                                }
                                itemHolder.tvFinger.setText(String.valueOf(model.getData().getThumbsUp()));
                                itemHolder.tvComment.setText(String.valueOf(model.getData().getCommentNumber()));

                            }
                        } else {
                            currentPage = 0;
                            getNetWorkWithAccount(GlobalDataYepao.getUser(getContext()).getId(), String.valueOf(currentPage));
                        }
                    }
                };

}
