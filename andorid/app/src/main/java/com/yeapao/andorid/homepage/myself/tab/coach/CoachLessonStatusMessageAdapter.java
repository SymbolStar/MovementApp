package com.yeapao.andorid.homepage.myself.tab.coach;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.ClassBeginsModel;
import com.yeapao.andorid.model.RollCallListModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/31.
 */

public class CoachLessonStatusMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final String TAG = "CoachLessonStatusMessageAdapter";


    private static Context mContext;
    private LayoutInflater inflater;

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    private ClassBeginsModel classBeginsModel;

    private LeaveEarlyListener leaveEarlyListener;
    private RollCallListener rollCallListener;


    public void setLeaveEarlyListener(LeaveEarlyListener listener) {
        if (listener != null) {
            leaveEarlyListener = listener;
        }
    }

    public void setRollCallListener(RollCallListener listener) {
        if (listener != null) {
            rollCallListener = listener;
        }
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public CoachLessonStatusMessageAdapter(Context context,ClassBeginsModel classBeginsModel) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.classBeginsModel = classBeginsModel;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ONE:
                return new OneViewHolder(inflater.inflate(R.layout.item_coach_student_one, parent, false),leaveEarlyListener);
            case TWO:
                return new TwoViewHolder(inflater.inflate(R.layout.item_coach_student_two, parent, false),rollCallListener);
            case THREE:
                return new ThreeViewHolder(inflater.inflate(R.layout.item_coach_student_three, parent, false),rollCallListener);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return ONE;
        } else if (position == 1) {
            return TWO;
        } else if (position == 2) {
            return THREE;
        }
        return 4;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof OneViewHolder) {

            ((OneViewHolder) holder).rvCoachStatusOneList.getAdapter().notifyDataSetChanged();

            ((OneViewHolder)holder).tvCoachStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int status = ((OneViewHolder) holder).llCoachOneStatus.getVisibility();
                    if (status == View.VISIBLE) {
                        ((OneViewHolder) holder).llCoachOneStatus.setVisibility(View.GONE);
                    } else {
                        ((OneViewHolder) holder).llCoachOneStatus.setVisibility(View.VISIBLE);
                    }
                }
            });

        }

        if (holder instanceof TwoViewHolder) {

            ((TwoViewHolder) holder).rvCoachStatusTwoList.getAdapter().notifyDataSetChanged();

            ((TwoViewHolder)holder).tvCoachStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int status = ((TwoViewHolder) holder).llCoachTwoStatus.getVisibility();
                    if (status == View.VISIBLE) {
                        ((TwoViewHolder) holder).llCoachTwoStatus.setVisibility(View.GONE);
                    } else {
                        ((TwoViewHolder) holder).llCoachTwoStatus.setVisibility(View.VISIBLE);

                    }
                }
            });
        }
        if (holder instanceof ThreeViewHolder) {

            ((ThreeViewHolder) holder).rvCoachStatusThreeList.getAdapter().notifyDataSetChanged();

            ((ThreeViewHolder)holder).tvCoachStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int status = ((ThreeViewHolder) holder).llCoachThreeStatus.getVisibility();
                    if (status == View.VISIBLE) {
                        ((ThreeViewHolder) holder).llCoachThreeStatus.setVisibility(View.GONE);
                    } else {
                        ((ThreeViewHolder) holder).llCoachThreeStatus.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    class OneViewHolder extends RecyclerView.ViewHolder {

        private CoachlessonItemMessageAdapter messageAdapter;
        private LeaveEarlyListener listener;

        @BindView(R.id.tv_coach_status)
        TextView tvCoachStatus;
        @BindView(R.id.tv_choose_all)
        TextView tvChooseAll;
        @BindView(R.id.cb_choose_all)
        CheckBox cbChooseAll;
        @BindView(R.id.tv_student_back)
        TextView tvStudentBack;
        @BindView(R.id.rl_bottom)
        RelativeLayout rlBottom;
        @BindView(R.id.rv_coach_status_one_list)
        RecyclerView rvCoachStatusOneList;
        @BindView(R.id.ll_coach_one_status)
        LinearLayout llCoachOneStatus;
        @BindView(R.id.tv_choose_num)
        TextView tvChooseNum;


        OneViewHolder(View view, LeaveEarlyListener listener) {
            super(view);
            this.listener = listener;
            ButterKnife.bind(this, view);
            initView();
        }

        private void initView() {
            rvCoachStatusOneList.setLayoutManager(new GridLayoutManager(mContext, 3));
            if (messageAdapter == null) {
                messageAdapter = new CoachlessonItemMessageAdapter(mContext,classBeginsModel.getData().getIsNormalList());
                rvCoachStatusOneList.setAdapter(messageAdapter);
                messageAdapter.setStudentsCheckedListener(new LessonStudentsCheckedListener() {
                    @Override
                    public void getCheckedStudentNum(int count) {
                        tvChooseNum.setText("选中"+String.valueOf(count)+"人");
                    }
                });
                messageAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
                    @Override
                    public void OnItemClick(View v, int position) {
                        ToastManager.showToast(mContext, "one Click");
                    }
                });
            }

            cbChooseAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cbChooseAll.isChecked()) {
                        messageAdapter.chooseAllStatus(true);
                    } else {
                        messageAdapter.chooseAllStatus(false);
                    }
                }
            });

            tvStudentBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvChooseNum.setText("选中"+String.valueOf(0)+"人");

                    List<RollCallListModel.DataBean> model = messageAdapter.getlistData();
                    String ids = "";
                    for (int i = 0; i < model.size(); i++) {
                        if (model.get(i).isStatus()) {
                            if (ids.equals("")) {
                                ids = String.valueOf(model.get(i).getReservationDetailsId());
                            } else {
                                ids+= ","+String.valueOf(model.get(i).getReservationDetailsId());
                            }
                            model.get(i).setStatus(false);
                        }
                    }
                    listener.leaveListener(ids);
                    classBeginsModel.getData().getIsNormalList().removeAll(model);
                    classBeginsModel.getData().getIsLeaveEarlyList().addAll(model);

                    notifyDataSetChanged();


                }
            });

        }
    }

     class TwoViewHolder extends RecyclerView.ViewHolder {

        private CoachlessonItemMessageAdapter messageAdapter;
         private RollCallListener rollCallListener;

        @BindView(R.id.tv_coach_status)
        TextView tvCoachStatus;
        @BindView(R.id.tv_choose_all)
        TextView tvChooseAll;
        @BindView(R.id.cb_choose_all)
        CheckBox cbChooseAll;
        @BindView(R.id.rl_top)
        RelativeLayout rlTop;
        @BindView(R.id.tv_student_get)
        TextView tvStudentGet;
        @BindView(R.id.rl_bottom)
        RelativeLayout rlBottom;
        @BindView(R.id.rv_coach_status_two_list)
        RecyclerView rvCoachStatusTwoList;
        @BindView(R.id.ll_coach_two_status)
        LinearLayout llCoachTwoStatus;
         @BindView(R.id.tv_choose_num)
         TextView tvChooseNum;

        TwoViewHolder(View view,RollCallListener listener) {
            super(view);
            rollCallListener = listener;
            ButterKnife.bind(this, view);
            initView();
        }

        private void initView() {
            rvCoachStatusTwoList.setLayoutManager(new GridLayoutManager(mContext, 3));
            if (messageAdapter == null) {
                messageAdapter = new CoachlessonItemMessageAdapter(mContext,classBeginsModel.getData().getIsLateList());
                rvCoachStatusTwoList.setAdapter(messageAdapter);
                messageAdapter.setStudentsCheckedListener(new LessonStudentsCheckedListener() {
                    @Override
                    public void getCheckedStudentNum(int count) {
                        tvChooseNum.setText("选中"+String.valueOf(count)+"人");
                    }
                });
                messageAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
                    @Override
                    public void OnItemClick(View v, int position) {
                        ToastManager.showToast(mContext, "one Click");
                    }
                });

            }

            cbChooseAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cbChooseAll.isChecked()) {
                        messageAdapter.chooseAllStatus(true);
                    } else {
                        messageAdapter.chooseAllStatus(false);
                    }
                }
            });

            tvStudentGet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvChooseNum.setText("选中"+String.valueOf(0)+"人");

                    List<RollCallListModel.DataBean> model = messageAdapter.getlistData();

                    String ids = "";
                    for (int i = 0; i < model.size(); i++) {
                        if (model.get(i).isStatus()) {
                            if (ids.equals("")) {
                                ids = String.valueOf(model.get(i).getReservationDetailsId());
                            } else {
                                ids+= ","+String.valueOf(model.get(i).getReservationDetailsId());
                            }
                            model.get(i).setStatus(false);
                        }
                    }
                    rollCallListener.rollCall(ids);
                    ToastManager.showToast(mContext,ids);
                    classBeginsModel.getData().getIsLateList().removeAll(model);
                    classBeginsModel.getData().getIsNormalList().addAll(model);

                    notifyDataSetChanged();

                }
            });



        }

    }

     class ThreeViewHolder extends RecyclerView.ViewHolder {
        private CoachlessonItemMessageAdapter messageAdapter;
         private RollCallListener rollCallListener;
        @BindView(R.id.tv_coach_status)
        TextView tvCoachStatus;
        @BindView(R.id.tv_choose_all)
        TextView tvChooseAll;
        @BindView(R.id.cb_choose_all)
        CheckBox cbChooseAll;
        @BindView(R.id.rl_top)
        RelativeLayout rlTop;
        @BindView(R.id.rv_coach_status_three_list)
        RecyclerView rvCoachStatusThreeList;
        @BindView(R.id.ll_coach_three_status)
        LinearLayout llCoachThreeStatus;

         ThreeViewHolder(View view, RollCallListener listener) {
             super(view);
             rollCallListener = listener;
             ButterKnife.bind(this, view);
             initView();
         }

        private void initView() {
            rvCoachStatusThreeList.setLayoutManager(new GridLayoutManager(mContext, 3));
            if (messageAdapter == null) {
                messageAdapter = new CoachlessonItemMessageAdapter(mContext,classBeginsModel.getData().getIsLeaveEarlyList(),true);
                rvCoachStatusThreeList.setAdapter(messageAdapter);
                messageAdapter.setLeaveEarlyListener(new LeaveEarlyListener() {
                    @Override
                    public void leaveListener(String id) {
//                        TODO qindao
                        rollCallListener.rollCall(id);
                    }
                });
                messageAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
                    @Override
                    public void OnItemClick(View v, int position) {
                        ToastManager.showToast(mContext, "one Click");
                    }
                });
            }
            cbChooseAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cbChooseAll.isChecked()) {
                        messageAdapter.chooseAllStatus(true);
                    } else {
                        messageAdapter.chooseAllStatus(false);
                    }
                }
            });
        }
    }
}
