package com.yeapao.andorid.homepage.myself.tab.coach;

import android.content.Context;
import android.net.sip.SipAudioCall;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.model.RollCallListModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/31.
 */

public class CoachlessonItemMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CoachLessonCallMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;


    private static final int BOTTOM_TYPE = 1;
    private static final int DEFINE_TYPE = 0;

    private List<RollCallListModel.DataBean> callListModel;
    private boolean leave = false;

    private OnRecyclerViewClickListener mListener;
    private LessonStudentsCheckedListener studentsCheckedListener;
    private LeaveEarlyListener leaveEarlyListener;

    public CoachlessonItemMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public CoachlessonItemMessageAdapter(Context context, List<RollCallListModel.DataBean> model) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        callListModel = model;
    }
    public CoachlessonItemMessageAdapter(Context context, List<RollCallListModel.DataBean> model,boolean leave) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        callListModel = model;
        this.leave = leave;
    }

    public void refreshItem(int position) {
        notifyItemChanged(position);
    }

    public List<RollCallListModel.DataBean> getEndData() {
        return callListModel;
    }

    public String getIds() {
        String ids = "";
        for (int i = 0; i < callListModel.size(); i++) {
            if (callListModel.get(i).isStatus()) {
                if (ids.equals("")) {
                    ids = String.valueOf(callListModel.get(i).getReservationDetailsId());
                } else {
                    ids+= ","+String.valueOf(callListModel.get(i).getReservationDetailsId());
                }
            }
        }

        for (int i = 0; i < callListModel.size(); i++) {
            if (callListModel.get(i).isStatus()) {
                callListModel.remove(i);
                i--;
            }
        }
        notifyDataSetChanged();
        return ids;
    }

    public List<RollCallListModel.DataBean> getlistData() {
        List<RollCallListModel.DataBean> model = new ArrayList<>();
        for (int i = 0; i < callListModel.size(); i++) {
            if (callListModel.get(i).isStatus()) {
                model.add(callListModel.get(i));
            }
        }
        return model;
    }

    public void chooseAllStatus(boolean status) {
        if (status) {
            for (int i = 0; i < callListModel.size(); i++) {
                callListModel.get(i).setStatus(status);
            }
        } else {
            for (int i = 0; i < callListModel.size(); i++) {
                callListModel.get(i).setStatus(false);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_coach_student, parent, false), mListener,studentsCheckedListener,leaveEarlyListener);
    }


    public void setStudentsCheckedListener(LessonStudentsCheckedListener listener) {
        if (listener != null) {
            studentsCheckedListener = listener;
        }
    }

    public void setLeaveEarlyListener(LeaveEarlyListener leaveEarlyListener) {
        if (leaveEarlyListener != null) {
            this.leaveEarlyListener = leaveEarlyListener;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).tvStudentName.setText(callListModel.get(position).getName());
        ((ViewHolder)holder).tvStudentName.setText(callListModel.get(position).getName());
        GlideUtil glideUtil = new GlideUtil();
        glideUtil.glideLoadingImage(mContext, ConstantYeaPao.HOST+callListModel.get(position).getHead(),
                R.drawable.y_you,((ViewHolder) holder).ivStudentHead);

        if (leave) {
            ((ViewHolder) holder).ivChooseStatus.setVisibility(View.GONE);
            ((ViewHolder) holder).ivCancel.setVisibility(View.GONE);

        } else {
            ((ViewHolder) holder).ivChooseStatus.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).ivCancel.setVisibility(View.GONE);

            if (callListModel.get(position).isStatus()) {
                ((ViewHolder) holder).ivChooseStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.coach_choose_s));
            } else {
                ((ViewHolder) holder).ivChooseStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.coach_choose_n));
            }
        }




        int count = callListModel.get(position).getPainDegreeList().size();
        List<RollCallListModel.DataBean.PainDegreeListBean> painDegreeListBeanList = new ArrayList<>();
        painDegreeListBeanList = callListModel.get(position).getPainDegreeList();
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
                ((ViewHolder) holder).tvPartOne.setText(callListModel.get(position).getPainDegreeList().get(0).getPosition());
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
        return callListModel.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener listener;
        private LessonStudentsCheckedListener studentsCheckedListener;
        private LeaveEarlyListener leaveEarlyListener;

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
        @BindView(R.id.iv_cancel)
        ImageView ivCancel;

        ViewHolder(View view, OnRecyclerViewClickListener listener, LessonStudentsCheckedListener lessonStudentsCheckedListener,
                   final LeaveEarlyListener leaveEarlyListener) {
            super(view);
            this.listener = listener;
            this.studentsCheckedListener = lessonStudentsCheckedListener;
            this.leaveEarlyListener = leaveEarlyListener;
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            ivCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastManager.showToast(mContext,"lllll");
                    if (leaveEarlyListener == null) {

                    } else {
                        leaveEarlyListener.leaveListener(String.valueOf(callListModel.get(getLayoutPosition()).getReservationDetailsId()));
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {

                if (callListModel.get(getLayoutPosition()).isStatus()) {
                    callListModel.get(getLayoutPosition()).setStatus(false);
                } else {
                    callListModel.get(getLayoutPosition()).setStatus(true);
                }
//                TODO 这里每次去遍历不好 可以将数量放在数据模型里面  这里后面优化
                int count = 0;
                for (int i = 0; i < callListModel.size(); i++) {
                    if (callListModel.get(i).isStatus()) {
                        count++;
                    }
                }
                if (studentsCheckedListener == null) {

                } else {
                    studentsCheckedListener.getCheckedStudentNum(count);
                }


                notifyDataSetChanged();
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }



}
