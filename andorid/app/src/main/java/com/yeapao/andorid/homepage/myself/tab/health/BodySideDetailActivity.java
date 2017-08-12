package com.yeapao.andorid.homepage.myself.tab.health;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.formatter.IValueFormatter;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.BodySideDetailModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/8/12.
 */

public class BodySideDetailActivity extends BaseActivity {

    private static final String TAG = "BodySideDetailActivity";

    private GlideUtil glideUtil = new GlideUtil();

    @BindView(R.id.tv_body_side_one_1)
    TextView tvBodySideOne1;
    @BindView(R.id.tv_body_side_one_2)
    TextView tvBodySideOne2;
    @BindView(R.id.tv_body_side_one_3)
    TextView tvBodySideOne3;
    @BindView(R.id.tv_body_side_one_4)
    TextView tvBodySideOne4;
    @BindView(R.id.tv_body_side_one_5)
    TextView tvBodySideOne5;
    @BindView(R.id.iv_body_side_one_6)
    ImageView ivBodySideOne6;
    @BindView(R.id.tv_body_side_two_4)
    TextView tvBodySideTwo4;
    @BindView(R.id.tv_body_side_two_7)
    TextView tvBodySideTwo7;
    @BindView(R.id.tv_body_side_two_2)
    TextView tvBodySideTwo2;
    @BindView(R.id.tv_body_side_two_5)
    TextView tvBodySideTwo5;
    @BindView(R.id.tv_body_side_two_6)
    TextView tvBodySideTwo6;
    @BindView(R.id.tv_body_side_two_3)
    TextView tvBodySideTwo3;
    @BindView(R.id.tv_body_side_two_1)
    TextView tvBodySideTwo1;
    @BindView(R.id.tv_body_side_three_6)
    TextView tvBodySideThree6;
    @BindView(R.id.tv_body_side_three_4)
    TextView tvBodySideThree4;
    @BindView(R.id.tv_body_side_three_5)
    TextView tvBodySideThree5;
    @BindView(R.id.tv_body_side_three_1)
    TextView tvBodySideThree1;
    @BindView(R.id.tv_body_side_three_3)
    TextView tvBodySideThree3;
    @BindView(R.id.tv_body_side_three_2)
    TextView tvBodySideThree2;
    @BindView(R.id.iv_body_side_four_4)
    ImageView ivBodySideFour4;
    @BindView(R.id.iv_body_side_four_2)
    ImageView ivBodySideFour2;
    @BindView(R.id.iv_body_side_four_1)
    ImageView ivBodySideFour1;
    @BindView(R.id.iv_body_side_four_3)
    ImageView ivBodySideFour3;


    private String bodySideId = "";

    public static void start(Context context, String bodySideId) {
        Intent intent = new Intent();
        intent.putExtra("bodySideId", bodySideId);
        intent.setClass(context, BodySideDetailActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_side_record_detail);
        ButterKnife.bind(this);
        initTopBar();
        Intent intent = getIntent();
        bodySideId = intent.getStringExtra("bodySideId");
        getNetWork(bodySideId);

    }

    @Override
    protected void initTopBar() {
        initTitle("体测记录");
        initBack();

    }

    private void showResult(BodySideDetailModel model) {

        tvBodySideOne1.setText(model.getData().getBodySideOne().getQuietHeartRate());
        tvBodySideOne2.setText(model.getData().getBodySideOne().getHeight());
        tvBodySideOne3.setText(model.getData().getBodySideOne().getInBody());
        tvBodySideOne4.setText(model.getData().getBodySideOne().getBloodPressure());
        tvBodySideOne5.setText(model.getData().getBodySideOne().getWeight());

        glideUtil.glideLoadingImage(getContext(), ConstantYeaPao.HOST +
                        model.getData().getBodySideOne().getPresentation(),
                R.drawable.y_you, ivBodySideOne6);
        tvBodySideTwo1.setText(model.getData().getBodySideTwo().getUpperRight());
        tvBodySideTwo2.setText(model.getData().getBodySideTwo().getAbdomen());
        tvBodySideTwo3.setText(model.getData().getBodySideTwo().getHips());
        tvBodySideTwo4.setText(model.getData().getBodySideTwo().getLowerRight());
        tvBodySideTwo5.setText(model.getData().getBodySideTwo().getUpperLeft());
        tvBodySideTwo6.setText(model.getData().getBodySideTwo().getWaist());
        tvBodySideTwo7.setText(model.getData().getBodySideTwo().getLowerLeft());

        tvBodySideThree1.setText(model.getData().getBodySideThree().getUpperLimbStrength());
        tvBodySideThree2.setText(model.getData().getBodySideThree().getLowerExtremityStrength());
        tvBodySideThree3.setText(model.getData().getBodySideThree().getPrecursor());
        tvBodySideThree4.setText(model.getData().getBodySideThree().getHeartRateOne());
        tvBodySideThree5.setText(model.getData().getBodySideThree().getHeartRateTwo());
        tvBodySideThree6.setText(model.getData().getBodySideThree().getHeartRateThree());

        glideUtil.glideLoadingImage(getContext(),ConstantYeaPao.HOST+model.getData().getBodySideFour().getPositive(),
                R.drawable.y_you,ivBodySideFour1);
        glideUtil.glideLoadingImage(getContext(),ConstantYeaPao.HOST+model.getData().getBodySideFour().getSide(),
                R.drawable.y_you,ivBodySideFour2);
        glideUtil.glideLoadingImage(getContext(),ConstantYeaPao.HOST+model.getData().getBodySideFour().getBack(),
                R.drawable.y_you,ivBodySideFour3);
        glideUtil.glideLoadingImage(getContext(),ConstantYeaPao.HOST+model.getData().getBodySideFour().getFurredTongue(),
                R.drawable.y_you,ivBodySideFour4);

    }

    @Override
    protected Context getContext() {
        return this;
    }


    private void getNetWork(String id) {
        subscription = Network.getYeapaoApi()
                .getBodySideDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<BodySideDetailModel> modelObserver = new Observer<BodySideDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(BodySideDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                showResult(model);
            }
        }
    };


}
