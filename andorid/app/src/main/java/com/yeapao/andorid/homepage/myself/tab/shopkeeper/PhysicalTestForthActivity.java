package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/31.
 */

public class PhysicalTestForthActivity extends BaseActivity {

    private static final String TAG = "PhysicalTestForthActivity";
    @BindView(R.id.tv_physical_finish)
    TextView tvPhysicalFinish;
    @BindView(R.id.rv_physical_forth_list)
    RecyclerView rvPhysicalForthList;


    private PhysicalTestForthMessageAdapter physicalTestMessageAdapter;
    private LinearLayoutManager linearLayoutManager;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PhysicalTestForthActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_forth);
        ButterKnife.bind(this);
        initTopBar();
        initView();

    }


    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPhysicalForthList.setLayoutManager(linearLayoutManager);
        getData();
    }

    private void getData() {
        showResult();
    }

    private void showResult() {
        if (physicalTestMessageAdapter == null) {
            physicalTestMessageAdapter = new PhysicalTestForthMessageAdapter(getContext());
            rvPhysicalForthList.setAdapter(physicalTestMessageAdapter);

        } else {
            rvPhysicalForthList.setAdapter(physicalTestMessageAdapter);
            physicalTestMessageAdapter.notifyDataSetChanged();

        }
    }


    @Override
    protected void initTopBar() {
        initTitle("体测第四节（共四节）");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick(R.id.tv_physical_finish)
    public void onViewClicked() {
//        TODO 上传数据
    }
}
