package com.yeapao.andorid.homepage.circle;

import com.scottfu.sflibrary.mvp.BasePresenter;
import com.scottfu.sflibrary.mvp.BaseView;
import com.yeapao.andorid.model.CircleListModel;

/**
 * Created by fujindong on 2017/7/11.
 */

public interface CircleContract {

    interface View extends BaseView<Presenter> {
       void showResult(CircleListModel circleListModel);
    }


    interface Presenter extends BasePresenter {
    }

}
