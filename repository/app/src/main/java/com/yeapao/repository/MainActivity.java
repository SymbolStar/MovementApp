package com.yeapao.repository;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.friendlyarm.AndroidSDK.HardwareControler;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.repository.api.Network;
import com.yeapao.repository.api.NormalDataModel;
import com.yeapao.repository.jpush.ExampleUtil;
import com.yeapao.repository.jpush.LocalBroadcastManager;
import com.yeapao.repository.serialport.SerialProtDataUtil;
import com.yeapao.repository.serialport.SwitchStatusListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_forth)
    TextView tvForth;
    @BindView(R.id.tv_third)
    TextView tvThird;
    @BindView(R.id.tv_six)
    TextView tvSix;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.tv_fifth)
    TextView tvFifth;
    @BindView(R.id.tv_first)
    TextView tvFirst;
    @BindView(R.id.btn_third)
    Button btnThird;
    @BindView(R.id.btn_second)
    Button btnSecond;
    @BindView(R.id.btn_forth)
    Button btnForth;
    @BindView(R.id.btn_fifith)
    Button btnFifith;
    @BindView(R.id.btn_six)
    Button btnSix;
    @BindView(R.id.btn_first)
    Button btnFirst;


    private TextView title;

    private SerialProtDataUtil serialProtDataUtil;


    private boolean status = false;

    protected Subscription subscription;

    //    jpush
    public static boolean isForeground = false;

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    @OnClick({R.id.btn_third, R.id.btn_second, R.id.btn_forth, R.id.btn_fifith, R.id.btn_six, R.id.btn_first})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_third:
                serialProtDataUtil.openSwitch(3);
                break;
            case R.id.btn_second:
                serialProtDataUtil.openSwitch(2);
                break;
            case R.id.btn_forth:
                serialProtDataUtil.openSwitch(4);
                break;
            case R.id.btn_fifith:
                serialProtDataUtil.openSwitch(5);
                break;
            case R.id.btn_six:
                serialProtDataUtil.openSwitch(6);
                break;
            case R.id.btn_first:
               serialProtDataUtil.openSwitch(1);
                break;
        }
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    getNetWork(messge);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
//                    toast key

//                  TODO 判断message内容 控制设备的开关
                    openDoor();
                    ToastManager.showToast(MainActivity.this, showMsg.toString());
                }
            } catch (Exception e) {
            }
        }
    }



    private  void openDoor() {

        if (SerialProtDataUtil.switchStatus.isOne()) {
            return;
        } else {
            serialProtDataUtil.openSwitch(1);
        }

        Thread t = new Thread(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (SerialProtDataUtil.switchStatus.isOne()) {
                            serialProtDataUtil.openSwitch(1);
                        } else {
                            LogUtil.e("openDoor","门已关");
                        }
                    }
                });

            }
        };
        t.start();
    }

    private void openAllStatus(boolean flag) {
        if (flag) {
            serialProtDataUtil.openSwitch(2);
            serialProtDataUtil.openSwitch(3);
            serialProtDataUtil.openSwitch(4);
            serialProtDataUtil.openSwitch(5);
            serialProtDataUtil.openSwitch(6);
        }
    }


    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        registerMessageReceiver();

//        title = (TextView) findViewById(R.id.tv_title);
//
//        title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastManager.showToast(getContent(), getLocalMac(getContent()) + "-------MAC");
//                ToastManager.showToast(getContent(), getAndroidId(getContent()) + "------AndroidId");
//                if (status) {
//                    HardwareControler.PWMPlay(2);
//                    status = false;
//                } else {
//                    HardwareControler.PWMStop();
//                    status = true;
//                }
//            }
//        });

        LogUtil.e("AndroidId", getAndroidId(getContent()));
        JPushInterface.setAlias(getContent(), 22, getAndroidId(getContent()));
        tvTitle.setText("设备ID: " + getAndroidId(getContent()));
    }


    private void initSerialPort() {
        serialProtDataUtil = new SerialProtDataUtil(getContent());
        serialProtDataUtil.initSerialProt();
        serialProtDataUtil.setSwitchStatusListener(new SwitchStatusListener() {
            @Override
            public void updateStatus(int number) {
                switch (number) {
                    case 1:
                        if (SerialProtDataUtil.switchStatus.isOne()) {
                            btnFirst.setText("开");
                        } else {
                            btnFirst.setText("关");
                        }
                        break;
                    case 2:
                        if (SerialProtDataUtil.switchStatus.isTwo()) {
                            btnSecond.setText("开");
                        } else {
                            btnSecond.setText("关");
                        }
                        break;
                    case 3:
                        if (SerialProtDataUtil.switchStatus.isThree()) {
                            btnThird.setText("开");
                        } else {
                            btnThird.setText("关");
                        }
                        break;
                    case 4:
                        if (SerialProtDataUtil.switchStatus.isFour()) {
                            btnForth.setText("开");
                        } else {
                            btnForth.setText("关");
                        }
                        break;
                    case 5:
                        if (SerialProtDataUtil.switchStatus.isFive()) {
                            btnFifith.setText("开");
                        } else {
                            btnFifith.setText("关");
                        }
                        break;
                    case 6:
                        if (SerialProtDataUtil.switchStatus.isSix()) {
                            btnSix.setText("开");
                        } else {
                            btnSix.setText("关");
                        }
                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                }
            }
        });
    }


    private Context getContent() {
        return this;
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
        initSerialPort();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
        unsubscribe();
    }


    // Mac地址
    private static String getLocalMac(Context context) {
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    // Android Id
    private static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }


    private void getNetWork(String id) {
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestDoorStatus(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<NormalDataModel> modelObserver = new Observer<NormalDataModel>() {
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
