package com.yeapao.andorid.homepage.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.CangInputCallback;
import com.yeapao.andorid.dialog.DialogCallback;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.map.repository.CangDetailActivity;
import com.yeapao.andorid.homepage.myself.tab.food.TestActivity;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TestScanActivity extends BaseActivity implements QRCodeView.Delegate {
    private static final String TAG = TestScanActivity.class.getSimpleName();
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_input_code)
    ImageView ivInputCode;
    @BindView(R.id.iv_open_light)
    ImageView ivOpenLight;

    private QRCodeView mQRCodeView;

    private boolean lightFlag = false;

    private String type = "";


    public static void start(Context context, String type) {
        Intent intent = new Intent();
        intent.putExtra("type", type);
        intent.setClass(context, TestScanActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void initTopBar() {

    }

    @Override
    protected Context getContext() {
        return null;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        LogUtil.e(TAG,type);

//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);

        mQRCodeView.showScanRect();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    //震动
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        vibrate();
        mQRCodeView.stopSpot();

        if (type.equals("1")) {
                //TODO 开门操作
            requestOpenDoor(result, GlobalDataYepao.getUser(getContext()).getId(),"1");
            finish();
        } else {
            CangDetailActivity.start(TestScanActivity.this,result,"1",result);
        }



    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_spot:
                mQRCodeView.startSpot();
                break;
            case R.id.stop_spot:
                mQRCodeView.stopSpot();
                break;
            case R.id.start_spot_showrect:
                mQRCodeView.startSpotAndShowRect();
                break;
            case R.id.stop_spot_hiddenrect:
                mQRCodeView.stopSpotAndHiddenRect();
                break;
            case R.id.show_rect:
                mQRCodeView.showScanRect();
                break;
            case R.id.hidden_rect:
                mQRCodeView.hiddenScanRect();
                break;
            case R.id.start_preview:
                mQRCodeView.startCamera();
                break;
            case R.id.stop_preview:
                mQRCodeView.stopCamera();
                break;
            case R.id.open_flashlight:
                mQRCodeView.openFlashlight();
                break;
            case R.id.close_flashlight:
                mQRCodeView.closeFlashlight();
                break;
            case R.id.scan_barcode:
                mQRCodeView.changeToScanBarcodeStyle();
                break;
            case R.id.scan_qrcode:
                mQRCodeView.changeToScanQRCodeStyle();
                break;
            case R.id.choose_qrcde_from_gallery:
                break;
        }
    }


    @OnClick({R.id.iv_left, R.id.iv_input_code, R.id.iv_open_light})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.iv_input_code:
                DialogUtils.showCangInputDialog(TestScanActivity.this, new CangInputCallback() {
                    @Override
                    public void getContent(String content) {
                        if (content == null || content.equals("")) {
                            ToastManager.showToast(TestScanActivity.this, "请重新输入健身舱ID");
                        } else {
                            if (type.equals("1")) {
                                requestOpenDoor(content, GlobalDataYepao.getUser(getContext()).getId(), "2");
                                finish();
                            } else {
                                CangDetailActivity.start(TestScanActivity.this,content,"2",content);
                            }

                        }
                    }
                });
                break;
            case R.id.iv_open_light:
                if (!lightFlag) {
                    mQRCodeView.openFlashlight();
                    lightFlag = true;
                } else {
                    mQRCodeView.closeFlashlight();
                    lightFlag = false;
                }
                break;
        }
    }



    private void requestOpenDoor(String deviceNo, String customerId, String type) {
        LogUtil.e(TAG, deviceNo + customerId + type);
        subscription = Network.getYeapaoApi()
                .requestOpenDoor(deviceNo, customerId, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(DoorModelObserver);
    }

    Observer<NormalDataModel> DoorModelObserver = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {

            }
        }
    };


}