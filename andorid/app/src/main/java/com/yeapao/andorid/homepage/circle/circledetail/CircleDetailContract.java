package com.yeapao.andorid.homepage.circle.circledetail;

import com.scottfu.sflibrary.mvp.BasePresenter;
import com.scottfu.sflibrary.mvp.BaseView;

/**
 * Created by fujindong on 2017/7/18.
 */

public interface CircleDetailContract  {

    interface View extends BaseView<Presenter> {
        void showResult();

    }


    interface Presenter extends BasePresenter {
    }


}
