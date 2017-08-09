package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.BodySideListModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/28.
 */

public class PhysicalReservationMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final String TAG = "PhysicalReservationMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;
    private BodySideListModel bodySideListModel;


    private OnRecyclerViewClickListener mListener;




    public PhysicalReservationMessageAdapter(Context context, BodySideListModel bodySideListModel) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.bodySideListModel = bodySideListModel;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_physical_card, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).tvPhysicalTime.setText(bodySideListModel.getData().get(position).getStartTime());
        String name = "";
        for (int i = 0; i < bodySideListModel.getData().get(position).getBodySideUserOut().size(); i++) {
            name += bodySideListModel.getData().get(position).getBodySideUserOut().get(i).getUserName();
            if (bodySideListModel.getData().get(position).getBodySideUserOut().get(i).getIsMember().equals("1")) {
                name += " (会员)";
            } else {
                name += " (非会员)";
            }
            if (i == 0) {
                name += " /";
            }
        }

        ((ViewHolder) holder).tvAccountName.setText(name);


        LogUtil.e(TAG+"----step",bodySideListModel.getData().get(position).getStep());

        if (bodySideListModel.getData().get(position).getStep().equals("0")) {
            ((ViewHolder) holder).tvPhysicalProcessing.setText("未开始");
            ((ViewHolder) holder).tvPhysicalStatus.setText("开始体测");
            ((ViewHolder) holder).tvPhysicalStatus.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.physical_shape));

        } else if (bodySideListModel.getData().get(position).getStep().equals("4")) {
            ((ViewHolder) holder).tvPhysicalProcessing.setText("已结束");
            ((ViewHolder) holder).tvPhysicalStatus.setText("体测结束");
            ((ViewHolder) holder).tvPhysicalStatus.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.physical_shape_n));
        } else {
            ((ViewHolder) holder).tvPhysicalProcessing.setText("进行中");
            ((ViewHolder) holder).tvPhysicalStatus.setText("继续体测");
            ((ViewHolder) holder).tvPhysicalStatus.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.physical_shape));
        }


    }

    public void setOnItemClickListener(OnRecyclerViewClickListener listener) {
        mListener = listener;
    }


    @Override
    public int getItemCount() {
        return bodySideListModel.getData().size();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener listener;

        @BindView(R.id.tv_physical_processing)
        TextView tvPhysicalProcessing;
        @BindView(R.id.tv_physical_time)
        TextView tvPhysicalTime;
        @BindView(R.id.tv_account_name)
        TextView tvAccountName;
        @BindView(R.id.tv_physical_status)
        TextView tvPhysicalStatus;
        @BindView(R.id.cv_physical_card)
        CardView cvPhysicalCard;

        ViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v,getLayoutPosition());
                bodySideListModel.getData().get(getLayoutPosition()).setStatus(true);

                if (bodySideListModel.getData().get(getLayoutPosition()).getStep().equals("0")) {
                    PhysicalTestActivity.start(mContext, bodySideListModel, getLayoutPosition());
                } else if (bodySideListModel.getData().get(getLayoutPosition()).getStep().equals("1")) {
                    PhysicalTestSecondActivity.start(mContext, bodySideListModel, getLayoutPosition());
                } else if (bodySideListModel.getData().get(getLayoutPosition()).getStep().equals("2")) {
                    PhysicalTestThirdActivity.start(mContext, bodySideListModel, getLayoutPosition());
                } else {
                    PhysicalTestForthActivity.start(mContext,bodySideListModel,getLayoutPosition());
                }


            }
        }
    }
}
