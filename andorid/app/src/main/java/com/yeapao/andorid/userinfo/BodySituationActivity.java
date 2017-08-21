package com.yeapao.andorid.userinfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.homepage.myself.tab.health.BodySideDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/8/20.
 */

public class BodySituationActivity extends BaseActivity {

    private static final String TAG = "BodySituationActivity";
    @BindView(R.id.tv_body_status_1)
    TextView tvBodyStatus1;
    @BindView(R.id.tv_body_status_2)
    TextView tvBodyStatus2;
    @BindView(R.id.tv_body_status_4)
    TextView tvBodyStatus4;
    @BindView(R.id.tv_body_status_3)
    TextView tvBodyStatus3;
    @BindView(R.id.tv_body_status_5)
    TextView tvBodyStatus5;
    @BindView(R.id.tv_body_status_6)
    TextView tvBodyStatus6;
    @BindView(R.id.et_body_content)
    EditText etBodyContent;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BodySituationActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail_3);
        ButterKnife.bind(this);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("情况选择");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_body_status_1, R.id.tv_body_status_2, R.id.tv_body_status_4, R.id.tv_body_status_3, R.id.tv_body_status_5, R.id.tv_body_status_6, R.id.et_body_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_body_status_1:
                if (tvBodyStatus1.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
                    tvBodyStatus1.setTextColor(getResources().getColor(R.color.account_hint_text));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_no_n);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus1.setCompoundDrawables(nav_up, null, null , null);
                } else {
                    tvBodyStatus1.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_no_s);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus1.setCompoundDrawables(nav_up, null, null , null);
                }
                break;
            case R.id.tv_body_status_2:
                if (tvBodyStatus2.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
                    tvBodyStatus2.setTextColor(getResources().getColor(R.color.account_hint_text));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_blood_n);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus2.setCompoundDrawables(nav_up, null, null , null);
                } else {
                    tvBodyStatus2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_blood_s);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus2.setCompoundDrawables(nav_up, null, null , null);
                }
                break;
            case R.id.tv_body_status_4:
                if (tvBodyStatus4.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
                    tvBodyStatus4.setTextColor(getResources().getColor(R.color.account_hint_text));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_meniscus_n);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus4.setCompoundDrawables(nav_up, null, null , null);
                } else {
                    tvBodyStatus4.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_meniscus_s);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus4.setCompoundDrawables(nav_up, null, null , null);
                }

                break;
            case R.id.tv_body_status_3:
                if (tvBodyStatus3.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
                    tvBodyStatus3.setTextColor(getResources().getColor(R.color.account_hint_text));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_heart_n);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus3.setCompoundDrawables(nav_up, null, null , null);
                } else {
                    tvBodyStatus3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_heart_s);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus3.setCompoundDrawables(nav_up, null, null , null);
                }
                break;
            case R.id.tv_body_status_5:
                if (tvBodyStatus5.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
                    tvBodyStatus5.setTextColor(getResources().getColor(R.color.account_hint_text));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_waist_n);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus5.setCompoundDrawables(nav_up, null, null , null);
                } else {
                    tvBodyStatus5.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_waist_s);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus5.setCompoundDrawables(nav_up, null, null , null);
                }
                break;
            case R.id.tv_body_status_6:
                if (tvBodyStatus6.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
                    tvBodyStatus6.setTextColor(getResources().getColor(R.color.account_hint_text));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_other_n);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus6.setCompoundDrawables(nav_up, null, null , null);
                } else {
                    tvBodyStatus6.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_other_s);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus6.setCompoundDrawables(nav_up, null, null , null);
                }
                break;
            case R.id.et_body_content:

                break;
        }
    }
}
