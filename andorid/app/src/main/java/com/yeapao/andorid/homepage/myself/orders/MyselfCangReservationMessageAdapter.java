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
import com.yeapao.andorid.model.ActualOrderListModel;
import com.yeapao.andorid.model.CangReservationOrderListModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/9/18.
 */

public class MyselfCangReservationMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "MyselfCangOrderMessageAdapter";

    private Context mContext;
    private LayoutInflater mInflater;

    private OnRecyclerViewClickListener mListener;

    private CangOrderStatusListener cangOrderStatusListener;


    private CangReservationOrderListModel cangReservationOrderListModel = new CangReservationOrderListModel();

    public void setCangOrderStatusListener(CangOrderStatusListener listener) {
        cangOrderStatusListener = listener;
    }

    public void setOnItemClickListener(OnRecyclerViewClickListener listener) {
        mListener = listener;
    }


    public MyselfCangReservationMessageAdapter(Context context, CangReservationOrderListModel model) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        cangReservationOrderListModel = model;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_myself_cang_order_card, parent, false), mListener,cangOrderStatusListener);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder)holder).tvMyOrderTitle.setText("健身舱预约");
        if (cangReservationOrderListModel.getData().get(position).getStatus().equals("1")) {
            ((ViewHolder) holder).tvNPay.setText("未付款");
            ((ViewHolder) holder).tvNPay.setTextColor(mContext.getResources().getColor(R.color.yellow_text_color));
            ((ViewHolder) holder).tvDeleteOrder.setText("继续支付");
            ((ViewHolder) holder).tvDeleteOrder.setTextColor(mContext.getResources().getColor(R.color.yellow_text_color));
            ((ViewHolder) holder).tvDeleteOrder.setBackground(mContext.getResources().getDrawable(R.drawable.my_order_button_shape));
        } else {
            ((ViewHolder) holder).tvNPay.setText("已付款");
            ((ViewHolder) holder).tvNPay.setTextColor(mContext.getResources().getColor(R.color.text_color));
            ((ViewHolder) holder).tvDeleteOrder.setText("删除订单");
            ((ViewHolder) holder).tvDeleteOrder.setTextColor(mContext.getResources().getColor(R.color.text_hint_color));
            ((ViewHolder) holder).tvDeleteOrder.setBackground(mContext.getResources().getDrawable(R.drawable.my_order_button_n_shape));
        }
        ((ViewHolder) holder).tvTime.setText("预约时长  "+String.valueOf(cangReservationOrderListModel.getData().get(position).getDuration())+"分钟");

        ((ViewHolder) holder).tvSum.setText(mContext.getResources().getString(R.string.RMB)+cangReservationOrderListModel.getData()
        .get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return cangReservationOrderListModel.getData().size();
    }

    static class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener mListener;
        private CangOrderStatusListener cangListener;

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

        ViewHolder(View view,OnRecyclerViewClickListener listener,CangOrderStatusListener cangListener) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            mListener = listener;
            this.cangListener = cangListener;
            initView(view);
        }

        private void initView(View view) {
            tvDeleteOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cangListener.cangOrderDo(getLayoutPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            mListener.OnItemClick(v,getLayoutPosition());
        }
    }
}
