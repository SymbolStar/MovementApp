package com.yeapao.andorid.homepage.lesson;

import android.content.Context;
import android.content.Intent;
import android.icu.math.MathContext;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.lessondetails.LessonDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by fujindong on 2017/7/12.
 */

public class LessonMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static Context mContext;
    private LayoutInflater inflater;
    private OnRecyclerViewClickListener mListener;

    private static final int NORMAL_TYPE = 0;
    private static final int HEADER_TYPE = 1;


    public LessonMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case NORMAL_TYPE:
                return new NormalViewHolder(inflater.inflate(R.layout.item_lesson_detail_card, parent, false), mListener);
            case HEADER_TYPE:
                return new HeaderViewHolder(inflater.inflate(R.layout.item_lesson_top, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
//        TODO set lesson params



        } else {
//           TODO set lesson header
            ((HeaderViewHolder)holder).vpLessonImage.setAdapter(new LessonViewPager());
            ((HeaderViewHolder)holder).ciLessonIndicator.setViewPager(((HeaderViewHolder)holder).vpLessonImage);
            ((HeaderViewHolder)holder).vpLessonImage.setCurrentItem(0);

        }
    }




    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        } else {
            return NORMAL_TYPE;
        }
    }



    @Override
    public int getItemCount() {
        return 6;
    }

    public void setItemClickListener(OnRecyclerViewClickListener listener) {
        mListener = listener;
    }

    static class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnRecyclerViewClickListener listener;

        @BindView(R.id.iv_lesson_image)
        ImageView ivLessonImage;
        @BindView(R.id.tv_look_lesson_detail)
        TextView tvLookLessonDetail;
        @BindView(R.id.btn_reservation)
        Button btnReservation;
        @BindView(R.id.tv_lesson_address)
        TextView tvLessonAddress;
        @BindView(R.id.tv_lesson_name)
        TextView tvLessonName;
        @BindView(R.id.iv_lesson_collect)
        ImageView ivLessonCollect;

        NormalViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            view.setOnClickListener(this);

        }

        @OnClick(R.id.iv_lesson_collect)
        void setIvLessonCollect() {

        }

        @OnClick(R.id.tv_look_lesson_detail)
        void setTvLookLessonDetail() {
            ToastManager.showToast(mContext,"lessonDetail");
            mContext.startActivity(new Intent(mContext, LessonDetailActivity.class));
        }

        @OnClick(R.id.btn_reservation)
        void setBtnReservation() {
            ToastManager.showToast(mContext,"Reservation");
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }

    static class HeaderViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.vp_lesson_image)
        ViewPager vpLessonImage;
        @BindView(R.id.ci_lesson_indicator)
        CircleIndicator ciLessonIndicator;

        HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
