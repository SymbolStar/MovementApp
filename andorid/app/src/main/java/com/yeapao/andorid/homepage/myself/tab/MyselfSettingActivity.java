package com.yeapao.andorid.homepage.myself.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/27.
 */

public class MyselfSettingActivity extends BaseActivity {

    private static final String TAG = "MyselfSettingActivity";
    @BindView(R.id.rl_change_password)
    RelativeLayout rlChangePassword;
    @BindView(R.id.rl_feedback)
    RelativeLayout rlFeedback;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfSettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("设置");
        initBack();
    }


    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.rl_change_password, R.id.rl_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_change_password:
                MyselfChangePasswordActivity.start(getContext());
                break;
            case R.id.rl_feedback:
                MyselfFeedBackActivity.start(getContext());
                break;
        }
    }
}
