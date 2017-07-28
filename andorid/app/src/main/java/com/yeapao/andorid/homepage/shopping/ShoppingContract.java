package com.yeapao.andorid.homepage.shopping;

import com.scottfu.sflibrary.mvp.BasePresenter;
import com.scottfu.sflibrary.mvp.BaseView;
import com.yeapao.andorid.model.ShoppingDataModel;

/**
 * Created by fujindong on 2017/7/11.
 */

public interface ShoppingContract {

    interface View extends BaseView<Presenter> {
        void showResult(ShoppingDataModel shoppingDataModel);

        void startLoading();

        void stopLoading();
    }


    interface Presenter extends BasePresenter {

        void getData();

    }

}
