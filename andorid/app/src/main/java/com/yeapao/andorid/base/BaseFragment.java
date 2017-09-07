package com.yeapao.andorid.base;

import android.support.v4.app.Fragment;

import rx.Subscription;

/**
 * Created by fujindong on 2017/9/6.
 */

public class BaseFragment extends Fragment {



    protected Subscription subscription;

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }
}
