package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.BodySideListModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/28.
 */

public class PhysicalReservationActivity extends BaseActivity {

    private static final String TAG = "PhysicalReservationActivity";
    @BindView(R.id.rv_physical_list)
    RecyclerView rvPhysicalList;

    private LinearLayoutManager llm;
    private PhysicalReservationMessageAdapter reservationMessageAdapter;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PhysicalReservationActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical);
        ButterKnife.bind(this);
        initTopBar();
        initView();

    }

    @Override
    protected void onResume() {
        LogUtil.e(TAG,"physicalReservation----onRsume");
        super.onResume();
        getData();
    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPhysicalList.setLayoutManager(llm);
        getData();
    }

    private void getData() {

        getNetWork(GlobalDataYepao.getUser(getContext()).getId());



    }


            private void getNetWork(String id) {
                    LogUtil.e(TAG,id);
                    subscription = Network.getYeapaoApi()
                            .getBodySide(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserver);
                }

                  Observer<BodySideListModel> modelObserver = new Observer<BodySideListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(BodySideListModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            showResult(model);
                        }
                    }
                };


    private void showResult(BodySideListModel bodySideListModel) {
        if (reservationMessageAdapter == null) {
            reservationMessageAdapter = new PhysicalReservationMessageAdapter(getContext(),bodySideListModel);
            rvPhysicalList.setAdapter(reservationMessageAdapter);
            reservationMessageAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {

                    ToastManager.showToast(getContext(), position);
                }
            });
        } else {
            rvPhysicalList.setAdapter(reservationMessageAdapter);
            reservationMessageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initTopBar() {
        initTitle("体测预约");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
