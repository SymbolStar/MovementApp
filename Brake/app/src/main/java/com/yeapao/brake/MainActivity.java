package com.yeapao.brake;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.yeapao.brake.bean.AccountMessage;

public class MainActivity extends AppCompatActivity implements BrakeContract.View{


    private TextView textViewUserName;
    private TextView textViewEntranceNum;

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
        textViewUserName = (TextView) findViewById(R.id.tv_user_name);
        textViewEntranceNum = (TextView) findViewById(R.id.tv_entrace_num);
        mTest = findViewById(R.id.v_test);
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.readCard();
                mPresenter.outPutGPIO();
            }
        });

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
//        mPresenter.getData("888056");
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void showResult(AccountMessage accountMessage) {

        textViewUserName.setText("尊敬的会员：" + accountMessage.getUser().getNickname());

    }
}
