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
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.scottfu.sflibrary.image.GifSizeFilter;
import com.scottfu.sflibrary.image.ImageFileUtils;
import com.scottfu.sflibrary.util.BitmapCompressV2;
import com.scottfu.sflibrary.util.FileUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.BodySideForthBackModel;
import com.yeapao.andorid.model.BodySideForthSaveModel;
import com.yeapao.andorid.model.BodySideListModel;
import com.yeapao.andorid.model.BodySideThreeGetDataModel;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
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
 * Created by fujindong on 2017/7/31.
 */

public class PhysicalTestForthActivity extends BaseActivity {

    private static final String TAG = "PhysicalTestForthActivity";
    @BindView(R.id.rv_physical_forth_list)
    RecyclerView rvPhysicalForthList;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.tv_before_club)
    TextView tvBeforeClub;


    private static final int REQUEST_CODE_CHOOSE = 23;
    List<Uri> mSelected;
    private File imageFile;

    private PhysicalTestForthMessageAdapter physicalTestMessageAdapter;
    private LinearLayoutManager linearLayoutManager;


    private BodySideListModel bodySideListModel;
    private int position;

    private int imagePosition;
    private int imageKind;


    private List<BodySideForthSaveModel> bodySideForthSaveModels = new ArrayList<>();


    public static void start(Context context,BodySideListModel bodySideListModel,int position) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG, bodySideListModel);
        intent.putExtras(bundle);
        intent.putExtra("position", position);
        intent.setClass(context, PhysicalTestForthActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_forth);
        Intent intent = getIntent();
        bodySideListModel = (BodySideListModel) intent.getSerializableExtra(TAG);
        position = intent.getIntExtra("position", 0);
        ButterKnife.bind(this);
        initTopBar();
        initView();

    }


    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPhysicalForthList.setLayoutManager(linearLayoutManager);
        initBodySideData();
        getData();
    }

    private void initBodySideData() {
        for (int i = 0; i < 2; i++) {
            BodySideForthSaveModel bodySideForthSaveModel = new BodySideForthSaveModel();
            bodySideForthSaveModel.setBodySideId("0");
            bodySideForthSaveModel.setPositive(null);
            bodySideForthSaveModel.setSide(null);
            bodySideForthSaveModel.setBack(null);
            bodySideForthSaveModel.setFurredTongue(null);
            bodySideForthSaveModels.add(bodySideForthSaveModel);
        }
    }

    private void getData() {
       getBodySideThreeData(String.valueOf(bodySideListModel.getData().get(position).getScheduleId()));
    }

    private void showResult() {
        if (physicalTestMessageAdapter == null) {
            physicalTestMessageAdapter = new PhysicalTestForthMessageAdapter(getContext(),bodySideListModel.getData().get(position).getBodySideUserOut(),
                    bodySideForthSaveModels);
            physicalTestMessageAdapter.setImageClickListener(new PhysicalForthTakeImageListener() {
                @Override
                public void takephoto(int position,int kind) {
                    imagePosition = position;
                    imageKind = kind;

                    Matisse.from(PhysicalTestForthActivity.this)
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
            rvPhysicalForthList.setAdapter(physicalTestMessageAdapter);

        } else {
            rvPhysicalForthList.setAdapter(physicalTestMessageAdapter);
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

                physicalTestMessageAdapter.refreshImage(imagePosition,imageKind,imageFile);
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

                        LogUtil.e(TAG,compressFile.getAbsolutePath());

//                        File imageFileC;
//
//                        switch (imageKind) {
//                            case 1:
//                                imageFileC = new File(FileUtil.IMAGE_PATH, "positive" + ".jpg");
//                                compressFile.renameTo(imageFileC);
//                                LogUtil.e(TAG,compressFile.getAbsolutePath());
//                                LogUtil.e(TAG,imageFileC.getAbsolutePath());
//                                imageFile = imageFileC;
//                                break;
//                            case 2:
//                                imageFileC = new File(FileUtil.IMAGE_PATH, "side" + ".jpg");
//                                compressFile.renameTo(imageFileC);
//                                LogUtil.e(TAG,compressFile.getAbsolutePath());
//                                LogUtil.e(TAG,imageFileC.getAbsolutePath());
//                                imageFile = imageFileC;
//                                break;
//                            case 3:
//                                imageFileC = new File(FileUtil.IMAGE_PATH, "back" + ".jpg");
//                                compressFile.renameTo(imageFileC);
//                                LogUtil.e(TAG,compressFile.getAbsolutePath());
//                                LogUtil.e(TAG,imageFileC.getAbsolutePath());
//                                imageFile = imageFileC;
//                                break;
//                            case 4:
//                                imageFileC = new File(FileUtil.IMAGE_PATH, "furredTongue" + ".jpg");
//                                compressFile.renameTo(imageFileC);
//                                LogUtil.e(TAG,compressFile.getAbsolutePath());
//                                LogUtil.e(TAG,imageFileC.getAbsolutePath());
//                                imageFile = imageFileC;
//                                break;
//                        }

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


    @Override
    protected void initTopBar() {
        initTitle("体测第四节（共四节）");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }


    @OnClick(R.id.tv_before_club)
    public void beforeViewClicked() {
        finish();
    }

    @OnClick(R.id.tv_finish)
    public void onViewClicked() {
//        TODO 上传数据
        bodySideForthSaveModels = physicalTestMessageAdapter.getBodySideForthData();
        uploadForthData(0);
        uploadForthData(1);

    }

            private void uploadForthData(int position) {

//                TODO 都要做为空判断
                RequestBody file1 = RequestBody.create(MediaType.parse("multipart/form-data"), bodySideForthSaveModels.get(position).getPositive());
                RequestBody file2 = RequestBody.create(MediaType.parse("multipart/form-data"), bodySideForthSaveModels.get(position).getSide());
                RequestBody file3 = RequestBody.create(MediaType.parse("multipart/form-data"), bodySideForthSaveModels.get(position).getBack());
                RequestBody file4 = RequestBody.create(MediaType.parse("multipart/form-data"), bodySideForthSaveModels.get(position).getFurredTongue());
                String id = bodySideForthSaveModels.get(position).getBodySideId();
                    subscription = Network.getYeapaoApi()
                            .uploadDataForth(file1,file2,file3,file4,id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe( modelObserverForth);
                }

                  Observer<BodySideForthBackModel> modelObserverForth = new Observer<BodySideForthBackModel>() {
                    @Override
                    public void onCompleted() {
                        finish();

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(BodySideForthBackModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {

                        }
                    }
                };




    private void getBodySideThreeData(String id) {
        subscription = Network.getYeapaoApi()
                .getBodySideThree(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverBodySideThreeGetData );
    }

    Observer<BodySideThreeGetDataModel> modelObserverBodySideThreeGetData = new Observer<BodySideThreeGetDataModel>() {
        @Override
        public void onCompleted() {
            showResult();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG,e.toString());

        }

        @Override
        public void onNext(BodySideThreeGetDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                bodySideForthSaveModels.get(0).setBodySideId(String.valueOf(model.getData().get(0).getBodySideId()));
                bodySideForthSaveModels.get(1).setBodySideId(String.valueOf(model.getData().get(1).getBodySideId()));
            }
        }
    };



}
