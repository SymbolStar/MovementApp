package com.yeapao.andorid.homepage.circle;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.model.CircleListModel;
import com.yeapao.andorid.photo.PhotoViewPagerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by fujindong on 2017/9/21.
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ImageRecyclerAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    private GlideUtil glideUtil = new GlideUtil();


    private List<CircleListModel.DataBean.CommunityListBean.imagesUrl> imagesUrlList = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();


    public ImageRecyclerAdapter(Context context, List<CircleListModel.DataBean.CommunityListBean.imagesUrl> list) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        imagesUrlList = list;
        for (int i = 0; i < imagesUrlList.size(); i++) {
            images.add(imagesUrlList.get(i).getImageUrl());
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_circle_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (imagesUrlList.size() == 1) {
            ((ViewHolder) holder).cvImageCard.setLayoutParams(new ViewGroup.LayoutParams(ScreenUtil.dpToPxInt(mContext, 225), ScreenUtil.dpToPxInt(mContext, 130)));
        } else if (imagesUrlList.size() >= 2) {
            ((ViewHolder) holder).cvImageCard.setLayoutParams(new ViewGroup.LayoutParams(ScreenUtil.dpToPxInt(mContext, 100), ScreenUtil.dpToPxInt(mContext, 85)));
        }

        ((ViewHolder) holder).cvImageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleFragmentView.isPhotoPreView = false;
                PhotoViewPagerActivity.startToImageList(mContext,images,position);
            }
        });

        glideUtil.glideLoadingImage(mContext,imagesUrlList.get(position).getImageUrl(),R.drawable.home_banner_take_place,((ViewHolder) holder).ivCircleImage);
    }

    @Override
    public int getItemCount() {
        return imagesUrlList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_circle_image)
        ImageView ivCircleImage;
        @BindView(R.id.cv_image_card)
        CardView cvImageCard;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
