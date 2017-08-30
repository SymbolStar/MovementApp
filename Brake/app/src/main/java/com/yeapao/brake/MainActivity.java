package com.yeapao.brake;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.GlideUtil;
import com.yeapao.brake.adapter.LessonMessageAdapter;
import com.yeapao.brake.adapter.MouthRankMessageAdapter;
import com.yeapao.brake.bean.AccountMessage;
import com.yeapao.brake.bean.CheckInOrOutModel;
import com.yeapao.brake.bean.ScreenModel;

public class MainActivity extends AppCompatActivity implements BrakeContract.View{


    private TextView textViewUserName;
    private TextView textViewEntranceNum;
    private ImageView imageViewAccount;

    private RecyclerView rvMouth;
    private RecyclerView rvWeek;
    private RecyclerView rvlesson;

    private MouthRankMessageAdapter messageAdapter;
    private MouthRankMessageAdapter weekmMessageAdapter;
    private LessonMessageAdapter lessonMessageAdapter;

    private View mTest;


    private BrakeContract.Presenter mPresenter;
    private BrakePresenter mBrakePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        隐藏导航栏
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                 | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);

        initView();




    }


    private void initView() {
        rvMouth = (RecyclerView) findViewById(R.id.rv_mouth);
        rvWeek = (RecyclerView) findViewById(R.id.rv_week);
        rvlesson = (RecyclerView) findViewById(R.id.rv_lesson);


        textViewUserName = (TextView) findViewById(R.id.tv_user_name);
        textViewEntranceNum = (TextView) findViewById(R.id.tv_entrace_num);
        imageViewAccount = (ImageView) findViewById(R.id.iv_head);
        mTest = findViewById(R.id.v_test);
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.readCard();
//                mPresenter.outPutGPIO();
            }
        });

        rvMouth.setLayoutManager(new LinearLayoutManager(getContext()));
        rvWeek.setLayoutManager(new LinearLayoutManager(getContext()));
        rvlesson.setLayoutManager(new GridLayoutManager(getContext(),3));

    }

    private Context getContext() {
        return this;
    }


    @Override
    protected void onResume() {
        super.onResume();
        mBrakePresenter = new BrakePresenter(getContext(), this);

    }


    @Override
    public void setPresenter(BrakeContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
        mPresenter.start();
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void showResult(CheckInOrOutModel accountMessage) {

        textViewUserName.setText("尊敬的会员：" + accountMessage.getUser().getNickname());
        GlideUtil glideUtil = new GlideUtil();
        glideUtil.glideLoadingImage(getContext(),accountMessage.getPic2(),R.drawable.head,imageViewAccount);
//        textViewEntranceNum.setText("您是今天第 "+accountMessage.getUser().+"位入场的健身达人");
        textViewEntranceNum.setVisibility(View.GONE);


    }

    @Override
    public void showScreenResult(ScreenModel screenModel) {
        textViewEntranceNum.setVisibility(View.VISIBLE);
        textViewEntranceNum.setText("您是今天第 "+String.valueOf(screenModel.getCount())+"位入场的健身达人");
        if (screenModel.getMonthRank().size() != 0) {
            messageAdapter = new MouthRankMessageAdapter(getContext(), screenModel, 1);
            rvMouth.setAdapter(messageAdapter);
        }

        if (screenModel.getWeekRank().size() != 0) {
            weekmMessageAdapter = new MouthRankMessageAdapter(getContext(), screenModel, 2);
            rvWeek.setAdapter(weekmMessageAdapter);
        }
        if (screenModel.getClasses().size() != 0) {
            lessonMessageAdapter = new LessonMessageAdapter(getContext(), screenModel.getClasses());
            rvlesson.setAdapter(lessonMessageAdapter);
        } else {

        }
    }
}
