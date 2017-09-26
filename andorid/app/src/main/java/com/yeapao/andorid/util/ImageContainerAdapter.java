package com.yeapao.andorid.util;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.image.ImageFileUtils;
import com.scottfu.sflibrary.util.BitmapCompressV2;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fujindong on 2017/8/1.
 */

public class ImageContainerAdapter extends BaseAdapter {


    private static final String TAG = "ChooseFoodContainerAdapter";


    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mImageArrayList = new ArrayList<>();
    private List<Uri> uris;
    private ImageDeleteListener mListener;

    private List<File> imageFiles;

    public interface ImageDeleteListener {
        void deleteListener(int position);
    }

    public void setImageDeleteListener(ImageDeleteListener listener) {
        mListener = listener;
    }
//
//    public ImageContainerAdapter(Context context ,List<Uri> urlLists) {
//        flag = true;
//        mContext = context;
//        mInflater = LayoutInflater.from(context);
//        uris = urlLists;
//
//    }

    public ImageContainerAdapter(Context context ,List<File> files) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        imageFiles = files;
    }

    @Override
    public int getCount() {
        return imageFiles.size();
    }

    @Override
    public Object getItem(int position) {
        return imageFiles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_choose_image, null);
            viewHolder = new ViewHolder();
            viewHolder.contentImage = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.deleteImage = (ImageView) convertView.findViewById(R.id.iv_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LogUtil.e("ImagePath",imageFiles.get(position).getAbsolutePath());

            try {
                FileInputStream fis = new FileInputStream(imageFiles.get(position));
                viewHolder.contentImage.setImageBitmap(BitmapFactory.decodeStream(fis));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            viewHolder.deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.deleteListener(position);
                }
            });
//            viewHolder.contentImage.setImageBitmap(ImageFileUtils.getBitmapFromUri(mContext, uris.get(position)));

        return convertView;
    }

    class ViewHolder {
        ImageView contentImage;
        ImageView deleteImage;

    }


}
