package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.BodySideListModel;
import com.yeapao.andorid.model.BodySideOneData;
import com.yeapao.andorid.model.BodySideOneGetModel;
import com.yeapao.andorid.model.BodySideSecondSaveModel;
import com.yeapao.andorid.model.BodySideTwoBackModel;
import com.yeapao.andorid.model.BodySideTwoGetBackModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.POST;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/29.
 */

public class PhysicalTestSecondActivity extends BaseActivity {

    private static final String TAG = "PhysicalTestSecondActivity";
    @BindView(R.id.rv_physical_second_list)
    RecyclerView rvPhysicalSecondList;
    @BindView(R.id.tv_before_club)
    TextView tvBeforeClub;
    @BindView(R.id.tv_next_club)
    TextView tvNextClub;

    private PhysicalTestSecondMessageAdapter physicalTestMessageAdapter;
    private LinearLayoutManager linearLayoutManager;

    private BodySideListModel bodySideListModel;
    private int position;

    private int bodySideIdOne, bodySideIdTwo;


    private List<BodySideSecondSaveModel> bodySideSecondSaveModels = new ArrayList<>();



    public static void start(Context context, BodySideListModel bodySideListModel,int position) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG, bodySideListModel);
        intent.putExtras(bundle);
        intent.putExtra("position", position);
        intent.setClass(context, PhysicalTestSecondActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_second);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        bodySideListModel = (BodySideListModel) intent.getSerializableExtra(TAG);
        position = intent.getIntExtra("position", 0);

        initTopBar();
        initView();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPhysicalSecondList.setLayoutManager(linearLayoutManager);
        initBodySideModel();
        getData();
    }

    private void initBodySideModel() {
        for (int i = 0; i < 2; i++) {
            BodySideSecondSaveModel bodySideSecondSaveModel = new BodySideSecondSaveModel();
            bodySideSecondSaveModel.setBodySideId("0");
            bodySideSecondSaveModel.setUpperRight("0");
            bodySideSecondSaveModel.setUpperLeft("0");
            bodySideSecondSaveModel.setAbdomen("0");
            bodySideSecondSaveModel.setWaist("0");
            bodySideSecondSaveModel.setHips("0");
            bodySideSecondSaveModel.setLowerLeft("0");
            bodySideSecondSaveModel.setLowerRight("0");
            bodySideSecondSaveModel.setBodySideTwo("0");
            bodySideSecondSaveModels.add(bodySideSecondSaveModel);
        }

    }

    private void getData() {



        getBodySideTwo(String.valueOf(bodySideListModel.getData().get(position).getScheduleId()));



    }





    private void showResult() {
        if (physicalTestMessageAdapter == null) {
            physicalTestMessageAdapter = new PhysicalTestSecondMessageAdapter(getContext(),bodySideListModel.getData().get(position).getBodySideUserOut(),bodySideSecondSaveModels);
            rvPhysicalSecondList.setAdapter(physicalTestMessageAdapter);

        } else {
            rvPhysicalSecondList.setAdapter(physicalTestMessageAdapter);
            physicalTestMessageAdapter.notifyDataSetChanged();

        }
    }

    //    TODO 第一节已体测 获取第一节数据
    private void getNetWorkOne(String id) {
        LogUtil.e(TAG,id);
        subscription = Network.getYeapaoApi()
                .getBodySideOne(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( oneModelObserver);
    }

    Observer<BodySideOneGetModel> oneModelObserver = new Observer<BodySideOneGetModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());
        }

        @Override
        public void onNext(BodySideOneGetModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                bodySideSecondSaveModels.get(0).setBodySideId(String.valueOf(model.getData().get(0).getBodySideId()));
                bodySideSecondSaveModels.get(1).setBodySideId(String.valueOf(model.getData().get(1).getBodySideId()));
                showResult();
            }
        }
    };


    @Override
    protected void initTopBar() {
        initTitle("体测第二节（共四节）");
        initBack();

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_before_club, R.id.tv_next_club})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_before_club:
                finish();
                break;
            case R.id.tv_next_club:

                bodySideSecondSaveModels = physicalTestMessageAdapter.getSecondBodyData();
                uploadData(0);
                uploadData(1);

                PhysicalTestThirdActivity.start(getContext(),bodySideListModel,position);
                break;
        }
    }


            private void uploadData(int position) {
                    subscription = Network.getYeapaoApi()
                            .uploadDataSecond(bodySideSecondSaveModels.get(position).getUpperRight(),
                                    bodySideSecondSaveModels.get(position).getUpperLeft(),
                                    bodySideSecondSaveModels.get(position).getAbdomen(),
                                    bodySideSecondSaveModels.get(position).getWaist(),
                                    bodySideSecondSaveModels.get(position).getHips(),
                                    bodySideSecondSaveModels.get(position).getLowerRight(),
                                    bodySideSecondSaveModels.get(position).getLowerLeft(),
                                    bodySideSecondSaveModels.get(position).getBodySideId(),
                                    bodySideSecondSaveModels.get(position).getBodySideTwo())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserver );
                }

                  Observer<BodySideTwoBackModel> modelObserver = new Observer<BodySideTwoBackModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(BodySideTwoBackModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {

                        }
                    }
                };



                        private void getBodySideTwo(String id) {
                                subscription = Network.getYeapaoApi()
                                        .getBodySideTwo(id)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(bodySideTwoModelObserver );
                            }

                              Observer<BodySideTwoGetBackModel> bodySideTwoModelObserver = new Observer<BodySideTwoGetBackModel>() {
                                @Override
                                public void onCompleted() {
                                    showResult();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    LogUtil.e(TAG,e.toString());

                                }

                                @Override
                                public void onNext(BodySideTwoGetBackModel model) {
                                    LogUtil.e(TAG, model.getErrmsg());
                                    if (model.getErrmsg().equals("ok")) {
                                        for (int i = 0; i < model.getData().size(); i++) {
                                            bodySideSecondSaveModels.get(i).setBodySideTwo(String.valueOf(model.getData().get(i).getBodySideTwoId()));
                                            bodySideSecondSaveModels.get(i).setUpperRight(model.getData().get(i).getUpperRight());
                                            bodySideSecondSaveModels.get(i).setUpperLeft(model.getData().get(i).getUpperLeft());
                                            bodySideSecondSaveModels.get(i).setAbdomen(model.getData().get(i).getAbdomen());
                                            bodySideSecondSaveModels.get(i).setWaist(model.getData().get(i).getWaist());
                                            bodySideSecondSaveModels.get(i).setHips(model.getData().get(i).getHips());
                                            bodySideSecondSaveModels.get(i).setLowerRight(model.getData().get(i).getLowerRight());
                                            bodySideSecondSaveModels.get(i).setLowerLeft(model.getData().get(i).getLowerLeft());
                                            bodySideSecondSaveModels.get(i).setBodySideId(String.valueOf(model.getData().get(i).getBodySideId()));

                                        }



                                    } else {
                                        getNetWorkOne(String.valueOf(bodySideListModel.getData().get(position).getScheduleId()));
                                    }
                                }
                            };

}
