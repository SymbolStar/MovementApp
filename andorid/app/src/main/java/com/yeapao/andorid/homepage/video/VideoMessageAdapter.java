package com.yeapao.andorid.homepage.video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.VideoDataModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by fujindong on 2017/8/12.
 */

public class VideoMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "VideoMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;
    private VideoDataModel videoModels;
    private GlideUtil glideUtil = new GlideUtil();

    public VideoMessageAdapter(Context context,VideoDataModel videoDataModel) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        videoModels = videoDataModel;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder)holder).jpsVideoPlay.setUp(videoModels.getData().get(position).getUrl()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, videoModels.getData().get(position).getTitle());
        ((ViewHolder) holder).jpsVideoPlay.thumbImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pic01));
        glideUtil.glideLoadingImage(mContext,videoModels.getData().get(position).getImageUrl(),
                R.drawable.pic01,((ViewHolder) holder).jpsVideoPlay.thumbImageView);
            ((ViewHolder) holder).jpsVideoPlay.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);

    }

    @Override
    public int getItemCount() {
        return videoModels.getData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
//        @BindView(R.id.iv_video_play)
//        ImageView ivVideoPlay;
//        @BindView(R.id.tv_video_title)
//        TextView tvVideoTitle;
        @BindView(R.id.jps_video_play)
        JCVideoPlayerStandard jpsVideoPlay;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
