package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.model.BodySideListModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/29.
 */

public class PhysicalTestMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "PhysicalTestMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    private ConstraintSet constraintSet1 = new ConstraintSet();
    private ConstraintSet constraintSet2 = new ConstraintSet();


    private List<BodySideListModel.DataBean.BodySideUserOutBean> bodySideUserOutBeenList;

    private boolean flag = false;

    public PhysicalTestMessageAdapter(Context context, List<BodySideListModel.DataBean.BodySideUserOutBean> bodySideUserOutBeen) {

        mContext = context;
        inflater = LayoutInflater.from(context);
        this.bodySideUserOutBeenList = bodySideUserOutBeen;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OpenViewHolder(inflater.inflate(R.layout.item_physical_first, parent, false));
    }

    @TargetApi(19)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof OpenViewHolder) {
            if (position == 0) {
                ((OpenViewHolder) holder).etHeart.requestFocus();
            }

            GlideUtil glideUtil = new GlideUtil();
            glideUtil.glideLoadingImage(mContext, ConstantYeaPao.HOST + bodySideUserOutBeenList.get(position).getHead(),
                    R.drawable.y_you, ((OpenViewHolder) holder).ivHead);

            ((OpenViewHolder) holder).tvAccountName.setText(bodySideUserOutBeenList.get(position).getUserName());


            ((OpenViewHolder) holder).tvPhysicalStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Build.VERSION.SDK_INT >= 19) {
                        TransitionManager.beginDelayedTransition(((OpenViewHolder) holder).clOpenStatus);
                    }

                    boolean status = ((OpenViewHolder) holder).clDetail.getVisibility() == View.VISIBLE;

                    if (status) {
                        LogUtil.e(TAG, "gone");
                        constraintSet1.clone(((OpenViewHolder) holder).clOpenStatus);
                        constraintSet1.setVisibility(R.id.cl_detail, ConstraintSet.GONE);
                        constraintSet1.applyTo(((OpenViewHolder) holder).clOpenStatus);
                        flag = true;
                    } else {
                        LogUtil.e(TAG, "visible");
                        constraintSet1.clone(((OpenViewHolder) holder).clOpenStatus);
                        constraintSet1.setVisibility(R.id.cl_detail, ConstraintSet.VISIBLE);
                        constraintSet1.applyTo(((OpenViewHolder) holder).clOpenStatus);
                        flag = false;
                    }
                }
            });

        }
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class OpenViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.cl_detail)
        ConstraintLayout clDetail;
        @BindView(R.id.cl_open_status)
        ConstraintLayout clOpenStatus;
        @BindView(R.id.et_high)
        EditText etHigh;
        @BindView(R.id.et_inbody)
        EditText etInbody;
        @BindView(R.id.et_high_pressure)
        EditText etHighPressure;
        @BindView(R.id.et_weight)
        EditText etWeight;
        @BindView(R.id.et_blow_pressure)
        EditText etBlowPressure;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_account_name)
        TextView tvAccountName;
        @BindView(R.id.tv_Physical_status)
        TextView tvPhysicalStatus;
        @BindView(R.id.iv_gender)
        ImageView ivGender;
        @BindView(R.id.et_heart)
        EditText etHeart;
        @BindView(R.id.iv_take_photo)
        ImageView ivTakePhoto;

        OpenViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

    }

}
