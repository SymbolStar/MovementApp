package com.yeapao.andorid.homepage.myself;

import com.scottfu.sflibrary.mvp.BasePresenter;
import com.scottfu.sflibrary.mvp.BaseView;
import com.yeapao.andorid.homepage.circle.CircleContract;
import com.yeapao.andorid.model.UserData;

/**
 * Created by fujindong on 2017/7/11.
 */

public interface MyselfContract {


    interface View extends BaseView<Presenter> {

        void showResult(UserData userData);

        void initViewClick(boolean flag);

        void refreshUserData();
    }


    interface Presenter extends BasePresenter {

    }

}
