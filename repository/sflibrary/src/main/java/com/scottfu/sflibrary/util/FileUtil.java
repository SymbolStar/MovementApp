package com.scottfu.sflibrary.util;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class FileUtil {

    private static final String TAG = FileUtil.class.getSimpleName();

    public static String IMAGE_PATH = null;
    public static String AUDIO_PATH = null;
    public static String VIDEO_PATH = null;

    public static void initFilePath(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            IMAGE_PATH = getStorageDir(context, "image").getAbsolutePath();
            AUDIO_PATH = getStorageDir(context, "audio").getAbsolutePath();
            VIDEO_PATH = getStorageDir(context, "video").getAbsolutePath();
        } else {
            ToastManager.showToast(context,"SD卡无效");
        }
    }

    private static File getStorageDir(Context context, String fileName) {
        File file = context.getExternalFilesDir(fileName);
        if(!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }

	/** 
     * 文件转化为字节数组 
     *  
     * @param file 
     * @return 
     */  
    public static byte[] getBytesFromFile(File file) {
        byte[] ret = null;  
        try {  
            if (file == null) {  
                return null;  
            }  
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
            byte[] b = new byte[4096];  
            int n;  
            while ((n = in.read(b)) != -1) {  
                out.write(b, 0, n);  
            }  
            in.close();  
            out.close();  
            ret = out.toByteArray();  
        } catch (IOException e) {
            // log.error("helper:get bytes from file process error!");  
            e.printStackTrace();  
        }  
        return ret;  
    }

    /**
     * 获取文件大小
     * @param file
     * @return
     * @throws Exception
     */
    public static long getSingleFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在！");
        }
        return size;
    }

    /**
     * 获取指定文件夹大小
     * @param file
     * @return
     * @throws Exception
     */
    @Deprecated
    public static long getFileSizes(File file) throws Exception {
        long size = 0;
        File fileList[] = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {

            if (fileList[i].isDirectory()) {
                size = size + getFileSizes(fileList[i]);
            } else {
                size = size + getSingleFileSize(fileList[i]);
            }
        }
        return size;
    }

    /**
     * 获取文件夹大小
     * 时间上比getFileSizes 短
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }


    /**
     * 转换文件大小
     * @param fileSize
     * @return
     */
    public static String formatFileSize(long fileSize) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileSize == 0) {
            return wrongSize;
        }
        if (fileSize < 1024) {
            fileSizeString = decimalFormat.format(fileSize) + "B";
        }else if (fileSize < 1048576) {
            fileSizeString = decimalFormat.format((double) fileSize / 1024) + "KB";
        }else if (fileSize < 1073741824) {
            fileSizeString = decimalFormat.format((double) fileSize / 1048576) + "MB";
        } else {
            fileSizeString = decimalFormat.format((double) fileSize / 1073741824) + "GB";
        }
        return fileSizeString;
    }



    public static boolean deleteDir(Context context, File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(context,new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            boolean result = dir.delete();
            MediaScannerConnection.scanFile(context,new String[]{dir.getAbsolutePath()},null,null);
            return result;
        } else {
            return false;
        }
    }

}
