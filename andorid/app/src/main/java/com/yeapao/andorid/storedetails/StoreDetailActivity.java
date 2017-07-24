package com.yeapao.andorid.storedetails;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.NetImpl;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.StoreDetailModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/13.
 */

public class StoreDetailActivity extends BaseActivity {


    private static final String TAG = "StoreDetailActivity";
    private StoreDetailModel storeDetailModel = new StoreDetailModel();
    private Gson gson = new Gson();

    @BindView(R.id.tv_link)
    TextView tvLink;
    @BindView(R.id.tv_do)
    TextView tvDo;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_store_address)
    TextView tvStoreAddress;
    @BindView(R.id.tv_store_info)
    TextView tvStoreInfo;
    @BindView(R.id.iv_reservation_image)
    ImageView ivReservationImage;
    @BindView(R.id.tv_lesson_reservation)
    TextView tvLessonReservation;
    @BindView(R.id.rl_lesson_reservation)
    RelativeLayout rlLessonReservation;
    @BindView(R.id.tv_open_time)
    TextView tvOpenTime;
    @BindView(R.id.tv_tell)
    TextView tvTell;
    @BindView(R.id.tv_store_intro)
    TextView tvStoreIntro;
    @BindView(R.id.tv_warn_hint)
    TextView tvWarnHint;
    @BindView(R.id.tv_staff_name)
    TextView tvStaffName;
    @BindView(R.id.tv_good)
    TextView tvGood;
    @BindView(R.id.tv_qualifications)
    TextView tvQualifications;
    @BindView(R.id.rb_star)
    RatingBar rbStar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        ButterKnife.bind(this);
        initTopBar();
        getData();

    }


    @Override
    protected void initTopBar() {
        initBack();
        initTitle("商店详情");
        initRightIcon(getResources().getDrawable(R.drawable.shop_share));
        ivRightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastManager.showToast(getContext(), "share");
            }
        });
    }


    private void getData() {
        CloudClient.doHttpRequest(getContext(), ConstantYeaPao.GET_STORE_DETAIL, NetImpl.getInstance().getStoreDetail("1"), null, new JSONResultHandler() {
            @Override
            public void onSuccess(String jsonString) {
                LogUtil.e(TAG, jsonString);
                storeDetailModel = gson.fromJson(jsonString, StoreDetailModel.class);
                if (storeDetailModel.getErrmsg().equals("ok")) {
                    setData();
                }
            }

            @Override
            public void onError(VolleyError errorMessage) {

            }
        });
    }

    private void setData() {
        tvStoreName.setText("店铺名称"+storeDetailModel.getData().getShopName());
        tvStoreAddress.setText("店铺地址"+storeDetailModel.getData().getAddress());
        tvOpenTime.setText(storeDetailModel.getData().getBusinessHoursStart()+"-"+storeDetailModel.getData().getBusinessHoursEnd());
        tvTell.setText(storeDetailModel.getData().getPhone());
        tvStoreIntro.setText(storeDetailModel.getData().getSummary());
        tvWarnHint.setText(storeDetailModel.getData().getPrompt());
        tvStaffName.setText(storeDetailModel.getData().getStaffName());
        tvGood.setText(storeDetailModel.getData().getBeGoodAt());
        tvQualifications.setText(storeDetailModel.getData().getQualifications());
}


    @Override
    protected Context getContext() {
        return this;
    }


}
