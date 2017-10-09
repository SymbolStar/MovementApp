package com.yeapao.andorid.homepage.map.repository;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogCallback;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.map.TestScanActivity;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/9/15.
 */

public class StartSportActivity extends BaseActivity {

    private static final String TAG = "StartSportActivity";
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tv_count_time)
    TextView tvCountTime;
    @BindView(R.id.iv_cang_finish)
    ImageView ivCangFinish;

    //   test time
    private String startTime_n = "2017-09-16 15:20:00";

    private String currentSS;
    private String currentMM;
    private String sumMM;


    private Date currentDate;


    private String actualId;
    private String startDate;

    private long countTime = 0;
    private Timer timer;


    public static void start(Context context, String actualOrdersId, String startDate) {
        LogUtil.e("startSportActivity",actualOrdersId+"   "+startDate);
        Intent intent = new Intent();
        intent.putExtra("actualOrdersId", actualOrdersId);
        intent.putExtra("startDate", startDate);
        intent.setClass(context, StartSportActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_fit);
        ButterKnife.bind(this);
        initTopBar();
        Intent intent = getIntent();
        actualId = intent.getStringExtra("actualOrdersId");
        startDate = intent.getStringExtra("startDate");
        initView();
    }

    private void initView() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        long startTime = formatter.parse(startDate, pos).getTime();
        currentDate = new Date();
        long currentTime = currentDate.getTime();
        countTime = currentTime - startTime;
        timer = new Timer();
        timer.schedule(task,0,1000);
    }


    private String dateLongToString(long time) {

        String date;
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        date = sdf.format(time);

//        TODO  换算成分钟
        long ss = time / 1000;
        long ss1 = ss % 60;
        long mm = ss / 60;

        if (mm <= 0) {
            mm = 1;
        }

        sumMM = String.valueOf(mm);

        currentSS = String.valueOf(ss1);
        if (currentSS.length() == 1) {
            currentSS = "0" + currentSS;
        }
        currentMM = String.valueOf(mm);
        if (currentMM.length() == 1) {
            currentMM = "0" + currentMM;
        }


        LogUtil.e(TAG,String.valueOf(ss)+"   "+currentSS+"  "+currentMM);

        return date;



    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            countTime += 1000;
                            tvCountTime.setText(dateLongToString(countTime));
                        }
                    });

                }
            });
        }
    };


    @Override
    protected void initTopBar() {
        initTitle("正在使用");
        initBack();

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.iv_scan, R.id.iv_cang_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:

                TestScanActivity.start(getContext(),"1");
                break;
            case R.id.iv_cang_finish:
                DialogUtils.showMessageTwoButtonDialog(getContext(), "结束运动提示", "您确定要结束运动吗？", new DialogCallback() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onLeftClick() {

                    }

                    @Override
                    public void onRightClick() {
                        timer.cancel();
                        SportFinishActivity.start(getContext(),actualId,sumMM);
                        finish();
                    }
                });
                break;
        }
    }
}
