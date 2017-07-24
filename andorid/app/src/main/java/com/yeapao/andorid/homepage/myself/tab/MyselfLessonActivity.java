package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/23.
 */

public class MyselfLessonActivity extends BaseActivity {

    private static final String TAG = "MyselfLessonActivity";
    @BindView(R.id.tv_using)
    TextView tvUsing;
    @BindView(R.id.tv_overdue)
    TextView tvOverdue;
    @BindView(R.id.v_using_line)
    View vUsingLine;
    @BindView(R.id.v_overdue_line)
    View vOverdueLine;
    @BindView(R.id.rv_my_lesson_list)
    RecyclerView rvMyLessonList;

    private MyselfLessonMessageAdapter myselfLessonMessageAdapter;
    private LinearLayoutManager llm;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfLessonActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_lesson);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMyLessonList.setLayoutManager(llm);
        initData();
    }

    private void initData() {

        showResult();
    }

    private void showResult() {
        if (myselfLessonMessageAdapter == null) {
            myselfLessonMessageAdapter = new MyselfLessonMessageAdapter(getContext());
            rvMyLessonList.setAdapter(myselfLessonMessageAdapter);
            myselfLessonMessageAdapter.setItemOnClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(), String.valueOf(position));

                }
            });

        } else {
            rvMyLessonList.setAdapter(myselfLessonMessageAdapter);
            myselfLessonMessageAdapter.notifyDataSetChanged();
        }



    }

    @Override
    protected void initTopBar() {
        initTitle("我的课程");
        initBack();

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_using, R.id.tv_overdue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_using:
                tvUsing.setTextColor(getResources().getColor(R.color.myself_lesson_status_color));
                tvOverdue.setTextColor(getResources().getColor(R.color.text_color));
                vUsingLine.setVisibility(View.VISIBLE);
                vOverdueLine.setVisibility(View.GONE);
                break;
            case R.id.tv_overdue:
                tvUsing.setTextColor(getResources().getColor(R.color.text_color));
                tvOverdue.setTextColor(getResources().getColor(R.color.myself_lesson_status_color));
                vUsingLine.setVisibility(View.GONE);
                vOverdueLine.setVisibility(View.VISIBLE);
                break;
        }
    }
}
