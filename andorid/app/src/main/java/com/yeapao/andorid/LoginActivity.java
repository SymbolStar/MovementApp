package com.yeapao.andorid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Selection;
import android.text.Spannable;
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
import com.scottfu.sflibrary.util.CurrentActivity;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.NetImpl;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.LoginModel;
import com.yeapao.andorid.model.UserData;
import com.yeapao.andorid.util.GlobalDataConstant;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fujindong on 2017/7/24.
 */

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    private Gson gson = new Gson();
    private UserData user = new UserData();


    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_froget_password)
    TextView tvFrogetPassword;
    @BindView(R.id.iv_hide_password)
    ImageView ivHidePassword;

    private String phone = null;
    private String password = null;
    private boolean hidePassword = true;

    public static void start(Context context) {
            Intent intent = new Intent();
            intent.setClass(context, LoginActivity.class);
            context.startActivity(intent);
    }

    @OnClick(R.id.iv_hide_password)
    void setIvHidePassword(View view) {
        ToastManager.showToast(getContext(),"onclick");
        if (hidePassword) {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        hidePassword = !hidePassword;
        etPassword.postInvalidate();
        CharSequence charSequence = etPassword.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText,charSequence.length());
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initTopBar();
        initView();
        test();

    }

    private void test() {
        CloudClient.doHttpRequest(getContext(), ConstantYeaPao.GET_HOME_LIST, NetImpl.getInstance().getHomeData("0"), null, new JSONResultHandler() {
            @Override
            public void onSuccess(String jsonString) {
                LogUtil.e("ffff",jsonString);
            }

            @Override
            public void onError(VolleyError errorMessage) {

            }
        });


        CloudClient.getRequest(getContext(),ConstantYeaPao.GET_HOME_LIST);
    }

    private void initView() {
        user = GlobalDataYepao.getUser(getContext());
        if (user != null) {
            phone = user.getPhone();
            etAccount.setText(phone);
            etAccount.clearFocus();
            etPassword.requestFocus();
        }
    }

    @Override
    protected void initTopBar() {
        initTitle("登录");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.tv_login, R.id.tv_register, R.id.tv_froget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                loginRequest();
                break;
            case R.id.tv_register:
                Intent intent = new Intent();
                intent.setClass(getContext(), RegisterActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.tv_froget_password:
                ResetPasswordActivity.start(getContext());
                break;
        }
    }

    private void loginRequest() {
        phone = etAccount.getText().toString();
        password = etPassword.getText().toString();

        if (phone == null|| phone.equals("")) {
            ToastManager.showToast(getContext(),"请输入手机号");
            return;
        }

        if (password.length() < 6) {
            ToastManager.showToast(getContext(),"请输入至少6位的密码");
            return;
        }

        CloudClient.doHttpRequest(getContext(), ConstantYeaPao.LOGIN,
                NetImpl.getInstance().loginRequest(phone, password), null, new JSONResultHandler() {
            @Override
            public void onSuccess(String jsonString) {
                LogUtil.e(TAG, jsonString);
                LoginModel loginData = gson.fromJson(jsonString, LoginModel.class);
                if (loginData.getErrmsg().equals("ok")) {
                    UserData userData = loginData.getData();
                    userData.setPassword(password);
                    userData.setLogin(true);
                    GlobalDataYepao.setUser(getContext(),userData);
                    GlobalDataYepao.setIsLogin(true);
                    finish();
                } else {
                    ToastManager.showToast(getContext(),loginData.getErrmsg());
                }
            }

            @Override
            public void onError(VolleyError errorMessage) {
                ToastManager.showToast(getContext(), errorMessage.toString());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            finish();
        }
    }
}
