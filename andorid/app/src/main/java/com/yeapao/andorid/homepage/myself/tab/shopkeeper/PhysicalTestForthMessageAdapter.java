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
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/31.
 */

public class PhysicalTestForthMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "PhysicalTestForthMessageAdapter";


    private Context mContext;
    private LayoutInflater inflater;

    private ConstraintSet constraintSet1 = new ConstraintSet();

    public PhysicalTestForthMessageAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ForthViewHolder(inflater.inflate(R.layout.item_physical_forth, parent, false));
    }
    @TargetApi(19)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

//        TODO 图片的载入框架后续添加

        if (holder instanceof ForthViewHolder) {

            ((ForthViewHolder) holder).tvPhysicalStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Build.VERSION.SDK_INT >= 19) {
                        TransitionManager.beginDelayedTransition(((ForthViewHolder) holder).clOpenStatus);
                    }

                    boolean status = ((ForthViewHolder) holder).clPhysicalForth.getVisibility() == View.VISIBLE;

                    if (status) {
                        LogUtil.e(TAG, "gone");
                        constraintSet1.clone(((ForthViewHolder) holder).clOpenStatus);
                        constraintSet1.setVisibility(R.id.cl_physical_forth, ConstraintSet.GONE);
                        constraintSet1.applyTo(((ForthViewHolder) holder).clOpenStatus);
                    } else {
                        LogUtil.e(TAG, "visible");
                        constraintSet1.clone(((ForthViewHolder) holder).clOpenStatus);
                        constraintSet1.setVisibility(R.id.cl_physical_forth, ConstraintSet.VISIBLE);
                        constraintSet1.applyTo(((ForthViewHolder) holder).clOpenStatus);
                    }
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return 2;
    }

    static class ForthViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_account_name)
        TextView tvAccountName;
        @BindView(R.id.tv_Physical_status)
        TextView tvPhysicalStatus;
        @BindView(R.id.iv_gender)
        ImageView ivGender;
        @BindView(R.id.iv_physical_post)
        ImageView ivPhysicalPost;
        @BindView(R.id.iv_physical_back)
        ImageView ivPhysicalBack;
        @BindView(R.id.iv_physical_tongue)
        ImageView ivPhysicalTongue;
        @BindView(R.id.iv_physical_positive)
        ImageView ivPhysicalPositive;
        @BindView(R.id.cl_physical_forth)
        ConstraintLayout clPhysicalForth;
        @BindView(R.id.cl_open_status)
        ConstraintLayout clOpenStatus;
        @BindView(R.id.iv_shoppker_delete_1)
        ImageView ivShopplerDelete1;
        @BindView(R.id.iv_shoppker_delete_2)
        ImageView ivShopplerDelete2;
        @BindView(R.id.iv_shoppker_delete_3)
        ImageView ivShopplerDelete3;
        @BindView(R.id.iv_shoppker_delete_4)
        ImageView ivShopplerDelete4;



        ForthViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
