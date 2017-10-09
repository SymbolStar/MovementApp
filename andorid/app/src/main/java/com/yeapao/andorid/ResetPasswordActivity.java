package com.yeapao.andorid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.NetImpl;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.ReservationLessonModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/24.
 */

public class ResetPasswordActivity extends BaseActivity {


    private static final String TAG = "RestPasswordActivity";
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_passworid)
    EditText etPassworid;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.et_input_code)
    EditText etInputCode;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.iv_hide_password)
    ImageView ivHidePassword;

    private Gson gson = new Gson();
    private boolean getVerificationFlag = false;

    private boolean hidePassword = true;


    @OnClick(R.id.iv_hide_password)
    void setIvHidePassword(View view) {
        ToastManager.showToast(getContext(),"onclick");
        if (hidePassword) {
            etPassworid.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            etPassworid.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        hidePassword = !hidePassword;
        etPassworid.postInvalidate();
        CharSequence charSequence = etPassworid.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText,charSequence.length());
        }
    }

    @OnClick(R.id.tv_sure)
    void setRegister(View view) {

        String verificationStr = etInputCode.getText().toString();
        String mobile = etPhone.getText().toString();
        String password = etPassworid.getText().toString();

        if (TextUtils.isEmpty(mobile)) {
            ToastManager.showToast(getContext(), "手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(verificationStr)) {
            ToastManager.showToast(getContext(), "验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastManager.showToast(getContext(), "密码不能为空");
            return;
        }
        if (!checkNum(password, "[0-9a-zA-Z]{6,20}")) {
            ToastManager.showToast(getContext(), "密码须为6-20位字母、数字组合");
            return;
        }

//        doRegisterRequest(mobile, password, verificationStr, name);
            getNetWork(mobile, password,verificationStr);

    }

            private void getNetWork(String phone,String password,String code) {
                    LogUtil.e(TAG,phone+password+code);
                    subscription = Network.getYeapaoApi()
                            .requestForgotPassword(phone,password,code)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserver );
                }

                  Observer<NormalDataModel> modelObserver = new Observer<NormalDataModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());

                    }

                    @Override
                    public void onNext(NormalDataModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            finish();
                        }
                    }
                };

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ResetPasswordActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        initTopBar();
    }


    @OnClick(R.id.tv_get_code)
    void setGetCodeClick(View view) {

        final String mobile = etPhone.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            ToastManager.showToast(getContext(),"手机号不能为空");
            return;
        }
        if (!checkNum(mobile, "^[1][3,4,5,7,8][0-9]{9}$")) {
            ToastManager.showToast(getContext(),"请输入正确的手机号码");
            return;
        }
        // 计时器
        TimeCount time = new TimeCount(60000, 1000);
        time.start();
        getVerification(mobile);
    }



    // 计时线程
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {
            // 计时完毕时触发
            tvGetCode.setText("获取验证码");
            tvGetCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 计时过程显示
            tvGetCode.setClickable(false);
            tvGetCode.setText(millisUntilFinished / 1000 + "秒");
        }
    }



    private void getVerification(String mobile) {

        CloudClient.doHttpRequest(getContext(), ConstantYeaPao.GET_VERIFICATION,
                NetImpl.getInstance().getVerification(mobile), null, new JSONResultHandler() {
                    @Override
                    public void onSuccess(String jsonString) {
                        LogUtil.e(TAG, jsonString);
                        ReservationLessonModel model = gson.fromJson(jsonString, ReservationLessonModel.class);
                        if (model.getErrmsg().equals("ok")) {
                            getVerificationFlag = true;
                        } else {
                            getVerificationFlag = false;
                            ToastManager.showToast(getContext(),"获取验证码失败");
                        }
                    }
                    @Override
                    public void onError(VolleyError errorMessage) {
                        getVerificationFlag = false;
                        ToastManager.showToast(getContext(), errorMessage.toString());
                    }
                });
    }

    // 验证字符串
    private boolean checkNum(String str, String exp) {
        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    @Override
    protected void initTopBar() {
        initTitle("重置密码");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }
}
