package com.yeapao.andorid.homepage.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;

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

        showResult();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showResult() {
        if (shoppingMessageAdapter == null) {
            shoppingMessageAdapter = new ShoppingMessageAdapter(getContext());
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

    @OnClick(R.id.btn_order)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(),ShoppingOrderActivity.class));
    }
}
