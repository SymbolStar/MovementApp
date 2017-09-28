package com.yeapao.andorid.homepage.circle.circledetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.CommunityDetailModel;
import com.yeapao.andorid.util.AccountGradeUtils;
import com.yeapao.andorid.util.CircleDateUtils;
import com.yeapao.andorid.util.GlobalDataYepao;
import com.yeapao.andorid.util.SpannableTextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/9/25.
 */

public class CircleCommentMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CircleCommentMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;
    private GlideUtil glideUtil = new GlideUtil();

    private  boolean commentListStatus = false;  //注意不要用static 要做好创建时的初始化

    private List<CommunityDetailModel.DataBean.CommentsBean.CommunityCommentsOutsBean> communityCommentsOutsBeanList = new ArrayList<>();

    private static final int BOTTOM_TYPE = 0;
    private static final int GROUP_TYPE = 1;
    private CommentOnClickListener mCommentListener;

    public void setCommentOnClickListener(CommentOnClickListener listener) {
        mCommentListener = listener;
    }

    public CircleCommentMessageAdapter(Context context,
                                       List<CommunityDetailModel.DataBean.CommentsBean.CommunityCommentsOutsBean> communityCommentsOutsBeen) {
        commentListStatus = false;
        mContext = context;
        inflater = LayoutInflater.from(context);
        communityCommentsOutsBeanList = communityCommentsOutsBeen;
    }

    @Override
    public int getItemViewType(int position) {
        if (!commentListStatus) {
            if (position == 2) {
                return BOTTOM_TYPE;
            } else {
                return GROUP_TYPE;
            }
        } else {
            if (position == (getItemCount() - 1)) {
                return BOTTOM_TYPE;
            } else {
                return GROUP_TYPE;
            }
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BOTTOM_TYPE:
                return new BottomTabViewHolder(inflater.inflate(R.layout.item_comment_list_status, parent, false));
            case GROUP_TYPE:
                return new CircleChildCommentViewHolder(inflater.inflate(R.layout.item_circle_detail_child_comment, parent, false), mCommentListener);
            default:
                return new CircleChildCommentViewHolder(inflater.inflate(R.layout.item_circle_detail_child_comment, parent, false), mCommentListener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof BottomTabViewHolder) {

            if (position == 2) {
                ((BottomTabViewHolder) holder).tvCommentListStatus.setText("更多");
                ((BottomTabViewHolder) holder).tvCommentListStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commentListStatus = true;
                        notifyDataSetChanged();
                    }
                });
            } else {
                ((BottomTabViewHolder) holder).tvCommentListStatus.setText("收起");
                ((BottomTabViewHolder) holder).tvCommentListStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commentListStatus = false;
                        notifyDataSetChanged();
                    }
                });
            }


        } else {
            ((CircleChildCommentViewHolder) holder).tvPublishTime.setText(CircleDateUtils.getCircleDate(communityCommentsOutsBeanList.get(position).getCreateTime()));
            ((CircleChildCommentViewHolder) holder).tvNickName.setText(communityCommentsOutsBeanList.get(position).getName());
//TODO 优化回复字段的字体颜色
            String start = "回复" + communityCommentsOutsBeanList.get(position).getPassiveCustomerName() +
                    "：";
            String end = communityCommentsOutsBeanList.get(position).getComment();
            ((CircleChildCommentViewHolder) holder).tvContent.setText(SpannableTextUtils.setTextTwoColor(start, end));
//            ((CircleChildCommentViewHolder) holder).tvContent.setText(
//                    "回复"+communityCommentsOutsBeanList.get(position).getPassiveCustomerName()+
//                            "："+communityCommentsOutsBeanList.get(position).getComment());
//            if (communityCommentsOutsBeanList.get(position).getMaster().equals("1")) {
//                ((CircleChildCommentViewHolder) holder).ivMaster.setVisibility(View.VISIBLE);
//            } else {
//                ((CircleChildCommentViewHolder) holder).ivMaster.setVisibility(View.GONE);
//            }
//            ((CircleChildCommentViewHolder) holder).ivCircleBadge.setImageDrawable(AccountGradeUtils.getGradeDrawable(mContext,
//                    Integer.valueOf(communityCommentsOutsBeanList.get(position).getGrade())));
            glideUtil.glideLoadingImage(mContext, communityCommentsOutsBeanList.get(position).getHead(), R.drawable.y_you, ((CircleChildCommentViewHolder) holder).ivHeader);

            if (GlobalDataYepao.isLogin()) {
                if (String.valueOf(communityCommentsOutsBeanList.get(position).getCustomerId()).equals(GlobalDataYepao.getUser(mContext).getId())) {
                    ((CircleChildCommentViewHolder) holder).ivDelete.setVisibility(View.VISIBLE);
                } else {
                    ((CircleChildCommentViewHolder) holder).ivDelete.setVisibility(View.GONE);
                }
            } else {
                ((CircleChildCommentViewHolder) holder).ivDelete.setVisibility(View.GONE);
            }
            ((CircleChildCommentViewHolder) holder).ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCommentListener.itemDeleteIconClickListener(position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {

        if (communityCommentsOutsBeanList.size() < 2) {
            return communityCommentsOutsBeanList.size();
        }

        if (communityCommentsOutsBeanList.size() == 2) {
            return 2;
        }

        if (!commentListStatus) {
            return 3;
        } else {
            return communityCommentsOutsBeanList.size() + 1;
        }
    }

    static class CircleChildCommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CommentOnClickListener mListener;
        @BindView(R.id.iv_header)
        CircleImageView ivHeader;
        @BindView(R.id.tv_nick_name)
        TextView tvNickName;
        @BindView(R.id.tv_publish_time)
        TextView tvPublishTime;
        @BindView(R.id.iv_circle_badge)
        ImageView ivCircleBadge;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.iv_master)
        ImageView ivMaster;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        CircleChildCommentViewHolder(View view, CommentOnClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            mListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.itemOnClickListener(getLayoutPosition());
        }
    }

    static class BottomTabViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_comment_list_status)
        TextView tvCommentListStatus;

        BottomTabViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
