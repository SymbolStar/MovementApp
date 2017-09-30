package com.yeapao.andorid.photo;

import android.support.v7.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;


import com.scottfu.sflibrary.util.AsyncLoaderImage;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.dialog.DialogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import uk.co.senab.photoview.PhotoView;
/**
 * Created by fujindong on 2017/9/29.
 */

public class PhotoViewPagerActivity extends AppCompatActivity {

    private static final String TAG = "PhotoViewPagerActivity";

    public static final String PATH = "imagePath";
    public static final String NAME = "imageName";
    public static final String TYPE = "type";
    public static final String NAME_LIST = "imageList";
    public static final String CURRENT_ID = "currentId";

    //	private String path = "";
    private String type = "";

    private SamplePagerAdapter mSamplePagerAdapter;

    private ViewPager mViewPager;
    /**
     * 传图片地址 imagePath
     *
     * @param context
     * @param imagePath
     */
    public static void start(Context context, String imagePath) {

        Intent intent = new Intent(context, PhotoViewPagerActivity.class);
        intent.putExtra(PATH, imagePath);
        intent.putExtra(TYPE, PATH);
        context.startActivity(intent);
    }

    /**
     * context imageName
     * @param context
     * @param imageName
     */
    public static void startToImageName(Context context, String imageName) {
        Intent intent = new Intent(context, PhotoViewPagerActivity.class);
        intent.putExtra(NAME, imageName);
        intent.putExtra(TYPE, NAME);
        context.startActivity(intent);

    }

    /**
     * context ArrayList<String>
     * @param context
     * @param images
     */
    public static void startToImageList(Context context, ArrayList<String> images,int currentId) {
        Intent intent = new Intent(context, PhotoViewPagerActivity.class);
        intent.putStringArrayListExtra(NAME_LIST, images);
        intent.putExtra(TYPE, NAME_LIST);
        intent.putExtra(CURRENT_ID, String.valueOf(currentId));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);
        mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
        Intent intent = getIntent();
        type = intent.getStringExtra(TYPE);
        showImage();



    }



    protected Context getContext() {
        return this;
    }

    private void showImage() {

        if (type.equals(PATH)) {
            String	path = getIntent().getStringExtra(PATH);
            mSamplePagerAdapter = new SamplePagerAdapter(path);
            mViewPager.setAdapter(mSamplePagerAdapter);
            mViewPager.setCurrentItem(getCurrentItem(path));
        }else if (type.equals(NAME)) {
//            final String name = getIntent().getStringExtra(NAME);
//            AsyncLoaderImage ali = new AsyncLoaderImage();
//            ali.loadBitmap(ConstantYXT.REMOTE_URL_Original + name, new AsyncLoaderImage.ImageCallback() {
//                @Override
//                public void imageLoaded(final Bitmap imageBitmap, final String imageLocalPath) {
//                    mSamplePagerAdapter = new SamplePagerAdapter(imageLocalPath);
//                    mViewPager.setAdapter(mSamplePagerAdapter);
//                    mViewPager.setCurrentItem(getCurrentItem(imageLocalPath));
//                }
//            });
        }else if (type.equals(NAME_LIST)) {
            ArrayList<String> images = getIntent().getStringArrayListExtra(NAME_LIST);
            int currentId = Integer.valueOf(getIntent().getStringExtra(CURRENT_ID));
            mSamplePagerAdapter = new SamplePagerAdapter(images);
            mViewPager.setAdapter(mSamplePagerAdapter);
            mViewPager.setCurrentItem(currentId);
        }

    }

    private int getCurrentItem(String path) {
        int mCurrentItem = 0;
        File photo = new File(path);
        File photoFile = new File(photo.getParent());
        String[] allFile = photoFile.list();
        String photoName = null;
        photoName = path.replace(photo.getParent().toString() + "/", "");
        LogUtil.e(TAG, "接收到的图片名：" + photoName);
        for (int i = 0; i < allFile.length; i++) {
            if (photoName.equals(allFile[i])) {
                mCurrentItem = i;
                break;
            }
            LogUtil.e(TAG, "allFiles-Name=" + allFile[i]);
        }
        return mCurrentItem;
    }


    class SamplePagerAdapter extends PagerAdapter {

        private File photoPath = null;
        private File photoFile = null;
        private String[] allFiles = null;
        private ArrayList<String> images = new ArrayList<String>();
        private String stringPhotoName = "";

        public SamplePagerAdapter(String path) {
            photoPath = new File(path);
            LogUtil.e(TAG, "当前图片的path=" + path);
            if (type.equals(NAME)) {
                String	photoName = path.replace(photoPath.getParent().toString() + "/", "");
                String[] files = {photoName};
                allFiles = files;
            } else {
                File file = new File(photoPath.getParent());
                allFiles = file.list();
            }
        }

        public SamplePagerAdapter(ArrayList<String> images) {
            this.images = images;
        }

        @Override
        public int getCount() {
            if (type.equals(NAME_LIST)) {
                return images.size();
            }
            return allFiles.length;
        }


        /**
         * 设置当前item需要做什么处理 在instantiateItem 之后
         */
        @Override
        public void setPrimaryItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            LogUtil.e(TAG, "setPrimaryItem当前的position = " + position);
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final PhotoView photoView = new PhotoView(container.getContext());
            if (type.equals(NAME_LIST)) {
                AsyncLoaderImage ali = new AsyncLoaderImage();
                DialogUtils.showProgressDialog(getContext());
                ali.loadBitmap(images.get(position), new AsyncLoaderImage.ImageCallback() {
                    @Override
                    public void imageLoaded(final Bitmap imageBitmap, final String imageLocalPath) {
                        MediaScannerConnection.scanFile(getContext(),new String[]{imageLocalPath},null,null);
                        DialogUtils.cancelProgressDialog();
                        photoView.setImageBitmap(imageBitmap);
                        stringPhotoName = imageLocalPath;
                    }
                });
            } else {
                //			photoView.setImageResource(); //在photoView.class查看，有几种方式
                stringPhotoName = photoPath.getParent().toString() + "/" + allFiles[position];
                photoFile = new File(stringPhotoName);
                LogUtil.e(TAG, "photoName=" + photoFile.toString());
                photoView.setImageURI(Uri.fromFile(photoFile));
            }




            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

//            photoView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    final CustomAlertDialog alertDialog = new CustomAlertDialog(YXTPhotoViewPagerActivity.this);
//                    String title = getString(com.netease.nim.uikit.R.string.save_to_device);
//                    alertDialog.addItem(title, new CustomAlertDialog.onSeparateItemClickListener() {
//
//                        @Override
//                        public void onClick() {
//                            saveIamge(stringPhotoName);
//                        }
//                    });
//
//                    alertDialog.show();
//                    return false;
//                }
//            });

            return photoView;
        }


//        private void saveIamge(String path) {
//            //						String path = ConstantYXT.ALBUM_PATH;
//            String photoName = DateFormat.format("yyyyMMddhhmmss", new Date()).toString() + ".jpg";
//            String dstPath = ConstantYXT.ALBUM_PATH + photoName;
//            if (AttachmentStore.copy(path, dstPath) != -1) {
//                try {
//                    ContentValues values = new ContentValues(2);
//                    values.put(MediaStore.Images.Media.MIME_TYPE, C.MimeType.MIME_JPEG);
//                    values.put(MediaStore.Images.Media.DATA, dstPath);
//                    getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//                    Toast.makeText(YXTPhotoViewPagerActivity.this, getString(com.netease.nim.uikit.R.string.picture_save_to), Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//                    // may be java.lang.UnsupportedOperationException
//                    Toast.makeText(YXTPhotoViewPagerActivity.this, getString(com.netease.nim.uikit.R.string.picture_save_fail), Toast.LENGTH_LONG).show();
//                }
//            } else {
//                Toast.makeText(YXTPhotoViewPagerActivity.this, getString(com.netease.nim.uikit.R.string.picture_save_fail), Toast.LENGTH_LONG).show();
//            }
//        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
