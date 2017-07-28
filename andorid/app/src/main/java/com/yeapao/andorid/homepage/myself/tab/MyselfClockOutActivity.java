package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/23.
 */

public class MyselfClockOutActivity extends BaseActivity {

    private static final String TAG = "MyselfClockOutActivity";
    @BindView(R.id.tv_breakfest)
    TextView tvBreakfest;
    @BindView(R.id.tv_lunch)
    TextView tvLunch;
    @BindView(R.id.tv_dinner)
    TextView tvDinner;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_clock_out_content)
    EditText tvClockOutContent;
    @BindView(R.id.rb_admin)
    RadioButton rbAdmin;
    @BindView(R.id.iv_choose_image)
    ImageView ivChooseImage;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfClockOutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//初始隐藏键盘
        setContentView(R.layout.activity_clock_out);
        ButterKnife.bind(this);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("打卡");
        initBack();
        initRightText("发布");
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_breakfest, R.id.tv_lunch, R.id.tv_dinner, R.id.tv_weight, R.id.tv_clock_out_content, R.id.rb_admin, R.id.iv_choose_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_breakfest:
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                break;
            case R.id.tv_lunch:
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                break;
            case R.id.tv_dinner:
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                break;
            case R.id.tv_weight:
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                break;
            case R.id.tv_clock_out_content:
                break;
            case R.id.rb_admin:
                break;
            case R.id.iv_choose_image:
                break;
        }
    }
}
