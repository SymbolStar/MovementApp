package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

/**
 * Created by fujindong on 2017/7/28.
 */


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.homepage.myself.tab.coach.CoachLessonActivity;
import com.yeapao.andorid.model.IAmCoachListModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static github.chenupt.multiplemodel.aa.AAModelFactory.TAG;

/**
 * Created by chenupt@gmail.com on 2015/1/31.
 * Description TODO
 */
public class GuideFragment extends Fragment {

    @BindView(R.id.rv_lesson_reservation_list)
    RecyclerView rvLessonReservationList;
    Unbinder unbinder;
    private String bgRes;
    private ImageView imageView;

    private LessonReservationMessageAdapter lessonReservationMessageAdapter;
    private LinearLayoutManager llm;


    protected Subscription subscription;

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgRes = getArguments().getString("data");
        ToastManager.showToast(getContext(), bgRes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_reservation, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        imageView = (ImageView) getView().findViewById(R.id.image);
//        imageView.setBackgroundResource(bgRes);
        initView();

    }

    private void initView() {
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvLessonReservationList.setLayoutManager(llm);
        getData();
    }

    private void getData() {

        getCoachReservationLesson(GlobalDataYepao.getUser(getContext()).getId(),bgRes);

    }

    private void showResult(final IAmCoachListModel model) {
        if (lessonReservationMessageAdapter == null) {
            lessonReservationMessageAdapter = new LessonReservationMessageAdapter(getContext(),model);
            rvLessonReservationList.setAdapter(lessonReservationMessageAdapter);
            lessonReservationMessageAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(), position);
//                    TODO 需要来的activity进行判断
                    String activity = getActivity().getClass().getSimpleName();
                    if (activity.equals("MyselfCoachActivity")) {
                        CoachLessonActivity.start(getContext(),String.valueOf(model.getData().get(position).getScheduleId()),
                                model.getData().get(position).getScheduleTypeName());
                    }

                }
            });
        } else {
            rvLessonReservationList.setAdapter(lessonReservationMessageAdapter);
            lessonReservationMessageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }


            private void getCoachReservationLesson(String id,String data) {
                    subscription = Network.getYeapaoApi()
                            .getCoachList(id,data)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserverCoach);
                }

                  Observer<IAmCoachListModel> modelObserverCoach = new Observer<IAmCoachListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(IAmCoachListModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            showResult(model);
                        }
                    }
                };


}

