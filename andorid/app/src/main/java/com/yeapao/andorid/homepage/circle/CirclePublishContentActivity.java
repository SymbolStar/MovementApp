package com.yeapao.andorid.homepage.circle;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.easing.Circ;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/17.
 */

public class CirclePublishContentActivity extends BaseActivity {

    private static final String TAG = "CirclepublishContentActivity";
    private static final int REQUEST_CODE_CHOOSE = 23;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.iv_take_photo)
    ImageView ivTakePhoto;
    @BindView(R.id.hg_choose_image)
    HeightGirdView hgChooseImage;
    @BindView(R.id.tv_right)
    TextView tvRight;

    private Bitmap.Config mConfig = Bitmap.Config.ARGB_8888;

    private ImageContainerAdapter containerAdapter;

    private Map<String, RequestBody> imageMap = new ArrayMap<>();

    List<Uri> mSelected;
    private ArrayList<File> mImageArrayList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//初始隐藏键盘
        setContentView(R.layout.activity_publish_circle);
        ButterKnife.bind(this);
        initTopBar();

    }

    @Override
    protected void initTopBar() {
        initBack();
        initTitle("发表主题");
        initRightText("发布");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkContent();
            }
        });

        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                mActionTitleHintTextView.setText(s.length() + "/20");//用于提示已经输入了多少字
                if (s.length() > 140) {
                    ToastManager.showToast(getContext(), "内容最多只能输入140个字");
                    s.delete(140, s.length());
                    etContent.setText(s);
                    etContent.setSelection(140);
                }
            }
        });
    }

    private void checkContent() {

        String content = etContent.getText().toString();

        if (mImageArrayList.size() == 0) {
            if (content == null || content.equals("")) {
                ToastManager.showToast(getContext(), "发帖内容不能为空");
                return;
            } else {
                DialogUtils.showProgressDialog(getContext());
                getNetWorkNoImage(GlobalDataYepao.getUser(getContext()).getId(), content);
            }

        } else {
            DialogUtils.showProgressDialog(getContext());
            for (int i = 0; i < mImageArrayList.size(); i++) {
                RequestBody file = RequestBody.create(MediaType.parse("multipart/form-data"), mImageArrayList.get(i));
                String key = "imageUrls\";filename=\"" + mImageArrayList.get(i).getName().toString();
                LogUtil.e(TAG,key);
                imageMap.put(key, file);

            }
            getNetWorkImageMap(GlobalDataYepao.getUser(getContext()).getId(),content,imageMap);

        }

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick(R.id.iv_take_photo)
    public void onViewClicked() {
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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            DialogUtils.showProgressDialog(getContext());
            mSelected = Matisse.obtainResult(data);
            compressImage();
        }
    }


    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0) {
                DialogUtils.cancelProgressDialog();
                containerAdapter = new ImageContainerAdapter(getContext(), mImageArrayList);
                hgChooseImage.setAdapter(containerAdapter);
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


    private void getNetWork(String customerId, String content, RequestBody image) {
        LogUtil.e(TAG, customerId + " ---- " + content);
        subscription = Network.getYeapaoApi()
                .uploadCommunity(customerId, content, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<NormalDataModel> modelObserver = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {
            DialogUtils.cancelProgressDialog();
            SoftInputUtils.hideSoftinput(CirclePublishContentActivity.this);
            finish();
        }

        @Override
        public void onError(Throwable e) {
            DialogUtils.cancelProgressDialog();
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
////隐藏软键盘
//                View view = getWindow().peekDecorView();
//                if (view != null) {
//                    InputMethodManager inputmanger = (InputMethodManager) getSystemService(
//                            Context.INPUT_METHOD_SERVICE);
//                    inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                }
            }
        }
    };





    private void getNetWorkNoImage(String customerId, String content) {
        LogUtil.e(TAG, customerId + "   " + content);
        subscription = Network.getYeapaoApi()
                .uploadCommunityNoImage(customerId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }


    private void getNetWork(String customerId, String content, RequestBody file1, RequestBody file2) {
        subscription = Network.getYeapaoApi()
                .uploadCommunityImages(customerId, content, file1, file2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }


            private void getNetWorkImageMap(String customerId,String content,Map<String,RequestBody> imageMap) {
                    LogUtil.e(TAG,customerId+"   +++   "+content);
                    subscription = Network.getYeapaoApi()
                            .uploadCommunityImageMap(customerId,content,imageMap)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserver);
                }






}
