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
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.myself.tab.MyselfReservationMessageAdapter;
import com.yeapao.andorid.model.CangReservationOrderListModel;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/9/18.
 */

public class CangReservationFragment extends BaseFragment {

    private static final String TAG = "CangReservationFragment";

    private RecyclerView mCangReservationRecyclerView;
    private RelativeLayout mNoDataRelativeLayout;
    private MyselfCangReservationMessageAdapter mMessageAdapter;
    private CangReservationOrderListModel cangReservationOrderListModel = new CangReservationOrderListModel();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cang_reservation, container, false);
        getNetWork(GlobalDataYepao.getUser(getContext()).getId());
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mCangReservationRecyclerView = (RecyclerView) view.findViewById(R.id.rv_cang_reservation_list);
        mCangReservationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mNoDataRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_no_date);
        mCangReservationRecyclerView.setVisibility(View.GONE);
        mNoDataRelativeLayout.setVisibility(View.VISIBLE);

    }

            private void getNetWork(String id) {
                    LogUtil.e(TAG,id);
                    subscription = Network.getYeapaoApi()
                            .requestCangReservationOrderList(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserver );
                }

                  Observer<CangReservationOrderListModel> modelObserver = new Observer<CangReservationOrderListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(CangReservationOrderListModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            cangReservationOrderListModel = model;
                            showResult();
                        }
                    }
                };

    private void showResult() {
        if (cangReservationOrderListModel.getData().size() == 0) {
            mCangReservationRecyclerView.setVisibility(View.GONE);
            mNoDataRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            mCangReservationRecyclerView.setVisibility(View.VISIBLE);
            mNoDataRelativeLayout.setVisibility(View.GONE);
        }

        mMessageAdapter = new MyselfCangReservationMessageAdapter(getContext(), cangReservationOrderListModel);
        mCangReservationRecyclerView.setAdapter(mMessageAdapter);
        mMessageAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                CangOrderDetailActivity.start(getContext(),String.valueOf(cangReservationOrderListModel.getData().get(position).getReservaOrdersId()),CangOrderDetailActivity.CangReservation);
            }
        });
        mMessageAdapter.setCangOrderStatusListener(new CangOrderStatusListener() {
            @Override
            public void cangOrderDo(int flag) {
                DialogUtils.showProgressDialog(getContext());
                getNetWorkDeleteReservationOrders( String.valueOf(cangReservationOrderListModel.getData().get(flag).getReservaOrdersId()));
            }
        });

    }

            private void getNetWorkDeleteReservationOrders(String id) {
                    LogUtil.e(TAG,id);
                    subscription = Network.getYeapaoApi()
                            .requestDeleteCangReservationOrders(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserverDeleteCangReservationOrders);
                }

                  Observer<NormalDataModel> modelObserverDeleteCangReservationOrders = new Observer<NormalDataModel>() {
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


