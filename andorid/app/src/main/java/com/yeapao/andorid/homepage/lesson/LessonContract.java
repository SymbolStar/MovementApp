package com.yeapao.andorid.homepage.lesson;

import com.scottfu.sflibrary.mvp.BasePresenter;
import com.scottfu.sflibrary.mvp.BaseView;
import com.yeapao.andorid.model.HomeList;
import com.yeapao.andorid.model.SelectHomeList;

/**
 * Created by fujindong on 2017/7/11.
 */

public interface LessonContract {


    interface View extends BaseView<Presenter> {

        void showResult(HomeList homeList);

        void startLoading();

        void stopLoading();

        void showError();

        void showSelectResult(SelectHomeList homeList);

    }


    interface Presenter extends BasePresenter {

        void getData();

        void getLessonScreeningData(String time, String status, String region);

        void reservationRequest(String scheduleId, String curriculumId, String id);
    }

}

