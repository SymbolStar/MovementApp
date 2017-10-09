package com.yeapao.andorid.homepage.map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.math.MathContext;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.map.Text;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.scottfu.sflibrary.util.DateFormatter;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.dialog.DialogCallback;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.homepage.map.clusterutil.Cluster;
import com.yeapao.andorid.homepage.map.clusterutil.ClusterItem;
import com.yeapao.andorid.homepage.map.clusterutil.ClusterManager;
import com.yeapao.andorid.homepage.map.overlayutil.DrivingRouteOverlay;
import com.yeapao.andorid.homepage.map.overlayutil.OverlayManager;
import com.yeapao.andorid.homepage.map.repository.DepositActivity;
import com.yeapao.andorid.homepage.map.repository.RepairActivity;
import com.yeapao.andorid.homepage.map.repository.ReservationCangActivity;
import com.yeapao.andorid.homepage.map.repository.StartSportActivity;
import com.yeapao.andorid.homepage.message.MyMessageActivity;
import com.yeapao.andorid.homepage.myself.orders.MyselfOrdersActivity;
import com.yeapao.andorid.model.NormalDataModel;
import com.yeapao.andorid.model.WareHouseListModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by fujindong on 2017/9/7.
 */

public class MapFragmentView extends BaseFragment implements SensorEventListener, BaiduMap.OnMapClickListener, BaiduMap.OnMapLoadedCallback, OnGetRoutePlanResultListener {

    private static final String TAG = "MapFragmentView";
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.iv_find_qr)
    ImageView ivFindQr;
    @BindView(R.id.iv_open_light)
    ImageView ivOpenLight;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_reservation_warehouse)
    TextView tvWareHouse;
    @BindView(R.id.iv_location_icon)
    ImageView ivLocationIcon;
    @BindView(R.id.tv_reservation_time)
    TextView tvReservationTime;
    @BindView(R.id.tv_cang_hint)
    TextView tvCangHint;
    @BindView(R.id.tv_account_cang_status)
    TextView tvAccountCangStatus;

    Unbinder unbinder;
    private MapView mMapView;
    private BaiduMapOptions mapOptions;
    private BaiduMap mBaiduMap;
    private int currentMyItemIndex = 0;
    private boolean reservationStatus = false;
    private boolean timerFlag = false;

    private static boolean isMessageFlag = false;



    private long timeFlag = 0;

    //    倒计时
    private long countDown = 0;
    Timer timer = new Timer();


    private ConstraintLayout mConstraintLayout;
    private FrameLayout reservationFrameLayout;
    private ConstraintSet applyConstraintSet = new ConstraintSet();


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


    private WareHouseListModel mWareHouseList = new WareHouseListModel();


    //    地图标记
    private ClusterManager<MyItem> mClusterManager;

    //    线路轨迹
    RoutePlanSearch mSearch = null;
    int nodeIndex = -1; // 节点索引,供浏览节点时使用
    DrivingRouteResult nowResultdrive = null;
    RouteLine route = null;
    OverlayManager routeOverlay = null;


    public void refreshMessageIcon() {
        isMessageFlag = true;
    }

    @Override
    public void onMapLoaded() {

        LogUtil.e(TAG + "onMapLoaded", "9");

        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(9).build()));
    }


    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        LogUtil.e(TAG, "ongetDrivingRouteResult");
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getContext(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            // result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;

            if (result.getRouteLines().size() >= 1) {
                route = result.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new MyDrivingRouteOverLay(mBaiduMap);
//                routeOverlay = overlay;
//                mBaidumap.setOnMarkerClickListener(overlay);／/不需要监听
//                overlay.setData(result.getRouteLines().get(0));
//                overlay.addToMap();
//                overlay.zoomToSpan();
                test(result.getRouteLines().get(0));

                String ddd = "";
                double distance = (double) result.getRouteLines().get(0).getDistance();
                if (distance > 1000) {
                    ddd = String.valueOf(distance / 1000);
                    tvDistance.setText(ddd + "km");
                } else {
                    ddd = String.valueOf(result.getRouteLines().get(0).getDistance());
                    tvDistance.setText(ddd + "m");
                }
                LogUtil.e(TAG, "_____" + ddd + "+++++");


            } else {
                Log.d("route result", "结果数<0");
                return;
            }

        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    @Override
    public void onMapClick(LatLng latLng) {
        applyConstraintSet.clone(mConstraintLayout);
        applyConstraintSet.setVisibility(R.id.fl_reservation, ConstraintSet.GONE);
        applyConstraintSet.setVisibility(R.id.iv_find_qr, ConstraintSet.VISIBLE);

        applyConstraintSet.applyTo(mConstraintLayout);
//        reservationFrameLayout.setVisibility(View.GONE);
        LogUtil.e(TAG, latLng.toString());
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        LogUtil.e(TAG, "onMapPoiClick");
        return false;
    }


    /**
     * 每个Marker点，包含Marker点坐标以及图标
     */
    public class MyItem implements ClusterItem {
        private final LatLng mPosition;
        private int index;

        public MyItem(LatLng latLng) {
            mPosition = latLng;
        }

        public MyItem(LatLng latLng, int index) {
            mPosition = latLng;
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public BitmapDescriptor getBitmapDescriptor() {

            if (mWareHouseList.getData().getWarehouseListOut().get(index).getActualStatus().equals("1")) {
                return BitmapDescriptorFactory.fromResource(R.drawable.cang_run_s);
            } else if (mWareHouseList.getData().getWarehouseListOut().get(index).getReservaStatus().equals("1")) {

                if (mWareHouseList.getData().getWarehouseListOut().get(index).getIsMyReserva().equals("1")) {
                    reservationStatus = true;
                    return BitmapDescriptorFactory.fromResource(R.drawable.cang_run_my);
                } else {
                    return BitmapDescriptorFactory.fromResource(R.drawable.cang_run_s);
                }

            } else {
                return BitmapDescriptorFactory.fromResource(R.drawable.cang_run_n);
            }


        }
    }


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
        LogUtil.e(TAG, "----onCreate----");

        // 定位初始化##在此处设置定位的监听 避免重复定位
        mLocClient = new LocationClient(getContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }


//    public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
////        this.mMapView = new MapView(this.getActivity(), this.mapOptions);
//
//        return this.mMapView;
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LogUtil.e(TAG, "----onCreateView----");
        isFirstLoc = true;
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

//    @OnClick(R.id.tv_reservation_warehouse)
//    void setReservationWarehouse(View view) {
//
//        if (reservationStatus) {
//            DialogUtils.showMessageDialog(getContext(), getContext().getResources().getString(R.string.cang_message_hint_1),
//                    getContext().getResources().getString(R.string.cang_message_hint_2),getContext().getResources().getString(R.string.cang_message_hint_3), new DialogCallback() {
//                        @Override
//                        public void onItemClick(int position) {
//
//                        }
//
//                        @Override
//                        public void onLeftClick() {
//
//                        }
//
//                        @Override
//                        public void onRightClick() {
//
//                        }
//                    });
//        } else {
//            ReservationCangActivity.start(getContext(), String.valueOf(mWareHouseList.getData().getWarehouseListOut().get(currentMyItemIndex).getWarehouseId()));
//        }
//    }

    private void initView(View view) {
        mConstraintLayout = (ConstraintLayout) view.findViewById(R.id.cl_map);
        reservationFrameLayout = (FrameLayout) view.findViewById(R.id.fl_reservation);
        applyConstraintSet.clone(mConstraintLayout);
        TransitionManager.beginDelayedTransition(mConstraintLayout);

        mMapView = (MapView) view.findViewById(R.id.mv_repository);
        mBaiduMap = getBaiduMap();
        mBaiduMap.setOnMapClickListener(this);


//        隐藏必要的属性
        mMapView.showScaleControl(false);//地图上比例尺
        mMapView.showZoomControls(false);// 隐藏缩放控件
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }

        mUiSettings = mBaiduMap.getUiSettings();
        mUiSettings.setOverlookingGesturesEnabled(false);   //设置地图手势
        mUiSettings.setCompassEnabled(false);

        mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);//获取传感器管理服务

        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);


        mCurrentMode = LocationMode.FOLLOWING;
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.overlook(0).zoom(17.0f);//设置地图俯视角，
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

//        // 定位初始化
//        mLocClient = new LocationClient(getContext());
//        mLocClient.registerLocationListener(myListener);
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
////        option.setScanSpan(1000);
//        mLocClient.setLocOption(option);
//        mLocClient.start();

//        线路轨迹
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);

        if (isMessageFlag) {
            ivMessage.setImageDrawable(getContext().getResources().getDrawable(R.drawable.cang_information_s));
        } else {
            ivMessage.setImageDrawable(getContext().getResources().getDrawable(R.drawable.cang_information_n));
        }


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
        LogUtil.e(TAG, "onResume");
        this.mMapView.onResume();


        if (!GlobalDataYepao.isLogin()) {
            getNetWork("0");
        } else {
            getNetWork(GlobalDataYepao.getUser(getContext()).getId());
        }
        mLocClient.requestLocation();

        applyConstraintSet.clone(mConstraintLayout);
        applyConstraintSet.setVisibility(R.id.fl_reservation, ConstraintSet.GONE);
        applyConstraintSet.setVisibility(R.id.iv_find_qr, ConstraintSet.VISIBLE);
        applyConstraintSet.applyTo(mConstraintLayout);
        //为系统的方向传感器注册监听器
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
//                SensorManager.SENSOR_DELAY_UI);
//

//        获取舱的位置数据
//        if (!GlobalDataYepao.isLogin()) {
//            getNetWork("0");
//        } else {
//            getNetWork(GlobalDataYepao.getUser(getContext()).getId());
//        }
//        定义点聚合管理类
//        mClusterManager = new ClusterManager<MyItem>(getContext(), mBaiduMap);

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
                isMessageFlag = false;
                ivMessage.setImageDrawable(getContext().getResources().getDrawable(R.drawable.cang_information_n));
                MyMessageActivity.start(getContext());
                break;
            case R.id.iv_location:
                LogUtil.e("location", "location----------");
                mLocClient.requestLocation();
                DialogUtils.showProgressDialog(getContext(),true);
                onResume();

                break;
            case R.id.iv_find_qr:
                if (GlobalDataYepao.isLogin()) {
                    if (mWareHouseList.getData().getIsQualified().equals("1")) {

                        if (mWareHouseList.getData().getIsUnpaid() == 1) {
                            MyselfOrdersActivity.start(getContext());
                            return;
                        }

                        if (mWareHouseList.getData().getWarehouseId() != 0) {
                            TestScanActivity.start(getContext(), "1");
                        } else {
                            TestScanActivity.start(getContext(), "0");
                        }


                    } else {
                        DepositActivity.start(getContext());
                    }
                } else {
                    LoginActivity.start(getContext());
                }

                break;
            case R.id.iv_open_light:
                if (GlobalDataYepao.isLogin()) {
                    RepairActivity.start(getContext(), GlobalDataYepao.getUser(getContext()).getId());
                } else {
                    ToastManager.showToast(getContext(), "请先登录");
                    LoginActivity.start(getContext());
                }

                break;
        }
    }


    /**
     * 定位SDK监听函数
     * 点击定位的时候会走这边
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            LogUtil.e("MyLocationListener", "xxxxXXXXXX");
            if (isFirstLoc) {
                LogUtil.e("MyLocationListener", "receiveLocationlllllll");

                mCurrentLat = location.getLatitude();
                mCurrentLon = location.getLongitude();
                mCurrentAccracy = location.getRadius();
                locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(mCurrentDirection).latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
                mBaiduMap.setMyLocationData(locData);
//            if (isFirstLoc) {

                isFirstLoc = false;
//                LatLng ll = new LatLng(location.getLatitude(),
//                        location.getLongitude());
//                MapStatus.Builder builder = new MapStatus.Builder();
//                builder.target(ll).zoom(17.0f);
//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {

        }
    }


    private void getNetWork(String id) {
        timeFlag = System.currentTimeMillis();
        LogUtil.e(TAG, id);
        subscription = Network.getYeapaoApi()
                .requestWareHouseList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<WareHouseListModel> modelObserver = new Observer<WareHouseListModel>() {
        @Override
        public void onCompleted() {
            DialogUtils.cancelProgressDialog();
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());
            DialogUtils.cancelProgressDialog();

        }

        @Override
        public void onNext(WareHouseListModel model) {
            DialogUtils.cancelProgressDialog();
            LogUtil.e(TAG + "WareHouseListModel", model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                mWareHouseList = model;
                if (mWareHouseList.getData().getWarehouseId() != 0) {
                    tvAccountCangStatus.setVisibility(View.VISIBLE);
                    tvAccountCangStatus.setText(getContext().getResources().getString(R.string.account_cang_status));
                    tvAccountCangStatus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StartSportActivity.start(getContext(), GlobalDataYepao.getCangDeviceData(getContext()).getId(),
                                    GlobalDataYepao.getCangDeviceData(getContext()).getStartTime());
                        }
                    });
//                    TODO
                } else {
                    tvAccountCangStatus.setVisibility(View.GONE);
                }
                if (mWareHouseList.getData().getIsUnpaid() == 1) {
                    tvAccountCangStatus.setVisibility(View.VISIBLE);
                    tvAccountCangStatus.setText(getContext().getResources().getString(R.string.account_cang_status2));
                    tvAccountCangStatus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyselfOrdersActivity.start(getContext());
                        }
                    });

                } else {
                    tvAccountCangStatus.setVisibility(View.GONE);
                }

                addMarkers();
            }
        }
    };


    /**
     * 在地图上添加标记
     */
    public void addMarkers() {
        LogUtil.e("获取标记的延时", String.valueOf(System.currentTimeMillis() - timeFlag));
        mClusterManager = new ClusterManager<MyItem>(getContext(), mBaiduMap);
//        mClusterManager.clearItems();
        List<MyItem> items = new ArrayList<MyItem>();
        for (int i = 0; i < mWareHouseList.getData().getWarehouseListOut().size(); i++) {
            items.add(new MyItem(new LatLng(mWareHouseList.getData().getWarehouseListOut().get(i).getLatitude(),
                    mWareHouseList.getData().getWarehouseListOut().get(i).getLongitude()), i));
//            items.add(new MyItem(new LatLng(mWareHouseList.getData().getWarehouseListOut().get(i).getLatitude(),
//                    mWareHouseList.getData().getWarehouseListOut().get(i).getLongitude())));
        }
        mClusterManager.addItems(items);

        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
        mBaiduMap.setOnMarkerClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
            @Override
            public boolean onClusterClick(Cluster<MyItem> cluster) {
                LogUtil.e("ClusterClickListener", String.valueOf(cluster.getSize()));
                return false;
            }
        });

        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
            @Override
            public boolean onClusterItemClick(final MyItem item) {
                ivFindQr.setVisibility(View.GONE);
                if (routeOverlay != null) {
                    routeOverlay.removeFromMap();
                }
                currentMyItemIndex = item.getIndex();
                if (mWareHouseList.getData().getWarehouseListOut().get(item.getIndex()).getIsMyReserva().equals("1")) {
                    tvWareHouse.setText("扫码开门");
                    tvWareHouse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastManager.showToast(getContext(), "扫码开门");
                            TestScanActivity.start(getContext(), "0");
                        }
                    });
                    tvDistance.setVisibility(View.GONE);
                    ivLocationIcon.setVisibility(View.GONE);
                    tvReservationTime.setVisibility(View.VISIBLE);
                    DateFormatter dateFormatter = new DateFormatter();
                    long llll = Long.valueOf(mWareHouseList.getData().getWarehouseListOut().get(item.getIndex()).getReservaStartTime());
                    long l2 = Long.valueOf(mWareHouseList.getData().getWarehouseListOut().get(item.getIndex()).getDuration());
                    countDown = dateFormatter.getData(llll, l2);
                    LogUtil.e(TAG, String.valueOf(countDown));
//                 TODO 倒计时计算
                    if (timerFlag) {

                    } else {
                        timer.schedule(task, 1000, 1000);
                    }

                    tvCangHint.setText("健身舱：" + mWareHouseList.getData().getWarehouseListOut().get(item.getIndex()).getWarehouseName());
                } else if (mWareHouseList.getData().getWarehouseListOut().get(item.getIndex()).getActualStatus().equals("1") ||
                        mWareHouseList.getData().getWarehouseListOut().get(item.getIndex()).getReservaStatus().equals("1")) {

                    if (mWareHouseList.getData().getWarehouseListOut().get(item.getIndex()).getIsOfflineReminder().equals("1")) {
                        tvWareHouse.setText("取消下线提醒");
                    } else {
                        tvWareHouse.setText("下线提醒");
                    }
                    tvWareHouse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastManager.showToast(getContext(), "下线提醒");
                            if (mWareHouseList.getData().getWarehouseListOut().get(item.getIndex()).getIsOfflineReminder().equals("1")) {
                                getNetWorkDelOffLine(String.valueOf(mWareHouseList.getData().getWarehouseListOut().get(item.getIndex()).getWarehouseId()));
                            } else {
                                getNetWorkOffLine(String.valueOf(mWareHouseList.getData().getWarehouseListOut().get(item.getIndex()).getWarehouseId()));
                            }

                        }
                    });
                    tvDistance.setVisibility(View.VISIBLE);
                    ivLocationIcon.setVisibility(View.VISIBLE);
                    tvReservationTime.setVisibility(View.GONE);
                    tvCangHint.setText(mWareHouseList.getData().getReservationPrice() + "元/5分钟");

                } else {
                    tvWareHouse.setText("预约使用");
                    tvWareHouse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastManager.showToast(getContext(), "预约使用");


                            if (reservationStatus) {
                                DialogUtils.showMessageDialog(getContext(), getContext().getResources().getString(R.string.cang_message_hint_1),
                                        getContext().getResources().getString(R.string.cang_message_hint_2), getContext().getResources().getString(R.string.cang_message_hint_3), new DialogCallback() {
                                            @Override
                                            public void onItemClick(int position) {

                                            }

                                            @Override
                                            public void onLeftClick() {

                                            }

                                            @Override
                                            public void onRightClick() {

                                            }
                                        });
                            } else {
                                ReservationCangActivity.start(getContext(), String.valueOf(mWareHouseList.getData().getWarehouseListOut().get(currentMyItemIndex).getWarehouseId()));
                            }


                        }
                    });
                    tvDistance.setVisibility(View.VISIBLE);
                    ivLocationIcon.setVisibility(View.VISIBLE);
                    tvReservationTime.setVisibility(View.GONE);
                    tvCangHint.setText(mWareHouseList.getData().getReservationPrice() + "元/5分钟");
                }

                applyConstraintSet.clone(mConstraintLayout);
                applyConstraintSet.setVisibility(R.id.fl_reservation, ConstraintSet.VISIBLE);
                applyConstraintSet.setVisibility(R.id.iv_find_qr, ConstraintSet.GONE);
                applyConstraintSet.applyTo(mConstraintLayout);
//                reservationFrameLayout.setVisibility(View.VISIBLE);
                PlanNode stNode = PlanNode.withLocation(new LatLng(mCurrentLat, mCurrentLon));
                PlanNode enNode = PlanNode.withLocation(item.getPosition());
                mSearch.drivingSearch((new DrivingRoutePlanOption()).from(stNode).to(enNode));

                return false;
            }
        });
        LatLng ll = new LatLng(mCurrentLat,
                mCurrentLon);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(17.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    countDown -= 1000;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss", Locale.getDefault());
                    String time1 = simpleDateFormat.format(countDown);
                    LogUtil.e(TAG, time1);
                    tvReservationTime.setText(time1);
                    timerFlag = true;
                    if (countDown < 0) {
                        timer.cancel();
                        task.cancel();
                        timerFlag = false;
                    }
                }
            });
        }
    };


    private class MyDrivingRouteOverLay extends DrivingRouteOverlay {
        /**
         * 构造函数
         *
         * @param baiduMap 该DrivingRouteOvelray引用的 BaiduMap
         */
        public MyDrivingRouteOverLay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            return null;
        }


    }

    private void test(DrivingRouteLine drivingRouteLine) {
        final ArrayList<OverlayOptions> list = new ArrayList<OverlayOptions>();
        PolylineOptions object = new PolylineOptions();
        List<LatLng> arg0 = new ArrayList<LatLng>();
        List<DrivingRouteLine.DrivingStep> allStep = drivingRouteLine.getAllStep();
        for (int i = 0; i < allStep.size(); i++) {
            DrivingRouteLine.DrivingStep drivingStep = allStep.get(i);
            List<LatLng> wayPoints = drivingStep.getWayPoints();
            arg0.addAll(wayPoints);
        }
        object.color(getContext().getResources().getColor(R.color.route_color)).width(5).points(arg0);

        list.add(object);
        routeOverlay = new OverlayManager(mBaiduMap) {

            @Override
            public boolean onPolylineClick(Polyline arg0) {
                return false;
            }

            @Override
            public boolean onMarkerClick(Marker arg0) {
                return false;
            }

            @Override
            public List<OverlayOptions> getOverlayOptions() {
                return list;
            }
        };
        routeOverlay.addToMap();
    }


    private void getNetWorkOffLine(String warehouseId) {
        LogUtil.e(TAG + "offline", warehouseId);
        subscription = Network.getYeapaoApi()
                .requestOffLine(GlobalDataYepao.getUser(getContext()).getId(), warehouseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverOffLine);
    }

    Observer<NormalDataModel> modelObserverOffLine = new Observer<NormalDataModel>() {
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

            }
        }
    };


    private void getNetWorkDelOffLine(String warehouseId) {
        LogUtil.e(TAG + "deloffline", warehouseId);
        subscription = Network.getYeapaoApi()
                .requestDelOffLine(GlobalDataYepao.getUser(getContext()).getId(), warehouseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserverOffLine);
    }


}
