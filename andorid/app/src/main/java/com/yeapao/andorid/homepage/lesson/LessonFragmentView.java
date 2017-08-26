package com.yeapao.andorid.homepage.lesson;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.popwindow.PopWindowCategory;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.message.MyMessageActivity;
import com.yeapao.andorid.lessondetails.LessonDetailActivity;
import com.yeapao.andorid.model.HomeList;
import com.yeapao.andorid.model.LessonScreeningData;
import com.yeapao.andorid.model.SelectHomeList;
import com.yeapao.andorid.storedetails.StoreDetailActivity;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by fujindong on 2017/7/11.
 */

public class LessonFragmentView extends Fragment implements LessonContract.View {

    private static final String TAG = "lessonFragmentView";

    @BindView(R.id.rv_lesson_list)
    RecyclerView rvLessonList;
    @BindView(R.id.srl_lesson)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.ll_Lesson_screening)
    LinearLayout llLessonScreening;
    @BindView(R.id.ll_lesson_time)
    LinearLayout llLessonTime;
    @BindView(R.id.ll_lesson_status)
    LinearLayout llLessonStatus;
    @BindView(R.id.ll_lesson_scope)
    LinearLayout llLessonScope;
    @BindView(R.id.tv_lesson_time)
    TextView tvLessonTime;
    @BindView(R.id.tv_lesson_status)
    TextView tvLessonStatus;
    @BindView(R.id.tv_lesson_scope)
    TextView tvLessonScope;
    @BindView(R.id.iv_message)
    ImageView IvMessage;


    private PopWindowCategory popWindowCategory = null;

    private LessonContract.Presenter mPresenter;

    private LessonMessageAdapter lessonMessageAdapter;

    private LinearLayoutManager llm;

    private Badge messageBadge;

    private boolean isGome = true;
    private boolean mScreenIsClick = false;
    private LessonScreeningData lessonScopeData = new LessonScreeningData();


    enum ButtonIndex {
        TIME,
        STATUS,
        SCOPE
    }

    ButtonIndex lastClickedIndex;

    private String[] itemTime = {"全部", "今天", "最近三天", "本周"};
    private String[] itemStatus = {"全部状态", "有效", "失效"};
    private String[] itemScope;

    private HomeList mHomeList;

    public LessonFragmentView() {

    }



    public static LessonFragmentView newInstance() {
        return new LessonFragmentView();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.e(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        unbinder =ButterKnife.bind(this, view);
        lastClickedIndex = ButtonIndex.TIME;
        mPresenter.start();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        initViews(view);
//        initBadge();
        return view;
    }

    private void initBadge() {
        messageBadge = new QBadgeView(getContext())
                .setBadgeNumber(1)
                .setGravityOffset(0, 5, true)
                .bindTarget(IvMessage)
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                            ToastManager.showToast(getContext(), "badge is removed");
                        }
                    }
                });
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e(TAG, "onResume");
        mPresenter.getData();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e(TAG, "onDestroy");
    }

    @Override
    public void setPresenter(LessonContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvLessonList.setLayoutManager(llm);

        IvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e(TAG,"message");

                if (GlobalDataYepao.isLogin()) {
                    MyMessageActivity.start(getContext());
                } else {
                    ToastManager.showToast(getContext(),"请先登陆");
                }
            }
        });

//        TODO
        rvLessonList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                if (mScreenIsClick ){
//                    mScreenIsClick = false;
////                    //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
////                    int n = mIndex - mLinearLayoutManager.findFirstVisibleItemPosition();
////                    if ( 0 <= n && n < mRecyclerView.getChildCount()){
////                        //获取要置顶的项顶部离RecyclerView顶部的距离
////                        int top = mRecyclerView.getChildAt(n).getTop();
////                        //最后的移动
//                        mRecyclerView.scrollBy(0, top);
//                    }


                View view = llm.findViewByPosition(0);
                if (view == null) {
                    return;
                } else {
                    LogUtil.e(TAG, String.valueOf(view.getBottom()));
                    if (view.getBottom() <= 120 && isGome == true) {

                        llLessonScreening.setVisibility(View.VISIBLE);
                        isGome = false;

                    } else if (view.getBottom() > 120 && isGome == false) {
                        llLessonScreening.setVisibility(View.GONE);
                        isGome = true;
                    }
                }


            }
        });


//        showResult();
        initPopWindow();
    }

    private void initPopWindow() {
        popWindowCategory = new PopWindowCategory();
        popWindowCategory.initPop(getActivity(), new PopWindowCategory.PopItemClick() {
            @Override
            public void onItemClick(int position) {
                LogUtil.e("LessonFragment", "pop is clicked");
                if (lastClickedIndex == ButtonIndex.TIME) {
                    switch (position) {
                        case 0:
                            lessonScopeData.setScopeTime("-1");
                            lessonScopeData.setScopeTimeName("全部时间");
                            break;
                        case 1:
                            lessonScopeData.setScopeTime("1");
                            lessonScopeData.setScopeTimeName("今天");
                            break;
                        case 2:
                            lessonScopeData.setScopeTime("3");
                            lessonScopeData.setScopeTimeName("最近三天");
                            break;
                        case 3:
                            lessonScopeData.setScopeTime("7");
                            lessonScopeData.setScopeTimeName("本周");
                            break;
                    }
                } else if (lastClickedIndex == ButtonIndex.STATUS) {
                    if (position == 0) {
                        lessonScopeData.setStatus("0");
                        lessonScopeData.setStatusName("全部状态");
                    } else if (position == 1) {
                        lessonScopeData.setStatus("1");
                        lessonScopeData.setStatusName("有效");
                    } else if (position == 2) {
                        lessonScopeData.setStatus("2");
                        lessonScopeData.setStatusName("无效");
                    }
                } else if (lastClickedIndex == ButtonIndex.SCOPE) {
                    lessonScopeData.setRegion(itemScope[position]);
                    lessonScopeData.setRegionName(itemScope[position]);
                }
                setScreeningTab();
                lessonMessageAdapter.refreshLessonScreening(lessonScopeData);
                mPresenter.getLessonScreeningData(lessonScopeData.getScopeTime(), lessonScopeData.getStatus(),
                        lessonScopeData.getRegion());
                popWindowCategory.dismiss();
                llLessonScreening.setVisibility(View.GONE);
                isGome = true;
            }
        }, new PopWindowCategory.PopItemClick() {
            @Override
            public void onItemClick(int position) {
            }
        });
    }


    private void setScreeningTab() {
        tvLessonTime.setText(lessonScopeData.getScopeTimeName());
        tvLessonStatus.setText(lessonScopeData.getStatusName());
        tvLessonScope.setText(lessonScopeData.getRegionName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @Override
    public void showResult(final HomeList homeList) {
        mHomeList = homeList;
//        if (lessonMessageAdapter == null) {
            lessonMessageAdapter = new LessonMessageAdapter(getActivity(), homeList);
            rvLessonList.setAdapter(lessonMessageAdapter);
            lessonMessageAdapter.setItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    LessonDetailActivity.start(getContext(),homeList.getShopScheduleList().get(position).getScheduleId());
                }
            });
            lessonMessageAdapter.setScreeningListener(new LessonScreeningListener() {
                @Override
                public void screeningTitle(String title) {
                    isGome = false;
//                    lessonMessageAdapter.notifyItemRemoved(0);
                    moveToPosition(1);
                    llLessonScreening.setVisibility(View.VISIBLE);
                    if (title.equals("time")) {
                        selectTime();
                    } else if (title.equals("status")) {
                        selectStatus();
                    } else {
                        selectScope();
                    }

                }
            });
            lessonMessageAdapter.setReservationListener(new ReservationListener() {
                @Override
                public void onReservationClickListener(String scheduleId, String curriculumId, String id) {

                    mPresenter.reservationRequest(scheduleId,curriculumId,id);
                }
            });
//        } else {
//            rvLessonList.setAdapter(lessonMessageAdapter);
//            lessonMessageAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public void showSelectResult(SelectHomeList homeList) {
        lessonMessageAdapter.refreshShopScheduleList(homeList);
    }


    @Override
    public void startLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }


    @OnClick(R.id.ll_lesson_time)
    void selectTime() {
        if (lastClickedIndex == ButtonIndex.TIME && popWindowCategory.isShowing()) {
            popWindowCategory.dismiss();
            return;
        }
        lastClickedIndex = ButtonIndex.TIME;
        popWindowCategory.loadData(0, itemTime);
        popWindowCategory.showAsDropDown(getActivity().findViewById(R.id.line));
        popWindowCategory.update();
    }

    @OnClick(R.id.ll_lesson_status)
    void selectStatus() {
        if (lastClickedIndex == ButtonIndex.STATUS && popWindowCategory.isShowing()) {
            popWindowCategory.dismiss();
            return;
        }
        lastClickedIndex = ButtonIndex.STATUS;
        popWindowCategory.loadData(0, itemStatus);
        popWindowCategory.showAsDropDown(getActivity().findViewById(R.id.line));
        popWindowCategory.update();
    }

    @OnClick(R.id.ll_lesson_scope)
    void selectScope() {
        if (lastClickedIndex == ButtonIndex.SCOPE && popWindowCategory.isShowing()) {
            popWindowCategory.dismiss();
            return;
        }
        lastClickedIndex = ButtonIndex.SCOPE;


        if (mHomeList.getAreaList() != null) {
            itemScope = mHomeList.getAreaList();
        }

        popWindowCategory.loadData(0, itemScope);
        popWindowCategory.showAsDropDown(getActivity().findViewById(R.id.line));
        popWindowCategory.update();
    }

    @Override
    public void showError() {

    }


    public void goneScreening() {
        if (llLessonScreening.getVisibility() == View.VISIBLE) {
            llLessonScreening.setVisibility(View.GONE);
            isGome = true;
        }
    }


    private void moveToPosition(int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = llm.findFirstVisibleItemPosition();
        int lastItem = llm.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            rvLessonList.scrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = rvLessonList.getChildAt(n - firstItem).getTop();
            rvLessonList.scrollBy(0, top);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            rvLessonList.scrollToPosition(n);
            //这里这个变量是用在RecyclerView滚动监听里面的
            mScreenIsClick = true;
        }

    }




}
