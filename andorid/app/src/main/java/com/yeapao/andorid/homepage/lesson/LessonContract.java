package com.yeapao.andorid.homepage.lesson;

import com.scottfu.sflibrary.mvp.BasePresenter;
import com.scottfu.sflibrary.mvp.BaseView;
import com.yeapao.andorid.model.HomeList;

/**
 * Created by fujindong on 2017/7/11.
 */

public interface LessonContract {


    interface View extends BaseView<Presenter> {

        void showResult(HomeList homeList);

        void startLoading();

        void stopLoading();

        void showError();

        void showSelectResult(HomeList homeList);

    }


    interface Presenter extends BasePresenter {

        void getData();

        void getLessonScreeningData(String time, String status, String region);

    }

}

