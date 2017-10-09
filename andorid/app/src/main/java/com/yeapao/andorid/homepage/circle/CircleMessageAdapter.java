package com.yeapao.andorid.homepage.circle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.os.TraceCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.scottfu.sflibrary.recyclerview.GridSpacingItemDecoration;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.recyclerview.SpaceItemDecoration;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.homepage.circle.circledetail.CircleViewPager;
import com.yeapao.andorid.homepage.myself.MyselfMessageAdapter;
import com.yeapao.andorid.model.CircleListModel;
import com.yeapao.andorid.util.AccountGradeUtils;
import com.yeapao.andorid.util.CircleDateUtils;
import com.yeapao.andorid.util.SpannableTextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by fujindong on 2017/7/16.
 */

public class CircleMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CircleMessageAdapter";

    private CircleMessageAdapter circleMessageAdapter = this;

    private static Context mContext;
    private LayoutInflater inflater;
    private OnRecyclerViewClickListener mListener;
    private PraiseClickListener mPraiseListener;

    private static final int HEADER_TYPE = 0;
    private static final int GROUP_TYPE = 1;
    private static final int CIRCLE_TYPE = 2;
    private static final int FOOTER_TYPE = 3;

    private static CircleListModel mCircleListModel = new CircleListModel();

    private GlideUtil glideUtil = new GlideUtil();

    private boolean footerFlag = false;


    public void setmPraiseListener(PraiseClickListener listener) {
        mPraiseListener = listener;
    }

    public CircleMessageAdapter(Context context,CircleListModel circleListModel) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        mCircleListModel = circleListModel;
    }

    /**
     * 刷新单条item
     * @param position
     * @param status 0／1
     */
    public void refreshItemPraise(int position, String status) {
//        mCircleListModel.getData().getCommunityList().get(position).setFabulous(status);
//        notifyItemChanged(position);

    }

    public void loadMore(CircleListModel model) {
        mCircleListModel.getData().getCommunityList().addAll(model.getData().getCommunityList());
        notifyDataSetChanged();
    }

    public void loadNothing() {
//        TODO 取消尾部加载tab
        footerFlag = true;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER_TYPE:
                return new HeaderViewHolder(inflater.inflate(R.layout.item_circle_top, parent, false));
            case GROUP_TYPE:
                return new GroupChatListViewHolder(inflater.inflate(R.layout.item_circle_group_chat, parent, false));
            case CIRCLE_TYPE:
                return new CircleItemViewHolder(inflater.inflate(R.layout.item_circle_card, parent, false),mListener,mPraiseListener);
            case FOOTER_TYPE:
                return new FooterViewHolder(inflater.inflate(R.layout.list_footer, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).vpCircleImage.setAdapter(new CircleViewPager(mContext, mCircleListModel.getData().getBannerList()));
            ((HeaderViewHolder) holder).ciCircleIndicator.setViewPager(((HeaderViewHolder) holder).vpCircleImage);
            ((HeaderViewHolder) holder).vpCircleImage.setCurrentItem(0);
        } else if (holder instanceof CircleItemViewHolder) {
//            ((CircleItemViewHolder) holder).ivCircleBadge.setImageDrawable(AccountGradeUtils.getGradeDrawable(mContext,
//                    mCircleListModel.getData().getCommunityList().get(position - 1).getGrade()));

            if (mCircleListModel.getData().getCommunityList().get(position - 1).getFabulous().equals("1")) {
                Drawable img = mContext.getResources().getDrawable(R.drawable.circle_finger_s);
                // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                ((CircleItemViewHolder) holder).tvFinger.setCompoundDrawables(img, null, null, null);
            } else {
                Drawable img = mContext.getResources().getDrawable(R.drawable.circle_finger_n);
                // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                ((CircleItemViewHolder) holder).tvFinger.setCompoundDrawables(img, null, null, null);
            }

            ((CircleItemViewHolder) holder).tvNickName.setText(mCircleListModel.getData().getCommunityList().get(position - 1).getUserName());

            if (mCircleListModel.getData().getCommunityList().get(position - 1).getContent() == null || mCircleListModel.getData().getCommunityList().get(position - 1).getContent().equals("")) {
                ((CircleItemViewHolder) holder).tvContent.setVisibility(View.GONE);
            } else {
                if (mCircleListModel.getData().getCommunityList().get(position - 1).getType().equals("community")) {
                    ((CircleItemViewHolder) holder).tvContent.setText(mCircleListModel.getData().getCommunityList().get(position - 1).getContent());
                } else if (mCircleListModel.getData().getCommunityList().get(position - 1).getType().equals("breakfast")) {
                    String breakfast = "#早餐打卡#";
                    ((CircleItemViewHolder) holder).tvContent.setText(SpannableTextUtils.setTextTwoColor(breakfast,
                            mCircleListModel.getData().getCommunityList().get(position - 1).getContent()));
                } else if (mCircleListModel.getData().getCommunityList().get(position - 1).getType().equals("lunch")) {
                    String lunch = "#午餐打卡#";
                    ((CircleItemViewHolder) holder).tvContent.setText(SpannableTextUtils.setTextTwoColor(lunch,
                            mCircleListModel.getData().getCommunityList().get(position - 1).getContent()));
                } else {
                    String dinner = "#晚餐打卡#";
                    ((CircleItemViewHolder) holder).tvContent.setText(SpannableTextUtils.setTextTwoColor(dinner,
                            mCircleListModel.getData().getCommunityList().get(position - 1).getContent()));
                }
            }

            ((CircleItemViewHolder) holder).tvPublishTime.setText(CircleDateUtils.getCircleDate(mCircleListModel.getData().getCommunityList().get(position-1).getCreateTime()));

            ((CircleItemViewHolder) holder).tvComment.setText(String.valueOf(mCircleListModel.getData().getCommunityList().get(position - 1).getCommentNumber()));
            ((CircleItemViewHolder) holder).tvFinger.setText(String.valueOf(mCircleListModel.getData().getCommunityList().get(position - 1).getThumbsUp()));
            glideUtil.glideLoadingImage(mContext, mCircleListModel.getData().getCommunityList().get(position - 1).getHeadUrl(), R.drawable.y_you, ((CircleItemViewHolder) holder).ivHeader);
//            if (mCircleListModel.getData().getCommunityList().get(position - 1).getMaster().equals("1")) {
//                ((CircleItemViewHolder) holder).ivMaster.setVisibility(View.VISIBLE);
//            } else {
//                ((CircleItemViewHolder) holder).ivMaster.setVisibility(View.GONE);
//            }

//            if (mCircleListModel.getData().getCommunityList().get(position).getImages().size() == 1) {
//                ((CircleItemViewHolder) holder).rvImages.setLayoutManager(new GridLayoutManager(mContext,3));
//            } else {
//
//            }
            if (mCircleListModel.getData().getCommunityList().get(position-1).getImages().size() == 0) {
                ((CircleItemViewHolder) holder).rvImages.setVisibility(View.GONE);
            } else {

                if (mCircleListModel.getData().getCommunityList().get(position - 1).getImages().size() == 4) {
                    ((CircleItemViewHolder) holder).rvImages.setLayoutManager(new GridLayoutManager(mContext,2));
//                    ((CircleItemViewHolder) holder).rvImages.addItemDecoration(new SpaceItemDecoration(ScreenUtil.dpToPxInt(mContext, 8)));
//                    ((CircleItemViewHolder) holder).rvImages.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                } else {
                    ((CircleItemViewHolder) holder).rvImages.setLayoutManager(new GridLayoutManager(mContext,3));
//                    ((CircleItemViewHolder) holder).rvImages.addItemDecoration(new GridSpacingItemDecoration(3, ScreenUtil.dpToPxInt(mContext, 8), false));
//                    ((CircleItemViewHolder) holder).rvImages.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                }

                ((CircleItemViewHolder) holder).rvImages.setVisibility(View.VISIBLE);
                ((CircleItemViewHolder) holder).rvImages.setAdapter(new ImageRecyclerAdapter(mContext,mCircleListModel.getData().getCommunityList().get(position-1).getImages()));
            }



        } else {

            if (footerFlag) {
                ((FooterViewHolder) holder).llFooter.setVisibility(View.GONE);
            } else {
                ((FooterViewHolder) holder).llFooter.setVisibility(View.VISIBLE);
            }

        }

    }

    public void setItemClickListener(OnRecyclerViewClickListener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    @Override
    public int getItemCount() {
        return mCircleListModel.getData().getCommunityList().size()+2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        } else if (position == mCircleListModel.getData().getCommunityList().size()+1) {
            return FOOTER_TYPE;
        } else {
            return CIRCLE_TYPE;
        }

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

    public interface PraiseClickListener {
        void onPraiseClicklistener(int position);
    }


    static class CircleItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnRecyclerViewClickListener OnRecyclerListener;
        private ImageRecyclerAdapter imageAdapter;
        private PraiseClickListener mPraiseListener;


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
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.tv_finger)
        TextView tvFinger;
        @BindView(R.id.iv_header)
        ImageView ivHeader;
        @BindView(R.id.iv_master)
        ImageView ivMaster;


        CircleItemViewHolder(View view,OnRecyclerViewClickListener listener,PraiseClickListener praiseListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.OnRecyclerListener = listener;
            mPraiseListener = praiseListener;
            view.setOnClickListener(this);
            initView();
        }

        private void initView() {
//            rvImages.setLayoutManager(new GridLayoutManager(mContext,3));
            rvImages.addItemDecoration(new SpaceItemDecoration(ScreenUtil.dpToPxInt(mContext, 8)));
            tvFinger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPraiseListener.onPraiseClicklistener(getLayoutPosition()-1);
                }
            });
        }

        @Override
        public void onClick(View v) {
            OnRecyclerListener.OnItemClick(v,getLayoutPosition()-1);

        }
    }



    public class FooterViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llFooter;



        public FooterViewHolder(View itemView) {
            super(itemView);
            llFooter = (LinearLayout) itemView.findViewById(R.id.ll_circle_footer);
        }

    }


}
