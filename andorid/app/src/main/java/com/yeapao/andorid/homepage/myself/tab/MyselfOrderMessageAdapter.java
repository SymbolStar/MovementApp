package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.model.MyOrderDataModel;

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
    private MyOrderDataModel myOrderDataModel;
    private GotoPay mGotopay;
    private DeleteOrder mDeleteOrder;

    public MyselfOrderMessageAdapter(Context context, MyOrderDataModel myOrderDataModel) {
        this.myOrderDataModel = myOrderDataModel;
        mContext = context;
        inflater = LayoutInflater.from(context);

    }


    public interface GotoPay {
        void gotoOrder(int position);
    }

    public interface DeleteOrder {
        void deleteOrder(int position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_myself_order_card, parent, false),mListener,mGotopay,mDeleteOrder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).tvMyOrderTitle.setText(myOrderDataModel.getData().get(position).getCurriculumName());
        ((ViewHolder)holder).tvTime.setText("有效期至 "+myOrderDataModel.getData().get(position).getEndTime());
        GlideUtil glideUtil = new GlideUtil();
        glideUtil.glideLoadingImage(mContext, ConstantYeaPao.HOST+myOrderDataModel.getData().get(position).getImage(),
                R.drawable.y_you,((ViewHolder)holder).ivContent);
        ((ViewHolder) holder).tvNPay.setText(myOrderDataModel.getData().get(position).getStatus());
        if (myOrderDataModel.getData().get(position).getStatus().equals("待支付")) {
            ((ViewHolder) holder).tvPay.setVisibility(View.VISIBLE);
        } else {
            ((ViewHolder) holder).tvPay.setVisibility(View.GONE);
        }
    }


    public void setGotoPayListener(GotoPay gotoPayListener) {
        mGotopay = gotoPayListener;
    }

    public void setDeleteOrderListener(DeleteOrder deleteOrderListener) {
        mDeleteOrder = deleteOrderListener;
    }

    public void setOnItemClick(OnRecyclerViewClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return myOrderDataModel.getData().size();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener listener;
         private GotoPay gotoPayListener;
         private DeleteOrder mDeleteOrder;

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
        @BindView(R.id.iv_content)
        ImageView ivContent;

         ViewHolder(View view, OnRecyclerViewClickListener listener, GotoPay gotoPay,DeleteOrder deleteOrder) {
             super(view);
             ButterKnife.bind(this, view);
             this.listener = listener;
             gotoPayListener = gotoPay;
             mDeleteOrder = deleteOrder;
             view.setOnClickListener(this);

         }


        @OnClick({R.id.tv_pay, R.id.tv_delete_order})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.tv_pay:
                    gotoPayListener.gotoOrder(getLayoutPosition());
                    break;
                case R.id.tv_delete_order:
                    mDeleteOrder.deleteOrder(getLayoutPosition());
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
