package com.yeapao.andorid.api;

import android.app.Application;

import com.scottfu.sflibrary.util.NetworkState;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.YepaoApplication;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fujindong on 2017/8/3.
 */

public class Network {

    private static YeapaoApi yeapaoApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();


    public static YeapaoApi getYeapaoApi() {
        if (!NetworkState.networkConnected(YepaoApplication.getContextObject())) {
            ToastManager.repeatToast(YepaoApplication.getContextObject(),"无法连接到网络，请检查网络连接");
        }
        if ( yeapaoApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://47.92.113.97:8080/yepao/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            yeapaoApi = retrofit.create(YeapaoApi.class);
        }
        return yeapaoApi;
    }

}
