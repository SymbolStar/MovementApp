package com.yeapao.andorid.lessondetails;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/7/14.
 */

public class LessonDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initBack();
        initTitle("课程详情");
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
