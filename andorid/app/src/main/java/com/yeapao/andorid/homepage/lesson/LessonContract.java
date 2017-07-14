package com.yeapao.andorid.homepage.lesson;

import com.scottfu.sflibrary.mvp.BasePresenter;
import com.scottfu.sflibrary.mvp.BaseView;

/**
 * Created by fujindong on 2017/7/11.
 */

public interface LessonContract {


    interface View extends BaseView<Presenter> {

        void showResult();

        void showError();

    }


    interface Presenter extends BasePresenter {

        void getData();

    }

}

