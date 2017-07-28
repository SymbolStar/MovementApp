package com.yeapao.andorid.homepage.shopping;

import android.content.Context;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.homepage.circle.CircleContract;
import com.yeapao.andorid.model.ShoppingDataModel;

/**
 * Created by fujindong on 2017/7/11.
 */

public class ShoppingPresenter implements ShoppingContract.Presenter {

    private static final String TAG = "ShoppingPresenter";

    private Context mContext;
    private ShoppingContract.View mView;
    private Gson gson = new Gson();

    public ShoppingPresenter(Context context, ShoppingContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.startLoading();
        getData();
    }

    @Override
    public void getData() {
        CloudClient.getHttpRequest(mContext, ConstantYeaPao.GET_SHOPPING_LIST, new JSONResultHandler() {
            @Override
            public void onSuccess(String jsonString) {
                LogUtil.e("ShoppingPresenter", jsonString);
                ShoppingDataModel shoppingDataModel = gson.fromJson(jsonString, ShoppingDataModel.class);
                if (shoppingDataModel.getErrmsg().equals("ok")) {
                    mView.stopLoading();
                    mView.showResult(shoppingDataModel);
                } else {
                    ToastManager.showToast(mContext,"加载失败");
                }
            }

            @Override
            public void onError(VolleyError errorMessage) {
                LogUtil.e(TAG, errorMessage.toString());
                ToastManager.showToast(mContext,"加载失败");
            }
        });
    }
}
