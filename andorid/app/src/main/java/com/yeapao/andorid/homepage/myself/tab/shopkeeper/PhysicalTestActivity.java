package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/28.
 */

public class PhysicalTestActivity extends BaseActivity {

    private static final String TAG = "PhysicalTestActivity";
    @BindView(R.id.tv_next_club)
    TextView tvNextClub;
    @BindView(R.id.rv_physical_test_list)
    RecyclerView rvPhysicalTestList;


    private PhysicalTestMessageAdapter physicalTestMessageAdapter;
    private LinearLayoutManager linearLayoutManager;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PhysicalTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//初始隐藏键盘
        setContentView(R.layout.activity_physical_test);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPhysicalTestList.setLayoutManager(linearLayoutManager);
        getData();
    }

    private void getData() {
        showResult();
    }

    private void showResult() {
        if (physicalTestMessageAdapter == null) {
            physicalTestMessageAdapter = new PhysicalTestMessageAdapter(getContext());
            rvPhysicalTestList.setAdapter(physicalTestMessageAdapter);

        } else {
            rvPhysicalTestList.setAdapter(physicalTestMessageAdapter);
            physicalTestMessageAdapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void initTopBar() {
        initBack();
        initTitle("体测第一节（共四节）");
    }

    @OnClick(R.id.tv_next_club)
    void setTvNextClub(View view) {

    }


    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick(R.id.tv_next_club)
    public void onViewClicked() {
        ToastManager.showToast(getContext(),"下一节");
        PhysicalTestSecondActivity.start(getContext());
    }


}
