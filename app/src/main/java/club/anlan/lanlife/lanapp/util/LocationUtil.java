package club.anlan.lanlife.lanapp.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;

import java.text.SimpleDateFormat;
import java.util.Locale;

import club.anlan.lanlife.lanapp.domain.LocationRes;

/**
 * 高德地图工具类
 *
 * @author lan
 * @version 1.0
 * @date 2021/9/11 22:41
 */
public class LocationUtil {

    static {
        AMapLocationClient.setApiKey("74f1c15bf560f1a0d213a37a3a3ce823");
    }

    private AMapLocationClient locationClient = null;

    private AMapLocationClientOption locationOption = null;

    /**
     * 开始定位
     */
    public final static int MSG_LOCATION_START = 0;
    /**
     * 定位完成
     */
    public final static int MSG_LOCATION_FINISH = 1;
    /**
     * 停止定位
     */
    public final static int MSG_LOCATION_STOP = 2;

    private static SimpleDateFormat sdf = null;

    /**
     * 初始化定位
     */
    public void initLocation(Context context, AMapLocationListener locationListener) {
        //初始化client
        locationClient = new AMapLocationClient(context);
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        //可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setGpsFirst(true);
        //可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setHttpTimeOut(30000);
        //可选，设置定位间隔。默认为10秒
        mOption.setInterval(1000 * 60 * 1);
        //可选，设置是否返回逆地理地址信息。默认是true
        mOption.setNeedAddress(false);
        //可选，设置是否单次定位。默认是false
        mOption.setOnceLocation(true);
        //可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        mOption.setOnceLocationLatest(false);
        //可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        //可选，设置是否使用传感器。默认是false
        mOption.setSensorEnable(true);
        //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setWifiScan(true);
        //可选，设置是否使用缓存定位，默认为true
        mOption.setLocationCacheEnable(true);
        //可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);
        return mOption;
    }

    /**
     * 获取GPS状态的字符串
     *
     * @param statusCode GPS状态码
     */
    private String getGPSStatusString(int statusCode) {
        String str = "";
        switch (statusCode) {
            case AMapLocationQualityReport.GPS_STATUS_OK:
                str = "GPS状态正常";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPROVIDER:
                str = "手机中没有GPS Provider，无法进行GPS定位";
                break;
            case AMapLocationQualityReport.GPS_STATUS_OFF:
                str = "GPS关闭，建议开启GPS，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_MODE_SAVING:
                str = "选择的定位模式中不包含GPS定位，建议选择包含GPS定位的模式，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPERMISSION:
                str = "没有GPS定位权限，建议开启gps定位权限";
                break;
        }
        return str;
    }

    /**
     * 开始定位
     */
    public void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     */
    public void destroyLocation() {
        if (null != locationClient) {
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }


    /**
     * 根据定位结果返回定位信息的字符串
     */
    public synchronized static LocationRes getLocationStr(AMapLocation location) {
        if (location.getErrorCode() == 0) {
            LocationRes locationRes = new LocationRes(location.getLongitude(), location.getLatitude(),
                    location.getAccuracy(), location.getProvider(), location.getSatellites());
            return locationRes;
//            sb.append("定位成功" + "\n");
//            sb.append("定位类型: " + location.getLocationType() + "\n");
//            sb.append("经    度    : " + location.getLongitude() + "\n");
//            sb.append("纬    度    : " + location.getLatitude() + "\n");
//            sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
//            sb.append("提供者    : " + location.getProvider() + "\n");
//
//            sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
//            sb.append("角    度    : " + location.getBearing() + "\n");
//            // 获取当前提供定位服务的卫星个数
//            sb.append("星    数    : " + location.getSatellites() + "\n");
//            sb.append("国    家    : " + location.getCountry() + "\n");
//            sb.append("省            : " + location.getProvince() + "\n");
//            sb.append("市            : " + location.getCity() + "\n");
//            sb.append("城市编码 : " + location.getCityCode() + "\n");
//            sb.append("区            : " + location.getDistrict() + "\n");
//            sb.append("区域 码   : " + location.getAdCode() + "\n");
//            sb.append("地    址    : " + location.getAddress() + "\n");
//            sb.append("兴趣点    : " + location.getPoiName() + "\n");
//            //定位完成的时间
//            sb.append("定位时间: " + formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
        } else {
            //定位失败
            LocationRes locationRes = new LocationRes();
            locationRes.setErrorInfo(location.getErrorInfo());
            locationRes.setErrorDetail(location.getLocationDetail());
            Log.e("locationUtil", String.valueOf(location.getErrorCode()));
            Log.e("locationUtil", location.getErrorInfo());
            Log.e("locationUtil", location.getLocationDetail());
            return locationRes;
        }
    }


    public static String formatUTC(long l, String strPattern) {
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (sdf == null) {
            try {
                sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
            } catch (Throwable e) {
            }
        } else {
            sdf.applyPattern(strPattern);
        }
        return sdf == null ? "NULL" : sdf.format(l);
    }

    /**
     * 获取app的名称
     */
    public static String getAppName(Context context) {
        String appName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            appName = context.getResources().getString(labelRes);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return appName;
    }
}
