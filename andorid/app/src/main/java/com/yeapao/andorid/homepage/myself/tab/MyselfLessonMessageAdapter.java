package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.myself.MyselfMessageAdapter;
import com.yeapao.andorid.model.MyselfLessonModel;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by fujindong on 2017/7/23.
 */

public class MyselfLessonMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private OnRecyclerViewClickListener mListener;

    private MyselfLessonModel lessonModel;

    private String status = "0";

    private ConstraintSet applyConstraintSet = new ConstraintSet();

    public MyselfLessonMessageAdapter(Context context,MyselfLessonModel lessonModel,String status) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.lessonModel = lessonModel;
        this.status = status;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_my_lesson_card, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (status.equals("1")) {
            ((ViewHolder) holder).tvMyLessonTitle.setText(lessonModel.getData().get(position).getCurriculumType());
            ((ViewHolder) holder).tvMyLessonRemainingDay.setText(lessonModel.getData().get(position).getTime());
            ((ViewHolder) holder).tvMyLessonRemainingClub.setText(lessonModel.getData().get(position).getSurplusNum());
            ((ViewHolder) holder).tvMyLessonPrice.setText(lessonModel.getData().get(position).getTotalPrice());
            ((ViewHolder) holder).ivOverdue.setVisibility(View.GONE);
            applyConstraintSet.clone(((ViewHolder) holder).clLessonCard);
            applyConstraintSet.setMargin(R.id.tv_day,ConstraintSet.RIGHT, (int) ScreenUtil.dpToPx(mContext,0));
            applyConstraintSet.setMargin(R.id.tv_club,ConstraintSet.RIGHT, (int) ScreenUtil.dpToPx(mContext,0));
            applyConstraintSet.applyTo(((ViewHolder) holder).clLessonCard);
        } else {
            ((ViewHolder) holder).tvMyLessonTitle.setText(lessonModel.getData().get(position).getCurriculumType());
            ((ViewHolder) holder).tvMyLessonRemainingDay.setText(lessonModel.getData().get(position).getTime());
            ((ViewHolder) holder).tvMyLessonRemainingClub.setText(lessonModel.getData().get(position).getSurplusNum());
            ((ViewHolder) holder).tvMyLessonPrice.setText(lessonModel.getData().get(position).getTotalPrice());
            ((ViewHolder) holder).ivOverdue.setVisibility(View.VISIBLE);
            applyConstraintSet.clone(((ViewHolder) holder).clLessonCard);
            applyConstraintSet.setMargin(R.id.tv_day,ConstraintSet.RIGHT, (int) ScreenUtil.dpToPx(mContext,30));
            applyConstraintSet.setMargin(R.id.tv_club,ConstraintSet.RIGHT, (int) ScreenUtil.dpToPx(mContext,30));
            applyConstraintSet.applyTo(((ViewHolder) holder).clLessonCard);

        }

    }


    public void setItemOnClickListener(OnRecyclerViewClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return lessonModel.getData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener listener;

        @BindView(R.id.iv_overdue)
        ImageView ivOverdue;
        @BindView(R.id.cl_lesson_card)
        ConstraintLayout clLessonCard;
        @BindView(R.id.tv_my_lesson_title)
        TextView tvMyLessonTitle;
        @BindView(R.id.tv_my_lesson_price)
        TextView tvMyLessonPrice;
        @BindView(R.id.tv_my_lesson_remaining_day)
        TextView tvMyLessonRemainingDay;
        @BindView(R.id.tv_my_lesson_remaining_club)
        TextView tvMyLessonRemainingClub;
        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.tv_club)
        TextView tvClub;

        ViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }
}
