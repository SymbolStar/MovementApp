package com.yeapao.andorid.homepage.lesson;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.scottfu.sflibrary.popwindow.PopWindowCategory;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.storedetails.StoreDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/11.
 */

public class LessonFragmentView extends Fragment implements LessonContract.View {

    private static final String TAG = "lessonFragmentView";

    @BindView(R.id.rv_lesson_list)
    RecyclerView rvLessonList;

    @BindView(R.id.ll_Lesson_screening)
    LinearLayout llLessonScreening;
    @BindView(R.id.ll_lesson_time)
    LinearLayout llLessonTime;
    @BindView(R.id.ll_lesson_status)
    LinearLayout llLessonStatus;
    @BindView(R.id.ll_lesson_scope)
    LinearLayout llLessonScope;

    private PopWindowCategory popWindowCategory = null;

    private LessonContract.Presenter mPresenter;

    private LessonMessageAdapter lessonMessageAdapter;

    private LinearLayoutManager llm;

    private boolean isGome = true;
    private boolean mScreenIsClick = false;

    enum ButtonIndex {
        TIME,
        STATUS,
        SCOPE
    }

    ButtonIndex lastClickedIndex;

    private String[] itemTime = {"今天", "本周", "最近三天"};
    private String[] itemStatus = {"1", "2", "3"};
    private String[] itemScope = {"1", "2", "3"};

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
        ButterKnife.bind(this, view);
        lastClickedIndex = ButtonIndex.TIME;
        initViews(view);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e(TAG, "onResume");
        showResult();
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


        showResult();
        initPopWindow();
    }

    private void initPopWindow() {
        popWindowCategory = new PopWindowCategory();
        popWindowCategory.initPop(getActivity(), new PopWindowCategory.PopItemClick() {
            @Override
            public void onItemClick(int position) {

            }
        }, new PopWindowCategory.PopItemClick() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showResult() {
        if (lessonMessageAdapter == null) {

            lessonMessageAdapter = new LessonMessageAdapter(getActivity());
            rvLessonList.setAdapter(lessonMessageAdapter);
            lessonMessageAdapter.setItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getActivity(), "onclick");
                    startActivity(new Intent(getActivity(), StoreDetailActivity.class));
                }
            });
            lessonMessageAdapter.setScreeningListener(new LessonScreeningListener() {
                @Override
                public void screeningTitle(String title) {
                    isGome = false;
                    ToastManager.showToast(getActivity(), "screening");
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
        } else {
            rvLessonList.setAdapter(lessonMessageAdapter);
            lessonMessageAdapter.notifyDataSetChanged();
        }
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
        popWindowCategory.loadData(0, itemScope);
        popWindowCategory.showAsDropDown(getActivity().findViewById(R.id.line));
        popWindowCategory.update();
    }

    @Override
    public void showError() {

    }


    private void moveToPosition( int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = llm.findFirstVisibleItemPosition();
        int lastItem = llm.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem ){
            //当要置顶的项在当前显示的第一个项的前面时
            rvLessonList.scrollToPosition(n);
        }else if ( n <= lastItem ){
            //当要置顶的项已经在屏幕上显示时
            int top = rvLessonList.getChildAt(n - firstItem).getTop();
            rvLessonList.scrollBy(0, top);
        }else{
            //当要置顶的项在当前显示的最后一项的后面时
            rvLessonList.scrollToPosition(n);
            //这里这个变量是用在RecyclerView滚动监听里面的
            mScreenIsClick = true;
        }

    }

}
