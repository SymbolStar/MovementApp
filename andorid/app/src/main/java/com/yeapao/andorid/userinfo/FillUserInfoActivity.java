package com.yeapao.andorid.userinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/8/20.
 */

public class FillUserInfoActivity extends BaseActivity {

    private static final String TAG = "FillUserInfoActivity";
    @BindView(R.id.tv_birth_date)
    TextView tvBirthDate;
    @BindView(R.id.tv_high)
    TextView tvHigh;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.iv_boy)
    ImageView ivBoy;
    @BindView(R.id.tv_boy)
    TextView tvBoy;
    @BindView(R.id.iv_girl)
    ImageView ivGirl;
    @BindView(R.id.tv_girl)
    TextView tvGirl;
    @BindView(R.id.v_boy)
    View vBoy;
    @BindView(R.id.v_girl)
    View vGirl;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FillUserInfoActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        ButterKnife.bind(this);
        initTopBar();

    }

    @Override
    protected void initTopBar() {
        initTitle("完善资料");
        initBack();

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_birth_date, R.id.tv_high, R.id.tv_weight, R.id.tv_next, R.id.v_boy, R.id.v_girl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_birth_date:
                break;
            case R.id.tv_high:
                break;
            case R.id.tv_weight:
                break;
            case R.id.tv_next:
                FitnessActivity.start(getContext());
                break;
            case R.id.v_boy:
                tvBoy.setTextColor(getResources().getColor(R.color.colorPrimary));
                ivBoy.setImageDrawable(getResources().getDrawable(R.drawable.health_boy_s));
                tvGirl.setTextColor(getResources().getColor(R.color.account_hint_text));
                ivGirl.setImageDrawable(getResources().getDrawable(R.drawable.health_girl_n));
                break;
            case R.id.v_girl:
                tvBoy.setTextColor(getResources().getColor(R.color.account_hint_text));
                ivBoy.setImageDrawable(getResources().getDrawable(R.drawable.health_boy_n));
                tvGirl.setTextColor(getResources().getColor(R.color.colorPrimary));
                ivGirl.setImageDrawable(getResources().getDrawable(R.drawable.health_girl_s));
                break;
        }
    }
}
