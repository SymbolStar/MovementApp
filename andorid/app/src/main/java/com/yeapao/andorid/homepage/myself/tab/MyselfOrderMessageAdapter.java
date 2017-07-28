package com.yeapao.andorid.homepage.myself.tab;

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
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/24.
 */

public class MyselfOrderMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "MyselfOrderMessageAdapter";
    private Context mContext;
    private LayoutInflater inflater;
    private OnRecyclerViewClickListener mListener;

    public MyselfOrderMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_myself_order_card, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    public void setOnItemClick(OnRecyclerViewClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener listener;

        @BindView(R.id.tv_my_order_title)
        TextView tvMyOrderTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_n_pay)
        TextView tvNPay;
        @BindView(R.id.tv_pay)
        TextView tvPay;
        @BindView(R.id.tv_delete_order)
        TextView tvDeleteOrder;

        ViewHolder(View view, OnRecyclerViewClickListener listener) {

            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            view.setOnClickListener(this);

        }


        @OnClick({R.id.tv_pay, R.id.tv_delete_order})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.tv_pay:

                    break;
                case R.id.tv_delete_order:
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }
}
