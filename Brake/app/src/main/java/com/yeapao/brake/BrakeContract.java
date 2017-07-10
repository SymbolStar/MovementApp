package com.yeapao.brake;

import com.scottfu.sflibrary.mvp.BasePresenter;
import com.scottfu.sflibrary.mvp.BaseView;
import com.yeapao.brake.bean.AccountMessage;

/**
 * Created by fujindong on 2017/7/6.
 */

public interface BrakeContract {

    interface View extends BaseView<Presenter> {

        void showResult(AccountMessage accountMessage);

    }


    interface Presenter extends BasePresenter {

        void getData(String userId);

    }


}
