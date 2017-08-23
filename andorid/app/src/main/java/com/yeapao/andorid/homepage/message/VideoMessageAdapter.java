package com.yeapao.andorid.homepage.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.model.PunchTheClockModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/8/20.
 */

public class VideoMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "VideoMessageAdapter";

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

    public VideoMessageAdapter(Context context, PunchTheClockModel model) {
        punchTheClockModel = model;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_video_message, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        TODO tvTime
        ((ViewHolder)holder).tvVideoMessageTime.setText(punchTheClockModel.getData().get(position).getCreateTime());
        ((ViewHolder)holder).tvVideoTitle.setText(punchTheClockModel.getData().get(position).getNotificationContent());
    }

    @Override
    public int getItemCount() {
        return punchTheClockModel.getData().size();
    }

     class ViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_video_message_time)
        TextView tvVideoMessageTime;
        @BindView(R.id.tv_video_title)
        TextView tvVideoTitle;
        @BindView(R.id.rl_video_tab)
        RelativeLayout rlVideoTab;

         private gotoCardListener listener;

        ViewHolder(View view, final gotoCardListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            rlVideoTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.gotoCard();
                }
            });
        }
    }
}
