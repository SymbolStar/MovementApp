package com.yeapao.andorid.homepage.myself;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.homepage.lesson.LessonMessageAdapter;
import com.yeapao.andorid.homepage.myself.tab.MyselfClockOutActivity;
import com.yeapao.andorid.homepage.myself.tab.MyselfFoodActivity;
import com.yeapao.andorid.homepage.myself.tab.food.MyselfFoodV2Activity;
import com.yeapao.andorid.homepage.myself.tab.shopkeeper.MyselfClockOutActivityV2;
import com.yeapao.andorid.model.MyselfTabModel;
import com.yeapao.andorid.model.UserData;
import com.yeapao.andorid.util.AccountGradeUtils;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/19.
 */

public class MyselfMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "MyselfMessageAdapter";

    private static  Context mContext;
    private LayoutInflater inflater;

    private OnRecyclerViewClickListener mListener;
    private OnMyselfTabListener mTabListener;

    private static final int MYSELF_HEADER_TYPE = 0;
    private static final int MYSELF_DATA_TYPE = 1;
    private static final int MYSELF_OPTION_TYPE = 2;

    private ConstraintSet applyConstraintSet = new ConstraintSet();



    private static List<MyselfTabModel> myselfTabModels = new ArrayList<>();
    private UserData mUserData = new UserData();

    public MyselfMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        mUserData = GlobalDataYepao.getUser(mContext);
        setMyselfTabModelData();

    }

    private void setMyselfTabModelData() {

            myselfTabModels.clear();

        for (int i = 0; i < 3; i++) {

            MyselfTabModel myselfTabModel = new MyselfTabModel();
            switch (i) {
                case 0:
                    myselfTabModel.setViewId(mContext.getResources().getDrawable(R.drawable.my_cash));
                    myselfTabModel.setTabName("认证");
                    myselfTabModels.add(myselfTabModel);
                    break;
                case 1:
                    myselfTabModel.setViewId(mContext.getResources().getDrawable(R.drawable.my_record));
                    myselfTabModel.setTabName("健康数据库");
                    myselfTabModels.add(myselfTabModel);
                    break;
                case 2:
                    myselfTabModel.setViewId(mContext.getResources().getDrawable(R.drawable.my_phacse_order));
                    myselfTabModel.setTabName("我的订单");
                    myselfTabModels.add(myselfTabModel);
                    break;
//                    myselfTabModel.setViewId(mContext.getResources().getDrawable(R.drawable.my_order));
//                    myselfTabModel.setTabName("我的预约");
//                    myselfTabModels.add(myselfTabModel);
//                    break;
                case 3:
                    myselfTabModel.setViewId(mContext.getResources().getDrawable(R.drawable.my_lesson));
                    myselfTabModel.setTabName("我的课程");
                    myselfTabModels.add(myselfTabModel);
                    break;
                case 4:
                    myselfTabModel.setViewId(mContext.getResources().getDrawable(R.drawable.my_phacse_order));
                    myselfTabModel.setTabName("我的订单");
                    myselfTabModels.add(myselfTabModel);
                    break;
//                case 4:
//                    myselfTabModel.setViewId(mContext.getResources().getDrawable(R.drawable.my_note));
//                    myselfTabModel.setTabName("我的帖子");
//                    myselfTabModels.add(myselfTabModel);
//                    break;
                case 5:
                    myselfTabModel.setViewId(mContext.getResources().getDrawable(R.drawable.my_coach));
                    myselfTabModel.setTabName("我是教练");
                    myselfTabModels.add(myselfTabModel);
                    break;
                case 6:
                    myselfTabModel.setViewId(mContext.getResources().getDrawable(R.drawable.my_shopkeeper));
                    myselfTabModel.setTabName("我是店长");
                    myselfTabModels.add(myselfTabModel);
                    break;
            }

        }

//        if (GlobalDataYepao.isLogin()) {
//            if (GlobalDataYepao.getUser(mContext).getPost().equals("1")) {
//                myselfTabModels.remove(5);
//            } else if (GlobalDataYepao.getUser(mContext).getPost().equals("2")) {
//                myselfTabModels.remove(6);
//            } else {
//                myselfTabModels.remove(5);
//                myselfTabModels.remove(5);
//            }
//        }



    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return MYSELF_HEADER_TYPE;
        } else if (position == 1) {
            return MYSELF_DATA_TYPE;
        } else {
            return MYSELF_OPTION_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MYSELF_HEADER_TYPE:
                return new HeaderViewHolder(inflater.inflate(R.layout.item_myself_header, parent, false), mListener);
            case MYSELF_DATA_TYPE:
                return new DataViewHolder(inflater.inflate(R.layout.item_myself_options, parent, false));
            case MYSELF_OPTION_TYPE:
                return new TabViewHolder(inflater.inflate(R.layout.item_myself_tab, parent, false), mTabListener);
        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof TabViewHolder) {
            ((TabViewHolder) holder).ivMyselfTabIcon.setImageDrawable(myselfTabModels.get(position - 2).getViewId());
            ((TabViewHolder) holder).tvMyselfTabTitle.setText(myselfTabModels.get(position-2).getTabName());
        } else if (holder instanceof HeaderViewHolder) {
            mUserData = GlobalDataYepao.getUser(mContext);
            if (mUserData != null) {
                GlideUtil glideUtil = new GlideUtil();
                glideUtil.glideLoadingImage(mContext, ConstantYeaPao.HOST + mUserData.getHead(), R.drawable.y_you, ((HeaderViewHolder) holder).ivAccountHead);
                ((HeaderViewHolder) holder).tvAccountName.setText(mUserData.getName());
                ((HeaderViewHolder) holder).tvAccountName.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                applyConstraintSet.clone(((HeaderViewHolder) holder).clHeader);
                applyConstraintSet.setMargin(R.id.tv_account_name,ConstraintSet.TOP, (int)ScreenUtil.dpToPx(mContext,40));
                applyConstraintSet.applyTo(((HeaderViewHolder) holder).clHeader);
                ((HeaderViewHolder) holder).tvAccountTell.setText(mUserData.getPhone());

                int grade = Integer.valueOf(mUserData.getGrade());
                ((HeaderViewHolder) holder).ivAccountBadge.setImageDrawable(AccountGradeUtils.getGradeDrawable(mContext,grade));
            } else {
                ((HeaderViewHolder) holder).ivAccountHead.setImageDrawable(mContext.getResources().getDrawable(R.drawable.y_you));
                ((HeaderViewHolder) holder).tvAccountName.setText("注册／登录");
                ((HeaderViewHolder) holder).tvAccountName.setTextColor(mContext.getResources().getColor(R.color.login_text_color));
                ((HeaderViewHolder) holder).tvAccountTell.setText("");
                ((HeaderViewHolder) holder).ivAccountBadge.setVisibility(View.GONE);
                applyConstraintSet.clone(((HeaderViewHolder) holder).clHeader);
                applyConstraintSet.setMargin(R.id.tv_account_name,ConstraintSet.TOP, (int)ScreenUtil.dpToPx(mContext,50));
                applyConstraintSet.applyTo(((HeaderViewHolder) holder).clHeader);
            }


        }

    }

    @Override
    public int getItemCount() {
        return 2 + myselfTabModels.size();
    }

    public void setItemClickListener(OnRecyclerViewClickListener listener) {
        mListener = listener;
    }

    public void setMyselfTabListener(OnMyselfTabListener listener) {
        mTabListener = listener;
    }


    static class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnRecyclerViewClickListener listener;

        @BindView(R.id.iv_account_bg)
        ImageView ivAccountBg;
        @BindView(R.id.iv_account_head)
        CircleImageView ivAccountHead;
        @BindView(R.id.tv_account_name)
        TextView tvAccountName;
        @BindView(R.id.tv_account_tell)
        TextView tvAccountTell;
        @BindView(R.id.iv_account_badge)
        ImageView ivAccountBadge;
        @BindView(R.id.iv_account_set_right)
        ImageView ivAccountSetRight;
        @BindView(R.id.cl_header)
        ConstraintLayout clHeader;

        HeaderViewHolder(View view, OnRecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            this.listener = listener;



        }

        @OnClick(R.id.iv_account_head)
        void setIvAccountHead(View view) {
            LogUtil.e(TAG,"accounthead");
        }


        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.v_card_click)
        View vCardClick;
        @BindView(R.id.v_food_click)
        View vFoodClick;


        DataViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


        @OnClick({R.id.v_card_click, R.id.v_food_click})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.v_card_click:
                    LogUtil.e(TAG, "card");
                    MyselfClockOutActivity.start(mContext);
//                    MyselfClockOutActivityV2.start(mContext);
                    break;
                case R.id.v_food_click:
                    LogUtil.e(TAG, "food");
//                    MyselfFoodActivity.start(mContext);
                    MyselfFoodV2Activity.start(mContext);
                    break;
            }
        }

    }

    static class TabViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnMyselfTabListener listener;


        @BindView(R.id.iv_myself_tab_icon)
        ImageView ivMyselfTabIcon;
        @BindView(R.id.tv_myself_tab_title)
        TextView tvMyselfTabTitle;

        TabViewHolder(View view, OnMyselfTabListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                LogUtil.e(TAG,String.valueOf(getLayoutPosition()));
                listener.onTabClick(v, getLayoutPosition(),myselfTabModels.get(getLayoutPosition()-2).getTabName());
            }
        }
    }
}
