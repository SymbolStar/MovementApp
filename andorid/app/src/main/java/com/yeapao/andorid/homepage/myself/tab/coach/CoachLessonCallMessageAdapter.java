package com.yeapao.andorid.homepage.myself.tab.coach;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.model.RollCallListModel;

import org.w3c.dom.Text;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

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

    private RollCallListModel callListModel;


    private OnRecyclerViewClickListener mListener;

    public CoachLessonCallMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public CoachLessonCallMessageAdapter(Context context, RollCallListModel model) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        callListModel = model;
    }

    public void refreshItem(int position) {
        notifyItemChanged(position);
    }

    public RollCallListModel getEndData() {
        return callListModel;
    }

    public void chooseAllStatus(boolean status) {
        if (status) {
            for (int i = 0; i < callListModel.getData().size(); i++) {
                callListModel.getData().get(i).setStatus(status);
            }
        } else {
            for (int i = 0; i < callListModel.getData().size(); i++) {
                callListModel.getData().get(i).setStatus(false);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_coach_student, parent, false), mListener);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).tvStudentName.setText(callListModel.getData().get(position).getName());
        ((ViewHolder)holder).tvStudentName.setText(callListModel.getData().get(position).getName());
        GlideUtil glideUtil = new GlideUtil();
        glideUtil.glideLoadingImage(mContext, ConstantYeaPao.HOST+callListModel.getData().get(position).getHead(),
                R.drawable.y_you,((ViewHolder) holder).ivStudentHead);

        if (callListModel.getData().get(position).isStatus()) {
            ((ViewHolder) holder).ivChooseStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.coach_choose_s));
        } else {
            ((ViewHolder) holder).ivChooseStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.coach_choose_n));
        }


        int count = callListModel.getData().get(position).getPainDegreeList().size();
        List<RollCallListModel.DataBean.PainDegreeListBean> painDegreeListBeanList = new ArrayList<>();
        painDegreeListBeanList = callListModel.getData().get(position).getPainDegreeList();
        switch (count) {
            case 0:
                ((ViewHolder) holder).tvPartTwo.setVisibility(View.GONE);
                ((ViewHolder) holder).ivPartTwo.setVisibility(View.GONE);
                ((ViewHolder) holder).ivPartThree.setVisibility(View.GONE);
                ((ViewHolder) holder).tvPartThree.setVisibility(View.GONE);
                ((ViewHolder) holder).tvPartOne.setVisibility(View.GONE);
                ((ViewHolder) holder).ivPartOne.setVisibility(View.GONE);
                ((ViewHolder) holder).tvNoDegree.setVisibility(View.VISIBLE);

                break;
            case 1:
                ((ViewHolder) holder).tvNoDegree.setVisibility(View.GONE);
                ((ViewHolder) holder).tvPartTwo.setVisibility(View.GONE);
                ((ViewHolder) holder).ivPartTwo.setVisibility(View.GONE);
                ((ViewHolder) holder).ivPartThree.setVisibility(View.GONE);
                ((ViewHolder) holder).tvPartThree.setVisibility(View.GONE);
                ((ViewHolder) holder).ivPartOne.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).tvPartOne.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).tvPartOne.setText(callListModel.getData().get(position).getPainDegreeList().get(0).getPosition());
                int level = painDegreeListBeanList.get(0).getDegree();
                setLevel(((ViewHolder) holder).ivPartOne, level);
                break;

//                if (level <= 3) {
//                    ((ViewHolder) holder).ivPartOne.setImageDrawable(mContext.getResources().getDrawable(R.drawable.couch_pain_one));
//                } else if (level <= 6) {
//                    ((ViewHolder) holder).ivPartOne.setImageDrawable(mContext.getResources().getDrawable(R.drawable.couch_pain_two));
//                } else {
//                    ((ViewHolder) holder).ivPartOne.setImageDrawable(mContext.getResources().getDrawable(R.drawable.couch_pain_three));
//                }

            case 2:
                ((ViewHolder) holder).tvNoDegree.setVisibility(View.GONE);
                ((ViewHolder) holder).ivPartThree.setVisibility(View.GONE);
                ((ViewHolder) holder).tvPartThree.setVisibility(View.GONE);
                ((ViewHolder) holder).ivPartOne.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).tvPartOne.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).ivPartTwo.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).tvPartTwo.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).tvPartOne.setText(painDegreeListBeanList.get(0).getPosition());
                setLevel(((ViewHolder) holder).ivPartOne,painDegreeListBeanList.get(0).getDegree());
                ((ViewHolder) holder).tvPartTwo.setText(painDegreeListBeanList.get(1).getPosition());
                setLevel(((ViewHolder) holder).ivPartTwo,painDegreeListBeanList.get(1).getDegree());
                break;

            case 3:
                ((ViewHolder) holder).tvNoDegree.setVisibility(View.GONE);
                ((ViewHolder) holder).ivPartOne.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).tvPartOne.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).ivPartTwo.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).tvPartTwo.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).ivPartThree.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).tvPartThree.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).tvPartOne.setText(painDegreeListBeanList.get(0).getPosition());
                setLevel(((ViewHolder) holder).ivPartOne,painDegreeListBeanList.get(0).getDegree());
                ((ViewHolder) holder).tvPartTwo.setText(painDegreeListBeanList.get(1).getPosition());
                setLevel(((ViewHolder) holder).ivPartTwo,painDegreeListBeanList.get(1).getDegree());
                ((ViewHolder) holder).tvPartThree.setText(painDegreeListBeanList.get(2).getPosition());
                setLevel(((ViewHolder) holder).ivPartThree,painDegreeListBeanList.get(2).getDegree());
                break;
        }



    }

    private void setLevel(ImageView imageView, int level) {
        if (level <= 3) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.couch_pain_one));
        } else if (level <= 6) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.couch_pain_two));
        } else {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.couch_pain_three));
        }
    }

    public void setOnItemClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    @Override
    public int getItemCount() {
        return callListModel.getData().size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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
        @BindView(R.id.tv_no_degree)
        TextView tvNoDegree;

        ViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            this.listener = listener;
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {

                if (callListModel.getData().get(getLayoutPosition()).isStatus()) {
                    callListModel.getData().get(getLayoutPosition()).setStatus(false);
                } else {
                    callListModel.getData().get(getLayoutPosition()).setStatus(true);
                }
                notifyDataSetChanged();
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }
}
