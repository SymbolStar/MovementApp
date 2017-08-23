package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.NetImpl;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.MyselfLessonModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/23.
 */

public class MyselfLessonActivity extends BaseActivity {

    private static final String TAG = "MyselfLessonActivity";
    @BindView(R.id.tv_using)
    TextView tvUsing;
    @BindView(R.id.tv_overdue)
    TextView tvOverdue;
    @BindView(R.id.v_using_line)
    View vUsingLine;
    @BindView(R.id.v_overdue_line)
    View vOverdueLine;
    @BindView(R.id.rv_my_lesson_list)
    RecyclerView rvMyLessonList;

    private String status = "1";
    private MyselfLessonMessageAdapter myselfLessonMessageAdapter;
    private LinearLayoutManager llm;

    private Gson gson = new Gson();

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfLessonActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_lesson);
        ButterKnife.bind(this);
        initTopBar();
        initView();
    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMyLessonList.setLayoutManager(llm);
        initData("1");
    }

    private void initData(String flag) {

        String id = GlobalDataYepao.getUser(getContext()).getId();
        getNetWork(id, flag);
//
//        LogUtil.e(TAG,flag);
//        CloudClient.doHttpRequest(getContext(),
//                ConstantYeaPao.GET_LESSON_LIST,
//                NetImpl.getInstance().getLessonList(GlobalDataYepao.getUser(getContext()).getId(), flag),
//                null, new JSONResultHandler() {
//                    @Override
//                    public void onSuccess(String jsonString) {
//                        LogUtil.e(TAG,jsonString);
//                        MyselfLessonModel model = gson.fromJson(jsonString, MyselfLessonModel.class);
//                        if (model.getErrmsg().equals("ok")) {
//                                showResult(model);
//                        } else {
//                            ToastManager.showToast(getContext(),model.getErrmsg());
//                        }
//                    }
//
//                    @Override
//                    public void onError(VolleyError errorMessage) {
//                        ToastManager.showToast(getContext(),errorMessage.toString());
//                    }
//                });
    }

    private void showResult(MyselfLessonModel model) {
//        if (myselfLessonMessageAdapter == null) {
            myselfLessonMessageAdapter = new MyselfLessonMessageAdapter(getContext(),model,status);
            rvMyLessonList.setAdapter(myselfLessonMessageAdapter);
            myselfLessonMessageAdapter.setItemOnClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(), String.valueOf(position));

                }
            });

//        } else {
//            rvMyLessonList.setAdapter(myselfLessonMessageAdapter);
//            myselfLessonMessageAdapter.notifyDataSetChanged();
//        }



    }

    @Override
    protected void initTopBar() {
        initTitle("我的课程");
        initBack();

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_using, R.id.tv_overdue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_using:
                status = "1";
                tvUsing.setTextColor(getResources().getColor(R.color.myself_lesson_status_color));
                tvOverdue.setTextColor(getResources().getColor(R.color.text_color));
                vUsingLine.setVisibility(View.VISIBLE);
                vOverdueLine.setVisibility(View.GONE);
                initData("1");
                break;
            case R.id.tv_overdue:
                status = "0";
                tvUsing.setTextColor(getResources().getColor(R.color.text_color));
                tvOverdue.setTextColor(getResources().getColor(R.color.myself_lesson_status_color));
                vUsingLine.setVisibility(View.GONE);
                vOverdueLine.setVisibility(View.VISIBLE);
                initData("0");
                break;
        }
    }


            private void getNetWork(String id,String status) {
                    LogUtil.e(TAG,status);
                    subscription = Network.getYeapaoApi()
                            .requestMyCurriculumList(id,status)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserver);
                }

                  Observer<MyselfLessonModel> modelObserver = new Observer<MyselfLessonModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(MyselfLessonModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            showResult(model);

                        }
                    }
                };

}
