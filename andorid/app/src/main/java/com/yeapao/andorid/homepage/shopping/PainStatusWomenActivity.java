package com.yeapao.andorid.homepage.shopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.PainModel;
import com.yeapao.andorid.util.PainPickerDialog;
import com.yeapao.andorid.util.PickerPainListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/8/15.
 */

public class PainStatusWomenActivity extends BaseActivity {

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
    @BindView(R.id.tv_pain_men_back_2)
    TextView tvPainMenBack2;
    @BindView(R.id.tv_pain_men_back_1)
    TextView tvPainMenBack1;
    @BindView(R.id.tv_pain_men_back_3)
    TextView tvPainMenBack3;
    @BindView(R.id.tv_pain_men_back_4)
    TextView tvPainMenBack4;
    @BindView(R.id.ll_men_positive)
    LinearLayout llMenPositive;
    @BindView(R.id.ll_men_back)
    LinearLayout llMenBack;
    @BindView(R.id.iv_change_v2)
    ImageView ivChangeV2;


    private PainPickerDialog mpainPickerDialog;

    private TextView currentTextView;

    private int count = 0;

    private String reservationId;

    private List<String> painList = new ArrayList<>();
    private List<PainModel> painModels = new ArrayList<>();


    public static void start(Context context,String reservationId) {
        Intent intent = new Intent();
        intent.putExtra("reservationId", reservationId);
        intent.setClass(context, PainStatusWomenActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_women_positive);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        reservationId = intent.getStringExtra("reservationId");
        initTopBar();
        initView();
    }

    private void initView() {
        llMenPositive.setVisibility(View.VISIBLE);
        mpainPickerDialog = new PainPickerDialog();
        mpainPickerDialog.setPickerPainListener(new PickerPainListener() {
            @Override
            public void getPainValue(String value) {
                String[] pain = currentTextView.getText().toString().split("  ");
                pain[1] = value;

                currentTextView.setText(pain[0] + "  " + pain[1]);
                painList.add(currentTextView.getText().toString());

            }

            @Override
            public void cancel() {
                String[] pain = currentTextView.getText().toString().split("  ");
                pain[1] = "0";

                currentTextView.setText(pain[0] + "  " + pain[1]);
                mpainPickerDialog.dismiss();

            }

            @Override
            public void determine() {
                mpainPickerDialog.dismiss();
                String[] pain = currentTextView.getText().toString().split("  ");
                PainModel painModel = new PainModel();
                painModel.setLayoutId(currentTextView.getId());
                painModel.setPainPosition(pain[0]);
                painModel.setValue(pain[1]);
                painModels.add(painModel);
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
        TextView rightText = (TextView) findViewById(R.id.tv_right);
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (painModels.size() == 0) {
                    finish();
                } else {
                    DialogUtils.showProgressDialog(getContext());
                    for (int i = 0; i < painModels.size(); i++) {
                        getNetWork(reservationId,painModels.get(i).getPainPosition(),painModels.get(i).getValue());
                    }
                }


            }
        });

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.iv_change_v2,R.id.tv_pain_men_back_1,R.id.tv_pain_men_back_2,R.id.tv_pain_men_back_3,R.id.tv_pain_men_back_4,R.id.iv_change, R.id.tv_pain_men_front_1, R.id.tv_pain_men_front_2, R.id.tv_pain_men_front_7, R.id.tv_pain_men_front_9, R.id.tv_pain_men_front_11, R.id.tv_pain_men_front_10, R.id.tv_pain_men_front_8, R.id.tv_pain_men_front_6, R.id.tv_pain_men_front_4, R.id.tv_pain_men_front_3, R.id.tv_pain_men_front_5, R.id.tv_pain_men_front_12})
    public void onViewClicked(View view) {


        for (int i = 0; i < painModels.size(); i++) {
            if (painModels.get(i).getLayoutId() == view.getId()||painModels.get(i).getValue().equals("0")) {
                painModels.remove(i);
                i--;
            }
        }

        for (int i = painModels.size(); i >= 3; i++) {
            ToastManager.showToast(getContext(),"最多选择三个疼痛部位");
        }


        switch (view.getId()) {
            case R.id.iv_change:
                        llMenPositive.setVisibility(View.GONE);
                        llMenBack.setVisibility(View.VISIBLE);

                break;
            case R.id.iv_change_v2:
                llMenPositive.setVisibility(View.VISIBLE);
                llMenBack.setVisibility(View.GONE);
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
            case R.id.tv_pain_men_back_1:
                currentTextView = tvPainMenBack1;
                showpickerPain();
                break;
            case R.id.tv_pain_men_back_2:
                currentTextView = tvPainMenBack2;
                showpickerPain();
                break;
            case R.id.tv_pain_men_back_3:
                currentTextView = tvPainMenBack3;
                showpickerPain();
                break;
            case R.id.tv_pain_men_back_4:
                currentTextView = tvPainMenBack4;
                showpickerPain();
                break;
        }
    }


    private void getNetWork(String reservationId, String position, String degree) {

        LogUtil.e(TAG, reservationId+"===="+position+"===="+degree );
        subscription = Network.getYeapaoApi()
                .requestPainData(reservationId, position, degree)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

                  Observer<NormalDataModel> modelObserver = new Observer<NormalDataModel>() {
                    @Override
                    public void onCompleted() {
//                        TODO 多个上传结束的处理
                        count++;
                        if (count == painModels.size()) {
                            DialogUtils.cancelProgressDialog();
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(NormalDataModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {

                        }
                    }
                };


}
