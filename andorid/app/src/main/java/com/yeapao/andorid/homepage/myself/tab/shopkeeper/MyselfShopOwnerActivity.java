package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.IsAmShopModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/27.
 */

public class MyselfShopOwnerActivity extends BaseActivity {

    private static final String TAG = "MyselfShopOwnerActivity";
    @BindView(R.id.rl_shopkeeper_edit)
    RelativeLayout rlShopkeeperEdit;
    @BindView(R.id.rl_shopkeeper_lesson)
    RelativeLayout rlShopkeeperLesson;
    @BindView(R.id.tv_admission_num)
    TextView tvAdmissionNum;
    @BindView(R.id.tv_bespeak_num)
    TextView tvBespeakNum;
    @BindView(R.id.tv_total_sale_num)
    TextView tvTotalSaleNum;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfShopOwnerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_shop_owner);
        ButterKnife.bind(this);
        initTopBar();
        initView();

    }

    private void initView() {

        getAmShopData(GlobalDataYepao.getUser(getContext()).getId());
    }

    @Override
    protected void initTopBar() {
        initTitle("我是店长");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.rl_shopkeeper_edit, R.id.rl_shopkeeper_lesson})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_shopkeeper_edit:
                PhysicalReservationActivity.start(getContext());
                break;
            case R.id.rl_shopkeeper_lesson:
                ShopKeeperLessonReservationActivity.start(getContext());

                break;
        }
    }


    private void getAmShopData(String id) {
        LogUtil.e(TAG,"id------"+id);
        subscription = Network.getYeapaoApi()
                .getIsAnShop(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isAmShopModelObserver);
    }

    Observer<IsAmShopModel> isAmShopModelObserver = new Observer<IsAmShopModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());

        }

        @Override
        public void onNext(IsAmShopModel isAmShopModel) {
            LogUtil.e(TAG, isAmShopModel.getErrmsg());
            if (isAmShopModel.getErrmsg().equals("ok")) {

//                TODO 服务器返回空值

                tvTotalSaleNum.setText(isAmShopModel.getData().getTotalSale());
                tvAdmissionNum.setText(isAmShopModel.getData().getAdmissionNum());
                tvBespeakNum.setText(isAmShopModel.getData().getBespeakNum());
            }
        }
    };

}
