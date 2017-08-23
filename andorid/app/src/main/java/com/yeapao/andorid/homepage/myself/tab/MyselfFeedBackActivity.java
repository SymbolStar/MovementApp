package com.yeapao.andorid.homepage.myself.tab;

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
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/27.
 */

public class MyselfFeedBackActivity extends BaseActivity {

    private static final String TAG = "MyselfFeedBackActivity";

    private TextView sendTextView;

    private EditText contentEditText;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyselfFeedBackActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initTopBar();
        initView();

    }

    private void initView() {
        sendTextView = (TextView) findViewById(R.id.tv_right);
        contentEditText = (EditText) findViewById(R.id.et_content);
        sendTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = contentEditText.getText().toString();
                if (content == null || content.equals("")) {
                    ToastManager.showToast(getContext(), "请填写意见反馈");
                } else {
                    getNetWork(GlobalDataYepao.getUser(getContext()).getId(), content);
                }

            }
        });

    }

    @Override
    protected void initTopBar() {
        initTitle("建议反馈");
        initBack();
        initRightText("发送");
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private void getNetWork(String customerId, String content) {
        LogUtil.e(TAG, customerId + "===" + content);
        subscription = Network.getYeapaoApi()
                .requestFeedBackSave(customerId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<NormalDataModel> modelObserver = new Observer<NormalDataModel>() {
        @Override
        public void onCompleted() {
            finish();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(NormalDataModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
//隐藏软键盘
                View view = getWindow().peekDecorView();
                if (view != null) {
                    InputMethodManager inputmanger = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
    };

}
