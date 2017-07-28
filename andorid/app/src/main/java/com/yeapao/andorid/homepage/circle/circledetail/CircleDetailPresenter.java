package com.yeapao.andorid.homepage.circle.circledetail;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by fujindong on 2017/7/18.
 */

public class CircleDetailPresenter implements CircleDetailContract.Presenter {


    private Context mContext;
    private CircleDetailContract.View mView;

    public CircleDetailPresenter(@NonNull Context context,@NonNull CircleDetailContract.View view) {
        mContext = context;
        mView = view;
    }



    @Override
    public void start() {

    }
}
