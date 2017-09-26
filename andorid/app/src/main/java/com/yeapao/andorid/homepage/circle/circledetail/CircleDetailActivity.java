package com.yeapao.andorid.homepage.circle.circledetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/7/18.
 */

public class CircleDetailActivity extends BaseActivity {
    private static final String TAG = "CircleDetailActivity";

    private CircleDetailFragmentView mFragment;
    private String mCommunityId;



    public static void start(Context context, String communityId,String fabulous) {
        LogUtil.e(TAG+"+++start", communityId+"   "+fabulous);
        Intent intent = new Intent();
        intent.putExtra("communityId", communityId);
        intent.putExtra("fabulous", fabulous);
        intent.setClass(context, CircleDetailActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_detail);
        initTopBar();

        if (savedInstanceState != null) {
            mFragment = (CircleDetailFragmentView) getSupportFragmentManager().getFragment(savedInstanceState, "CircleDetailFragment");
        } else {
            mFragment = new CircleDetailFragmentView();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, mFragment).commit();
        }

        CircleDetailPresenter presenter = new CircleDetailPresenter(subscription, CircleDetailActivity.this, mFragment);

        Intent intent = getIntent();

        presenter.setCommunityId(intent.getStringExtra("communityId"));
        presenter.setFabulous(intent.getStringExtra("fabulous"));

    }


    @Override
    protected void initTopBar() {
        initTitle("帖子详情");
        initBack();
    }


    @Override
    protected Context getContext() {
        return this;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "CircleDetailFragment", mFragment);
        }
    }
}
