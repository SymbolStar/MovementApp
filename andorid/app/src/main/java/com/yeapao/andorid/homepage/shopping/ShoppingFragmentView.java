package com.yeapao.andorid.homepage.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.myself.MyselfPresenter;
import com.yeapao.andorid.model.ShoppingDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by fujindong on 2017/7/11.
 */

public class ShoppingFragmentView extends Fragment implements ShoppingContract.View {


    @BindView(R.id.rv_shopping_list)
    RecyclerView rvShoppingList;

    Unbinder unbinder;
    @BindView(R.id.btn_order)
    Button btnOrder;
    @BindView(R.id.srl_shopping_refresh)
    SwipeRefreshLayout srlShoppingRefresh;
    @BindView(R.id.tv_order_title)
    TextView tvOrderTitle;
    @BindView(R.id.rl_order)
    RelativeLayout rlOrder;


    private ShoppingContract.Presenter mPresenter;

    private LinearLayoutManager linearLayoutManager;
    private ShoppingMessageAdapter shoppingMessageAdapter;
    private int checkedPosition = 1111;
    private ShoppingDataModel shoppingDataModels;


    public ShoppingFragmentView() {

    }

    public static ShoppingFragmentView newInstance() {
        return new ShoppingFragmentView();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_shopping, null);
        unbinder = ButterKnife.bind(this, view);
        srlShoppingRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData();
            }
        });
        srlShoppingRefresh.setColorSchemeResources(R.color.colorPrimary);
        initViews(view);
        mPresenter.start();
        return view;
    }

    @Override
    public void setPresenter(ShoppingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initViews(View view) {
        rlOrder.setVisibility(View.GONE);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvShoppingList.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @Override
    public void showResult(final ShoppingDataModel shoppingDataModel) {
        this.shoppingDataModels = shoppingDataModel;
        if (shoppingMessageAdapter == null) {
            shoppingMessageAdapter = new ShoppingMessageAdapter(getContext(), shoppingDataModel);
            rvShoppingList.setAdapter(shoppingMessageAdapter);
            shoppingMessageAdapter.setItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    CheckBox checkBox = (CheckBox) v.findViewById(R.id.cb_down);
                    if (checkBox.isChecked()) {

                        if (checkedPosition != 1111) {
                            shoppingDataModel.getData().get(checkedPosition).setLineChecked(false);
                        }
                        shoppingDataModel.getData().get(position).setLineChecked(true);
                        checkedPosition = position;
                        showOrderTab();

                    } else {
                        shoppingDataModel.getData().get(position).setLineChecked(false);
                        checkedPosition = 1111;
                        rlOrder.setVisibility(View.GONE);
                    }

                    LogUtil.e("shoppingListposition", String.valueOf(checkedPosition));
                    shoppingMessageAdapter.notifyDataSetChanged();
                }
            });

        } else {
            rvShoppingList.setAdapter(shoppingMessageAdapter);
            shoppingMessageAdapter.notifyDataSetChanged();
        }
    }

    private void showOrderTab() {
        rlOrder.setVisibility(View.VISIBLE);
        tvOrderTitle.setText(shoppingDataModels.getData().get(checkedPosition).getCurriculumName() + " ￥" +
                shoppingDataModels.getData().get(checkedPosition).getLinePrice());
    }

    @Override
    public void startLoading() {
        srlShoppingRefresh.setRefreshing(true);
    }

    @Override
    public void stopLoading() {

        srlShoppingRefresh.setRefreshing(false);
    }

    @OnClick(R.id.btn_order)
    public void onViewClicked() {

        if (GlobalDataYepao.isLogin()) {
            ShoppingOrderActivity.start(getContext(), String.valueOf(shoppingDataModels.getData().get(checkedPosition).getMap_curriculum_typesId()),
                    String.valueOf(shoppingDataModels.getData().get(checkedPosition).getLinePrice()),
                    GlobalDataYepao.getUser(getContext()).getId());
        } else {
            LoginActivity.start(getContext());
        }
    }
}
