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
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.myself.tab.coach.CoachLessonActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenupt@gmail.com on 2015/1/31.
 * Description TODO
 */
public class GuideFragment extends Fragment {

    @BindView(R.id.rv_lesson_reservation_list)
    RecyclerView rvLessonReservationList;
    Unbinder unbinder;
    private int bgRes;
    private ImageView imageView;

    private LessonReservationMessageAdapter lessonReservationMessageAdapter;
    private LinearLayoutManager llm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgRes = getArguments().getInt("data");
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
        showResult();
    }

    private void showResult() {
        if (lessonReservationMessageAdapter == null) {
            lessonReservationMessageAdapter = new LessonReservationMessageAdapter(getContext());
            rvLessonReservationList.setAdapter(lessonReservationMessageAdapter);
            lessonReservationMessageAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    ToastManager.showToast(getContext(), position);
//                    TODO 需要来的activity进行判断
                    String activity = getActivity().getClass().getSimpleName();
                    if (activity.equals("MyselfCoachActivity")) {
                        CoachLessonActivity.start(getContext());
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
    }
}

