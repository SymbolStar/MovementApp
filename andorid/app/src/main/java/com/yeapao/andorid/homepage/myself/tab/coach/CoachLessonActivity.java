package com.yeapao.andorid.homepage.myself.tab.coach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/31.
 */

public class CoachLessonActivity extends BaseActivity {

    private static final String TAG = "CoachLessonActivity";
    @BindView(R.id.tv_choose_all)
    TextView tvChooseAll;
    @BindView(R.id.cb_choose_all)
    CheckBox cbChooseAll;
    @BindView(R.id.rv_top)
    RelativeLayout rvTop;
    @BindView(R.id.tv_people_num)
    TextView tvPeopleNum;
    @BindView(R.id.tv_start_lesson)
    TextView tvStartLesson;
    @BindView(R.id.rv_coach_lesson_list)
    RecyclerView rvCoachLessonList;


    private CoachLessonCallMessageAdapter messageAdapter;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CoachLessonActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_lesson_call_name);
        ButterKnife.bind(this);
        initTopBar();
        initView();

    }

    private void initView() {
        rvCoachLessonList.setLayoutManager(new GridLayoutManager(this,3));

        getData();
    }

    private void getData() {
        showResult();
    }

    private void showResult() {
        if (messageAdapter == null) {
            messageAdapter = new CoachLessonCallMessageAdapter(getContext());
            rvCoachLessonList.setAdapter(messageAdapter);
            messageAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(), "onClick");
                    messageAdapter.refreshItem(position);

                }
            });
        } else {
            rvCoachLessonList.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void initTopBar() {
        initTitle("课程名");
        initRightText("课程详情");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_choose_all, R.id.cb_choose_all, R.id.tv_start_lesson})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_all:
                break;
            case R.id.cb_choose_all:
                break;
            case R.id.tv_start_lesson:
                CoachLessonStatusActivity.start(getContext());

                break;
        }
    }
}
