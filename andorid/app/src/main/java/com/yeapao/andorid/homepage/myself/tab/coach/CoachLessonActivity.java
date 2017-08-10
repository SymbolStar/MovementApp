package com.yeapao.andorid.homepage.myself.tab.coach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.homepage.shopping.ShoppingContract;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.RollCallListModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/31.
 */

public class CoachLessonActivity extends BaseActivity {

    private static final String TAG = "CoachLessonActivity";
    @BindView(R.id.tv_choose_all)
    TextView tvChooseAll;
    @BindView(R.id.cb_choose_all)
    CheckBox cbChooseAll;
    @BindView(R.id.rv_top)
    RelativeLayout rvTop;
    @BindView(R.id.tv_people_num)
    TextView tvPeopleNum;
    @BindView(R.id.tv_start_lesson)
    TextView tvStartLesson;
    @BindView(R.id.rv_coach_lesson_list)
    RecyclerView rvCoachLessonList;


    private CoachLessonCallMessageAdapter messageAdapter;
    private String scheduleId;
    private String scheduleName;


    public static void start(Context context, String scheduleId, String name) {
        Intent intent = new Intent();
        intent.putExtra("scheduleId", scheduleId);
        intent.putExtra("name", name);
        intent.setClass(context, CoachLessonActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_lesson_call_name);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        scheduleId = intent.getStringExtra("scheduleId");
        scheduleName = intent.getStringExtra("name");
        LogUtil.e(TAG, scheduleId);
        initTopBar();
        initView();

    }

    private void initView() {
        rvCoachLessonList.setLayoutManager(new GridLayoutManager(this, 3));

        getData();
    }

    private void getData() {

        getNetWork(scheduleId);


    }

    private void showResult(RollCallListModel model) {
        tvPeopleNum.setText(String.valueOf(model.getData().size())+"人");
        if (messageAdapter == null) {
            messageAdapter = new CoachLessonCallMessageAdapter(getContext(), model);
            rvCoachLessonList.setAdapter(messageAdapter);
            messageAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(), "onClick");
//                    messageAdapter.refreshItem(position);

                }
            });
        } else {
            rvCoachLessonList.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void initTopBar() {
        initTitle(scheduleName);
        initRightText("课程详情");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_choose_all, R.id.cb_choose_all, R.id.tv_start_lesson})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_all:
                break;
            case R.id.cb_choose_all:
                if (cbChooseAll.isChecked()) {
                    messageAdapter.chooseAllStatus(true);
                } else {
                    messageAdapter.chooseAllStatus(false);

                }
                break;
            case R.id.tv_start_lesson:
                String ids = "";
                RollCallListModel model = new RollCallListModel();
                model = messageAdapter.getEndData();
                for (int i = 0; i < model.getData().size(); i++) {
                    if (model.getData().get(i).isStatus()) {
                        if (ids.equals("")) {
                            ids = String.valueOf(model.getData().get(i).getReservationDetailsId());
                        } else {
                            ids+= ","+String.valueOf(model.getData().get(i).getReservationDetailsId());
                        }
                    }
                }

                LogUtil.e(TAG,ids);

                requeastCall(scheduleId,ids);

                CoachLessonStatusActivity.start(getContext(),scheduleId,scheduleName);

                break;
        }
    }


    //            获取学员列表
    private void getNetWork(String id) {
        subscription = Network.getYeapaoApi()
                .getRollCallList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<RollCallListModel> modelObserver = new Observer<RollCallListModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(RollCallListModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                showResult(model);
            }
        }
    };


    private void requeastCall(String id, String ids) {

        subscription = Network.getYeapaoApi()
                .requestRollCall(id, ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(requestCallModelObserver);
    }

    Observer<NormalDataModel> requestCallModelObserver = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {
            finish();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {

            }
        }
    };

}
