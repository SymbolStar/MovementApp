package com.yeapao.brake;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.brake.api.ConstantBrake;
import com.yeapao.brake.api.NetImpl;
import com.yeapao.brake.bean.AccountMessage;

/**
 * Created by fujindong on 2017/7/7.
 */

public class BrakePresenter implements BrakeContract.Presenter {


    private BrakeContract.View mView;
    private Context mContext;

    private Gson gson = new Gson();

    public BrakePresenter(Context context, BrakeContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);

    }

    @Override
    public void start() {

    }

    @Override
    public void getData(String userId) {
        CloudClient.doHttpRequestV4(mContext, ConstantBrake.GET_USER_MESSAGE, NetImpl.getInstance().setUserParams(userId), null, new JSONResultHandler() {
            @Override
            public void onSuccess(String jsonString) {
                Log.e("success", jsonString);
                AccountMessage accountMessage = gson.fromJson(jsonString, AccountMessage.class);
                if (accountMessage.getRs().equals("Y")) {
                    mView.showResult(accountMessage);
                } else {
                    ToastManager.showToast(mContext,"会员信息错误");
                }
//                ToastManager.showToast(mContext, accountMessage.getCheckinfee());
            }

            @Override
            public void onError(VolleyError errorMessage) {
                Log.e("error", errorMessage.toString());
                ToastManager.showToast(mContext,errorMessage.toString());
            }
        });
    }
}
