package com.yeapao.andorid.homepage.lesson;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.FullyLinearLayoutManager;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.lessondetails.LessonDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by fujindong on 2017/7/11.
 */

public class LessonFragmentView extends Fragment implements LessonContract.View {

    private static final String TAG = "lessonFragmentView";

    @BindView(R.id.rv_lesson_list)
    RecyclerView rvLessonList;

//    @BindView(R.id.vp_lesson_image)
//    ViewPager vpLessonImage;
//
//    @BindView(R.id.ci_lesson_indicator)
//    CircleIndicator ciLessonIndicator;


    private LessonContract.Presenter mPresenter;

    private LessonMessageAdapter lessonMessageAdapter;


    public LessonFragmentView() {

    }


    public static LessonFragmentView newInstance() {
        return new LessonFragmentView();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.e(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        ButterKnife.bind(this, view);
        initViews(view);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e(TAG,"onResume");
        showResult();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e(TAG,"onDestroy");
    }

    @Override
    public void setPresenter(LessonContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvLessonList.setLayoutManager(llm);
        showResult();
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
                    startActivity(new Intent(getActivity(), LessonDetailActivity.class));
                }
            });
        } else {
            rvLessonList.setAdapter(lessonMessageAdapter);
            lessonMessageAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void showError() {

    }
}
