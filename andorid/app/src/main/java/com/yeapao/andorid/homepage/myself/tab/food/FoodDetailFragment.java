package com.yeapao.andorid.homepage.myself.tab.food;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fujindong on 2017/8/1.
 */

public class FoodDetailFragment extends Fragment {


    @BindView(R.id.rv_food_detial_list)
    RecyclerView rvFoodDetialList;
    Unbinder unbinder;
    private int bgRes;//get background res

    private FoodMessageAdapter messageAdapter;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgRes = getArguments().getInt("data");//get background res
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        rvFoodDetialList.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();
    }

    private void getData() {
        showResult();
    }

    private void showResult() {
        if (messageAdapter == null) {
            messageAdapter = new FoodMessageAdapter(getContext(),getFragmentManager());
            rvFoodDetialList.setAdapter(messageAdapter);

        } else {
            rvFoodDetialList.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
