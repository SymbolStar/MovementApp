package com.scottfu.sflibrary.util;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;

import id.zelory.compressor.Compressor;

/**
 * Created by fujindong on 2017/8/2.
 */

public class BitmapCompressV2 {


    private static final String PIC_CACHE_PATH = FileUtil.IMAGE_PATH;


    public static File getCompressImageFile(Context context, File imageFile) {
        long fileSize = 0;
        try {
            fileSize = FileUtil.getSingleFileSize(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fileSize > 1048576) {//如果图片大于1MB
            File compressFile = new Compressor.Builder(context)
                    .setMaxWidth(720)
                    .setMaxHeight(1080)
                    .setQuality(100)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setDestinationDirectoryPath(PIC_CACHE_PATH)
                    .build()
                    .compressToFile(imageFile);
            return compressFile;
        } else {
            return imageFile;
        }
    }

    /**
     * 用于预览图片的压缩显示 提高显示质量 minSDk 14
     * @param context
     * @param imageFile
     * @return
     */
    public static File getCompressImageAttrWEBP(Context context, File imageFile) {
        long fileSize = 0;
        try {
            fileSize = FileUtil.getSingleFileSize(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fileSize > 1048576) {//如果图片大于1MB
            File compressFile = new Compressor.Builder(context)
                    .setMaxWidth(720)
                    .setMaxHeight(1080)
                    .setQuality(100)
                    .setCompressFormat(Bitmap.CompressFormat.WEBP)
                    .setDestinationDirectoryPath(PIC_CACHE_PATH)
                    .build()
                    .compressToFile(imageFile);
            return compressFile;
        } else {
            return imageFile;
        }
    }
}
