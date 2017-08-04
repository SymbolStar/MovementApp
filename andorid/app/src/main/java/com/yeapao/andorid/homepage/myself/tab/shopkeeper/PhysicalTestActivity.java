package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseActivity;
import com.yeapao.andorid.model.BodySideOneModel;
import com.yeapao.andorid.model.Myfiles;
import com.yeapao.andorid.model.TestData;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/7/28.
 */

public class PhysicalTestActivity extends BaseActivity {

    private static final String TAG = "PhysicalTestActivity";
    @BindView(R.id.tv_next_club)
    TextView tvNextClub;
    @BindView(R.id.rv_physical_test_list)
    RecyclerView rvPhysicalTestList;


    private PhysicalTestMessageAdapter physicalTestMessageAdapter;
    private LinearLayoutManager linearLayoutManager;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PhysicalTestActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//初始隐藏键盘
        setContentView(R.layout.activity_physical_test);
        ButterKnife.bind(this);
        initTopBar();
        initView();



        File file = new File("/storage/emulated/0/Android/data/com.yeapao.andorid/files/image/120d25a5-40e1-4e6a-b647-e6efa531f7e2.jpeg");

        RequestBody requesetFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("myfiles", file.getName(), requesetFile);
//        upLoad(requesetFile);
        upLoadMyfile(part);
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPhysicalTestList.setLayoutManager(linearLayoutManager);
        getData();
    }

    private void getData() {
        showResult();
    }

    private void showResult() {
        if (physicalTestMessageAdapter == null) {
            physicalTestMessageAdapter = new PhysicalTestMessageAdapter(getContext());
            rvPhysicalTestList.setAdapter(physicalTestMessageAdapter);

        } else {
            rvPhysicalTestList.setAdapter(physicalTestMessageAdapter);
            physicalTestMessageAdapter.notifyDataSetChanged();

        }
    }


    private void upLoad(RequestBody file) {
        subscription = Network.getYeapaoApi()
                .uploadFile("100","100-150","174","55","111","1","1","0",file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(testDataObserver);

    }
    private void upLoadMyfile(MultipartBody.Part file) {
        subscription = Network.getYeapaoApi()
                .myFiles(file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myfilesObserver);

    }

    Observer<Myfiles> myfilesObserver = new Observer<Myfiles>() {
        @Override
        public void onCompleted() {
            LogUtil.e("Body","Completed");
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e("Body",e.toString());
        }

        @Override
        public void onNext(Myfiles myfiles) {
            LogUtil.e("Body",myfiles.getErrmsg());
        }
    };


    Observer<BodySideOneModel> testDataObserver = new Observer<BodySideOneModel>() {
        @Override
        public void onCompleted() {
            LogUtil.e("Body","Completed");
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e("Body",e.toString());

        }

        @Override
        public void onNext(BodySideOneModel bodySideOneModel) {
            LogUtil.e("Body",bodySideOneModel.getErrmsg());
        }
    };


    @Override
    protected void initTopBar() {
        initBack();
        initTitle("体测第一节（共四节）");
    }

    @OnClick(R.id.tv_next_club)
    void setTvNextClub(View view) {

    }


    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick(R.id.tv_next_club)
    public void onViewClicked() {
        ToastManager.showToast(getContext(),"下一节");
        PhysicalTestSecondActivity.start(getContext());
    }


}
