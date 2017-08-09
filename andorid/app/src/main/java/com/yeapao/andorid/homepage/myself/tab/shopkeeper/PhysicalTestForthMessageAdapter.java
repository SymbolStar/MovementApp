package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
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
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.model.BodySideForthSaveModel;
import com.yeapao.andorid.model.BodySideListModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

    private PhysicalForthTakeImageListener physicalImageListener;

    private List<BodySideListModel.DataBean.BodySideUserOutBean> bodySideUserOutBeenList;

    private List<BodySideForthSaveModel> bodySideForthSaveModels = new ArrayList<>();

    public PhysicalTestForthMessageAdapter(Context context,List<BodySideListModel.DataBean.BodySideUserOutBean> bodySideUserOutBeen,
                                           List<BodySideForthSaveModel> bodySideForthSaveModels) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        bodySideUserOutBeenList = bodySideUserOutBeen;
        this.bodySideForthSaveModels = bodySideForthSaveModels;
    }

    public void setImageClickListener(PhysicalForthTakeImageListener physicalImageListener) {
        if (physicalImageListener != null) {
            this.physicalImageListener = physicalImageListener;
        }
    }

    public List<BodySideForthSaveModel> getBodySideForthData() {
        return bodySideForthSaveModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ForthViewHolder(inflater.inflate(R.layout.item_physical_forth, parent, false),physicalImageListener);
    }
    @TargetApi(19)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        GlideUtil glideUtil = new GlideUtil();
//        TODO 图片的载入框架后续添加

        if (holder instanceof ForthViewHolder) {


            if (bodySideForthSaveModels.get(position).getPositive() != null) {
                try {
                    FileInputStream fis = new FileInputStream(bodySideForthSaveModels.get(position).getPositive());
                    ((ForthViewHolder) holder).ivPhysicalPositive.setImageBitmap(BitmapFactory.decodeStream(fis));
                    ((ForthViewHolder) holder).ivShopplerDelete1.setVisibility(View.VISIBLE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                ((ForthViewHolder) holder).ivShopplerDelete1.setVisibility(View.GONE);
                ((ForthViewHolder) holder).ivPhysicalPositive.setImageDrawable(mContext.getResources().getDrawable(R.drawable.shopkeeper_uploadimg));
            }

            if (bodySideForthSaveModels.get(position).getSide() != null) {
                try {
                    FileInputStream fis = new FileInputStream(bodySideForthSaveModels.get(position).getSide());
                    ((ForthViewHolder) holder).ivPhysicalPost.setImageBitmap(BitmapFactory.decodeStream(fis));
                    ((ForthViewHolder) holder).ivShopplerDelete2.setVisibility(View.VISIBLE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                ((ForthViewHolder) holder).ivShopplerDelete2.setVisibility(View.GONE);
                ((ForthViewHolder) holder).ivPhysicalPost.setImageDrawable(mContext.getResources().getDrawable(R.drawable.shopkeeper_uploadimg));
            }

            if (bodySideForthSaveModels.get(position).getBack() != null) {
                try {
                    FileInputStream fis = new FileInputStream(bodySideForthSaveModels.get(position).getBack());
                    ((ForthViewHolder) holder).ivPhysicalBack.setImageBitmap(BitmapFactory.decodeStream(fis));
                    ((ForthViewHolder) holder).ivShopplerDelete3.setVisibility(View.VISIBLE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                ((ForthViewHolder) holder).ivShopplerDelete3.setVisibility(View.GONE);
                ((ForthViewHolder) holder).ivPhysicalBack.setImageDrawable(mContext.getResources().getDrawable(R.drawable.shopkeeper_uploadimg));
            }

            if (bodySideForthSaveModels.get(position).getFurredTongue() != null) {
                try {
                    FileInputStream fis = new FileInputStream(bodySideForthSaveModels.get(position).getFurredTongue());
                    ((ForthViewHolder) holder).ivPhysicalTongue.setImageBitmap(BitmapFactory.decodeStream(fis));
                    ((ForthViewHolder) holder).ivShopplerDelete4.setVisibility(View.VISIBLE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                ((ForthViewHolder) holder).ivShopplerDelete4.setVisibility(View.GONE);
                ((ForthViewHolder) holder).ivPhysicalTongue.setImageDrawable(mContext.getResources().getDrawable(R.drawable.shopkeeper_uploadimg));
            }





            glideUtil.glideLoadingImage(mContext, ConstantYeaPao.HOST + bodySideUserOutBeenList.get(position).getHead(),
                    R.drawable.y_you, ((ForthViewHolder) holder).ivHead);

            ((ForthViewHolder) holder).tvAccountName.setText(bodySideUserOutBeenList.get(position).getUserName());
            if (bodySideUserOutBeenList.get(position).getGender().equals("男")) {
                ((ForthViewHolder) holder).ivGender.setImageDrawable(mContext.getResources().getDrawable(R.drawable.boy));
            } else {
                ((ForthViewHolder) holder).ivGender.setImageDrawable(mContext.getResources().getDrawable(R.drawable.girl));
            }



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

    public void refreshImage(int imagePosition, int imageKind, File imageFile) {
        switch (imageKind) {
            case 1:
                bodySideForthSaveModels.get(imagePosition).setPositive(imageFile);
                break;
            case 2:
                bodySideForthSaveModels.get(imagePosition).setSide(imageFile);
                break;
            case 3:
                bodySideForthSaveModels.get(imagePosition).setBack(imageFile);
                break;
            case 4:
                bodySideForthSaveModels.get(imagePosition).setFurredTongue(imageFile);
                break;

        }
        notifyDataSetChanged();
    }


    class ForthViewHolder extends RecyclerView.ViewHolder {


         private PhysicalForthTakeImageListener listener;


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



        ForthViewHolder(View view,PhysicalForthTakeImageListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            intView();
        }

        private void intView() {

            ivPhysicalPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.takephoto(getLayoutPosition(),1);
                }
            });
            ivPhysicalPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.takephoto(getLayoutPosition(),2);
                }
            });
            ivPhysicalBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.takephoto(getLayoutPosition(),3);
                }
            });
            ivPhysicalTongue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.takephoto(getLayoutPosition(),4);
                }
            });

            ivShopplerDelete1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bodySideForthSaveModels.get(getLayoutPosition()).setPositive(null);
                    notifyDataSetChanged();
                }
            });

            ivShopplerDelete2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bodySideForthSaveModels.get(getLayoutPosition()).setSide(null);
                    notifyDataSetChanged();
                }
            });

            ivShopplerDelete3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bodySideForthSaveModels.get(getLayoutPosition()).setBack(null);
                    notifyDataSetChanged();
                }
            });

            ivShopplerDelete4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bodySideForthSaveModels.get(getLayoutPosition()).setFurredTongue(null);
                    notifyDataSetChanged();
                }
            });


        }
    }
}
