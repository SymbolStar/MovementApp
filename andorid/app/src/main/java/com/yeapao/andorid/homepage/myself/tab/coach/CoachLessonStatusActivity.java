package com.yeapao.andorid.homepage.myself.tab.coach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/31.
 */

public class CoachLessonStatusActivity extends BaseActivity {

    private static final String TAG = "CoachLessonStatusActivity";
    @BindView(R.id.rv_coach_lesson_status_list)
    RecyclerView rvCoachLessonStatusList;

    private CoachLessonStatusMessageAdapter messageAdapter;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CoachLessonStatusActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_lesson_status);
        ButterKnife.bind(this);
        initTopBar();
        initView();


    }

    private void initView() {
        rvCoachLessonStatusList.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();
    }

    private void getData() {

        showResult();

    }

    private void showResult() {

        if (messageAdapter == null) {
            messageAdapter = new CoachLessonStatusMessageAdapter(getContext());
            rvCoachLessonStatusList.setAdapter(messageAdapter);
        } else {
            rvCoachLessonStatusList.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();

        }

    }

    @Override
    protected void initTopBar() {
        initTitle("课程名");
        initBack();

    }

    @Override
    protected Context getContext() {
        return this;
    }
}
