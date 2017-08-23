package com.yeapao.andorid.homepage.message;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.model.PunchTheClockModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/8/19.
 */

public class CardMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CardMessageAdapter";

    private Context mContext;
    private LayoutInflater mInflater;
    private gotoCardListener mListener;

    private PunchTheClockModel punchTheClockModel;

    interface gotoCardListener {
        void gotoCard();
    }

    public void setCardClickListener(gotoCardListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    public CardMessageAdapter(Context context, PunchTheClockModel model) {
        punchTheClockModel = model;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater.inflate(R.layout.item_card_list, parent, false);


        return new ViewHolder( mInflater.inflate(R.layout.item_card_list, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        TODO setting tvTime
        ((ViewHolder)holder).tvTime.setText(punchTheClockModel.getData().get(position).getCreateTime());
        ((ViewHolder) holder).tvClockCardContent.setText(punchTheClockModel.getData().get(position).getNotificationContent());

    }

    @Override
    public int getItemCount() {
        return punchTheClockModel.getData().size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cv_goto_card)
        CardView cvGotoCard;
         @BindView(R.id.tv_time)
         TextView tvTime;
         @BindView(R.id.tv_clock_card_content)
         TextView tvClockCardContent;

         private gotoCardListener listener;


        ViewHolder(View view, final gotoCardListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            cvGotoCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.gotoCard();
                }
            });
        }


    }
}
