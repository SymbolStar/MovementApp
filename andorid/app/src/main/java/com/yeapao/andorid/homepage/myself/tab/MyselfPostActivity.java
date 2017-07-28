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
 * Created by fujindong on 2017/7/23.
 */

public class MyselfPostActivity extends BaseActivity {

    private static final String TAG = "MyselfPostActivity";
    @BindView(R.id.rv_my_post_list)
    RecyclerView rvMyPostList;
    private LinearLayoutManager llm;
    private MyselfPostMessageAdapter mPostMessageAdapter;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfPostActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMyPostList.setLayoutManager(llm);

        getData();
        
    }

    private void getData() {


        refreshView();
    }

    private void refreshView() {
        if (mPostMessageAdapter == null) {
            mPostMessageAdapter = new MyselfPostMessageAdapter(getContext());
            rvMyPostList.setAdapter(mPostMessageAdapter);
            mPostMessageAdapter.setItemOnClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(), "onItemClick");
                }
            });
        } else {
            rvMyPostList.setAdapter(mPostMessageAdapter);
            mPostMessageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initTopBar() {
        initTitle("我的帖子");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    
}
