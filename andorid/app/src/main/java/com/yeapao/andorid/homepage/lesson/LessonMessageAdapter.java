package com.yeapao.andorid.homepage.lesson;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.lessondetails.LessonDetailActivity;
import com.yeapao.andorid.model.HomeList;
import com.yeapao.andorid.storedetails.StoreDetailActivity;

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
    private LessonScreeningListener mScreeningListener;
    private HomeList mHomeMessageList;

    private  static boolean isHeaderGone = false;

    private static final int NORMAL_TYPE = 0;
    private static final int HEADER_TYPE = 1;


    public LessonMessageAdapter(Context context, HomeList homeList) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        mHomeMessageList = homeList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case NORMAL_TYPE:
                return new NormalViewHolder(inflater.inflate(R.layout.item_lesson_detail_card, parent, false), mListener);
            case HEADER_TYPE:
                return new HeaderViewHolder(inflater.inflate(R.layout.item_lesson_top, parent, false),mScreeningListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
//        TODO set lesson params
            Glide.with(mContext)
                    .load(ConstantYeaPao.HOST+mHomeMessageList.getShopScheduleList().get(position-1).getBackgroundImage())
                    .asBitmap()
                    .placeholder(R.drawable.home_store_take_place)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.drawable.home_store_take_place)
                    .centerCrop()
                    .into(((NormalViewHolder)holder).ivLessonImage);


        } else {
//           TODO set lesson header

            ((HeaderViewHolder)holder).vpLessonImage.setAdapter(new LessonViewPager(mContext,mHomeMessageList.getAdvertisementList()));

            ((HeaderViewHolder)holder).ciLessonIndicator.setViewPager(((HeaderViewHolder)holder).vpLessonImage);
            ((HeaderViewHolder)holder).vpLessonImage.setCurrentItem(0);

        }
    }




    @Override
    public int getItemViewType(int position) {
        if (isHeaderGone) {
            return NORMAL_TYPE;
        } else {
            if (position == 0) {
                return HEADER_TYPE;
            } else {
                return NORMAL_TYPE;
            }
        }

    }



    @Override
    public int getItemCount() {
        return mHomeMessageList.getShopScheduleList().size()+1;
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
            mContext.startActivity(new Intent(mContext, StoreDetailActivity.class));
        }

        @OnClick(R.id.btn_reservation)
        void setBtnReservation() {
            ToastManager.showToast(mContext,"Reservation");
            mContext.startActivity(new Intent(mContext, LessonDetailActivity.class));
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }

    public void setScreeningListener(LessonScreeningListener listener) {
        mScreeningListener = listener;
    }





    static class HeaderViewHolder  extends RecyclerView.ViewHolder{

        private LessonScreeningListener listener;

        @BindView(R.id.vp_lesson_image)
        ViewPager vpLessonImage;
        @BindView(R.id.ci_lesson_indicator)
        CircleIndicator ciLessonIndicator;
        @BindView(R.id.ll_lesson_time)
        LinearLayout llLessonTime;
        @BindView(R.id.ll_lesson_status)
        LinearLayout llLessonStatus;
        @BindView(R.id.ll_lesson_scope)
        LinearLayout llLessonScope;

        @BindView(R.id.ll_lesson_header)
        LinearLayout llLessonHeader;

        HeaderViewHolder(View view,LessonScreeningListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
        }

        @OnClick(R.id.ll_lesson_time)
        void lessonTimeOnClick() {
            ToastManager.showToast(mContext,"time");
//            isHeaderGone = true;
            listener.screeningTitle("time");

        }
        @OnClick(R.id.ll_lesson_status)
        void lessonStatusOnClick() {
            ToastManager.showToast(mContext,"status");
//            isHeaderGone = true;
            listener.screeningTitle("status");
        }
        @OnClick(R.id.ll_lesson_scope)
        void lessonScopeOnClick() {
            ToastManager.showToast(mContext,"scope");
//            isHeaderGone = true;
            listener.screeningTitle("scope");
        }
    }
}
