package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/29.
 */

public class PhysicalTestSecondActivity extends BaseActivity {

    private static final String TAG = "PhysicalTestSecondActivity";
    @BindView(R.id.rv_physical_second_list)
    RecyclerView rvPhysicalSecondList;
    @BindView(R.id.tv_before_club)
    TextView tvBeforeClub;
    @BindView(R.id.tv_next_club)
    TextView tvNextClub;

    private PhysicalTestSecondMessageAdapter physicalTestMessageAdapter;
    private LinearLayoutManager linearLayoutManager;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PhysicalTestSecondActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_second);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPhysicalSecondList.setLayoutManager(linearLayoutManager);
        getData();
    }

    private void getData() {
        showResult();
    }

    private void showResult() {
        if (physicalTestMessageAdapter == null) {
            physicalTestMessageAdapter = new PhysicalTestSecondMessageAdapter(getContext());
            rvPhysicalSecondList.setAdapter(physicalTestMessageAdapter);

        } else {
            rvPhysicalSecondList.setAdapter(physicalTestMessageAdapter);
            physicalTestMessageAdapter.notifyDataSetChanged();

        }
    }


    @Override
    protected void initTopBar() {
        initTitle("体测第二节（共四节）");
        initBack();

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_before_club, R.id.tv_next_club})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_before_club:
                finish();
                break;
            case R.id.tv_next_club:
                break;
        }
    }
}
