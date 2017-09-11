package com.yeapao.andorid.homepage.map.repository;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/9/11.
 */

public class RepairActivity extends BaseActivity {

    private static final String TAG = "RepairActivity";
    @BindView(R.id.et_repository_null)
    EditText etRepositoryNull;
    @BindView(R.id.iv_error_1)
    CheckBox ivError1;
    @BindView(R.id.iv_error_2)
    CheckBox ivError2;
    @BindView(R.id.iv_error_3)
    CheckBox ivError3;
    @BindView(R.id.iv_error_4)
    CheckBox ivError4;
    @BindView(R.id.iv_error_5)
    CheckBox ivError5;
    @BindView(R.id.iv_error_6)
    CheckBox ivError6;
    @BindView(R.id.et_error_content)
    EditText etErrorContent;
    @BindView(R.id.tv_sure)
    TextView tvSure;

    private String repairContent = "";
    private List<CheckBox> errorList = new ArrayList<>();

    private String customerId = "";
    private String warehouseName = "";


    private static final String CUSTOMER_ID = "CUSTOMER_ID";
    private static final String WAREHOUSE_NAME = "WAREHOUSE_NAME";
    public static void start(Context context, String customerId) {
        LogUtil.e(TAG,customerId);
        Intent intent = new Intent();
        intent.putExtra("customer_id", customerId);
        intent.setClass(context, RepairActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);
        ButterKnife.bind(this);
        initTopBar();
        initView();
        customerId = GlobalDataYepao.getUser(getContext()).getId();
        LogUtil.e(TAG,"-----"+customerId);

    }

    private void initView() {
        initErrorList();



    }

    private void initErrorList() {
        errorList.add(ivError1);
        errorList.add(ivError2);
        errorList.add(ivError3);
        errorList.add(ivError4);
        errorList.add(ivError5);
        errorList.add(ivError6);
    }

    @Override
    protected void initTopBar() {
        initTitle("报修");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick({R.id.iv_error_1, R.id.iv_error_2, R.id.iv_error_3, R.id.iv_error_4, R.id.iv_error_5, R.id.iv_error_6, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_error_1:
                break;
            case R.id.iv_error_2:
                break;
            case R.id.iv_error_3:
                break;
            case R.id.iv_error_4:
                break;
            case R.id.iv_error_5:
                break;
            case R.id.iv_error_6:
                break;
            case R.id.tv_sure:
                checkRepair();
                break;
        }
    }

    private void checkRepair() {
        String repositoryId = etRepositoryNull.getText().toString();
        String errorContent = etErrorContent.getText().toString();
        if (repositoryId == null || repositoryId.equals("")) {
            ToastManager.showToast(getContext(),"请输入健身舱编号");
            return;
        }


        repairContent = "";
        for (int i = 0; i < errorList.size(); i++) {
            if (errorList.get(i).isChecked()) {
                if (repairContent == null || repairContent.equals("")) {
                    repairContent = String.valueOf(i + 1);
                } else {
                    repairContent += ","+String.valueOf(i + 1);
                }
            }
        }


        if (errorContent == null || errorContent.equals("")) {

        } else {
            if (repairContent == null || repairContent.equals("")) {
                repairContent = errorContent;
            } else {
                repairContent += "," + errorContent;
            }
        }


        if (repairContent == null || repairContent.equals("")) {
            ToastManager.showToast(getContext(), "请选择报修内容");
            return;
        }

        LogUtil.e(TAG,repairContent);


        getNetWork(customerId,repositoryId,repairContent);
        DialogUtils.showProgressDialog(getContext());
    }



            private void getNetWork(String id,String warehouseId,String content) {
                    LogUtil.e(TAG,id+warehouseId+content);
                    subscription = Network.getYeapaoApi()
                            .requestSaveGuarantee(id,warehouseId,content)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(modelObserver );
                }

                  Observer<NormalDataModel> modelObserver = new Observer<NormalDataModel>() {
                    @Override
                    public void onCompleted() {
                        DialogUtils.cancelProgressDialog();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG,e.toString());
                        DialogUtils.cancelProgressDialog();
                        finish();
                    }

                    @Override
                    public void onNext(NormalDataModel model) {
                        LogUtil.e(TAG, model.getErrmsg());
                        if (model.getErrmsg().equals("ok")) {
                            DialogUtils.cancelProgressDialog();
                        }
                    }
                };

}
