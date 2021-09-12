package club.anlan.lanlife.lanapp.activity;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.amap.api.location.AMapLocationClient;

import club.anlan.lanlife.lanapp.R;
import club.anlan.lanlife.lanapp.service.LocationAlarmService;

/**
 * app 入口
 *
 * @author lan
 * @version 1.0
 * @date 2021/9/11 20:49
 */
public class MainActivity extends BaseActivity {

    private final static String TAG = "MainActivity";

    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请 WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 123);//自定义的code
        }
    }

    @Override
    protected void initData() {
        AMapLocationClient.setApiKey("74f1c15bf560f1a0d213a37a3a3ce823");
        context.startForegroundService(new Intent(context, LocationAlarmService.class));
    }
}
