package com.yeapao.andorid.homepage.myself.orders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/9/18.
 */

public class MyselfCangOrderMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "MyselfCangOrderMessageAdapter";

    private Context mContext;
    private LayoutInflater mInflater;

    private OnRecyclerViewClickListener mListener;


    public void setOnItemClickListener(OnRecyclerViewClickListener listener) {
        mListener = listener;
    }


    public MyselfCangOrderMessageAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_myself_cang_order_card, parent, false), mListener);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener mListener;


        @BindView(R.id.iv_content)
        ImageView ivContent;
        @BindView(R.id.tv_my_order_title)
        TextView tvMyOrderTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_n_pay)
        TextView tvNPay;
        @BindView(R.id.tv_delete_order)
        TextView tvDeleteOrder;
        @BindView(R.id.tv_sum)
        TextView tvSum;

        ViewHolder(View view,OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            mListener = listener;
        }

        @Override
        public void onClick(View v) {
            mListener.OnItemClick(v,getLayoutPosition());
        }
    }
}
