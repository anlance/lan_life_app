package club.anlan.lanlife.app.model.domain

import com.amap.api.location.AMapLocation

/**
 *
 * 定位结果
 *
 * @author lan
 * @date 2022/11/11 0:57
 * @version 1.0
 */
data class LocationRes(
    /**
     * 用户 id
     */
    private var userId: String,

    /**
     * 精度
     */
    val longitude: Double,

    /**
     * 纬度
     */
    val latitude: Double,

    /**
     * 精度
     */
    val accuracy: Float,

    /**
     * 提供者
     */
    val provider: String,

    /**
     * 星数
     */
    val satellites: Int,

    )

fun valueOf(location: AMapLocation): LocationRes {
    val userId = "c69932b86390d0e8fa5a1c86cb9440b3"
    return LocationRes(
        userId = userId, longitude = location.longitude, latitude = location.latitude,
        accuracy = location.accuracy, provider = location.provider, satellites = location.satellites
    )
}
