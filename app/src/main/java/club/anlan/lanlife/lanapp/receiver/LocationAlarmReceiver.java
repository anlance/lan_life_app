package club.anlan.lanlife.lanapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import club.anlan.lanlife.lanapp.service.LocationAlarmService;

/**
 * 定位 广播接收器
 *
 * @author lan
 * @version 1.0
 * @date 2021/9/12 16:44
 */
public class LocationAlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "LocationAlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //循环启动 Service
        Log.e(TAG, "收到");
        Intent i = new Intent(context, LocationAlarmService.class);
        context.startService(i);
    }
}
