package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.yeapao.andorid.R.id.view;

/**
 * Created by fujindong on 2017/8/16.
 */

public class MyselfClockOutActivityV2 extends BaseActivity {

    private static final String TAG = "MyselfClockOutActivityV2";
    @BindView(R.id.et_weight)
    EditText etWeight;
    private TextView publishTextView;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfClockOutActivityV2.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_out_v2);
        ButterKnife.bind(this);
        initTopBar();
        publishTextView = (TextView) findViewById(R.id.tv_right);
        publishTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = etWeight.getText().toString();
                if (weight == null || weight.equals("")) {
                    ToastManager.showToast(getContext(),"请输入体重");
                    return;
                }

                DialogUtils.showProgressDialog(getContext());
                getNetWork(GlobalDataYepao.getUser(getContext()).getId(), weight);
            }
        });
    }

    @Override
    protected void initTopBar() {
        initTitle("打卡");
        initBack();
        initRightText("发布");
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private void getNetWork(String id, String weight) {
        subscription = Network.getYeapaoApi()
                .requestClockOut(id, weight)
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
                DialogUtils.cancelProgressDialog();
                //隐藏软键盘
                 View view = getWindow().peekDecorView();if (view != null) {
                InputMethodManager inputmanger = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);}
                finish();
            }
        }
    };


}
