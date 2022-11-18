package club.anlan.lanlife.app.client.impl

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import club.anlan.lanlife.app.R
import club.anlan.lanlife.app.client.LocationClient
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import java.security.MessageDigest
import java.util.*


/**
 *
 * 默认定位 client-高德
 *
 * @author lan
 * @date 2022/11/9 21:23
 * @version 1.0
 */
class DefaultLocationClient : Application(), LocationClient {

    private val TAG = "DefaultLocationClient"

    private var locationClient: AMapLocationClient? = null

    override fun init(context: Context, locationListener: AMapLocationListener) {
        Log.i(TAG, "init")
        AMapLocationClient.setApiKey("8883d5f5d9436eed3de80f118c9df0d1")
        try {
            AMapLocationClient.updatePrivacyShow(context, true, true)
            AMapLocationClient.updatePrivacyAgree(context, true)
            locationClient = AMapLocationClient(context)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //设置定位参数
        locationClient?.setLocationOption(getDefaultOption())
        // 设置定位监听
        locationClient?.setLocationListener(locationListener)
    }

    override fun startLocation(context: Context, locationListener: AMapLocationListener) {
        Log.i(TAG, "startLocation")
        if (Objects.isNull(locationClient)) {
            this.init(context, locationListener)
        }
        val res = sHA1(context);
        //设置定位参数
        locationClient?.startLocation()
    }


    override fun stopLocation() {
        locationClient?.stopLocation()
    }


    /**
     * 默认的定位参数
     */
    private fun getDefaultOption(): AMapLocationClientOption? {
        val mOption = AMapLocationClientOption()
        //可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        //可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.isGpsFirst = true
        //可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.httpTimeOut = 30000
        //可选，设置定位间隔。默认为10秒
        mOption.interval = (1000 * 60 * 1).toLong()
        //可选，设置是否返回逆地理地址信息。默认是true
        mOption.isNeedAddress = true
        //可选，设置是否单次定位。默认是false
        mOption.isOnceLocation = true
        //可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        mOption.isOnceLocationLatest = false
        //可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP)
        //可选，设置是否使用传感器。默认是false
        mOption.isSensorEnable = true
        //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.isWifiScan = true
        //可选，设置是否使用缓存定位，默认为true
        mOption.isLocationCacheEnable = true
        //可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        mOption.geoLanguage = AMapLocationClientOption.GeoLanguage.DEFAULT
        return mOption
    }


    fun sHA1(context: Context): String? {
        try {
            val info = context.packageManager.getPackageInfo(
                context.packageName, PackageManager.GET_SIGNATURES
            )
            val cert = info.signatures[0].toByteArray()
            val md = MessageDigest.getInstance("SHA1")
            val publicKey = md.digest(cert)
            val hexString = StringBuffer()
            for (i in publicKey.indices) {
                val appendString = Integer.toHexString(
                    0xFF and publicKey[i]
                        .toInt()
                )
                    .uppercase(Locale.US)
                if (appendString.length == 1) hexString.append("0")
                hexString.append(appendString)
                hexString.append(":")
            }
            val result = hexString.toString()
            return result.substring(0, result.length - 1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}