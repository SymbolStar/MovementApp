package com.yeapao.andorid.homepage.map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by fujindong on 2017/9/7.
 */

public class MapFragmentView extends Fragment implements SensorEventListener {

    private static final String a = SupportMapFragment.class.getSimpleName();
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.iv_find_qr)
    ImageView ivFindQr;
    @BindView(R.id.iv_open_light)
    ImageView ivOpenLight;
    Unbinder unbinder;
    private MapView mMapView;
    private BaiduMapOptions mapOptions;
    private BaiduMap mBaiduMap;


    private Camera m_Camera;

    //    定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    private static final int accuracyCircleFillColor = 0xAAFFFF88;
    private static final int accuracyCircleStrokeColor = 0xAA00FF00;
    private SensorManager mSensorManager;
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;


    // UI相关
    RadioGroup.OnCheckedChangeListener radioButtonListener;
    Button requestLocButton;
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private float direction;
    private UiSettings mUiSettings;




    public MapFragmentView() {
    }

    //    暂时这么写 最好的写法就是根据官方的提示 setArguments bundle 传值
    @SuppressLint("ValidFragment")
    //Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bu
    private MapFragmentView(BaiduMapOptions var1) {
        this.mapOptions = var1;
    }

    public static MapFragmentView newInstance() {
        return new MapFragmentView();
    }

    public static MapFragmentView newInstance(BaiduMapOptions var0) {
        return new MapFragmentView(var0);
    }

    public BaiduMap getBaiduMap() {
        return this.mMapView == null ? null : this.mMapView.getMap();
    }

    public MapView getmMapView() {
        return this.mMapView;
    }

    public void onAttach(Activity var1) {
        super.onAttach(var1);
    }

    public void onCreate(Bundle var1) {
        super.onCreate(var1);
    }

//    public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
////        this.mMapView = new MapView(this.getActivity(), this.mapOptions);
//
//        return this.mMapView;
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        initView(view);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initView(View view) {
        mMapView = (MapView) view.findViewById(R.id.mv_repository);
        mBaiduMap = getBaiduMap();
//        隐藏必要的属性
        mMapView.showScaleControl(false);//地图上比例尺
        mMapView.showZoomControls(false);// 隐藏缩放控件
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }

        mUiSettings = mBaiduMap.getUiSettings();
        mUiSettings.setOverlookingGesturesEnabled(false);   //设置地图手势

        mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        mCurrentMode = LocationMode.FOLLOWING;
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.overlook(0);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(getContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

    }


    public void onViewCreated(View var1, Bundle var2) {
        super.onViewCreated(var1, var2);
    }

    public void onActivityCreated(Bundle var1) {
        super.onActivityCreated(var1);
    }

    public void onViewStateRestored(Bundle var1) {
        super.onViewStateRestored(var1);
        if (var1 != null) {
            ;
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        this.mMapView.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);

    }

    public void onSaveInstanceState(Bundle var1) {
        super.onSaveInstanceState(var1);
    }

    public void onPause() {
        super.onPause();
        this.mMapView.onPause();
    }

    public void onStop() {
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mMapView.onDestroy();
        unbinder.unbind();
    }

    public void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;

        super.onDestroy();
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onConfigurationChanged(Configuration var1) {
        super.onConfigurationChanged(var1);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double x = event.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @OnClick({R.id.iv_message, R.id.iv_location, R.id.iv_find_qr, R.id.iv_open_light})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_message:
                break;
            case R.id.iv_location:
                LogUtil.e("location","location----------");
                mLocClient.requestLocation();
                break;
            case R.id.iv_find_qr:
                break;
            case R.id.iv_open_light:
//                if (lightUtils.isFlashlightOn()) {
//                    lightUtils.Closeshoudian();
//                } else {
//                    lightUtils.Openshoudian();
//                }
                try{
                    m_Camera = Camera.open();
                    Camera.Parameters mParameters;
                    mParameters = m_Camera.getParameters();
                    mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    m_Camera.setParameters(mParameters);
                } catch(Exception ex){}
                break;
        }
    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }

            LogUtil.e("MyLocationListener","receiveLocation");

            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {

        }
    }


}
