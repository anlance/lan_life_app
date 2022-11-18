package club.anlan.lanlife.app.service

import android.app.*
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import club.anlan.lanlife.app.R
import club.anlan.lanlife.app.client.impl.DefaultLocationClient
import club.anlan.lanlife.app.constant.IdPool.LOCATION_CHANNEL_ID
import club.anlan.lanlife.app.model.domain.LocationRes
import club.anlan.lanlife.app.model.domain.valueOf
import club.anlan.lanlife.app.model.http.client.BasicHttpClient
import club.anlan.lanlife.app.receiver.LocationAlarmReceiver
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 * 定位
 *
 * @author lan
 * @date 2022/11/6 15:05
 * @version 1.0
 */
class LocationService : Service(), AMapLocationListener {

    private val TAG = "LocationService"

    private val INTERVAL = 1000 * 60 * 10

    private val PENDING_REQUEST = 0

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        initNotificationChannel()
        startForeground(1200, buildNotification())
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand")
        var locationClient = application as DefaultLocationClient
        locationClient.startLocation(this, this)

        //通过AlarmManager定时启动广播
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        //从开机到现在的毫秒书（手机睡眠(sleep)的时间也包括在内
        val triggerAtTime: Long = SystemClock.elapsedRealtime() + INTERVAL
        val i = Intent(this, LocationAlarmReceiver::class.java)
        val pIntent = PendingIntent.getBroadcast(this, PENDING_REQUEST, i, FLAG_IMMUTABLE)
        alarmManager[AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime] = pIntent
        return super.onStartCommand(intent, flags, startId)
    }

    private fun buildNotification(): Notification? {
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, LOCATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.avatar)
                .setContentTitle("前面...")
                .setContentText("行到水穷处，坐看云起时")
                .setOngoing(true)
                .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
        val notification = builder.build()
        notification.flags = Notification.DEFAULT_LIGHTS or Notification.FLAG_AUTO_CANCEL
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1200, notification)
        return notification
    }

    private fun initNotificationChannel() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            LOCATION_CHANNEL_ID,
            "定位",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.enableLights(true)
        channel.setShowBadge(true)
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        manager.createNotificationChannel(channel)
    }

    override fun onLocationChanged(location: AMapLocation?) {
        Log.i(TAG, "onLocationChanged")
        if (null != location) {
            //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
            if (location.errorCode == 0) {
                val locationRes = this.getLocationStr(location)
                Log.i(TAG, "" + locationRes)
                if (locationRes != null) {
                    try {
                        GlobalScope.launch {
                            val result = BasicHttpClient.instance().saveLocation(locationRes)
                            if (result.success) {
                                Log.i(TAG, "发送成功")
                            } else {
                                Log.e(TAG, "网络异常")
                            }
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, e.printStackTrace().toString())
                    }
                }
            } else {
                Log.e(TAG, "" + location.errorCode)
                Log.e(TAG, location.errorInfo)
                Log.e(TAG, location.toString())
            }
        } else {
            Log.e(TAG, "定位初始化配置错误")
        }
    }

    /**
     * 根据定位结果返回定位信息的字符串
     */
    private fun getLocationStr(location: AMapLocation): LocationRes? {
        return if (location.errorCode == 0) {
            return valueOf(location)
        } else {
            return null
        }
    }

}