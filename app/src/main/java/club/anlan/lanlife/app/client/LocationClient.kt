package club.anlan.lanlife.app.client

import android.content.Context
import com.amap.api.location.AMapLocationListener

/**
 *
 * 定位client
 *
 * @author lan
 * @date 2022/11/9 21:03
 * @version 1.0
 */
interface LocationClient {

    /**
     * 初始化
     */
    fun init(context: Context, locationListener: AMapLocationListener)

    /**
     * 开始定位
     */
    fun startLocation(context: Context, locationListener: AMapLocationListener)

    /**
     * 结束定位
     */
    fun stopLocation()
}