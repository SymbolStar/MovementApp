package com.yeapao.andorid.homepage.lesson.recommendlesson;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.SystemDateUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.homepage.lesson.LessonMessageAdapter;
import com.yeapao.andorid.lessondetails.LessonDetailActivity;
import com.yeapao.andorid.model.RecommendLessonModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/8/19.
 */

public class RecommendLessonMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecommendLessonMessageAdapter";


    private Context mContext;
    private LayoutInflater inflater;

    private OnRecyclerViewClickListener mListener;

    private RecommendLessonModel lessonModel;

    private GlideUtil glideUtil = new GlideUtil();


    public RecommendLessonMessageAdapter(Context context, RecommendLessonModel model) {
        lessonModel = model;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void setItemOnClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_recommend_lesson_card, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
//        TODO set lesson params
            ((ViewHolder) holder).tvLessonName.setText(lessonModel.getData().get(position).getCurriculumName());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(lessonModel.getData().get(position).getStartTime());
            stringBuilder.append("-");
            stringBuilder.append(lessonModel.getData().get(position).getEndTime());
            stringBuilder.append("/");
            stringBuilder.append(lessonModel.getData().get(position).getAddress());

            ((ViewHolder) holder).tvLessonAddress.setText(stringBuilder.toString());

            glideUtil.glideLoadingImage(mContext,ConstantYeaPao.HOST+lessonModel.getData().get(position).getBackgroundImage(),
                    R.drawable.home_store_take_place,((ViewHolder) holder).ivLessonImage);

            long bespeak, total;
            bespeak = lessonModel.getData().get(position).getBespeak();
            total = lessonModel.getData().get(position).getTotalNumber();
            ((ViewHolder) holder).rcpBespeak.setProgress(bespeak);
            ((ViewHolder) holder).rcpBespeak.setMax(total);
            ((ViewHolder) holder).tvBespeakNum.setText(String.valueOf(bespeak)+"/"+String.valueOf(total));

        }
    }

    @Override
    public int getItemCount() {
        return lessonModel.getData().size();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

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
        @BindView(R.id.rcp_bespeak)
        RoundCornerProgressBar rcpBespeak;
        @BindView(R.id.tv_bespeak_num)
        TextView tvBespeakNum;
        @BindView(R.id.cv_store_card)
        CardView cvStoreCard;

        ViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            view.setOnClickListener(this);
        }

        @OnClick(R.id.tv_look_lesson_detail)
        void setTvLookLessonDetail() {
            ToastManager.showToast(mContext,"lessonDetail");
//            LessonDetailActivity.start(mContext, scheduleId);
        }

        @OnClick(R.id.btn_reservation)
        void setBtnReservation() {
            ToastManager.showToast(mContext,"Reservation");
            int position = getLayoutPosition()-1;
            if (GlobalDataYepao.getUser(mContext) == null) {
                LoginActivity.start(mContext);
            } else {
            }
        }


        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v,getLayoutPosition());
            }
        }
    }
}
