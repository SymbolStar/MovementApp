package com.yeapao.andorid.homepage.circle;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by fujindong on 2017/7/16.
 */

public class CircleMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private OnRecyclerViewClickListener mListener;

    private static final int HEADER_TYPE = 0;
    private static final int GROUP_TYPE = 1;
    private static final int CIRCLE_TYPE = 2;


    public CircleMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER_TYPE:
                return new HeaderViewHolder(inflater.inflate(R.layout.item_circle_top, parent, false));
            case GROUP_TYPE:
                return new GroupChatListViewHolder(inflater.inflate(R.layout.item_circle_group_chat, parent, false));
            case CIRCLE_TYPE:
                return new CircleItemViewHolder(inflater.inflate(R.layout.item_circle_card, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        } else if (position == 1) {
            return GROUP_TYPE;
        } else if (position == 2) {
            return CIRCLE_TYPE;
        }


        return super.getItemViewType(position);
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vp_circle_image)
        ViewPager vpCircleImage;
        @BindView(R.id.ci_circle_indicator)
        CircleIndicator ciCircleIndicator;

        HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class GroupChatListViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.lv_group_chat_list)
        ListView lvGroupChatList;
        @BindView(R.id.tv_group_chat_more)
        TextView tvGroupChatMore;
        @BindView(R.id.iv_group_chat_more)
        ImageView ivGroupChatMore;

        GroupChatListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class CircleItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_nick_name)
        TextView tvNickName;
        @BindView(R.id.tv_publish_time)
        TextView tvPublishTime;
        @BindView(R.id.iv_circle_badge)
        ImageView ivCircleBadge;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.gv_images)
        GridView gvImages;
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.tv_finger)
        TextView tvFinger;

        CircleItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
