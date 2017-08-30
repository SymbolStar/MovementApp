package com.yeapao.brake;

import com.scottfu.sflibrary.mvp.BasePresenter;
import com.scottfu.sflibrary.mvp.BaseView;
import com.yeapao.brake.bean.AccountMessage;
import com.yeapao.brake.bean.CheckInOrOutModel;
import com.yeapao.brake.bean.ScreenModel;

/**
 * Created by fujindong on 2017/7/6.
 */

public interface BrakeContract {

    interface View extends BaseView<Presenter> {

        void showResult(CheckInOrOutModel accountMessage);

        void showScreenResult(ScreenModel screenModel);

    }


    interface Presenter extends BasePresenter {

        void getData(String userId);

        void readCard();

        void outPutGPIO();


    }


}
