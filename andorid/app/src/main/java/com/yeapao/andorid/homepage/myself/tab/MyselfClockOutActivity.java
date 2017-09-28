package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.ChecksumException;
import com.scottfu.sflibrary.image.GifSizeFilter;
import com.scottfu.sflibrary.image.ImageFileUtils;
import com.scottfu.sflibrary.util.BitmapCompressV2;
import com.scottfu.sflibrary.util.FileUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.SoftInputUtils;
import com.scottfu.sflibrary.util.ToastManager;
import com.scottfu.sflibrary.view.HeightGirdView;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;
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
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/23.
 */

public class MyselfClockOutActivity extends BaseActivity {

    private static final String TAG = "MyselfClockOutActivity";
    private static final int REQUEST_CODE_CHOOSE = 23;

    private Bitmap.Config mConfig = Bitmap.Config.ARGB_8888;

    private ImageContainerAdapter containerAdapter;

    private static final String BREAKFAST = "breakfast";
    private static final String LUNCH = "lunch";
    private static final String DINNER = "dinner";
    private static final String WEIGHT = "weight";

    private String clockStatus;
    private String circleStatus = "0";

    private Map<String, RequestBody> imageMap = new ArrayMap<>();

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
    CheckBox rbAdmin;
    @BindView(R.id.iv_choose_image)
    ImageView ivChooseImage;
    @BindView(R.id.gv_image_list)
    HeightGirdView gvImageList;
    @BindView(R.id.iv_weight_background)
    ImageView ivWeightBackground;
    @BindView(R.id.rl_weight)
    RelativeLayout rlWeight;
    @BindView(R.id.et_weight)
    EditText etWeight;
    @BindView(R.id.tv_right)
    TextView tvRight;


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


    @OnClick(R.id.tv_right)
    void setTvRight(View view) {
        String weight = etWeight.getText().toString();
        String content = tvClockOutContent.getText().toString();

        if (mImageArrayList.size() == 0) {
            if (clockStatus.equals(WEIGHT)) {
                if (weight == null || weight.equals("")) {
                    ToastManager.showToast(getContext(), "打卡内容不能为空");
                    return;
                } else {
                    RequestBody file = RequestBody.create(MediaType.parse("multipart/form-data"),"");
                    String key = "imageUrls\";filename=\"" + "imageUrlsNull";
                    imageMap.put(key, file);
                    DialogUtils.showProgressDialog(getContext());
                    getNetWorkImages(weight,WEIGHT,"1",imageMap);

                }
            } else {

                if (content == null || content.equals("")) {
                    ToastManager.showToast(getContext(), "打卡内容不能为空");
                    return;
                } else {
                    if (rbAdmin.isChecked()) {
                        circleStatus = "1";
                    } else {
                        circleStatus = "0";
                    }
                    RequestBody file = RequestBody.create(MediaType.parse("multipart/form-data"),"");
                    String key = "imageUrls\";filename=\"" + "imageUrlsNull";
                    imageMap.put(key, file);
                    DialogUtils.showProgressDialog(getContext());
                    getNetWorkImages(content, clockStatus, circleStatus,imageMap);
                }
            }
        } else {
            if (rbAdmin.isChecked()) {
                circleStatus = "1";
            } else {
                circleStatus = "0";
            }

            for (int i = 0; i < mImageArrayList.size(); i++) {
                RequestBody file = RequestBody.create(MediaType.parse("multipart/form-data"), mImageArrayList.get(i));
                String key = "imageUrls\";filename=\"" + mImageArrayList.get(i).getName().toString();
                LogUtil.e(TAG,key);
                imageMap.put(key, file);
            }
            DialogUtils.showProgressDialog(getContext());
            getNetWorkImages(content,clockStatus,circleStatus,imageMap);

        }
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

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);

            compressImage();


        }
    }


    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0) {

                containerAdapter = new ImageContainerAdapter(getContext(), mImageArrayList);
                gvImageList.setAdapter(containerAdapter);
                containerAdapter.setImageDeleteListener(new ImageContainerAdapter.ImageDeleteListener() {
                    @Override
                    public void deleteListener(int position) {
                        mImageArrayList.remove(position);
                        containerAdapter.notifyDataSetChanged();
                    }
                });


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
                clockStatus = BREAKFAST;
                ivWeightBackground.setVisibility(View.GONE);
                rlWeight.setVisibility(View.GONE);
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));

                break;
            case R.id.tv_lunch:
                clockStatus = LUNCH;
                ivWeightBackground.setVisibility(View.GONE);
                rlWeight.setVisibility(View.GONE);
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                break;
            case R.id.tv_dinner:
                clockStatus = DINNER;
                ivWeightBackground.setVisibility(View.GONE);
                rlWeight.setVisibility(View.GONE);
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                break;
            case R.id.tv_weight:
                clockStatus = WEIGHT;
                tvBreakfest.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvLunch.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvDinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_shape));
                tvWeight.setBackgroundDrawable(getResources().getDrawable(R.drawable.clock_out_tab_s_shape));
                ivWeightBackground.setVisibility(View.VISIBLE);
                rlWeight.setVisibility(View.VISIBLE);
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


            private void getNetWorkImages(String content, String type, String status, Map<String, RequestBody> images) {
                    LogUtil.e(TAG+" images ",content+type+status);
                    subscription = Network.getYeapaoApi()
                            .requestPushClock(GlobalDataYepao.getUser(getContext()).getId(),content,type,status,images)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(ImagesModelObserver);
                }

                  Observer<NormalDataModel> ImagesModelObserver = new Observer<NormalDataModel>() {
                    @Override
                    public void onCompleted() {
                        DialogUtils.cancelProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());
                        DialogUtils.cancelProgressDialog();
                    }

                    @Override
                    public void onNext(NormalDataModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            DialogUtils.cancelProgressDialog();
                            SoftInputUtils.hideSoftinput(MyselfClockOutActivity.this);
                                finish();
                        }
                    }
                };


}
