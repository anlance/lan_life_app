package club.anlan.lanlife.lanapp.service;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;

import java.util.HashMap;

import club.anlan.lanlife.lanapp.R;
import club.anlan.lanlife.lanapp.constant.Constant;
import club.anlan.lanlife.lanapp.constant.HttpUrl;
import club.anlan.lanlife.lanapp.domain.LocationRes;
import club.anlan.lanlife.lanapp.domain.ResultMessage;
import club.anlan.lanlife.lanapp.http.Callback;
import club.anlan.lanlife.lanapp.http.Http;
import club.anlan.lanlife.lanapp.receiver.LocationAlarmReceiver;
import club.anlan.lanlife.lanapp.util.LocationUtil;

/**
 * 定位 service
 *
 * @author lan
 * @version 1.0
 * @date 2021/9/12 16:40
 */
public class LocationAlarmService extends Service implements AMapLocationListener {

    private static final String TAG = "LocationAlarmService";

    private static final int INTERVAL = 1000 * 60 * 5;
    private static final int PENDING_REQUEST = 0;

    public LocationAlarmService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(Constant.LOCATION_START_FOREGROUND_ID, buildNotification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //这里模拟后台操作
        LocationUtil locationUtil = new LocationUtil();
        locationUtil.initLocation(getApplicationContext(), this);
        new Thread(locationUtil::startLocation).start();

        // 关闭通知
//        stopForeground(true);
        //通过AlarmManager定时启动广播
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //从开机到现在的毫秒书（手机睡眠(sleep)的时间也包括在内
        long triggerAtTime = SystemClock.elapsedRealtime() + INTERVAL;
        Intent i = new Intent("location");
        i.setComponent(new ComponentName(this.getApplicationContext(), LocationAlarmReceiver.class));
        PendingIntent pIntent = PendingIntent.getBroadcast(this, PENDING_REQUEST, i, PENDING_REQUEST);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pIntent);

        return super.onStartCommand(intent, flags, startId);
    }

    @SuppressLint("NewApi")
    private Notification buildNotification() {

        final String CHANNEL_ID = "default";
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "locatin_channel_group", NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.setShowBadge(true);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        manager.createNotificationChannel(channel);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.xixi)
//                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.avatar))
                .setContentTitle("定位中")
                .setTimeoutAfter(1000 * 3)
                .setContentText("正在使用定位功能")
                .setAutoCancel(true)
                .setOngoing(true)
                .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);


//        builder.setChannelId(Constant.LOCATION_CHANNEL_ID);
        Notification notification = builder.build();
        notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
        return notification;
    }

    @Override
    public void onLocationChanged(AMapLocation location) {
        if (null != location) {
            //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
            if (location.getErrorCode() == 0) {
                LocationRes locationRes = LocationUtil.getLocationStr(location);
                sendLocationInfo(locationRes);
                Log.i(TAG, JSON.toJSONString(locationRes));
            } else {
                Log.e(TAG, location.getErrorInfo());
            }
        } else {
            Log.e(TAG, "定位初始化配置错误");
        }
    }

    /**
     * 保存定位信息
     *
     * @param locationRes 定位信息
     */
    private void sendLocationInfo(LocationRes locationRes) {
        Http.config(HttpUrl.SAVE_LOCATION, locationRes).postRequest(new Callback() {
            @Override
            public void onSuccess(ResultMessage result) {
                if (result.isSuccess() && result.getData() != null) {
                    Log.i(TAG, "发送成功");
                }
            }

            @Override
            public void onFailure(Exception e) {
//                showToastSync("网络异常！");
                Log.e(TAG, "网络异常");
            }
        });
    }




}
