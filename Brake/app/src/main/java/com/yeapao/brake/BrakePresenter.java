package com.yeapao.brake;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.brake.api.ConstantBrake;
import com.yeapao.brake.api.NetImpl;
import com.yeapao.brake.bean.AccountMessage;
import com.yeapao.brake.serialport.SerialProtDataUtil;
import com.yeapao.brake.vbar.Vbar;

/**
 * Created by fujindong on 2017/7/7.
 */

public class BrakePresenter implements BrakeContract.Presenter {


    private static final String READ_CARD_1 = "AA660002101000";
    private static final String READ_CARD_2 = "AA66000214382C";
    private static final String READ_CARD_3 = "AA660009143901FFFFFFFFFFFF2C";

    private BrakeContract.View mView;
    private Context mContext;
    private Gson gson = new Gson();

    private String decodeStr = "";
    private boolean vBarStatus = false;
    private Vbar vbar = new Vbar();

    private SerialProtDataUtil serialProtDataUtil;

    public BrakePresenter(Context context, BrakeContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);
    }


    private void initSerialProt() {
        serialProtDataUtil = new SerialProtDataUtil(mContext);
        serialProtDataUtil.initSerialProt();
    }


    private void initVbar() {
        vBarStatus = vbar.vbarOpen("127.0.0.1", 0);
        if (vBarStatus) {
            ToastManager.showToast(mContext, "扫码设备打开成功");
        } else {
            ToastManager.showToast(mContext,"扫码设备打开失败");
        }
// 扫码线程 间隔500ms
        Thread t = new Thread(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                super.run();
                while(true)
                {
                    final String str = vbar.vbarScan();
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                    public void run() {
                        if(str != null)
                        {
//                            decodeStr=(str + "\r\n");
                            decodeStr = str;
                            ToastManager.showToast(mContext,decodeStr);
                        }
                    }
                });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();


    }

    @Override
    public void start() {
        initVbar();
        initSerialProt();

    }



    @Override
    public void getData(String userId) {
        CloudClient.doHttpRequestV4(mContext, ConstantBrake.GET_USER_MESSAGE, NetImpl.getInstance().setUserParams(userId), null, new JSONResultHandler() {
            @Override
            public void onSuccess(String jsonString) {
                Log.e("success", jsonString);
                AccountMessage accountMessage = gson.fromJson(jsonString, AccountMessage.class);
                if (accountMessage.getRs().equals("Y")) {
                    mView.showResult(accountMessage);
                } else {
                    ToastManager.showToast(mContext,"会员信息错误");
                }
//                ToastManager.showToast(mContext, accountMessage.getCheckinfee());
            }

            @Override
            public void onError(VolleyError errorMessage) {
                Log.e("error", errorMessage.toString());
                ToastManager.showToast(mContext,errorMessage.toString());
            }
        });



    }

    @Override
    public void readCard() {
        serialProtDataUtil.sendHexStr(READ_CARD_3);
    }
}
