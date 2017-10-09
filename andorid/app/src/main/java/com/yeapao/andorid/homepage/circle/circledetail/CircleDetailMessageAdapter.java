package com.yeapao.andorid.homepage.circle.circledetail;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.recyclerview.GridSpacingItemDecoration;
import com.scottfu.sflibrary.recyclerview.SpaceItemDecoration;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.circle.CircleMessageAdapter;
import com.yeapao.andorid.homepage.circle.ImageRecyclerAdapter;
import com.yeapao.andorid.model.CommunityDetailModel;
import com.yeapao.andorid.util.AccountGradeUtils;
import com.yeapao.andorid.util.CircleDateUtils;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.net.InterfaceAddress;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/18.
 */

public class CircleDetailMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CircleDetailMessageAdapter";

    private static Context mContext;
    private LayoutInflater inflater;
    private CircleDetailContract.View mView;
    private GlideUtil glideUtil = new GlideUtil();

    private CommunityDetailModel mCommunityDetailList;

    private setCircleCommentClickListener commentClickListener;
    private ChildCommentClickListener childCommentClickListener;
    private DeleteCommunityClickListener deleteCommunityClickListener;



    private static final int HEADER_TYPE = 0;
    private static final int GROUP_TYPE = 1;


    public interface DeleteCommunityClickListener {
        void deleteCommunity();
    }

    public void setDeleteCommunityClickListener(DeleteCommunityClickListener listener) {
        deleteCommunityClickListener = listener;
    }

    public void setCommentClickListener(setCircleCommentClickListener commentClickListener) {
        this.commentClickListener = commentClickListener;
    }

    public void setChildCommentClickListener(ChildCommentClickListener listener) {
        this.childCommentClickListener = listener;
    }


    public CircleDetailMessageAdapter(Context context, CommunityDetailModel communityDetailModel) {
        mCommunityDetailList = communityDetailModel;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        } else {
            return GROUP_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            return new CircleDetailTopViewHolder(inflater.inflate(R.layout.item_circle_detail_top, parent, false));
        } else {
            return new CircleDetailCommentViewHolder(inflater.inflate(R.layout.item_circle_detail_comment, parent, false),commentClickListener);

        }


    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CircleDetailTopViewHolder) {
            ((CircleDetailTopViewHolder) holder).tvPublishTime.setText(CircleDateUtils.getCircleDate(mCommunityDetailList.getData().getCreateTime()));

            if (GlobalDataYepao.isLogin()) {
                if (mCommunityDetailList.getData().getUserName().equals(GlobalDataYepao.getUser(mContext).getName())) {
                    ((CircleDetailTopViewHolder) holder).ivCommunityDelete.setVisibility(View.VISIBLE);
                    ((CircleDetailTopViewHolder) holder).ivCommunityDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteCommunityClickListener.deleteCommunity();
                        }
                    });
                } else {
                    ((CircleDetailTopViewHolder) holder).ivCommunityDelete.setVisibility(View.GONE);
                }
            } else {
                ((CircleDetailTopViewHolder) holder).ivCommunityDelete.setVisibility(View.GONE);
            }

//            ((CircleDetailTopViewHolder) holder).ivCircleBadge.setImageDrawable(AccountGradeUtils.getGradeDrawable(mContext,mCommunityDetailList.getData().getGrade()));
            ((CircleDetailTopViewHolder) holder).tvNickName.setText(mCommunityDetailList.getData().getUserName());
            if (mCommunityDetailList.getData().getContent() == null || mCommunityDetailList.getData().getContent().equals("")) {
                ((CircleDetailTopViewHolder) holder).tvContent.setVisibility(View.GONE);
            } else {
                ((CircleDetailTopViewHolder) holder).tvContent.setText(mCommunityDetailList.getData().getContent());
            }

            glideUtil.glideLoadingImage(mContext, mCommunityDetailList.getData().getHeadUrl(), R.drawable.y_you, ((CircleDetailTopViewHolder) holder).ivHeader);
//            if (mCommunityDetailList.getData().getMaster().equals("1")) {
//                ((CircleDetailTopViewHolder) holder).ivMaster.setVisibility(View.VISIBLE);
//            } else {
//                ((CircleDetailTopViewHolder) holder).ivMaster.setVisibility(View.GONE);
//            }

            ((CircleDetailTopViewHolder) holder).tvCommentSum.setText(String.valueOf("评论" + String.valueOf(mCommunityDetailList.getData().getCommentNumber())));
            ((CircleDetailTopViewHolder) holder).tvFingerSum.setText(String.valueOf(String.valueOf(mCommunityDetailList.getData().getThumbsUp())) + "赞");

            if (mCommunityDetailList.getData().getImages().size() == 0) {
                ((CircleDetailTopViewHolder) holder).rvImages.setVisibility(View.GONE);
            } else {

                if (mCommunityDetailList.getData().getImages().size() ==  4) {
                    ((CircleDetailTopViewHolder) holder).rvImages.setLayoutManager(new GridLayoutManager(mContext,2));
//                    ((CircleItemViewHolder) holder).rvImages.addItemDecoration(new SpaceItemDecoration(ScreenUtil.dpToPxInt(mContext, 8)));
//                    ((CircleItemViewHolder) holder).rvImages.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                } else {
                    ((CircleDetailTopViewHolder) holder).rvImages.setLayoutManager(new GridLayoutManager(mContext,3));
//                    ((CircleItemViewHolder) holder).rvImages.addItemDecoration(new GridSpacingItemDecoration(3, ScreenUtil.dpToPxInt(mContext, 8), false));
//                    ((CircleItemViewHolder) holder).rvImages.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                }



                ((CircleDetailTopViewHolder) holder).rvImages.setVisibility(View.VISIBLE);
                ((CircleDetailTopViewHolder) holder).rvImages.setAdapter(new ImageRecyclerAdapter(mContext, mCommunityDetailList.getData().getImages()));
            }


        } else {
            ((CircleDetailCommentViewHolder) holder).tvPublishTime.setText(CircleDateUtils.getCircleDate(mCommunityDetailList.getData().getComments().get(position-1).getCreateTime()));

            if (GlobalDataYepao.isLogin()) {
                if (String.valueOf(mCommunityDetailList.getData().getComments().get(position - 1).getCustomerId()).equals(GlobalDataYepao.getUser(mContext).getId())) {
                    ((CircleDetailCommentViewHolder) holder).ivCommentDelete.setVisibility(View.VISIBLE);
                } else {
                    ((CircleDetailCommentViewHolder) holder).ivCommentDelete.setVisibility(View.GONE);
                }
            } else {
                ((CircleDetailCommentViewHolder) holder).ivCommentDelete.setVisibility(View.GONE);
            }



            ((CircleDetailCommentViewHolder) holder).tvNickName.setText(mCommunityDetailList.getData().getComments().get(position - 1).getName());
            ((CircleDetailCommentViewHolder) holder).tvContent.setText(mCommunityDetailList.getData().getComments().get(position - 1).getComment());
            glideUtil.glideLoadingImage(mContext, mCommunityDetailList.getData().getComments().get(position-1).getHead(),
                    R.drawable.y_you, ((CircleDetailCommentViewHolder) holder).ivHeader);
//            if (mCommunityDetailList.getData().getComments().get(position-1).getMaster().equals("1")) {
//                ((CircleDetailCommentViewHolder) holder).ivMaster.setVisibility(View.VISIBLE);
//            } else {
//                ((CircleDetailCommentViewHolder) holder).ivMaster.setVisibility(View.GONE);
//            }
//            ((CircleDetailCommentViewHolder) holder).ivCircleBadge.setImageDrawable(AccountGradeUtils.getGradeDrawable(mContext,
//                    Integer.valueOf(mCommunityDetailList.getData().getComments().get(position-1).getGrade())));

            if (mCommunityDetailList.getData().getComments().get(position - 1).getCommunityCommentsOuts().size() == 0) {
                ((CircleDetailCommentViewHolder) holder).rvImages.setVisibility(View.GONE);
            } else {
                ((CircleDetailCommentViewHolder) holder).rvImages.setVisibility(View.VISIBLE);
                CircleCommentMessageAdapter circleCommentMessageAdapter = new CircleCommentMessageAdapter(mContext,
                        mCommunityDetailList.getData().getComments().get(position - 1).getCommunityCommentsOuts());
                ((CircleDetailCommentViewHolder) holder).rvImages.setAdapter(circleCommentMessageAdapter);
                circleCommentMessageAdapter.setCommentOnClickListener(new CommentOnClickListener() {
                    @Override
                    public void itemOnClickListener(int pos) {
                        childCommentClickListener.onChildCommentListener(position-1,pos);
                    }

                    @Override
                    public void itemDeleteIconClickListener(int pos) {
                        childCommentClickListener.onChildDeleteIconListener(position-1,pos);
                    }
                });

            } 
        }

    }




    @Override
    public int getItemCount() {
        return mCommunityDetailList.getData().getComments().size() + 1;
    }

    static class CircleDetailTopViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.rv_images)
        RecyclerView rvImages;
        @BindView(R.id.iv_master)
        ImageView ivMaster;
        @BindView(R.id.tv_comment_sum)
        TextView tvCommentSum;
        @BindView(R.id.tv_finger_sum)
        TextView tvFingerSum;
        @BindView(R.id.iv_community_delete)
        ImageView ivCommunityDelete;

        CircleDetailTopViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            initView();
        }

        private void initView() {
//            rvImages.addItemDecoration(new GridSpacingItemDecoration(3, ScreenUtil.dpToPxInt(mContext, 8), true));//设置间隔
            rvImages.addItemDecoration(new SpaceItemDecoration(ScreenUtil.dpToPxInt(mContext, 8)));
        }
    }

    public interface setCircleCommentClickListener {
        void onCommentClickListener(int position);

        void onCommentDeleteIconClickListener(int position);
    }

    public interface ChildCommentClickListener {
        void onChildCommentListener(int pos, int childPos);

        void onChildDeleteIconListener(int pos, int childPos);
    }


    static class CircleDetailCommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private setCircleCommentClickListener mlistener;

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
        @BindView(R.id.rv_images)
        RecyclerView rvImages;
        @BindView(R.id.iv_master)
        ImageView ivMaster;
        @BindView(R.id.iv_comment_delete)
        ImageView ivCommentDelete;

        CircleDetailCommentViewHolder(View view, setCircleCommentClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            initViews();
            mlistener = listener;
            view.setOnClickListener(this);
        }

        private void initViews() {
            rvImages.setLayoutManager(new LinearLayoutManager(mContext));
            ivCommentDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mlistener.onCommentDeleteIconClickListener(getLayoutPosition() - 1);
                }
            });
        }

        @Override
        public void onClick(View v) {
            mlistener.onCommentClickListener(getLayoutPosition()-1);
        }
    }
}
