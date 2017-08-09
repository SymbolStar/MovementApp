package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.PopupMenu;
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
import com.yeapao.andorid.model.BodySideThirdSaveModel;

import java.util.ArrayList;
import java.util.List;

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

    private List<BodySideListModel.DataBean.BodySideUserOutBean> bodySideUserOutBeenList = new ArrayList<>();

    private List<BodySideThirdSaveModel> bodySideThirdSaveModels = new ArrayList<>();



    public PhysicalTestThirdMessageAdapter(Context context, List<BodySideListModel.DataBean.BodySideUserOutBean> bodySideUserOutBeanList,
                                           List<BodySideThirdSaveModel> bodySideThirdSaveModels) {

        this.bodySideUserOutBeenList = bodySideUserOutBeanList;
        this.bodySideThirdSaveModels = bodySideThirdSaveModels;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    public List<BodySideThirdSaveModel> getThirdBodyData() {
        return bodySideThirdSaveModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThirdViewHolder(inflater.inflate(R.layout.item_physical_third, parent, false));
    }

    @TargetApi(19)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        GlideUtil glideUtil = new GlideUtil();
        if (holder instanceof ThirdViewHolder) {

            if (!bodySideThirdSaveModels.get(position).getUpperLimbStrength().equals("0")) {
                ((ThirdViewHolder) holder).etPhysicalThird1.setText(bodySideThirdSaveModels.get(position).getUpperLimbStrength());
                ((ThirdViewHolder) holder).etPhysicalThird2.setText(bodySideThirdSaveModels.get(position).getLowerExtremityStrength());
                ((ThirdViewHolder) holder).etPhysicalThird3.setText(bodySideThirdSaveModels.get(position).getPrecursor());
                ((ThirdViewHolder) holder).etPhysicalThird4.setText(bodySideThirdSaveModels.get(position).getHeartRateOne());
                ((ThirdViewHolder) holder).etPhysicalThird5.setText(bodySideThirdSaveModels.get(position).getHeartRateTwo());
                ((ThirdViewHolder) holder).etPhysicalThird6.setText(bodySideThirdSaveModels.get(position).getHeartRateThree());
            }



            if (position == 0) {
                ((ThirdViewHolder) holder).etPhysicalThird1.requestFocus();
            }
            glideUtil.glideLoadingImage(mContext, ConstantYeaPao.HOST + bodySideUserOutBeenList.get(position).getHead(),
                    R.drawable.y_you, ((ThirdViewHolder) holder).ivHead);

            ((ThirdViewHolder) holder).tvAccountName.setText(bodySideUserOutBeenList.get(position).getUserName());

            if (bodySideUserOutBeenList.get(position).getGender().equals("男")) {
                ((ThirdViewHolder) holder).ivGender.setImageDrawable(mContext.getResources().getDrawable(R.drawable.boy));
            } else {
                ((ThirdViewHolder) holder).ivGender.setImageDrawable(mContext.getResources().getDrawable(R.drawable.girl));
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

     class ThirdViewHolder  extends RecyclerView.ViewHolder{
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
            initView();
        }

        private void initView() {
            etPhysicalThird1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideThirdSaveModels.get(getLayoutPosition()).setUpperLimbStrength(s.toString());

                }
            });
            etPhysicalThird2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideThirdSaveModels.get(getLayoutPosition()).setLowerExtremityStrength(s.toString());

                }
            });
            etPhysicalThird3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideThirdSaveModels.get(getLayoutPosition()).setPrecursor(s.toString());

                }
            });
            etPhysicalThird4.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideThirdSaveModels.get(getLayoutPosition()).setHeartRateOne(s.toString());

                }
            });
            etPhysicalThird5.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideThirdSaveModels.get(getLayoutPosition()).setHeartRateTwo(s.toString());

                }
            });
            etPhysicalThird6.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bodySideThirdSaveModels.get(getLayoutPosition()).setHeartRateThree(s.toString());

                }
            });
        }
    }
}
