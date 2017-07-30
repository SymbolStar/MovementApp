package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPhysicalList.setLayoutManager(llm);
        getData();
    }

    private void getData() {

        showResult();
    }

    private void showResult() {
        if (reservationMessageAdapter == null) {
            reservationMessageAdapter = new PhysicalReservationMessageAdapter(getContext());
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
