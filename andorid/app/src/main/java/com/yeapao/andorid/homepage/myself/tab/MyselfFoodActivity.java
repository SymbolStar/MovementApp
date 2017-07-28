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
 * Created by fujindong on 2017/7/27.
 */

public class MyselfFoodActivity extends BaseActivity {

    private static final String TAG = "MyselfFoodActivity";
    @BindView(R.id.rv_food_list)
    RecyclerView rvFoodList;
    private LinearLayoutManager llm;
    private MyselfFoodMessageAdapter foodMessageAdapter;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfFoodActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_food);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvFoodList.setLayoutManager(llm);

        getData();
    }

    private void getData() {
        showResult();
    }

    private void showResult() {
        if (foodMessageAdapter == null) {
            foodMessageAdapter = new MyselfFoodMessageAdapter(getContext());
            rvFoodList.setAdapter(foodMessageAdapter);

        } else {
            rvFoodList.setAdapter(foodMessageAdapter);
            foodMessageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initTopBar() {
        initTitle("一周食谱");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
