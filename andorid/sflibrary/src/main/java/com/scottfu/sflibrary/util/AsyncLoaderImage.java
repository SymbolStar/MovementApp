package com.scottfu.sflibrary.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by fujindong on 2017/8/8.
 */

@SuppressLint("SdCardPath")
public class AsyncLoaderImage {

    private static String TAG = "AsyncLoaderImage";
    private static HashMap<String, SoftReference<Bitmap>> bitmapCache;

    private static String WHOLESALE_CONV = null;
    private static final int mTimeDiff = 5 * 24 * 60 * 60 * 1000;
    private static final int FREE_SD_SPACE_NEEDED_TO_CACHE = 50;
    private static final int CACHE_SIZE = 1024 * 1024;

    private String localPath="";

    public AsyncLoaderImage() {
//    	WHOLESALE_CONV = "/mnt/sdcard/Yxt/cache";
        WHOLESALE_CONV = FileUtil.IMAGE_PATH;


        if (bitmapCache == null) {
            bitmapCache = new HashMap<String, SoftReference<Bitmap>>();
        }
    }

    public AsyncLoaderImage(String path){
        WHOLESALE_CONV = path;
        if (bitmapCache == null) {
            bitmapCache = new HashMap<String, SoftReference<Bitmap>>();
        }
    }

    /**
     * 先从软引用中获取图片未取到则从SDCard上取
     *
     * @param url
     * @param imageCallback
     * @return
     */
    public  void loadBitmap(final String url, final ImageCallback imageCallback)
    {
        Bitmap bitmap;
        String filename = convertUrlToFileName(url);
        localPath = WHOLESALE_CONV + "/" + filename;
        /**
         * 从软引用中获取图片
         */
        if (bitmapCache.containsKey(url))
        {
            SoftReference<Bitmap> softReference = bitmapCache.get(url);
            bitmap = softReference.get();
            if (bitmap != null)
            {
                imageCallback.imageLoaded(bitmap, localPath);
                return;
            }
        }
        /**
         * 从SD卡中获取图片
         */
        bitmap = getBitmapFromSD(url);
        if (bitmap != null)
        {
            /**
             * 取到后放入软引用中
             */
            bitmapCache.put(url, new SoftReference<Bitmap>(bitmap));
            imageCallback.imageLoaded(bitmap, localPath);
            return;
        }

        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                imageCallback.imageLoaded((Bitmap) msg.obj, localPath);
            }
        };
        /**
         * 都未取到则开启线程下载图片
         */
        new Thread() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap = DownLoadBitmap(url);
                    if (bitmap != null) {
                        /**
                         * 下载完成后入软引用中
                         */
                        bitmapCache.put(url, new SoftReference<Bitmap>(bitmap));
                        /**
                         * 将图片保存到本地
                         */
                        saveBmpToSd(bitmap, url);
                        handler.obtainMessage(0, bitmap).sendToTarget();
                    }
                } catch (OutOfMemoryError error) {
                    Log.i(TAG, error.toString());
                    System.gc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public interface ImageCallback {
        public void imageLoaded(Bitmap imageBitmap, String imageUrl);
    }

    /**
     * 线程中调用此方法加载图片（防内存溢出）
     *
     * @param path
     * @return
     * @throws IOException
     */
    public Bitmap DownLoadBitmap(String url)
            throws MalformedURLException, IOException {
        Bitmap bmp = null;
        InputStream stream = new URL(url).openStream();
        System.out.println("从网络加载图片："+url);
        byte[] bytes = getBytes(stream);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
        opts.inSampleSize = calculateInSampleSize(opts, 480, 800);
        opts.inJustDecodeBounds = false;
        System.out.println("图片缩小倍数："+opts.inSampleSize);

        bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
        System.out.println("图片加载完成！");

        return bmp;
    }

    /**
     * 将图片流转化为byte数组
     * @param is
     * @return
     */
    private byte[] getBytes(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[2048];
        int len = 0;
        try {
            while ((len = is.read(b, 0, 2048)) != -1) {
                baos.write(b, 0, len);
                baos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    /**
     * 计算缩小倍数
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float)height / (float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width / (float)reqWidth);
            }
        }
        return inSampleSize;
    }

    /**
     * 从sd卡中读取文件
     * @param url
     * @return
     */
    public Bitmap getBitmapFromSD(String url)
    {
        Bitmap bmp = null;
        //判SD卡中是否存在文件
        String filename = convertUrlToFileName(url);
        String dir = getDirectory(filename);
        File file = new File(dir + "/" + filename);
        if (file.exists())
        {
            removeExpiredCache(dir, filename);
            updateFileTime(dir, filename);
            bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
            if (bmp != null)
            {
                return bmp;
            }
        }
        return null;

    }
    private String convertUrlToFileName(String url) {
        int lastIndex = url.lastIndexOf('/');
        return url.substring(lastIndex + 1);
    }
    private String getDirectory(String filename) {
        return WHOLESALE_CONV;
    }
    private void removeExpiredCache(String dirPath, String filename) {

        File file = new File(dirPath, filename);

        if (System.currentTimeMillis() - file.lastModified() > mTimeDiff) {

            System.out.println("删除过期文件：" + file.getAbsolutePath());
            file.delete();
        }
    }
    private void updateFileTime(String dir, String fileName) {
        File file = new File(dir, fileName);
        long newModifiedTime = System.currentTimeMillis();
//		System.out.println("修改文件更新时间：" + file.getAbsolutePath());
        file.setLastModified(newModifiedTime);
    }
    /**
     * 保存文件至SD卡
     * @param bm
     * @param url
     */
    private void saveBmpToSd(Bitmap bm, String url) {
        if (bm == null) {
            Log.w(TAG, " trying to savenull bitmap");
            return;
        }
        // 判断sdcard上的空间
        if (FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd())
        {
            Log.w(TAG, "Low free space onsd, do not cache");
            removeCache(WHOLESALE_CONV);
            return;
        }
        String filename = convertUrlToFileName(url);
        String dir = getDirectory(filename);
        File file = new File(dir + "/" + filename);

//		System.out.println("在sd卡上创建文件："+dir + "/" + filename);
        try {
            if (!file.exists())
            {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            OutputStream outStream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            Log.i(TAG, "Image saved tosd");

        } catch (FileNotFoundException e) {
            Log.w(TAG, "FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int freeSpaceOnSd() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
                .getPath());
        double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
                .getBlockSize()) / 1024*1024;
        return (int) sdFreeMB;
    }
    private void removeCache(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        int dirSize = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().contains(WHOLESALE_CONV)) {
                dirSize += files[i].length();
            }
        }
        if (dirSize > CACHE_SIZE * 1024*1024
                || FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()) {
            int removeFactor = (int) ((0.4 * files.length) + 1);

            Arrays.sort(files, new FileLastModifSort());

            Log.i(TAG, "Clear some expiredcache files ");

            for (int i = 0; i < removeFactor; i++) {

                if (files[i].getName().contains(WHOLESALE_CONV)) {

                    files[i].delete();

                }

            }

        }

    }
    /**
     * 根据文件的最后修改时间进行排序 *
     */
    class FileLastModifSort implements Comparator<File> {
        public int compare(File arg0, File arg1) {
            if (arg0.lastModified() > arg1.lastModified()) {
                return 1;
            } else if (arg0.lastModified() == arg1.lastModified()) {
                return 0;
            } else {
                return -1;
            }
        }
    }


}
