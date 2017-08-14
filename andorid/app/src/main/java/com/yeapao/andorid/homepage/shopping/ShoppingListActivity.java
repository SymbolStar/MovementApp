package com.yeapao.andorid.homepage.shopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

/**
 * Created by fujindong on 2017/8/14.
 */

public class ShoppingListActivity extends BaseActivity {

    private static final String TAG = "ShoppingListActivity";

    private ShoppingFragmentView mFragment;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ShoppingListActivity.class);
        context.startActivity(intent);

    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        if (savedInstanceState != null) {
            mFragment = (ShoppingFragmentView) getSupportFragmentManager().getFragment(savedInstanceState, "ShoppingListFragment");
        } else {
            mFragment = new ShoppingFragmentView();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_shopping_list, mFragment).commit();
        }

        ShoppingPresenter presenter = new ShoppingPresenter(getContext(), mFragment);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mFragment.isAdded()) {
            getSupportFragmentManager().putFragment((outState), "ShoppingListFragment", mFragment);
        }
    }

    @Override
    protected void initTopBar() {
//        initTitle("购买课程");
//        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

}
