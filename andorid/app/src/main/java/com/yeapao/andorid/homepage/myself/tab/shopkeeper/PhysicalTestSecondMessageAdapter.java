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
 * Created by fujindong on 2017/7/29.
 */

public class PhysicalTestSecondMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "PhysicalTestMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    private ConstraintSet constraintSet1 = new ConstraintSet();


    public PhysicalTestSecondMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SecondViewHolder(inflater.inflate(R.layout.item_physical_second, parent, false));
    }

    @TargetApi(19)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof SecondViewHolder) {

            if (position == 0) {
                ((SecondViewHolder) holder).etUpperLimbRight.requestFocus();
            }


            ((SecondViewHolder) holder).tvPhysicalStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Build.VERSION.SDK_INT >= 19) {
                        TransitionManager.beginDelayedTransition(((SecondViewHolder) holder).clOpenStatus);
                    }

                    boolean status = ((SecondViewHolder) holder).clSecondDetail.getVisibility() == View.VISIBLE;

                    if (status) {
                        LogUtil.e(TAG, "gone");
                        constraintSet1.clone(((SecondViewHolder) holder).clOpenStatus);
                        constraintSet1.setVisibility(R.id.cl_second_detail, ConstraintSet.GONE);
                        constraintSet1.applyTo(((SecondViewHolder) holder).clOpenStatus);
                    } else {
                        LogUtil.e(TAG, "visible");
                        constraintSet1.clone(((SecondViewHolder) holder).clOpenStatus);
                        constraintSet1.setVisibility(R.id.cl_second_detail, ConstraintSet.VISIBLE);
                        constraintSet1.applyTo(((SecondViewHolder) holder).clOpenStatus);
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


    class SecondViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_account_name)
        TextView tvAccountName;
        @BindView(R.id.tv_Physical_status)
        TextView tvPhysicalStatus;
        @BindView(R.id.iv_gender)
        ImageView ivGender;
        @BindView(R.id.et_lower_limb_right)
        EditText etLowerLimbRight;
        @BindView(R.id.et_lower_limb_left)
        EditText etLowerLimbLeft;
        @BindView(R.id.et_waist)
        EditText etWaist;
        @BindView(R.id.et_arm)
        EditText etArm;
        @BindView(R.id.et_abdomen)
        EditText etAbdomen;
        @BindView(R.id.et_upper_limb_right)
        EditText etUpperLimbRight;
        @BindView(R.id.et_upper_limb_left)
        EditText etUpperLimbLeft;
        @BindView(R.id.cl_open_status)
        ConstraintLayout clOpenStatus;
        @BindView(R.id.cl_second_detail)
        ConstraintLayout clSecondDetail;


        SecondViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
