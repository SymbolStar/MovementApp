package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.customview.CircleImageView;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.model.BodySideListModel;
import com.yeapao.andorid.model.BodySideSecondSaveModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/7/29.
 */

public class PhysicalTestSecondMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "PhysicalTestSecondMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    private ConstraintSet constraintSet1 = new ConstraintSet();

    private List<BodySideListModel.DataBean.BodySideUserOutBean> bodySideUserOutBeenList;

    private List<BodySideSecondSaveModel> bodySideSecondSaveModels = new ArrayList<>();


    public PhysicalTestSecondMessageAdapter(Context context,
                                            List<BodySideListModel.DataBean.BodySideUserOutBean> bodySideUserOutBeenList,
                                            List<BodySideSecondSaveModel> bodySideSecondSaveModels) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.bodySideUserOutBeenList = bodySideUserOutBeenList;
        this.bodySideSecondSaveModels = bodySideSecondSaveModels;


    }

    public List<BodySideSecondSaveModel> getSecondBodyData() {
        return bodySideSecondSaveModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SecondViewHolder(inflater.inflate(R.layout.item_physical_second, parent, false));
    }

    @TargetApi(19)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        GlideUtil glideUtil = new GlideUtil();
        if (holder instanceof SecondViewHolder) {

//            TODO have data
            if (!bodySideSecondSaveModels.get(position).getUpperRight().equals("0")) {
                ((SecondViewHolder) holder).etUpperLimbLeft.setText(bodySideSecondSaveModels.get(position).getUpperLeft());
                ((SecondViewHolder) holder).etUpperLimbRight.setText(bodySideSecondSaveModels.get(position).getUpperRight());
                ((SecondViewHolder) holder).etAbdomen.setText(bodySideSecondSaveModels.get(position).getAbdomen());
                ((SecondViewHolder) holder).etWaist.setText(bodySideSecondSaveModels.get(position).getWaist());
                ((SecondViewHolder) holder).etArm.setText(bodySideSecondSaveModels.get(position).getHips());
                ((SecondViewHolder) holder).etLowerLimbRight.setText(bodySideSecondSaveModels.get(position).getLowerRight());
                ((SecondViewHolder) holder).etLowerLimbLeft.setText(bodySideSecondSaveModels.get(position).getLowerLeft());
            }



            if (position == 0) {
                ((SecondViewHolder) holder).etUpperLimbRight.requestFocus();
            }

            glideUtil.glideLoadingImage(mContext, ConstantYeaPao.HOST + bodySideUserOutBeenList.get(position).getHead(),
                    R.drawable.y_you, ((SecondViewHolder) holder).ivHead);

            ((SecondViewHolder) holder).tvAccountName.setText(bodySideUserOutBeenList.get(position).getUserName());

            if (bodySideUserOutBeenList.get(position).getGender().equals("男")) {
                ((SecondViewHolder) holder).ivGender.setImageDrawable(mContext.getResources().getDrawable(R.drawable.boy));
            } else {
                ((SecondViewHolder) holder).ivGender.setImageDrawable(mContext.getResources().getDrawable(R.drawable.girl));
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
            initView();
        }

        private void initView() {
            etUpperLimbRight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideSecondSaveModels.get(getLayoutPosition()).setUpperRight(s.toString());
                }
            });
            etUpperLimbLeft.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideSecondSaveModels.get(getLayoutPosition()).setUpperLeft(s.toString());
                }
            });
            etAbdomen.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideSecondSaveModels.get(getLayoutPosition()).setAbdomen(s.toString());
                }
            });
            etWaist.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideSecondSaveModels.get(getLayoutPosition()).setWaist(s.toString());
                }
            });
            etArm.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideSecondSaveModels.get(getLayoutPosition()).setHips(s.toString());
                }
            });
            etLowerLimbLeft.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideSecondSaveModels.get(getLayoutPosition()).setLowerLeft(s.toString());
                }
            });
            etLowerLimbRight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideSecondSaveModels.get(getLayoutPosition()).setLowerRight(s.toString());
                }
            });
        }
    }
}
