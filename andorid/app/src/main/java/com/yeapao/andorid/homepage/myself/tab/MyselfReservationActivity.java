package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.NetImpl;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.ReservationLessonModel;
import com.yeapao.andorid.model.ReservationListModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/25.
 */

public class MyselfReservationActivity extends BaseActivity {

    private static final String TAG = "MyselfReservationActivity";
    @BindView(R.id.rv_my_reservation_list)
    RecyclerView rvMyReservationList;


    private LinearLayoutManager llm;
    private MyselfReservationMessageAdapter mReservationAdapter;
    private Gson gson = new Gson();
    private ReservationListModel listModel;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfReservationActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_reservation);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMyReservationList.setLayoutManager(llm);
        getData();
    }

    private void getData() {
        if (TextUtils.isEmpty(String.valueOf(GlobalDataYepao.getUser(getContext()).getId()))) {
            LoginActivity.start(getContext());
            return;
        }
        CloudClient.doHttpRequest(getContext(), ConstantYeaPao.GET_RESERVATION_LIST,
                NetImpl.getInstance().getReservationList(String.valueOf(GlobalDataYepao.getUser(getContext()).getId())), null, new JSONResultHandler() {
                    @Override
                    public void onSuccess(String jsonString) {
                        LogUtil.e(TAG,jsonString);
                         listModel = gson.fromJson(jsonString, ReservationListModel.class);
                        if (listModel.getErrmsg().equals("ok")) {
                                showResult();
                        }
                    }

                    @Override
                    public void onError(VolleyError errorMessage) {

                    }
                });
    }

    private void showResult() {
        if (mReservationAdapter == null) {
            mReservationAdapter = new MyselfReservationMessageAdapter(getContext(),listModel);
            rvMyReservationList.setAdapter(mReservationAdapter);
        } else {
            rvMyReservationList.setAdapter(mReservationAdapter);
            mReservationAdapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void initTopBar() {
        initTitle("我的预约");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

}
