package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.BodySideListModel;
import com.yeapao.andorid.model.BodySideThirdSaveModel;
import com.yeapao.andorid.model.BodySideThreeBackModel;
import com.yeapao.andorid.model.BodySideThreeGetDataModel;
import com.yeapao.andorid.model.BodySideTwoGetBackModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/30.
 */

public class PhysicalTestThirdActivity extends BaseActivity {

    private static final String TAG = "PhysicalTestThirdActivity";
    @BindView(R.id.tv_before_club)
    TextView tvBeforeClub;
    @BindView(R.id.tv_next_club)
    TextView tvNextClub;
    @BindView(R.id.rv_physical_third_list)
    RecyclerView rvPhysicalThirdList;

    private PhysicalTestThirdMessageAdapter physicalTestMessageAdapter;
    private LinearLayoutManager linearLayoutManager;


    private BodySideListModel bodySideListModel;
    private int position;

    private List<BodySideThirdSaveModel> bodySideThirdSaveModels = new ArrayList<>();


    public static void start(Context context, BodySideListModel bodySideListModel, int position) {

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG, bodySideListModel);
        intent.putExtras(bundle);
        intent.putExtra("position", position);
        intent.setClass(context, PhysicalTestThirdActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_third);
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
        rvPhysicalThirdList.setLayoutManager(linearLayoutManager);
        initBodySideModel();
        getData();
    }

    private void initBodySideModel() {
        for (int i = 0; i < 2; i++) {
            BodySideThirdSaveModel bodySideThirdSaveModel = new BodySideThirdSaveModel();
            bodySideThirdSaveModel.setUpperLimbStrength("0");
            bodySideThirdSaveModel.setLowerExtremityStrength("0");
            bodySideThirdSaveModel.setPrecursor("0");
            bodySideThirdSaveModel.setHeartRateOne("0");
            bodySideThirdSaveModel.setHeartRateTwo("0");
            bodySideThirdSaveModel.setHeartRateThree("0");
            bodySideThirdSaveModel.setBodySideId("0");
            bodySideThirdSaveModel.setBodySideThree("0");
            bodySideThirdSaveModels.add(bodySideThirdSaveModel);

        }

    }

    private void getData() {


        getBodySideThreeData(String.valueOf(bodySideListModel.getData().get(position).getScheduleId()));








    }

    private void showResult() {
        if (physicalTestMessageAdapter == null) {
            physicalTestMessageAdapter = new PhysicalTestThirdMessageAdapter(getContext(),bodySideListModel.getData().get(position).getBodySideUserOut(),
                    bodySideThirdSaveModels);
            rvPhysicalThirdList.setAdapter(physicalTestMessageAdapter);

        } else {
            rvPhysicalThirdList.setAdapter(physicalTestMessageAdapter);
            physicalTestMessageAdapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void initTopBar() {
        initTitle("体测第三节（共四节）");
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
                bodySideThirdSaveModels = physicalTestMessageAdapter.getThirdBodyData();
                uploadBodySideThree(0);
                uploadBodySideThree(1);

                PhysicalTestForthActivity.start(getContext(),bodySideListModel,position);

                break;
        }
    }



            private void uploadBodySideThree(int position) {
                    subscription = Network.getYeapaoApi()
                            .uploadDataThird(bodySideThirdSaveModels.get(position).getUpperLimbStrength(),
                                    bodySideThirdSaveModels.get(position).getLowerExtremityStrength(),
                                    bodySideThirdSaveModels.get(position).getPrecursor(),
                                    bodySideThirdSaveModels.get(position).getHeartRateOne(),
                                    bodySideThirdSaveModels.get(position).getHeartRateTwo(),
                                    bodySideThirdSaveModels.get(position).getHeartRateThree(),
                                    bodySideThirdSaveModels.get(position).getBodySideId(),
                                    bodySideThirdSaveModels.get(position).getBodySideThree())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserverBodySideThree);
                }

                  Observer<BodySideThreeBackModel> modelObserverBodySideThree = new Observer<BodySideThreeBackModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(BodySideThreeBackModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {

                        }
                    }
                };


//                获取第二节内容 从中获取bodySideId
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
                bodySideThirdSaveModels.get(0).setBodySideId(String.valueOf(model.getData().get(0).getBodySideId()));
                bodySideThirdSaveModels.get(1).setBodySideId(String.valueOf(model.getData().get(1).getBodySideId()));
            }
        }
    };



            private void getBodySideThreeData(String id) {
                    subscription = Network.getYeapaoApi()
                            .getBodySideThree(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserverBodySideThreeGetData );
                }

                  Observer<BodySideThreeGetDataModel> modelObserverBodySideThreeGetData = new Observer<BodySideThreeGetDataModel>() {
                    @Override
                    public void onCompleted() {
                            showResult();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(BodySideThreeGetDataModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            for (int i = 0; i < 2; i++) {
                                bodySideThirdSaveModels.get(i).setUpperLimbStrength(model.getData().get(i).getUpperLimbStrength());
                                bodySideThirdSaveModels.get(i).setLowerExtremityStrength(model.getData().get(i).getLowerExtremityStrength());
                                bodySideThirdSaveModels.get(i).setPrecursor(model.getData().get(i).getPrecursor());
                                bodySideThirdSaveModels.get(i).setHeartRateOne(model.getData().get(i).getHeartRateOne());
                                bodySideThirdSaveModels.get(i).setHeartRateTwo(model.getData().get(i).getHeartRateTwo());
                                bodySideThirdSaveModels.get(i).setHeartRateThree(model.getData().get(i).getHeartRateThree());
                                bodySideThirdSaveModels.get(i).setBodySideId(String.valueOf(model.getData().get(i).getBodySideId()));
                                bodySideThirdSaveModels.get(i).setBodySideThree(String.valueOf(model.getData().get(i).getBodySideThreeId()));
                            }

                        } else {
                            getBodySideTwo(String.valueOf(bodySideListModel.getData().get(position).getScheduleId()));
                        }
                    }
                };

}
