package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/25.
 */

public class MyselfReservationActivity extends BaseActivity {

    private static final String TAG = "MyselfReservationActivity";
    @BindView(R.id.rv_my_reservation_list)
    RecyclerView rvMyReservationList;


    private LinearLayoutManager llm;
    private MyselfReservationMessageAdapter mReservationAdapter;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfReservationActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_reservation);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMyReservationList.setLayoutManager(llm);
        getData();
    }

    private void getData() {

        showResult();
    }

    private void showResult() {
        if (mReservationAdapter == null) {
            mReservationAdapter = new MyselfReservationMessageAdapter(getContext());
            rvMyReservationList.setAdapter(mReservationAdapter);
        } else {
            rvMyReservationList.setAdapter(mReservationAdapter);
            mReservationAdapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void initTopBar() {
        initTitle("我的预约");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

}
