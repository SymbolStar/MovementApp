package com.yeapao.andorid.userinfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.CustomerData;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/8/20.
 */

public class FitnessActivity extends BaseActivity {

    private static final String TAG = "FitnessActivity";
    @BindView(R.id.tv_purpose_1)
    TextView tvPurpose1;
    @BindView(R.id.tv_purpose_2)
    TextView tvPurpose2;
    @BindView(R.id.tv_purpose_3)
    TextView tvPurpose3;
    @BindView(R.id.tv_purpose_4)
    TextView tvPurpose4;
    @BindView(R.id.tv_next)
    TextView tvNext;


    private String type = "0";


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FitnessActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail_2);
        ButterKnife.bind(this);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("健身目的");
        initBack();
    }

    private void refreshView() {
        tvPurpose1.setTextColor(getResources().getColor(R.color.account_hint_text));
        Drawable nav_up=getResources().getDrawable(R.drawable.health_reduce_fat_n);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        tvPurpose1.setCompoundDrawables(nav_up, null, null , null);

        tvPurpose2.setTextColor(getResources().getColor(R.color.account_hint_text));
        Drawable nav_up2=getResources().getDrawable(R.drawable.health_gain_muscle_n);
        nav_up2.setBounds(0, 0, nav_up2.getMinimumWidth(), nav_up2.getMinimumHeight());
        tvPurpose2.setCompoundDrawables(nav_up2, null, null , null);

        tvPurpose3.setTextColor(getResources().getColor(R.color.account_hint_text));
        Drawable nav_up3=getResources().getDrawable(R.drawable.health_relaxed_n);
        nav_up3.setBounds(0, 0, nav_up3.getMinimumWidth(), nav_up3.getMinimumHeight());
        tvPurpose3.setCompoundDrawables(nav_up3, null, null , null);

        tvPurpose4.setTextColor(getResources().getColor(R.color.account_hint_text));
        Drawable nav_up4=getResources().getDrawable(R.drawable.health_pressure_n);
        nav_up4.setBounds(0, 0, nav_up4.getMinimumWidth(), nav_up4.getMinimumHeight());
        tvPurpose4.setCompoundDrawables(nav_up4, null, null , null);
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_purpose_1, R.id.tv_purpose_2, R.id.tv_purpose_3, R.id.tv_purpose_4, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_purpose_1:
                type = "1";
//                if (tvPurpose1.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
//                    tvPurpose1.setTextColor(getResources().getColor(R.color.account_hint_text));
//                    Drawable nav_up=getResources().getDrawable(R.drawable.health_reduce_fat_n);
//                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                    tvPurpose1.setCompoundDrawables(nav_up, null, null , null);
//                } else {
//                    tvPurpose1.setTextColor(getResources().getColor(R.color.colorPrimary));
//                    Drawable nav_up=getResources().getDrawable(R.drawable.health_reduce_fat_s);
//                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                    tvPurpose1.setCompoundDrawables(nav_up, null, null , null);
//                }
                refreshView();
                tvPurpose1.setTextColor(getResources().getColor(R.color.colorPrimary));
                Drawable nav_up=getResources().getDrawable(R.drawable.health_reduce_fat_s);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                tvPurpose1.setCompoundDrawables(nav_up, null, null , null);


                break;
            case R.id.tv_purpose_2:
                type = "2";
//                if (tvPurpose2.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
//                    tvPurpose2.setTextColor(getResources().getColor(R.color.account_hint_text));
//                    Drawable nav_up=getResources().getDrawable(R.drawable.health_gain_muscle_n);
//                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                    tvPurpose2.setCompoundDrawables(nav_up, null, null , null);
//                } else {
//                    tvPurpose2.setTextColor(getResources().getColor(R.color.colorPrimary));
//                    Drawable nav_up=getResources().getDrawable(R.drawable.health_gain_muscle_s);
//                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                    tvPurpose2.setCompoundDrawables(nav_up, null, null , null);
//                }
                refreshView();
                tvPurpose2.setTextColor(getResources().getColor(R.color.colorPrimary));
                Drawable nav_up2 = getResources().getDrawable(R.drawable.health_gain_muscle_s);
                nav_up2.setBounds(0, 0, nav_up2.getMinimumWidth(), nav_up2.getMinimumHeight());
                tvPurpose2.setCompoundDrawables(nav_up2, null, null, null);

                break;
            case R.id.tv_purpose_3:
                type = "3";
//                if (tvPurpose3.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
//                    tvPurpose3.setTextColor(getResources().getColor(R.color.account_hint_text));
//                    Drawable nav_up=getResources().getDrawable(R.drawable.health_relaxed_n);
//                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                    tvPurpose3.setCompoundDrawables(nav_up, null, null , null);
//                } else {
//                    tvPurpose3.setTextColor(getResources().getColor(R.color.colorPrimary));
//                    Drawable nav_up=getResources().getDrawable(R.drawable.health_relaxed_s);
//                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                    tvPurpose3.setCompoundDrawables(nav_up, null, null , null);
//                }
                refreshView();
                tvPurpose3.setTextColor(getResources().getColor(R.color.colorPrimary));
                Drawable nav_up3 = getResources().getDrawable(R.drawable.health_relaxed_s);
                nav_up3.setBounds(0, 0, nav_up3.getMinimumWidth(), nav_up3.getMinimumHeight());
                tvPurpose3.setCompoundDrawables(nav_up3, null, null, null);

                break;
            case R.id.tv_purpose_4:
                type = "4";
//                if (tvPurpose4.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
//                    tvPurpose4.setTextColor(getResources().getColor(R.color.account_hint_text));
//                    Drawable nav_up=getResources().getDrawable(R.drawable.health_pressure_n);
//                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                    tvPurpose4.setCompoundDrawables(nav_up, null, null , null);
//                } else {
//                    tvPurpose4.setTextColor(getResources().getColor(R.color.colorPrimary));
//                    Drawable nav_up=getResources().getDrawable(R.drawable.health_pressure_s);
//                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                    tvPurpose4.setCompoundDrawables(nav_up, null, null , null);
//                }
                refreshView();
                tvPurpose4.setTextColor(getResources().getColor(R.color.colorPrimary));
                Drawable nav_up4 = getResources().getDrawable(R.drawable.health_pressure_s);
                nav_up4.setBounds(0, 0, nav_up4.getMinimumWidth(), nav_up4.getMinimumHeight());
                tvPurpose4.setCompoundDrawables(nav_up4, null, null, null);

                break;
            case R.id.tv_next:
                if (type.equals("0")) {
                    ToastManager.showToast(getContext(), "请选择健身目的");
                    return;
                } else {
                    CustomerData customerData = GlobalDataYepao.getCustomerData(getContext());
                    customerData.setObjective(type);
                    GlobalDataYepao.setCustomerData(getContext(),customerData);

//                    GlobalDataYepao.getCustomerData(getContext()).setObjective(type);
//                    CustomerData customerData = GlobalDataYepao.getCustomerData(getContext());
                }


                BodySituationActivity.start(getContext());
                finish();
                break;
        }
    }
}
