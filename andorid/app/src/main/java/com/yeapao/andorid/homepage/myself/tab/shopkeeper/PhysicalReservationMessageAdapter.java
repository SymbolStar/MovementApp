package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.support.v7.widget.CardView;
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

public class PhysicalReservationMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final String TAG = "PhysicalReservationMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    private OnRecyclerViewClickListener mListener;


    public PhysicalReservationMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_physical_card, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void setOnItemClickListener(OnRecyclerViewClickListener listener) {
        mListener = listener;
    }


    @Override
    public int getItemCount() {
        return 10;
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
                PhysicalTestActivity.start(mContext);
            }
        }
    }
}
