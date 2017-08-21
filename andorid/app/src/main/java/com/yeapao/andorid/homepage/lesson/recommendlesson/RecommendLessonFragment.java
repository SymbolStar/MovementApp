package com.yeapao.andorid.homepage.lesson.recommendlesson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.shopping.ShoppingOrderActivity;
import com.yeapao.andorid.lessondetails.LessonDetailActivity;
import com.yeapao.andorid.model.RecommendLessonModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static github.chenupt.multiplemodel.aa.AAModelFactory.TAG;

/**
 * Created by fujindong on 2017/8/19.
 */

public class RecommendLessonFragment extends Fragment {


    @BindView(R.id.rv_recommend_lesson_list)
    RecyclerView rvRecommendLessonList;
    private String bgRes;

    private RecommendLessonMessageAdapter lessonMessageAdapter;
    private LinearLayoutManager llm;

    private RecommendLessonModel lessonModel;


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
        ToastManager.showToast(getContext(), bgRes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend_lesson, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvRecommendLessonList.setLayoutManager(llm);
        getData();
    }

    private void getData() {
        DialogUtils.showProgressDialog(getContext());
        getNetWork(RecommendLessonActivity.shopId, GlobalDataYepao.getUser(getContext()).getId(),bgRes);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }


    private void getNetWork(String shopId, String id, String time) {
        subscription = Network.getYeapaoApi()
                .requesetRecommendLesson(shopId,id,time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

                  Observer<RecommendLessonModel> modelObserver = new Observer<RecommendLessonModel>() {
                    @Override
                    public void onCompleted() {
                        DialogUtils.cancelProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(RecommendLessonModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            lessonModel = model;
                            showResult(model);
                        }
                    }
                };

    private void showResult(RecommendLessonModel model) {
        if (lessonMessageAdapter == null) {
            lessonMessageAdapter = new RecommendLessonMessageAdapter(getContext(), model);
            rvRecommendLessonList.setAdapter(lessonMessageAdapter);
            lessonMessageAdapter.setItemOnClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(), "onItemClick");
                    LessonDetailActivity.start(getContext(),String.valueOf(lessonModel.getData().get(position).getScheduleId()));
                }
            });
        } else {
            rvRecommendLessonList.setAdapter(lessonMessageAdapter);
            lessonMessageAdapter.notifyDataSetChanged();
        }
    }


}
