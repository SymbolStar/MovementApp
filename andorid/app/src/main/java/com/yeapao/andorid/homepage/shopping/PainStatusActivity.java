package com.yeapao.andorid.homepage.shopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.util.PainPickerDialog;
import com.yeapao.andorid.util.PickerPainListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/8/15.
 */

public class PainStatusActivity extends BaseActivity {

    private static final String TAG = "PainStatusActivity";
    @BindView(R.id.iv_change)
    ImageView ivChange;
    @BindView(R.id.tv_pain_men_front_1)
    TextView tvPainMenFront1;
    @BindView(R.id.tv_pain_men_front_2)
    TextView tvPainMenFront2;
    @BindView(R.id.tv_pain_men_front_7)
    TextView tvPainMenFront7;
    @BindView(R.id.tv_pain_men_front_9)
    TextView tvPainMenFront9;
    @BindView(R.id.tv_pain_men_front_11)
    TextView tvPainMenFront11;
    @BindView(R.id.tv_pain_men_front_10)
    TextView tvPainMenFront10;
    @BindView(R.id.tv_pain_men_front_8)
    TextView tvPainMenFront8;
    @BindView(R.id.tv_pain_men_front_6)
    TextView tvPainMenFront6;
    @BindView(R.id.tv_pain_men_front_4)
    TextView tvPainMenFront4;
    @BindView(R.id.tv_pain_men_front_3)
    TextView tvPainMenFront3;
    @BindView(R.id.tv_pain_men_front_5)
    TextView tvPainMenFront5;
    @BindView(R.id.tv_pain_men_front_12)
    TextView tvPainMenFront12;

    private PainPickerDialog mpainPickerDialog;

    private TextView currentTextView;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PainStatusActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_man_positive);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        mpainPickerDialog = new PainPickerDialog();
        mpainPickerDialog.setPickerPainListener(new PickerPainListener() {
            @Override
            public void getPainValue(String value) {

                String[] pain = currentTextView.getText().toString().split("  ");
                pain[1] = value;

                currentTextView.setText(pain[0]+"  "+pain[1]);
            }

            @Override
            public void cancel() {
                String[] pain = currentTextView.getText().toString().split("  ");
                pain[1] = "0";

                currentTextView.setText(pain[0]+"  "+pain[1]);
                mpainPickerDialog.dismiss();

            }

            @Override
            public void determine() {
                mpainPickerDialog.dismiss();

            }
        });
    }

    private void showpickerPain() {
        if (mpainPickerDialog.isVisible()) {
            mpainPickerDialog.dismiss();
        } else {
            mpainPickerDialog.show(getSupportFragmentManager(), "date");
        }


    }

    @Override
    protected void initTopBar() {
        initTitle("疼痛记录");
        initBack();
        initRightText("完成");
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.iv_change, R.id.tv_pain_men_front_1, R.id.tv_pain_men_front_2, R.id.tv_pain_men_front_7, R.id.tv_pain_men_front_9, R.id.tv_pain_men_front_11, R.id.tv_pain_men_front_10, R.id.tv_pain_men_front_8, R.id.tv_pain_men_front_6, R.id.tv_pain_men_front_4, R.id.tv_pain_men_front_3, R.id.tv_pain_men_front_5,R.id.tv_pain_men_front_12})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_change:
                break;
            case R.id.tv_pain_men_front_1:
                currentTextView = tvPainMenFront1;
                showpickerPain();
                break;
            case R.id.tv_pain_men_front_2:
                currentTextView = tvPainMenFront2;
                showpickerPain();
                break;
            case R.id.tv_pain_men_front_7:
                currentTextView = tvPainMenFront7;
                showpickerPain();
                break;
            case R.id.tv_pain_men_front_9:
                currentTextView = tvPainMenFront9;
                showpickerPain();
                break;
            case R.id.tv_pain_men_front_11:
                currentTextView = tvPainMenFront11;
                showpickerPain();
                break;
            case R.id.tv_pain_men_front_10:
                currentTextView = tvPainMenFront10;
                showpickerPain();
                break;
            case R.id.tv_pain_men_front_8:
                currentTextView = tvPainMenFront8;
                showpickerPain();
                break;
            case R.id.tv_pain_men_front_6:
                currentTextView = tvPainMenFront6;
                showpickerPain();
                break;
            case R.id.tv_pain_men_front_4:
                currentTextView = tvPainMenFront4;
                showpickerPain();
                break;
            case R.id.tv_pain_men_front_3:
                currentTextView = tvPainMenFront3;
                showpickerPain();
                break;
            case R.id.tv_pain_men_front_5:
                currentTextView = tvPainMenFront5;
                showpickerPain();
                break;
            case R.id.tv_pain_men_front_12:
                currentTextView = tvPainMenFront12;
                showpickerPain();
                break;
        }
    }
}
