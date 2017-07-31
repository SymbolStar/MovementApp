package com.yeapao.andorid.homepage.myself.tab.coach;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.yeapao.andorid.R;

import org.w3c.dom.ls.LSException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/31.
 */

public class CoachLessonCallMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CoachLessonCallMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;


    private static final int BOTTOM_TYPE = 1;
    private static final int DEFINE_TYPE = 0;

    private OnRecyclerViewClickListener mListener;

    public CoachLessonCallMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void refreshItem(int position) {
        notifyItemChanged(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_coach_student, parent, false), mListener);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void setOnItemClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener listener;


        @BindView(R.id.iv_student_head)
        CircleImageView ivStudentHead;
        @BindView(R.id.tv_student_name)
        TextView tvStudentName;
        @BindView(R.id.tv_part_one)
        TextView tvPartOne;
        @BindView(R.id.tv_part_three)
        TextView tvPartThree;
        @BindView(R.id.tv_part_two)
        TextView tvPartTwo;
        @BindView(R.id.iv_part_one)
        ImageView ivPartOne;
        @BindView(R.id.iv_part_two)
        ImageView ivPartTwo;
        @BindView(R.id.iv_part_three)
        ImageView ivPartThree;
        @BindView(R.id.iv_choose_status)
        ImageView ivChooseStatus;

        ViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            this.listener = listener;
            ButterKnife.bind(this, view);
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
