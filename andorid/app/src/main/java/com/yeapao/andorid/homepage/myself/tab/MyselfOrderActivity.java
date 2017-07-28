package com.yeapao.andorid.homepage.myself.tab;

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
 * Created by fujindong on 2017/7/24.
 */

public class MyselfOrderActivity extends BaseActivity {

    private static final String TAG = "MySelfOrderActivity";
    private LinearLayoutManager llm;
    private MyselfOrderMessageAdapter mOrderMessageAdapter;


    @BindView(R.id.rv_my_order_list)
    RecyclerView rvMyOrderList;



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
        showResult();
    }

    private void showResult() {
        if (mOrderMessageAdapter == null) {
            mOrderMessageAdapter = new MyselfOrderMessageAdapter(getContext());
            rvMyOrderList.setAdapter(mOrderMessageAdapter);
            mOrderMessageAdapter.setOnItemClick(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(),TAG+"rv onClick");
                    MyselfOrderDetailActivity.start(getContext());
                }
            });
        } else {
            rvMyOrderList.setAdapter(mOrderMessageAdapter);
            mOrderMessageAdapter.notifyDataSetChanged();
        }
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
}
