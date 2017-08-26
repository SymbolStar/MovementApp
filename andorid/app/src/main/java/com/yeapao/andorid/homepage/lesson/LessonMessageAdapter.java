package com.yeapao.andorid.homepage.lesson;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.dialog.DialogCallback;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.shopping.ShoppingOrderActivity;
import com.yeapao.andorid.lessondetails.LessonDetailActivity;
import com.yeapao.andorid.model.HomeList;
import com.yeapao.andorid.model.LessonScreeningData;
import com.yeapao.andorid.model.SelectHomeList;
import com.yeapao.andorid.model.ShopScheduleList;
import com.yeapao.andorid.storedetails.StoreDetailActivity;
import com.yeapao.andorid.util.GlobalDataYepao;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
    private ReservationListener reservationListener;
    private HomeList mHomeMessageList;
    private SelectHomeList selectHomeList;
    private List<ShopScheduleList> shopScheduleList = new ArrayList<>();

    private  static boolean isHeaderGone = false;


    private static final int NORMAL_TYPE = 0;
    private static final int HEADER_TYPE = 1;

    private LessonScreeningData mLessonScreeningData = new LessonScreeningData();



    public LessonMessageAdapter(Context context, HomeList homeList) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        mHomeMessageList = homeList;
    }

    public void refreshShopScheduleList(SelectHomeList lists) {
        mHomeMessageList.getShopScheduleList().clear();
        mHomeMessageList.setShopScheduleList(lists.getData());

        notifyDataSetChanged();
    }

    public void refreshLessonScreening(LessonScreeningData lessonScreeningData) {
        mLessonScreeningData = lessonScreeningData;
        notifyItemChanged(0);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case NORMAL_TYPE:
                return new NormalViewHolder(inflater.inflate(R.layout.item_lesson_detail_card, parent, false), mListener,reservationListener);
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
            long bespeak, total;
            bespeak = mHomeMessageList.getShopScheduleList().get(position - 1).getBespeak();
            total = mHomeMessageList.getShopScheduleList().get(position - 1).getTotalNumber();
            ((NormalViewHolder) holder).roundCornerProgressBar.setProgress(bespeak);
            ((NormalViewHolder) holder).roundCornerProgressBar.setMax(total);
            ((NormalViewHolder) holder).tvBespeakNum.setText(String.valueOf(bespeak)+"/"+String.valueOf(total));

            if (mHomeMessageList.getShopScheduleList().size() == 1) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) ((NormalViewHolder) holder).cvStoreCard.getLayoutParams();
                layoutParams.bottomMargin = 900;
                ((NormalViewHolder) holder).cvStoreCard.setLayoutParams(layoutParams);
            } else {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) ((NormalViewHolder) holder).cvStoreCard.getLayoutParams();
                layoutParams.bottomMargin = 20;
                ((NormalViewHolder) holder).cvStoreCard.setLayoutParams(layoutParams);
            }

            if (mHomeMessageList.getShopScheduleList().get(position-1).getIsBespeak().equals("1")) {
                ((NormalViewHolder) holder).btnReservation.setText("已预约");
                ((NormalViewHolder) holder).btnReservation.setBackgroundColor(mContext.getResources().getColor(R.color.bg_grey));
            } else {
                ((NormalViewHolder) holder).btnReservation.setText("预约");
            }

            ((NormalViewHolder) holder).tvLessonName.setText(mHomeMessageList.getShopScheduleList().get(position-1).getCurriculumName());
            ((NormalViewHolder) holder).tvLessonAddress.setText(mHomeMessageList.getShopScheduleList().get(position-1).getDate()
            +"  "+mHomeMessageList.getShopScheduleList().get(position-1).getStartTime()+"/"+
            mHomeMessageList.getShopScheduleList().get(position-1).getAddress());

        } else {
//           TODO set lesson header
            ((HeaderViewHolder)holder).vpLessonImage.setAdapter(new LessonViewPager(mContext,mHomeMessageList.getAdvertisementList()));
            ((HeaderViewHolder)holder).ciLessonIndicator.setViewPager(((HeaderViewHolder)holder).vpLessonImage);
            ((HeaderViewHolder)holder).vpLessonImage.setCurrentItem(0);

            ((HeaderViewHolder) holder).tvLessonTime.setText(mLessonScreeningData.getScopeTimeName());
            ((HeaderViewHolder) holder).tvLessonStatus.setText(mLessonScreeningData.getStatusName());
            ((HeaderViewHolder) holder).tvLessonScope.setText(mLessonScreeningData.getRegionName());

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





     class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnRecyclerViewClickListener listener;
        private ReservationListener reservationListener;

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
        @BindView(R.id.cv_store_card)
        CardView cvStoreCard;
        @BindView(R.id.rcp_bespeak)
        RoundCornerProgressBar roundCornerProgressBar;
        @BindView(R.id.tv_bespeak_num)
        TextView tvBespeakNum;

        NormalViewHolder(View view, OnRecyclerViewClickListener listener,ReservationListener reservationListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            this.reservationListener = reservationListener;
            view.setOnClickListener(this);

        }

        @OnClick(R.id.iv_lesson_collect)
        void setIvLessonCollect() {

        }

        @OnClick(R.id.tv_look_lesson_detail)
        void setTvLookLessonDetail() {
//            ToastManager.showToast(mContext,"lessonDetail");
            String scheduleId = mHomeMessageList.getShopScheduleList().get(getLayoutPosition() - 1).getScheduleId();
            LessonDetailActivity.start(mContext, scheduleId);
        }

        @OnClick(R.id.btn_reservation)
        void setBtnReservation() {
//            ToastManager.showToast(mContext,"Reservation");
            int position = getLayoutPosition()-1;
            if (GlobalDataYepao.getUser(mContext) == null) {
                LoginActivity.start(mContext);
            } else {
                if (mHomeMessageList.getShopScheduleList().get(position).getLinePrice().equals("0")) {
                    reservationListener.onReservationClickListener(mHomeMessageList.getShopScheduleList().get(position).getScheduleId(),
                            mHomeMessageList.getShopScheduleList().get(position).getCurriculumId(), String.valueOf(GlobalDataYepao.getUser(mContext).getId()));
                } else {
                    if (mHomeMessageList.getShopScheduleList().get(position).getMySchedule().equals("1")) {
                        reservationListener.onReservationClickListener(mHomeMessageList.getShopScheduleList().get(position).getScheduleId(),
                                mHomeMessageList.getShopScheduleList().get(position).getCurriculumId(), String.valueOf(GlobalDataYepao.getUser(mContext).getId()));
                    } else {
                        String scheduleId = mHomeMessageList.getShopScheduleList().get(getLayoutPosition() - 1).getScheduleId();
                        LessonDetailActivity.start(mContext, scheduleId);
                    }
                }
              }
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v, getLayoutPosition()-1);
            }
        }
    }

    public void setScreeningListener(LessonScreeningListener listener) {
        mScreeningListener = listener;
    }

    public void setReservationListener(ReservationListener listener) {
        reservationListener = listener;
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

        @BindView(R.id.tv_lesson_time)
        TextView tvLessonTime;
        @BindView(R.id.tv_lesson_status)
        TextView tvLessonStatus;
        @BindView(R.id.tv_lesson_scope)
        TextView tvLessonScope;

        HeaderViewHolder(View view,LessonScreeningListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
        }

        @OnClick(R.id.ll_lesson_time)
        void lessonTimeOnClick() {
//            ToastManager.showToast(mContext,"time");
//            isHeaderGone = true;
            listener.screeningTitle("time");

        }
        @OnClick(R.id.ll_lesson_status)
        void lessonStatusOnClick() {
//            ToastManager.showToast(mContext,"status");
//            isHeaderGone = true;
            listener.screeningTitle("status");
        }
        @OnClick(R.id.ll_lesson_scope)
        void lessonScopeOnClick() {
//            ToastManager.showToast(mContext,"scope");
//            isHeaderGone = true;
            listener.screeningTitle("scope");
        }
    }
}
