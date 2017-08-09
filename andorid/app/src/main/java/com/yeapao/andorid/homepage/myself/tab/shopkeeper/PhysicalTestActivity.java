package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.scottfu.sflibrary.image.GifSizeFilter;
import com.scottfu.sflibrary.image.ImageFileUtils;
import com.scottfu.sflibrary.recyclerview.OnRecyclerViewClickListener;
import com.scottfu.sflibrary.util.BitmapCompressV2;
import com.scottfu.sflibrary.util.FileUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.BodySideListModel;
import com.yeapao.andorid.model.BodySideOneData;
import com.yeapao.andorid.model.BodySideOneGetModel;
import com.yeapao.andorid.model.BodySideOneModel;
import com.yeapao.andorid.model.Myfiles;
import com.yeapao.andorid.model.TestData;
import com.yeapao.andorid.util.ImageContainerAdapter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/28.
 */

public class PhysicalTestActivity extends BaseActivity {


    private static final String TAG = "PhysicalTestActivity";
    private static final int REQUEST_CODE_CHOOSE = 23;
    List<Uri> mSelected;

    @BindView(R.id.tv_next_club)
    TextView tvNextClub;
    @BindView(R.id.rv_physical_test_list)
    RecyclerView rvPhysicalTestList;

    private BodySideListModel bodySideListModel;
    private int position;

    private List<BodySideOneData> bodySideOneDatas;


    private PhysicalTestMessageAdapter physicalTestMessageAdapter;
    private LinearLayoutManager linearLayoutManager;

    private boolean updateFlag = false;

    private int imagePosition = 0;

    private File imageFile = null;

    private BodySideOneGetModel bodySideOneGetModel;


    public static void start(Context context, BodySideListModel bodySideListModel, int position) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG, bodySideListModel);
        intent.putExtras(bundle);
        intent.putExtra("position", position);
        intent.setClass(context, PhysicalTestActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//初始隐藏键盘
        setContentView(R.layout.activity_physical_test);
        Intent intent = getIntent();
        bodySideListModel = (BodySideListModel) intent.getSerializableExtra(TAG);
        position = intent.getIntExtra("position", 0);
        LogUtil.e(TAG, bodySideListModel.getData().get(0).getStartTime());
        ButterKnife.bind(this);
        initTopBar();
        initView();


    }


//    TODO 将之前项目的图片加载移植过来 将网络获取的图片加载到 上传的list 里面
//
//
//
//    上传多张图片测试成功
//File file = new File("/storage/emulated/0/Android/data/com.yeapao.andorid/files/image/1a67a7c0-5f7f-40bf-9260-2f9aadf04872.jpg");
//
//    RequestBody requesetFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//    MultipartBody.Part part = MultipartBody.Part.createFormData("myfiles", file.getName(), requesetFile);
//    upLoad(requesetFile);


    //    测试成功上传图片成功
    private void uploadImageFile() {
        File file = new File("/storage/emulated/0/Android/data/com.yeapao.andorid/files/image/120d25a5-40e1-4e6a-b647-e6efa531f7e2.jpeg");

        RequestBody requesetFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("myfiles", file.getName(), requesetFile);
//        upLoad(requesetFile);
        upLoadMyfile(part);
    }


    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPhysicalTestList.setLayoutManager(linearLayoutManager);

        tvNextClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFlag = true;
               bodySideOneDatas =  physicalTestMessageAdapter.getUserData();
                bodySideOneDatas.get(0).setScheduled(String.valueOf(bodySideListModel.getData().get(position).getScheduleId()));
                bodySideOneDatas.get(1).setScheduled(String.valueOf(bodySideListModel.getData().get(position).getScheduleId()));
                bodySideOneDatas.get(0).setBloodPressure(bodySideOneDatas.get(0).getBlowPressure() + "_" + bodySideOneDatas.get(0).getHighPressure());
                bodySideOneDatas.get(1).setBloodPressure(bodySideOneDatas.get(1).getBlowPressure() + "_" + bodySideOneDatas.get(1).getHighPressure());
//                LogUtil.e(TAG,"111"+bodySideOneDatas.get(0).getQuietHeartRate());

                upLoadData(0);
                upLoadData(1);

                PhysicalTestSecondActivity.start(getContext(),bodySideListModel,position);

            }
        });

        getData();
    }

    private void upLoadData(int position) {
        File file = bodySideOneDatas.get(position).getImageFile();

            RequestBody requesetFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            upLoad(requesetFile,position);




    }


    private void getData() {

        int step = Integer.valueOf(bodySideListModel.getData().get(position).getStep());

        if (step >= 1) {
            getNetWorkOne(String.valueOf(bodySideListModel.getData().get(position).getScheduleId()));
        } else {
            showResult(false);
        }



    }



    //    TODO 第一节已体测 获取第一节数据
            private void getNetWorkOne(String id) {
                    LogUtil.e(TAG,id);
                    subscription = Network.getYeapaoApi()
                            .getBodySideOne(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( oneModelObserver);
                }

                  Observer<BodySideOneGetModel> oneModelObserver = new Observer<BodySideOneGetModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());
                    }

                    @Override
                    public void onNext(BodySideOneGetModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            bodySideOneGetModel = model;
                            showResult(true);
                        }
                    }
                };


    private void showResult(boolean haveData) {
        if (physicalTestMessageAdapter == null) {
            if (haveData) {
                physicalTestMessageAdapter = new PhysicalTestMessageAdapter(getContext(), bodySideOneGetModel.getData(), bodySideListModel.getData().get(position).getBodySideUserOut());
            } else {
                physicalTestMessageAdapter = new PhysicalTestMessageAdapter(getContext(), bodySideListModel.getData().get(position).getBodySideUserOut());
            }
            rvPhysicalTestList.setAdapter(physicalTestMessageAdapter);
            physicalTestMessageAdapter.setPhysicalImageListener(new PhysicalImageListener() {
                @Override
                public void takePhoto(int position) {
                    imagePosition = position;
                    Matisse.from(PhysicalTestActivity.this)
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
                }
            });

        } else {
            rvPhysicalTestList.setAdapter(physicalTestMessageAdapter);
            physicalTestMessageAdapter.notifyDataSetChanged();

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

                physicalTestMessageAdapter.refreshImage(imagePosition,imageFile);
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
                        imageFile = compressFile;
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


    private void upLoad(RequestBody file,int position) {
        LogUtil.e(TAG, "upload");
        subscription = Network.getYeapaoApi()
                .uploadFile(bodySideOneDatas.get(position).getQuietHeartRate(),
                        bodySideOneDatas.get(position).getBloodPressure(),
                        bodySideOneDatas.get(position).getHeights(),
                        bodySideOneDatas.get(position).getWeight(),
                        bodySideOneDatas.get(position).getInBody(),
                        bodySideOneDatas.get(position).getScheduled(),
                        bodySideOneDatas.get(position).getCustomerId(),
                        bodySideOneDatas.get(position).getBodySideOne(), file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(testDataObserver);

    }


    private void upLoadMyfile(MultipartBody.Part file) {
        subscription = Network.getYeapaoApi()
                .myFiles(file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myfilesObserver);

    }

    Observer<Myfiles> myfilesObserver = new Observer<Myfiles>() {
        @Override
        public void onCompleted() {
            LogUtil.e("Body", "Completed");
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e("Body", e.toString());
        }

        @Override
        public void onNext(Myfiles myfiles) {
            LogUtil.e("Body", myfiles.getErrmsg());
        }
    };


    Observer<BodySideOneModel> testDataObserver = new Observer<BodySideOneModel>() {
        @Override
        public void onCompleted() {
            LogUtil.e("Body", "Completed");


        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e("Body", e.toString());

        }

        @Override
        public void onNext(BodySideOneModel bodySideOneModel) {
            LogUtil.e("Body", bodySideOneModel.getErrmsg());
        }
    };


    @Override
    protected void initTopBar() {
        initBack();
        initTitle("体测第一节（共四节）");
    }




    @Override
    protected Context getContext() {
        return this;
    }


}
