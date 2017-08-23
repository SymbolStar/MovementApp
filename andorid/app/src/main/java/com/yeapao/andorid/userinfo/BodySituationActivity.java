package com.yeapao.andorid.userinfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.homepage.myself.tab.health.BodySideDetailActivity;
import com.yeapao.andorid.model.CustomerData;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    @BindView(R.id.tv_body_status_finish)
    TextView tvBodyStatusFinish;

    private String content;

    private List<String> contentList = new ArrayList<>();

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
        for (int i = 0; i < 6; i++) {
            contentList.add("");
        }
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

    @OnClick({R.id.tv_body_status_finish,R.id.tv_body_status_1, R.id.tv_body_status_2, R.id.tv_body_status_4, R.id.tv_body_status_3, R.id.tv_body_status_5, R.id.tv_body_status_6, R.id.et_body_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_body_status_1:
                if (tvBodyStatus1.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
                    tvBodyStatus1.setTextColor(getResources().getColor(R.color.account_hint_text));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_no_n);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus1.setCompoundDrawables(nav_up, null, null , null);
                    contentList.set(0, "");
                } else {
                    tvBodyStatus1.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_no_s);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus1.setCompoundDrawables(nav_up, null, null , null);
                    contentList.set(0, tvBodyStatus1.getText().toString());
                }
                break;
            case R.id.tv_body_status_2:
                if (tvBodyStatus2.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
                    tvBodyStatus2.setTextColor(getResources().getColor(R.color.account_hint_text));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_blood_n);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus2.setCompoundDrawables(nav_up, null, null , null);
                    contentList.set(1, "");
                } else {
                    tvBodyStatus2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_blood_s);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus2.setCompoundDrawables(nav_up, null, null , null);
                    contentList.set(1, tvBodyStatus2.getText().toString());
                }
                break;
            case R.id.tv_body_status_4:
                if (tvBodyStatus4.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
                    tvBodyStatus4.setTextColor(getResources().getColor(R.color.account_hint_text));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_meniscus_n);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus4.setCompoundDrawables(nav_up, null, null , null);
                    contentList.set(3, "");
                } else {
                    tvBodyStatus4.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_meniscus_s);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus4.setCompoundDrawables(nav_up, null, null , null);
                    contentList.set(3, tvBodyStatus4.getText().toString());
                }

                break;
            case R.id.tv_body_status_3:
                if (tvBodyStatus3.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
                    tvBodyStatus3.setTextColor(getResources().getColor(R.color.account_hint_text));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_heart_n);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus3.setCompoundDrawables(nav_up, null, null , null);
                    contentList.set(2, "");
                } else {
                    tvBodyStatus3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_heart_s);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus3.setCompoundDrawables(nav_up, null, null , null);
                    contentList.set(2, tvBodyStatus3.getText().toString());
                }
                break;
            case R.id.tv_body_status_5:
                if (tvBodyStatus5.getCurrentTextColor() == getResources().getColor(R.color.colorPrimary)) {
                    tvBodyStatus5.setTextColor(getResources().getColor(R.color.account_hint_text));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_waist_n);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus5.setCompoundDrawables(nav_up, null, null , null);
                    contentList.set(4, "");
                } else {
                    tvBodyStatus5.setTextColor(getResources().getColor(R.color.colorPrimary));
                    Drawable nav_up=getResources().getDrawable(R.drawable.health_waist_s);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    tvBodyStatus5.setCompoundDrawables(nav_up, null, null , null);
                    contentList.set(4, tvBodyStatus3.getText().toString());
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
            case R.id.tv_body_status_finish:
                checkData();
                break;
        }
    }

    private void checkData() {
        contentList.set(5,etBodyContent.getText().toString());
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < 6; i++) {

            if (!contentList.get(i).equals("")) {
                if (count == 0) {
                    sb.append(contentList.get(i));
                } else {
                    sb.append(",");
                    sb.append(contentList.get(i));
                }

            }
        }

        CustomerData customerData = GlobalDataYepao.getCustomerData(getContext());
        customerData.setPhysicalCondition(sb.toString());
        GlobalDataYepao.setCustomerData(getContext(),customerData);


//        GlobalDataYepao.getCustomerData(getContext()).setPhysicalCondition(sb.toString());
getNetWork();
    }

            private void getNetWork() {
               CustomerData customerData =  GlobalDataYepao.getCustomerData(getContext());
                String id = GlobalDataYepao.getUser(getContext()).getId();
                subscription = Network.getYeapaoApi()
                        .requestCostomerData(GlobalDataYepao.getCustomerData(getContext()).getGender(),
                                GlobalDataYepao.getCustomerData(getContext()).getBirthDate(),
                                GlobalDataYepao.getCustomerData(getContext()).getHeight(),
                                GlobalDataYepao.getCustomerData(getContext()).getWeight(),
                                GlobalDataYepao.getCustomerData(getContext()).getObjective(),
                                GlobalDataYepao.getCustomerData(getContext()).getPhysicalCondition(),
                                GlobalDataYepao.getUser(getContext()).getId()
                        )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(modelObserver);
                }

                  Observer<NormalDataModel> modelObserver = new Observer<NormalDataModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(NormalDataModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            finish();
                        }
                    }
                };

}
