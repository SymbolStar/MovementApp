package com.yeapao.andorid.homepage.map.repository;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.CalorieMessageModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/9/28.
 */

public class PaySuccessActivity extends BaseActivity {
    private static final String TAG = "PaySuccessActivity";
    @BindView(R.id.iv_food)
    ImageView ivFood;
    @BindView(R.id.tv_food_name)
    TextView tvFoodName;
    @BindView(R.id.tv_food_sum)
    TextView tvFoodSum;
    @BindView(R.id.tv_sport_time)
    TextView tvSportTime;
    @BindView(R.id.tv_sport_order)
    TextView tvSportOrder;
    @BindView(R.id.tv_sport_evaluate)
    TextView tvSportEvaluate;
    @BindView(R.id.tv_sprot_tips)
    TextView tvSprotTips;

    private CalorieMessageModel mMessageModel = new CalorieMessageModel();
    private String time;

    public static void start(Context content, String time) {
        Intent intent = new Intent();
        intent.putExtra("time", time);
        intent.setClass(content, PaySuccessActivity.class);
        content.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);
        initTopBar();
        Intent intent = getIntent();
        time = intent.getStringExtra("time");
        getNetWork(time);

    }

    private void showResult() {
        tvSportTime.setText(time + "min");
        tvSportOrder.setText(getSportOrder(mMessageModel.getData().getObjectiveId()));
        tvFoodName.setText("("+getSprotText()+")");
        ivFood.setImageDrawable(getSprotImage());
        tvSportTime.setText(getTimeLevel());
    }

    @Override
    protected void initTopBar() {
        initTitle("支付成功");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private void getNetWork(String time) {
        LogUtil.e(TAG, time);
        subscription = Network.getYeapaoApi()
                .requestCalorie(GlobalDataYepao.getUser(getContext()).getId(), time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<CalorieMessageModel> modelObserver = new Observer<CalorieMessageModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(CalorieMessageModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                showResult();
            }
        }
    };

    private String getSportOrder(int flag) {
        switch (flag) {
            case 1:
                return "减脂";
            case 2:
                return "增肌";
            case 3:
                return "缓解肌肉疲劳";
            case 4:
                return "释放压力";
        }
        return "";
    }


    private Drawable getSprotImage() {
        if (mMessageModel.getData().getGender().equals("1")) {
            switch (mMessageModel.getData().getFood()) {
                case 1:
                    return getResources().getDrawable(R.drawable.cola);
                case 2:
                    return getResources().getDrawable(R.drawable.hamburger);
                case 3:
                    return getResources().getDrawable(R.drawable.pork_chop);
                case 4:
                    return getResources().getDrawable(R.drawable.chunjuan);
                case 5:
                    return getResources().getDrawable(R.drawable.chips);
            }
        } else {
            switch (mMessageModel.getData().getFood()) {
                case 1:
                    return getResources().getDrawable(R.drawable.sprite);
                case 2:
                    return getResources().getDrawable(R.drawable.puff);
                case 3:
                    return getResources().getDrawable(R.drawable.egg_tart);
                case 4:
                    return getResources().getDrawable(R.drawable.cake);
                case 5:
                    return getResources().getDrawable(R.drawable.hot_dog);
            }
        }
        return null;
    }

    private String getSprotText() {
        if (mMessageModel.getData().getGender().equals("1")) {
            switch (mMessageModel.getData().getFood()) {
                case 1:
                    return getResources().getString(R.string.food_man_1);
                case 2:
                    return getResources().getString(R.string.food_man_2);
                case 3:
                    return getResources().getString(R.string.food_man_3);
                case 4:
                    return getResources().getString(R.string.food_man_4);
                case 5:
                    return getResources().getString(R.string.food_man_5);
            }
        } else {
            switch (mMessageModel.getData().getFood()) {
                case 1:
                    return getResources().getString(R.string.food_women_1);
                case 2:
                    return getResources().getString(R.string.food_women_2);
                case 3:
                    return getResources().getString(R.string.food_women_3);
                case 4:
                    return getResources().getString(R.string.food_women_4);
                case 5:
                    return getResources().getString(R.string.food_women_5);
            }
        }
        return "";
    }

    private String getTimeLevel() {
        int times = Integer.valueOf(time);
        if (times <= 25) {
            return "较短";
        } else if (times <= 35) {
            return "合适";
        } else {
            return "较长";
        }
    }
}
