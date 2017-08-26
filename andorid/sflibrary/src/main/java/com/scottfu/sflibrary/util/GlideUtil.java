package com.scottfu.sflibrary.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by fujindong on 2017/7/22.
 */

public class GlideUtil {

    public void glideLoadingImage(Context context, String url, int initImageId, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .placeholder(initImageId)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(initImageId)
                    .centerCrop()
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
