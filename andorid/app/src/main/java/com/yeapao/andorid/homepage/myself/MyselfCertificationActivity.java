package com.yeapao.andorid.homepage.myself;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogCallback;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.map.repository.DepositActivity;
import com.yeapao.andorid.model.AliRefundModel;
import com.yeapao.andorid.model.MyAuthenticationModel;
import com.yeapao.andorid.model.WeXinRefundModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/9/12.
 */

public class MyselfCertificationActivity extends BaseActivity {

    private static final String TAG = "MyselfCertificationActivity";
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_deposit_status)
    TextView tvDepositStatus;
    @BindView(R.id.tv_deposit_price)
    TextView tvDepositPrice;
    @BindView(R.id.tv_deposit_status_2)
    TextView tvDepositStatus2;
    @BindView(R.id.tv_deposit_status_3)
    TextView tvDepositStatus3;
    private MyAuthenticationModel myAuthenticationModel = new MyAuthenticationModel();


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfCertificationActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_certification);
//        ButterKnife.bind(this);
//        initTopBar();
        DialogUtils.showProgressDialog(getContext());
        getNetWork(GlobalDataYepao.getUser(getContext()).getId());


    }

    @Override
    protected void initTopBar() {
        initTitle(getContext().getResources().getString(R.string.certification));
        initRightText(getContext().getResources().getString(R.string.refunds_doc));
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }


    private void getNetWork(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestCertification(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<MyAuthenticationModel> modelObserver = new Observer<MyAuthenticationModel>() {
        @Override
        public void onCompleted() {
            DialogUtils.cancelProgressDialog();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());
            finish();
        }

        @Override
        public void onNext(MyAuthenticationModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                myAuthenticationModel = model;
                initView();
                DialogUtils.cancelProgressDialog();

            }
        }
    };

    private void initView() {

        if (myAuthenticationModel.getData().getIsAuthentication().equals("1")) {
            setContentView(R.layout.activity_certification);
            ButterKnife.bind(this);
            initTopBar();
            tvDepositStatus.setText(getContext().getResources().getString(R.string.deposit_status));
            tvDepositStatus2.setText(getContext().getResources().getString(R.string.deposit_status2));
            tvDepositStatus3.setText(getContext().getResources().getString(R.string.deposit_status3));
            tvDepositPrice.setText("￥" + myAuthenticationModel.getData().getPrice());
        } else {
            DepositActivity.start(getContext());
            finish();
        }

    }

    @OnClick({R.id.tv_right, R.id.tv_deposit_status_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                startActivity(new Intent(getContext(),MyselfRefundActivity.class));

                break;
            case R.id.tv_deposit_status_3:
                DialogUtils.showRefundDialog(getContext(), new DialogCallback() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onLeftClick() {
                        LogUtil.e(TAG, "Dialog_on left click");
                    }

                    @Override
                    public void onRightClick() {
                        LogUtil.e(TAG, "Dialog_on right click");
                        DialogUtils.showProgressDialog(getContext());
                        if (myAuthenticationModel.getData().getTypes().equals("1")) {
                            requestAli(myAuthenticationModel.getData().getDepositOrdersCode(), myAuthenticationModel.getData().getPrice());
                        } else {
                            requestWeXin(myAuthenticationModel.getData().getDepositOrdersCode(), myAuthenticationModel.getData().getPrice());
                        }
                    }
                });
                break;
        }
    }


    private void requestWeXin(String code, String price) {
        LogUtil.e(TAG, code + "+++" + price);
        subscription = Network.getYeapaoApi()
                .requestWeXinRefund(code, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weXinRefundModelObserver);
    }

    Observer<WeXinRefundModel> weXinRefundModelObserver = new Observer<WeXinRefundModel>() {
        @Override
        public void onCompleted() {
            DialogUtils.cancelProgressDialog();
            finish();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());
            DialogUtils.cancelProgressDialog();
        }

        @Override
        public void onNext(WeXinRefundModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                DialogUtils.cancelProgressDialog();
                if (model.getData().getResult_code().equals("SUCCESS")) {
                    ToastManager.showToast(getContext(), getContext().getResources().getString(R.string.refund_success));
                }
            }
        }
    };


    private void requestAli(String code, String price) {
        LogUtil.e(TAG, code + price);
        subscription = Network.getYeapaoApi()
                .requestAliRefund(code, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aliRefundModelObserver);
    }

    Observer<AliRefundModel> aliRefundModelObserver = new Observer<AliRefundModel>() {
        @Override
        public void onCompleted() {
            DialogUtils.cancelProgressDialog();
            finish();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());
            DialogUtils.cancelProgressDialog();

        }

        @Override
        public void onNext(AliRefundModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                DialogUtils.cancelProgressDialog();
                if (model.getData().getResult_code().equals("SUCCESS")) {
                    ToastManager.showToast(getContext(), getContext().getResources().getString(R.string.refund_success));
                }
            }
        }
    };


}
