package com.yeapao.andorid.homepage.myself.tab.setting;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scottfu.sflibrary.image.GifSizeFilter;
import com.scottfu.sflibrary.image.ImageFileUtils;
import com.scottfu.sflibrary.util.BitmapCompressV2;
import com.scottfu.sflibrary.util.FileUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.util.AgePickerDialog;
import com.yeapao.andorid.util.GenderPickerDialog;
import com.yeapao.andorid.util.ImageContainerAdapter;
import com.yeapao.andorid.util.PickerPainListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.w3c.dom.Text;

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
 * Created by fujindong on 2017/8/20.
 */

public class ChangeDataActivity extends BaseActivity {

    private static final String TAG = "ChangeDataActivity";
    @BindView(R.id.rl_change_header)
    RelativeLayout rlChangeHeader;
    @BindView(R.id.rl_change_gender)
    RelativeLayout rlChangeGender;
    @BindView(R.id.rl_change_age)
    RelativeLayout rlChangeAge;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_user_gender)
    TextView tvUserGender;
    @BindView(R.id.iv_change_head)
    ImageView ivChangeHead;


    private AgePickerDialog agePickerDialog;
    private String age;

    private GenderPickerDialog genderPickerDialog;

    private static final int REQUEST_CODE_CHOOSE = 23;

    List<Uri> mSelected;
    private ArrayList<File> mImageArrayList = new ArrayList<>();

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ChangeDataActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_data);
        ButterKnife.bind(this);
        initTopBar();
        initView();


    }

    private void initView() {

        genderPickerDialog = new GenderPickerDialog();
        genderPickerDialog.setGenderListener(new GenderPickerDialog.GenderClickListener() {
            @Override
            public void isMen() {
                tvUserGender.setText("男");
                genderPickerDialog.dismiss();
            }

            @Override
            public void isWomen() {
                tvUserGender.setText("女");
                genderPickerDialog.dismiss();
            }

            @Override
            public void isCancel() {
                genderPickerDialog.dismiss();

            }
        });

        agePickerDialog = new AgePickerDialog();
        agePickerDialog.setPickerPainListener(new PickerPainListener() {
            @Override
            public void getPainValue(String value) {
                age = value;
            }

            @Override
            public void cancel() {
                age = "";
                agePickerDialog.dismiss();
            }

            @Override
            public void determine() {
                tvAge.setText(age);
                agePickerDialog.dismiss();
            }
        });
    }

    private void showPickerAge() {
        if (agePickerDialog.isVisible()) {
            agePickerDialog.dismiss();
        } else {
            agePickerDialog.show(getSupportFragmentManager(), "date");
        }
    }

    private void showPickerGender() {
        if (genderPickerDialog.isVisible()) {
            genderPickerDialog.dismiss();
        } else {
            genderPickerDialog.show(getSupportFragmentManager(), "gender");
        }
    }

    @Override
    protected void initTopBar() {
        initTitle("修改资料");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }



    @OnClick({R.id.rl_change_header, R.id.rl_change_gender, R.id.rl_change_age})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_change_header:
                mImageArrayList.clear();
                Matisse.from(this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .capture(true)
                        .theme(R.style.AppTheme_Image)
                        .captureStrategy(
                                new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))
                        .maxSelectable(1)
                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);
                break;
            case R.id.rl_change_gender:
                showPickerGender();
                break;
            case R.id.rl_change_age:
                showPickerAge();
                break;
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);

            compressImage();



        }
    }




    final Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(mImageArrayList.get(0));
                    ivChangeHead.setImageBitmap(BitmapFactory.decodeStream(fileInputStream));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

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
}
