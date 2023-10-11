package com.potech.helpform.ui.dashboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.potech.helpform.R;
import com.potech.helpform.dao.impl.CoorDaoImpl;
import com.potech.helpform.databinding.FragmentDashboardBinding;
import com.potech.helpform.entity.Coordinate;
import com.potech.helpform.ui.home.HomeFragment;

import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private LocationClientOption mLocationClientOption;
    private MyLocationData locData;

    public SharedPreferences sf;
    public SharedPreferences.Editor editor;
    public List<Coordinate> cl;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        SDKInitializer.initialize(getActivity().getApplicationContext());
        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProgressDialog waitingDialog=
                new ProgressDialog(getActivity());
        waitingDialog.setTitle("请等待");
        waitingDialog.setMessage("正在加载...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
        mMapView = (MapView) getActivity().findViewById(R.id.mapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        {
            sf = getActivity().getSharedPreferences("coor", 0);
            editor = sf.edit();
            mMapView = (MapView) getActivity().findViewById(R.id.mapView);
            mBaiduMap = mMapView.getMap();
            mBaiduMap.setMyLocationEnabled(true);
        }

        mLocationClient = new LocationClient(getActivity());
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        Log.v("", "");
        //设置locationClientOption
        mLocationClient.setLocOption(option);
        {
            Thread a = new Thread(){
                @Override
                public void run() {
                    cl = new CoorDaoImpl().getCoordinateList();
                }
            };
            a.start();
            try {
                a.join(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //List<Coordinate> cl = new CoorDaoImpl().getCoordinateList();
            for (Coordinate c:cl){
                LatLng point = new LatLng(c.getLatitude(), c.getLongitude());
                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.icon_marka);
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions Oloption = new MarkerOptions()
                        .position(point)
                        .icon(bitmap);
                //在地图上添加Marker，并显示
                mBaiduMap.addOverlay(Oloption);
            }
        }
        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();
        waitingDialog.dismiss();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null){
                return;
            }
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            //Log.v("position:", String.valueOf(locData.latitude));
            mBaiduMap.setMyLocationData(locData);
            editor.putString("latitude",locData.latitude+"");
            editor.putString("longitude",locData.longitude+"");
            editor.commit();
        }
    }

   @Override
    public void onResume() {
        super.onResume();
       {
           Thread a = new Thread(){
               @Override
               public void run() {
                   cl = new CoorDaoImpl().getCoordinateList();
               }
           };
           a.start();
           try {
               a.join(5000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           //List<Coordinate> cl = new CoorDaoImpl().getCoordinateList();
           for (Coordinate c:cl){

               LatLng point = new LatLng(c.getLatitude(), c.getLongitude());
               Log.v("point:",String.valueOf(c.getLatitude())+String.valueOf(c.getLongitude()));
               //构建Marker图标
               BitmapDescriptor bitmap = BitmapDescriptorFactory
                       .fromResource(R.mipmap.icon_marka);
               //构建MarkerOption，用于在地图上添加Marker
               OverlayOptions Oloption = new MarkerOptions()
                       .position(point)
                       .icon(bitmap);
               //在地图上添加Marker，并显示
               mBaiduMap.addOverlay(Oloption);
           }
       }
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
}