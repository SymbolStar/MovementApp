package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.AsyncLoaderImage;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.model.BodySideListModel;
import com.yeapao.andorid.model.BodySideOneData;
import com.yeapao.andorid.model.BodySideOneGetModel;
import com.yeapao.andorid.model.HomeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    private ArrayList<BodySideOneData> listModels = new ArrayList<>();

    private OnRecyclerViewClickListener mListener;

    private PhysicalImageListener physicalImageListener;


    private List<BodySideListModel.DataBean.BodySideUserOutBean> bodySideUserOutBeenList;

    private List<BodySideOneGetModel.DataBean> bodyGetLists = new ArrayList<>();

    private boolean flag = false;

    private boolean updateStatus = false;

    private int count = 0;

    private boolean haveData = false;

    public PhysicalTestMessageAdapter(Context context, List<BodySideOneGetModel.DataBean> bodyGetDataLists ,List<BodySideListModel.DataBean.BodySideUserOutBean> bodySideUserOutBeen) {
        haveData = true;
        mContext = context;
        inflater = LayoutInflater.from(context);
        bodyGetLists = bodyGetDataLists;
        this.bodySideUserOutBeenList = bodySideUserOutBeen;

        for (int i = 0; i < bodyGetDataLists.size(); i++) {
            BodySideOneData oneData = new BodySideOneData();
            oneData.setQuietHeartRate(bodyGetDataLists.get(i).getQuietHeartRate());
            oneData.setCustomerId(String.valueOf(bodyGetDataLists.get(i).getCustomerId()));
            oneData.setBodySideOne(String.valueOf(bodyGetDataLists.get(i).getBodySideOneId()));
            String[] blood = bodyGetDataLists.get(i).getBloodPressure().split("_");
            oneData.setBlowPressure(blood[0]);
            oneData.setHighPressure(blood[1]);
            oneData.setHeights(bodyGetDataLists.get(i).getHeight());
            oneData.setWeight(bodyGetDataLists.get(i).getWeight());
            oneData.setInBody(bodyGetDataLists.get(i).getInBody());
            listModels.add(oneData);

        }

        AsyncLoaderImage ali = new AsyncLoaderImage();
                ali.loadBitmap(ConstantYeaPao.HOST + bodyGetLists.get(0).getPresentation(), new AsyncLoaderImage.ImageCallback() {
                    @Override
                    public void imageLoaded(Bitmap imageBitmap, String imageUrl) {
                        listModels.get(0).setImageFile(new File(imageUrl));
                        notifyItemChanged(0);
                    }
                });
        ali.loadBitmap(ConstantYeaPao.HOST + bodyGetLists.get(1).getPresentation(), new AsyncLoaderImage.ImageCallback() {
            @Override
            public void imageLoaded(Bitmap imageBitmap, String imageUrl) {
                listModels.get(1).setImageFile(new File(imageUrl));
                notifyItemChanged(1);
            }
        });

    }


    public PhysicalTestMessageAdapter(Context context, List<BodySideListModel.DataBean.BodySideUserOutBean> bodySideUserOutBeen) {

        mContext = context;
        inflater = LayoutInflater.from(context);
        this.bodySideUserOutBeenList = bodySideUserOutBeen;

        for (int i = 0; i < 2; i++) {
            BodySideOneData oneData = new BodySideOneData();
            oneData.setQuietHeartRate("0");
            oneData.setBloodPressure("0");
            oneData.setHeights("0");
            oneData.setWeight("0");
            oneData.setInBody("0");
            oneData.setScheduled("0");
            oneData.setCustomerId("0");
            oneData.setBodySideOne("0");
            oneData.setBlowPressure("0");
            oneData.setHighPressure("0");
            oneData.setImageFile(null);
            oneData.setCustomerId(String.valueOf(bodySideUserOutBeenList.get(i).getCustomerId()));
            listModels.add(oneData);
        }

    }

    public void setPhysicalImageListener(PhysicalImageListener listener) {
        if (listener != null) {
            physicalImageListener = listener;
        }
    }


    public void refreshImage(int position,File imageFile) {
        listModels.get(position).setImageFile(imageFile);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OpenViewHolder(inflater.inflate(R.layout.item_physical_first, parent, false),physicalImageListener);
    }

    @TargetApi(19)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GlideUtil glideUtil = new GlideUtil();
        if (holder instanceof OpenViewHolder) {
            LogUtil.e("image----1",bodyGetLists.get(position).getPresentation());

            if (haveData) {
                ((OpenViewHolder) holder).etHeart.setText(listModels.get(position).getQuietHeartRate());
                ((OpenViewHolder) holder).etHigh.setText(listModels.get(position).getHeights());
                ((OpenViewHolder) holder).etWeight.setText(listModels.get(position).getWeight());
                ((OpenViewHolder) holder).etInbody.setText(listModels.get(position).getInBody());
//                String[] bloods = bodyGetLists.get(position).getBloodPressure().split("_");
//                ((OpenViewHolder) holder).etBlowPressure.setText(bloods[0]);
//                ((OpenViewHolder) holder).etHighPressure.setText(bloods[1]);
                ((OpenViewHolder) holder).etBlowPressure.setText(listModels.get(position).getBlowPressure());
                ((OpenViewHolder) holder).etHighPressure.setText(listModels.get(position).getHighPressure());


                if (listModels.get(position).getImageFile() == null) {
                    glideUtil.glideLoadingImage(mContext,
                        ConstantYeaPao.HOST+bodyGetLists.get(position).getPresentation(),R.drawable.first_step_img,
                        ((OpenViewHolder) holder).ivTakePhoto);
                }

//
//                glideUtil.glideLoadingImage(mContext,
//                        ConstantYeaPao.HOST+bodyGetLists.get(position).getPresentation(),R.drawable.first_step_img,
//                        ((OpenViewHolder) holder).ivTakePhoto);
//                AsyncLoaderImage ali = new AsyncLoaderImage();
//                ali.loadBitmap(ConstantYeaPao.HOST + bodyGetLists.get(position).getPresentation(), new AsyncLoaderImage.ImageCallback() {
//                    @Override
//                    public void imageLoaded(Bitmap imageBitmap, String imageUrl) {
//                        listModels.get(position).setImageFile(new File(imageUrl));
//                    }
//                });

            }



            if (position == 0) {
                ((OpenViewHolder) holder).etHeart.requestFocus();
            }


            glideUtil.glideLoadingImage(mContext, ConstantYeaPao.HOST + bodySideUserOutBeenList.get(position).getHead(),
                    R.drawable.y_you, ((OpenViewHolder) holder).ivHead);

            ((OpenViewHolder) holder).tvAccountName.setText(bodySideUserOutBeenList.get(position).getUserName());
            if (bodySideUserOutBeenList.get(position).getGender().equals("男")) {
                ((OpenViewHolder) holder).ivGender.setImageDrawable(mContext.getResources().getDrawable(R.drawable.boy));
            } else {
                ((OpenViewHolder) holder).ivGender.setImageDrawable(mContext.getResources().getDrawable(R.drawable.girl));
            }

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

            if (listModels.get(position).getImageFile() != null) {
                try {
                    FileInputStream fis = new FileInputStream(listModels.get(position).getImageFile());
                    ((OpenViewHolder) holder).ivTakePhoto.setImageBitmap(BitmapFactory.decodeStream(fis));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    public List<BodySideOneData> getUserData() {
            LogUtil.e(TAG,"get");
            return listModels;

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

        private PhysicalImageListener listener;

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

        OpenViewHolder(View view,PhysicalImageListener listener) {

            super(view);
            ButterKnife.bind(this, view);
            initView();
            this.listener = listener;

        }

        private void initView() {
            etHeart.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    LogUtil.e("afterTextChanged",s.toString());
                    listModels.get(getLayoutPosition()).setQuietHeartRate(s.toString());
                }
            });
            etHigh.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    listModels.get(getLayoutPosition()).setHeights(s.toString());
                }
            });
            etWeight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    listModels.get(getLayoutPosition()).setWeight(s.toString());

                }
            });
            etBlowPressure.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    listModels.get(getLayoutPosition()).setBlowPressure(s.toString());

                }
            });
            etHighPressure.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    listModels.get(getLayoutPosition()).setHighPressure(s.toString());
                }
            });
            etInbody.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    listModels.get(getLayoutPosition()).setInBody(s.toString());

                }
            });
            ivTakePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.takePhoto(getLayoutPosition());
                }
            });
        }


    }


}
