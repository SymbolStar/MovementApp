package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/28.
 */

public class LessonReservationMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "LessonReservationMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    private OnRecyclerViewClickListener mListener;


    public LessonReservationMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_lesson_reservation_list, parent, false), mListener);

    }

    public void setOnItemClickListener(OnRecyclerViewClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

//        TODO 我是教练  课程预约的状态判断 在UI上做相应的操作
        int status = 1;//课程状态的显示标识位
        switch (status) {
            case 1:
                ((ViewHolder)holder).tvCoachStatus.setText("进行中");
                ((ViewHolder) holder).tvCoachStatus.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.coach_yellow_shape));
                break;
            case 2:
                ((ViewHolder)holder).tvCoachStatus.setText("为开始");
                ((ViewHolder) holder).tvCoachStatus.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.coach_yellow_shape));
                break;
            case 3:
                ((ViewHolder)holder).tvCoachStatus.setText("已结束");
                ((ViewHolder) holder).tvCoachStatus.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.coach_grey_shape));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        private OnRecyclerViewClickListener listener;

        @BindView(R.id.tv_lesson_reservation_title)
        TextView tvLessonReservationTitle;
        @BindView(R.id.tv_lr_time)
        TextView tvLrTime;
        @BindView(R.id.tv_coach)
        TextView tvCoach;
        @BindView(R.id.tv_lr_reservation)
        TextView tvLrReservation;
        @BindView(R.id.tv_lr_location)
        TextView tvLrLocation;
        @BindView(R.id.tv_coach_status)
        TextView tvCoachStatus;

        ViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            this.listener = listener;
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v,getLayoutPosition());
            }
        }
    }
}
