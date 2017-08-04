package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.scottfu.sflibrary.image.GifSizeFilter;
import com.scottfu.sflibrary.image.ImageFileUtils;
import com.scottfu.sflibrary.util.BitmapCompressV2;
import com.scottfu.sflibrary.util.FileUtil;
import com.scottfu.sflibrary.view.HeightGirdView;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.util.ImageContainerAdapter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/23.
 */

public class MyselfClockOutActivity extends BaseActivity {

    private static final String TAG = "MyselfClockOutActivity";
    private static final int REQUEST_CODE_CHOOSE = 23;

    private Bitmap.Config mConfig = Bitmap.Config.ARGB_8888;

    private ImageContainerAdapter containerAdapter;

    List<Uri> mSelected;
    private ArrayList<File> mImageArrayList = new ArrayList<>();

    @BindView(R.id.tv_breakfest)
    TextView tvBreakfest;
    @BindView(R.id.tv_lunch)
    TextView tvLunch;
    @BindView(R.id.tv_dinner)
    TextView tvDinner;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_clock_out_content)
    EditText tvClockOutContent;
    @BindView(R.id.rb_admin)
    RadioButton rbAdmin;
    @BindView(R.id.iv_choose_image)
    ImageView ivChooseImage;
    @BindView(R.id.gv_image_list)
    HeightGirdView gvImageList;



    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfClockOutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//初始隐藏键盘
        setContentView(R.layout.activity_clock_out);
        ButterKnife.bind(this);
        initTopBar();
//        containerAdapter = new ImageContainerAdapter(getContext());
//        gvImageList.setAdapter(containerAdapter);
    }

    @Override
    protected void initTopBar() {
        initTitle("打卡");
        initBack();
        initRightText("发布");
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//
//        Uri uri = data.getData();
//        ContentResolver contentResolver = this.getContentResolver();
//        Cursor cursor = contentResolver.query(uri, null, null, null, null);
//        cursor.moveToFirst();
//        String localPicPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//        LogUtil.e("qqqq",localPicPath);
//        cursor.close();
//
//            try {
//                FileInputStream fis = new FileInputStream(new File(localPicPath));
//                ivChooseImage.setImageBitmap(BitmapFactory.decodeStream(fis));
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }




        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);

//            File file = new File(ImageFileUtils.getRealFilePath(getContext(), mSelected.get(0))) ;
//            try {
//                FileInputStream fileInputStream = new FileInputStream(file);
//                ivChooseImage.setImageBitmap(BitmapFactory.decodeStream(fileInputStream));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
            compressImage();

//            ivChooseImage.setImageBitmap(ImageFileUtils.getBitmapFromUri(getContext(),mSelected.get(0)));








//            String path = ImageFileUtils.getPhotoPathFromContentUri(getContext(), mSelected.get(0));

//            String path = ImageFileUtils.getRealFilePath(getContext(), mSelected.get(0));

//            imagePathList = data.getStringArrayListExtra("extra_result_selection_path");
//            LogUtil.e("1111",path);
    /*
            Log.e("Matisse", "mSelected: " + mSelected);
            Bitmap bb = ImageFileUtils.getBitmapFromUri(getContext(), mSelected.get(0));



            Tiny.FileCompressOptions compressOptions = new Tiny.FileCompressOptions();
            compressOptions.config = mConfig;

            Tiny.getInstance().source(bb).batchAsFile().withOptions(compressOptions).batchCompress(new FileBatchCallback() {
                @Override
                public void callback(boolean isSuccess, String[] outfile) {
                    if (!isSuccess) {
                        ToastManager.showToast(getContext(),"error");
                        return;
                    }
                    LogUtil.e("ddd",outfile[0]);
                }
            });*/


        }
    }




    final Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0) {

                containerAdapter = new ImageContainerAdapter(getContext(), mImageArrayList);
                gvImageList.setAdapter(containerAdapter);

            }
        }
    };



    private void compressImage() {
        new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < mSelected.size(); i++) {

                        Bitmap bitmap = ImageFileUtils.getBitmapFromUri(getContext(), mSelected.get(i));
                        File newFile = new File(FileUtil.IMAGE_PATH, UUID.randomUUID().toString() + ".jpg");
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                            if (!newFile.getParentFile().exists()) {
                                newFile.getParentFile().mkdirs();
                            }


                            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            newFile.createNewFile();
                        }

                        File compressFile = BitmapCompressV2.getCompressImageFile(getContext(), newFile);
                        mImageArrayList.add(compressFile);
//                        handler.obtainMessage(0, null).sendToTarget();
                    }
                    handler.obtainMessage(0, null).sendToTarget();
                } catch (OutOfMemoryError error) {
                    Log.i("ImageContainerAdapter", error.toString());
                    System.gc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }





    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_breakfest, R.id.tv_lunch, R.id.tv_dinner, R.id.tv_weight, R.id.tv_clock_out_content, R.id.rb_admin, R.id.iv_choose_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_breakfest:
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));

                break;
            case R.id.tv_lunch:
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                break;
            case R.id.tv_dinner:
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                break;
            case R.id.tv_weight:
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                break;
            case R.id.tv_clock_out_content:
                break;
            case R.id.rb_admin:
                break;
            case R.id.iv_choose_image:
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                startActivityForResult(intent, 1);

                Matisse.from(this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .capture(true)
                        .theme(R.style.AppTheme_Image)
                        .captureStrategy(
                                new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))
                        .maxSelectable(9)
                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);
                break;
        }
    }
}
