package com.example.local_site

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.LocationSource
import com.amap.api.maps2d.MapView
import com.amap.api.maps2d.MapsInitializer

import com.amap.api.maps2d.UiSettings
import com.amap.api.maps2d.model.LatLng
import com.amap.api.services.core.ServiceSettings


class MainActivity2 : AppCompatActivity(), LocationSource, AMapLocationListener {

    private var mMapView: MapView? = null
    private var aMap: AMap? = null
    private var mLocationClient: AMapLocationClient? = null
    private var mLocationOption: AMapLocationClientOption? = null
    private var mListener: LocationSource.OnLocationChangedListener? = null
    private var isFirstLoc = true

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
//        //获取地图控件引用
//        mMapView = findViewById<View>(R.id.map_view) as MapView
//        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
//        mMapView!!.onCreate(savedInstanceState)
//    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

    //    MapsInitializer.updatePrivacyShow(context, true, true)
    //    MapsInitializer.updatePrivacyAgree(context, true)
        ServiceSettings.updatePrivacyShow(this, true, true);
        ServiceSettings.updatePrivacyAgree(this,true);

        val refreshButton = findViewById<Button>(R.id.refresh_button)
        refreshButton.setOnClickListener {
            // 执行刷新定位操作
            refreshLocation()
        }


        mMapView = findViewById<View>(R.id.map_view) as MapView
        mMapView!!.onCreate(savedInstanceState)

        // Initialize the AMap object
        if (aMap == null) {
            aMap = mMapView?.map
            val settings = aMap?.uiSettings
            aMap?.setLocationSource(this)
            settings?.isMyLocationButtonEnabled = true
            aMap?.isMyLocationEnabled = true
        }

        // Initialize the AMapLocationClient and AMapLocationClientOption
        mLocationClient = AMapLocationClient(applicationContext)
        mLocationClient?.setLocationListener(this)
        mLocationOption = AMapLocationClientOption()
        mLocationOption?.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        mLocationOption?.isNeedAddress = true
        mLocationOption?.isOnceLocation = false
        mLocationOption?.isWifiActiveScan = true
        mLocationOption?.isMockEnable = false
        mLocationOption?.interval = 2000
        mLocationClient?.setLocationOption(mLocationOption)
        mLocationClient?.startLocation()
    }

    private fun refreshLocation() {
        // 停止之前的定位
        if (mLocationClient != null) {
            mLocationClient?.stopLocation()
        }

        // 开始新的定位
        if (mLocationClient != null) {
            mLocationClient?.startLocation()
        }
    }



    override fun activate(onLocationChangedListener: LocationSource.OnLocationChangedListener?) {
        mListener = onLocationChangedListener
    }

    override fun deactivate() {
        mListener = null
    }

//    override fun onLocationChanged(aMapLocation: AMapLocation?) {
//        if (aMapLocation != null) {
//            if (aMapLocation.errorCode == 0) {
//                // 定位成功处理逻辑
//                if (isFirstLoc) {
//                    aMap?.moveCamera(CameraUpdateFactory.zoomTo(17F))
//                    aMap?.moveCamera(CameraUpdateFactory.changeLatLng(LatLng(aMapLocation.latitude, aMapLocation.longitude))
//                            mListener?.onLocationChanged(aMapLocation)
//                            isFirstLoc = false
//
//                        // 显示定位信息
//                        val locationInfo = StringBuffer()
//                    locationInfo.append(aMapLocation.country)
//                    locationInfo.append(aMapLocation.province)
//                    locationInfo.append(aMapLocation.city)
//                    locationInfo.append(aMapLocation.district)
//                    locationInfo.append(aMapLocation.street)
//                    locationInfo.append(aMapLocation.streetNum)
//
//                    // 创建并显示 Toast 提示
//                    Toast.makeText(this, locationInfo.toString(), Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                // 定位失败处理逻辑
//                Toast.makeText(this, "定位失败，错误码：" + aMapLocation.errorCode, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    override fun onLocationChanged(aMapLocation: AMapLocation?) {
        if (aMapLocation != null) {
            if (aMapLocation.errorCode == 0) {
                // Handle the location data
                if (isFirstLoc) {
                    aMap?.moveCamera(CameraUpdateFactory.zoomTo(17F))
                    aMap?.moveCamera(CameraUpdateFactory.changeLatLng(LatLng(aMapLocation.latitude, aMapLocation.longitude)))
                    mListener?.onLocationChanged(aMapLocation)
                    isFirstLoc = false
                }
            } else {
                // Handle location error
                    Toast.makeText(this, "定位失败，错误码：" + aMapLocation.errorCode, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mMapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView?.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMapView?.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView?.onDestroy()
        mLocationClient?.onDestroy()
    }

}