package com.yeapao.andorid.homepage.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.model.CircleMessageModel;
import com.yeapao.andorid.model.PunchTheClockModel;
import com.yeapao.andorid.util.CircleDateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/8/20.
 */

public class CircleMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CircleMessageAdapter";

    private Context mContext;
    private LayoutInflater mInflater;
    private gotoCardListener mListener;

    private CircleMessageModel punchTheClockModel;

    interface gotoCardListener {
        void gotoCard(int position);
    }

    public void setCardClickListener(gotoCardListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    public CircleMessageAdapter(Context context, CircleMessageModel model) {
        punchTheClockModel = model;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_circle_message, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        TODO tvTime
        String content = "";
        ((ViewHolder)holder).tvCircleMessageTime.setText(CircleDateUtils.getCircleMessageDate(punchTheClockModel.getData().get(position).getDate()));
        if (punchTheClockModel.getData().get(position).getCommunity().equals("1")) {
            content = punchTheClockModel.getData().get(position).getName() + "回复了您的帖子，点击查看详情";
        } else {
            content = punchTheClockModel.getData().get(position).getName() + "回复了您：" + punchTheClockModel.getData().get(position).getMessageContent();
        }

        ((ViewHolder)holder).tvCircleTitle.setText(content);
    }

    @Override
    public int getItemCount() {
        return punchTheClockModel.getData().size();
    }

     class ViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_video_message_time)
        TextView tvCircleMessageTime;
        @BindView(R.id.tv_video_title)
        TextView tvCircleTitle;
        @BindView(R.id.rl_video_tab)
        RelativeLayout rlCircleTab;

         private gotoCardListener listener;

        ViewHolder(View view, final gotoCardListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            rlCircleTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.gotoCard(getLayoutPosition());
                }
            });
        }
    }
}
