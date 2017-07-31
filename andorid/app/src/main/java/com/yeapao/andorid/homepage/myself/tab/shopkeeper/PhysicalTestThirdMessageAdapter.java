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

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/30.
 */

public class PhysicalTestThirdMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final String TAG = "PhysicalTestThirdMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    private ConstraintSet constraintSet1 = new ConstraintSet();


    public PhysicalTestThirdMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThirdViewHolder(inflater.inflate(R.layout.item_physical_third, parent, false));
    }

    @TargetApi(19)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ThirdViewHolder) {

            if (position == 0) {
                ((ThirdViewHolder) holder).etPhysicalThird1.requestFocus();
            }


            ((ThirdViewHolder) holder).tvPhysicalStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Build.VERSION.SDK_INT >= 19) {
                        TransitionManager.beginDelayedTransition(((ThirdViewHolder) holder).clOpenStatus);
                    }

                    boolean status = ((ThirdViewHolder) holder).clThirdDetail.getVisibility() == View.VISIBLE;

                    if (status) {
                        LogUtil.e(TAG, "gone");
                        constraintSet1.clone(((ThirdViewHolder) holder).clOpenStatus);
                        constraintSet1.setVisibility(R.id.cl_third_detail, ConstraintSet.GONE);
                        constraintSet1.applyTo(((ThirdViewHolder) holder).clOpenStatus);
                    } else {
                        LogUtil.e(TAG, "visible");
                        constraintSet1.clone(((ThirdViewHolder) holder).clOpenStatus);
                        constraintSet1.setVisibility(R.id.cl_third_detail, ConstraintSet.VISIBLE);
                        constraintSet1.applyTo(((ThirdViewHolder) holder).clOpenStatus);
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

    static class ThirdViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_account_name)
        TextView tvAccountName;
        @BindView(R.id.tv_Physical_status)
        TextView tvPhysicalStatus;
        @BindView(R.id.iv_gender)
        ImageView ivGender;
        @BindView(R.id.tv_third_first_name)
        TextView tvThirdFirstName;
        @BindView(R.id.et_physical_third_6)
        EditText etPhysicalThird6;
        @BindView(R.id.et_physical_third_5)
        EditText etPhysicalThird5;
        @BindView(R.id.et_physical_third_4)
        EditText etPhysicalThird4;
        @BindView(R.id.et_physical_third_3)
        EditText etPhysicalThird3;
        @BindView(R.id.et_physical_third_2)
        EditText etPhysicalThird2;
        @BindView(R.id.et_physical_third_1)
        EditText etPhysicalThird1;
        @BindView(R.id.cl_open_status)
        ConstraintLayout clOpenStatus;
        @BindView(R.id.cl_third_detail)
        ConstraintLayout clThirdDetail;

        ThirdViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
