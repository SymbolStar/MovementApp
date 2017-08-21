package com.yeapao.andorid.homepage.message;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeapao.andorid.R;

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

    interface gotoCardListener {
        void gotoCard();
    }

    public void setCardClickListener(gotoCardListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    public CardMessageAdapter(Context context) {
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
    }

    @Override
    public int getItemCount() {
        return 10;
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cv_goto_card)
        CardView cvGotoCard;
         @BindView(R.id.tv_time)
         TextView tvTime;

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
