package com.yeapao.andorid.homepage.video;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.nfc.cardemulation.HostNfcFService;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.VideoTypeModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/8/21.
 */

public class VideoTabMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "VideoTabMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    private OnRecyclerViewClickListener mListener;

    private VideoTypeModel typeModel;

    private String currentType;



    public VideoTabMessageAdapter(Context context, VideoTypeModel model, String type) {
        currentType = type;
        typeModel = model;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemCLickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    public void refreshTab(String currentType) {
        this.currentType = currentType;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_video_tab, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (typeModel.getData().get(position).getType_name().equals(currentType)) {
            ((ViewHolder)holder).tvVideoType.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
        }

        ((ViewHolder)holder).tvVideoType.setText(typeModel.getData().get(position).getType_name());
    }

    @Override
    public int getItemCount() {
        return typeModel.getData().size();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tv_video_type)
        TextView tvVideoType;
        private OnRecyclerViewClickListener listener;

        ViewHolder(View view,OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v,getLayoutPosition());
            }

        }
    }
}
