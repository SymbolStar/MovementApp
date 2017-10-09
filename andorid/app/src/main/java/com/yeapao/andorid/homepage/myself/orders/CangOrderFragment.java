package com.yeapao.andorid.homepage.myself.orders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.ActialOrderDetailModel;
import com.yeapao.andorid.model.ActualOrderListModel;
import com.yeapao.andorid.model.CangDeleteActualOrdersModel;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/9/18.
 */

public class CangOrderFragment  extends BaseFragment{


    private static final String TAG = "CangOrderFragment";

    private RecyclerView mCangOrderRecyclerView;
    private RelativeLayout mNoDataRelativeLayout;

    private ActualOrderListModel mActualOrderListModel;

    private MyselfCangOrderMessageAdapter mMessageAdapter;

    public CangOrderFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getNetWork(GlobalDataYepao.getUser(getContext()).getId());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cang_order, container, false);
        getNetWork(GlobalDataYepao.getUser(getContext()).getId());
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mCangOrderRecyclerView = (RecyclerView) view.findViewById(R.id.rv_cang_order_list);
        mNoDataRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_no_date);
        mCangOrderRecyclerView.setVisibility(View.GONE);
        mNoDataRelativeLayout.setVisibility(View.VISIBLE);

        mCangOrderRecyclerView.setHasFixedSize(true);
        mCangOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }


            private void getNetWork(String id) {
                    LogUtil.e(TAG,id);
                    subscription = Network.getYeapaoApi()
                            .requestActualOrderList(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserver);
                }

                  Observer<ActualOrderListModel> modelObserver = new Observer<ActualOrderListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(ActualOrderListModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            mActualOrderListModel = model;
                            showResult();
                        }
                    }
                };

    private void showResult() {
        if (mActualOrderListModel.getData().size() == 0) {
            mCangOrderRecyclerView.setVisibility(View.GONE);
            mNoDataRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            mCangOrderRecyclerView.setVisibility(View.VISIBLE);
            mNoDataRelativeLayout.setVisibility(View.GONE);
        }
        mMessageAdapter = new MyselfCangOrderMessageAdapter(getContext(), mActualOrderListModel);
        mCangOrderRecyclerView.setAdapter(mMessageAdapter);
        mMessageAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                CangOrderDetailActivity.start(getContext(),String.valueOf(mActualOrderListModel.getData().get(position).getActualOrdersId()),CangOrderDetailActivity.CangOrder);
            }
        });

        mMessageAdapter.setCangOrderStatusListener(new CangOrderStatusListener() {
            @Override
            public void cangOrderDo(int flag) {
                if (mActualOrderListModel.getData().get(flag).getStatus().equals("1")) {
                    CangOrderPayActivity.start(getContext(),String.valueOf(mActualOrderListModel.getData().get(flag).getActualOrdersId()));
                } else {
//                    TODO 删除订单
                    DialogUtils.showProgressDialog(getContext());
                    getNetWorkDeleteCangOrder(String.valueOf(mActualOrderListModel.getData().get(flag).getActualOrdersId()));

                }
            }
        });
    }

            private void getNetWorkDeleteCangOrder(String id) {
                    LogUtil.e(TAG,id);
                    subscription = Network.getYeapaoApi()
                            .requestDeleteActualOrders(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserverDeleteCangOrder);
                }

                  Observer<NormalDataModel> modelObserverDeleteCangOrder = new Observer<NormalDataModel>() {
                    @Override
                    public void onCompleted() {
                        DialogUtils.cancelProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());
                        DialogUtils.cancelProgressDialog();
                    }

                    @Override
                    public void onNext(NormalDataModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            DialogUtils.cancelProgressDialog();
                        }
                    }
                };

}
