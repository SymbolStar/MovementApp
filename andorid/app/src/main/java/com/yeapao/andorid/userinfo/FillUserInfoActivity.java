package com.yeapao.andorid.userinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.CustomerData;
import com.yeapao.andorid.util.DatePickerDialog;
import com.yeapao.andorid.util.GlobalDataYepao;

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

    private String gender = "男";


    private DatePickerDialog datePickerDialog;


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
        initView();

    }

    private void initView() {
        datePickerDialog = new DatePickerDialog();
        datePickerDialog.setPickerListener(new DatePickerDialog.DateSelectedListener() {
            @Override
            public void getDate(String Date) {
                String[] date1 = Date.split("-");
                if (date1[1].length() == 1) {
                    date1[1] = "0" + date1[1];
                }
                if (date1[2].length() == 1) {
                    date1[2] = "0" + date1[2];
                }

                tvBirthDate.setText(date1[0]+"-"+date1[1]+"-"+date1[2]);
                datePickerDialog.dismiss();
            }

            @Override
            public void cancel() {
                datePickerDialog.dismiss();
            }
        });
    }


    @Override
    protected void initTopBar() {
        initTitle("完善资料");
        initBack();
    }

    private void showpickerDate() {
        if (datePickerDialog.isVisible()) {
            datePickerDialog.dismiss();
        } else {
            datePickerDialog.show(getSupportFragmentManager(), "date");
        }
    }


    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_birth_date, R.id.tv_high, R.id.tv_weight, R.id.tv_next, R.id.v_boy, R.id.v_girl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_birth_date:
                showpickerDate();
                break;
            case R.id.tv_high:
                break;
            case R.id.tv_weight:
                break;
            case R.id.tv_next:
                checkData();
                break;
            case R.id.v_boy:
                gender = "男";
                tvBoy.setTextColor(getResources().getColor(R.color.colorPrimary));
                ivBoy.setImageDrawable(getResources().getDrawable(R.drawable.health_boy_s));
                tvGirl.setTextColor(getResources().getColor(R.color.account_hint_text));
                ivGirl.setImageDrawable(getResources().getDrawable(R.drawable.health_girl_n));
                break;
            case R.id.v_girl:
                gender = "女";
                tvBoy.setTextColor(getResources().getColor(R.color.account_hint_text));
                ivBoy.setImageDrawable(getResources().getDrawable(R.drawable.health_boy_n));
                tvGirl.setTextColor(getResources().getColor(R.color.colorPrimary));
                ivGirl.setImageDrawable(getResources().getDrawable(R.drawable.health_girl_s));
                break;
        }
    }

    private void checkData() {
        String high = tvHigh.getText().toString();
        String weight = tvWeight.getText().toString();
        String birthDate = tvBirthDate.getText().toString();
        if (birthDate == null || birthDate.equals("")) {
            ToastManager.showToast(getContext(),"请选择出生日期");
            return;
        }
        if (high == null || high.equals("")) {
            ToastManager.showToast(getContext(),"请输入身高");
            return;
        }
        if (weight == null || weight.equals("")) {
            ToastManager.showToast(getContext(),"请输入体重");
            return;
        }
        CustomerData customerData = new CustomerData();
        customerData.setBirthDate(birthDate);
        customerData.setHeight(high);
        customerData.setWeight(weight);
        customerData.setGender(gender);
        customerData.setObjective("");
        customerData.setPhysicalCondition("");
        GlobalDataYepao.setCustomerData(getContext(),customerData);

//        GlobalDataYepao.getCustomerData(getContext()).setBirthDate(birthDate);
//        GlobalDataYepao.getCustomerData(getContext()).setHeight(high);
//        GlobalDataYepao.getCustomerData(getContext()).setWeight(weight);
//        GlobalDataYepao.getCustomerData(getContext()).setGender(gender);
        FitnessActivity.start(getContext());
        finish();
    }
}
