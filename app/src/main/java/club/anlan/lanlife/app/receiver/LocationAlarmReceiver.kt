package club.anlan.lanlife.app.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import club.anlan.lanlife.app.service.LocationService

/**
 *
 * 定位自启动
 *
 * @author lan
 * @date 2022/11/9 0:24
 * @version 1.0
 */
class LocationAlarmReceiver : BroadcastReceiver() {

    private val TAG = "LocationAlarmReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        // 循环启动 Service
        val i = Intent(context, LocationService::class.java)
        context?.startService(i)
    }
}