package com.yeapao.andorid.homepage.lesson;

import android.content.Context;

import com.google.gson.Gson;
import com.yeapao.andorid.homepage.circle.CircleContract;

/**
 * Created by fujindong on 2017/7/11.
 */

public class LessonPresenter implements LessonContract.Presenter {


    private Context mContext;
    private LessonContract.View mView;
    private Gson gson = new Gson();


    public LessonPresenter(Context context, LessonContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);

    }


    @Override
    public void start() {

    }

    @Override
    public void getData() {

    }
}
