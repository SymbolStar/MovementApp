package com.yeapao.andorid.homepage.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.myself.MyselfPresenter;
import com.yeapao.andorid.model.ShoppingDataModel;

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


    private ShoppingContract.Presenter mPresenter;

    private LinearLayoutManager linearLayoutManager;
    private ShoppingMessageAdapter shoppingMessageAdapter;

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
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvShoppingList.setLayoutManager(linearLayoutManager);

        srlShoppingRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData();
            }
        });
        srlShoppingRefresh.setColorSchemeResources(R.color.colorPrimary);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showResult(ShoppingDataModel shoppingDataModel) {
        if (shoppingMessageAdapter == null) {
            shoppingMessageAdapter = new ShoppingMessageAdapter(getContext(),shoppingDataModel);
            rvShoppingList.setAdapter(shoppingMessageAdapter);
            shoppingMessageAdapter.setItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(), "item Onclick");
                }
            });
        } else {
            rvShoppingList.setAdapter(shoppingMessageAdapter);
            shoppingMessageAdapter.notifyDataSetChanged();
        }
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
        startActivity(new Intent(getActivity(),ShoppingOrderActivity.class));
    }
}
