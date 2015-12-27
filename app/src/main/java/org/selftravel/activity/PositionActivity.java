package org.selftravel.activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;

import org.selftravel.R;


/**
 * Created by Administrator on 15-12-3.
 */
public class PositionActivity extends BaseActivity implements  LocationSource, AMapLocationListener {
    private MapView mapView;

    private AMap aMap;

    private OnLocationChangedListener listener;

    private AMapLocationClient mLocationClient;

    private AMapLocationClientOption mLocationClientOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);

        mapView = (MapView)findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        aMap = mapView.getMap();

        setUpView();
    }



    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();


    }
    private void setUpView(){
        MyLocationStyle myLocationStyle = new MyLocationStyle();

        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.navi_map_gps_locked));
        myLocationStyle.strokeWidth(1);

        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }
    //-------------------------------------------------
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        listener = onLocationChangedListener;
        if (mLocationClient==null){
            mLocationClient = new AMapLocationClient(this);
            mLocationClientOption = new AMapLocationClientOption();
            mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationClient.setLocationListener(this);
            mLocationClient.setLocationOption(mLocationClientOption);
            mLocationClient.startLocation();

        }
    }

    @Override
    public void deactivate() {
        listener = null;
        if (mLocationClient!=null){
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (listener!=null&&aMapLocation!=null){
            if (aMapLocation.getErrorCode()==0){
                listener.onLocationChanged(aMapLocation);
                double latitude = aMapLocation.getLatitude();
                double longitude = aMapLocation.getLongitude();
            }else {
                String errText = "定位失败"+aMapLocation.getErrorCode()+":"+aMapLocation.getErrorInfo();
                Toast.makeText(this,errText,Toast.LENGTH_SHORT).show();
            }
        }
    }


}
