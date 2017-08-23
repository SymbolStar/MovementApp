package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.shopping.ContinuePayOrderActivity;
import com.yeapao.andorid.model.MyOrderDataModel;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/24.
 */

public class MyselfOrderActivity extends BaseActivity {

    private static final String TAG = "MySelfOrderActivity";
    private LinearLayoutManager llm;
    private MyselfOrderMessageAdapter mOrderMessageAdapter;

    private MyOrderDataModel myOrderDataModel;

    @BindView(R.id.rv_my_order_list)
    RecyclerView rvMyOrderList;
    @BindView(R.id.rl_no_date)
    RelativeLayout rlNoDate;



    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfOrderActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMyOrderList.setLayoutManager(llm);

        getData();
    }

    private void getData() {
        getNetWork();

    }

    private void showResult() {
//        if (mOrderMessageAdapter == null) {
            mOrderMessageAdapter = new MyselfOrderMessageAdapter(getContext(),myOrderDataModel);
            rvMyOrderList.setAdapter(mOrderMessageAdapter);
            mOrderMessageAdapter.setOnItemClick(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(),TAG+"rv onClick");
                    if (myOrderDataModel.getData().get(position).getStatus().equals("待支付")) {

                    } else {
                        MyselfOrderDetailActivity.start(getContext(),String.valueOf(myOrderDataModel.getData().get(position).getOrderId()));
                    }

                }
            });
            mOrderMessageAdapter.setGotoPayListener(new MyselfOrderMessageAdapter.GotoPay() {
                @Override
                public void gotoOrder(int position) {
                    LogUtil.e(TAG,"gotopay");
                    ContinuePayOrderActivity.start(getContext(),myOrderDataModel.getData().get(position).getCurriculumName(),
                            myOrderDataModel.getData().get(position).getPrice(),
                            myOrderDataModel.getData().get(position).getOrderCode());
                }
            });
            mOrderMessageAdapter.setDeleteOrderListener(new MyselfOrderMessageAdapter.DeleteOrder() {
                @Override
                public void deleteOrder(int position) {
                    LogUtil.e(TAG,"deleteOrder");
                    requestDeleteOrder(String.valueOf(myOrderDataModel.getData().get(position).getOrderId()));

                }
            });
//        } else {
//            rvMyOrderList.setAdapter(mOrderMessageAdapter);
//            mOrderMessageAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    protected void initTopBar() {
        initBack();
        initTitle("我的订单");
    }


    @Override
    protected Context getContext() {
        return this;
    }



            private void getNetWork() {
                DialogUtils.showProgressDialog(getContext());
                    subscription = Network.getYeapaoApi()
                            .getOrderInfo(GlobalDataYepao.getUser(getContext()).getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserver );
                }

                  Observer<MyOrderDataModel> modelObserver = new Observer<MyOrderDataModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        DialogUtils.cancelProgressDialog();
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(MyOrderDataModel model) {
                        DialogUtils.cancelProgressDialog();
                        LogUtil.e(TAG+"eee", model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            myOrderDataModel = model;
                            LogUtil.e("=====", String.valueOf(myOrderDataModel.getData().size()));
                            showResult();
                        } else {
                            rvMyOrderList.setVisibility(View.GONE);
                            rlNoDate.setVisibility(View.VISIBLE);
                        }
                    }
                };


                        private void requestDeleteOrder(String id) {
                                LogUtil.e(TAG,id);
                                subscription = Network.getYeapaoApi()
                                        .requestDeleteOrder(id)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(modelObserverDeleteOrder );
                            }

                              Observer<NormalDataModel> modelObserverDeleteOrder = new Observer<NormalDataModel>() {
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
                                        initView();
                                    }
                                }
                            };
}
