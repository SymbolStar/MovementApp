package com.yeapao.andorid.homepage.myself.tab.food;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.model.FoodInfoModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/8/1.
 */

public class FoodDetailFragment extends Fragment {


    @BindView(R.id.rv_food_detial_list)
    RecyclerView rvFoodDetialList;
    Unbinder unbinder;
    private String bgRes;//get background res

    private FoodMessageAdapter messageAdapter;

    protected Subscription subscription;

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgRes = getArguments().getString("data");//get background res
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
        getNetWork(bgRes);
    }

    private void showResult(FoodInfoModel model) {
        if (messageAdapter == null) {
            messageAdapter = new FoodMessageAdapter(getContext(),getFragmentManager(),model);
            rvFoodDetialList.setAdapter(messageAdapter);

        } else {
            rvFoodDetialList.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();

        }
    }

    private void getNetWork(String date) {
        LogUtil.e(bgRes,date);
        subscription = Network.getYeapaoApi()
                .getFoodInfos(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( modelObserver);
    }

    Observer<FoodInfoModel> modelObserver = new Observer<FoodInfoModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(bgRes,e.toString());

        }

        @Override
        public void onNext(FoodInfoModel model) {
            LogUtil.e(bgRes, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                showResult(model);
            }
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("foodDetailFragment","onresume");
        if (GlobalDataYepao.foodFlag) {
            messageAdapter.refresh();
            GlobalDataYepao.foodFlag = false;
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }


}
