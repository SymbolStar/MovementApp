package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.SystemDateUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.FoodInfoModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/27.
 */

public class MyselfFoodActivity extends BaseActivity {

    private static final String TAG = "MyselfFoodActivity";
    @BindView(R.id.rv_food_list)
    RecyclerView rvFoodList;
    private LinearLayoutManager llm;
    private MyselfFoodMessageAdapter foodMessageAdapter;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfFoodActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_food);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvFoodList.setLayoutManager(llm);

        getData();
    }

    private void getData() {

        getNetWork( SystemDateUtil.getCurrentYYYYMMDD());
    }

    private void showResult() {
        if (foodMessageAdapter == null) {
            foodMessageAdapter = new MyselfFoodMessageAdapter(getContext());
            rvFoodList.setAdapter(foodMessageAdapter);

        } else {
            rvFoodList.setAdapter(foodMessageAdapter);
            foodMessageAdapter.notifyDataSetChanged();
        }
    }



            private void getNetWork(String date) {
                    LogUtil.e(TAG,date);
                    subscription = Network.getYeapaoApi()
                            .getFoodInfos(date)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( );
                }

                  Observer<FoodInfoModel> modelObserver = new Observer<FoodInfoModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(FoodInfoModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {

                        }
                    }
                };

    @Override
    protected void initTopBar() {
        initTitle("一周食谱");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
