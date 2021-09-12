package club.anlan.lanlife.lanapp.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * activity 抽象类
 *
 * @author lan
 * @version 1.0
 * @date 2021/9/11 19:48
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Context context;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(initLayout());
        initData();
        initView();

        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                count++;
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
                if (count > 0) {
                    count--;
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    /**
     * toast
     *
     * @param msg 消息
     */
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * toast 异步
     *
     * @param msg 消息
     */
    public void showToastSync(String msg) {
        Looper.prepare();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    /**
     * 跳转 activity
     *
     * @param cls activity
     */
    public void navigateTo(Class cls) {
        Intent in = new Intent(context, cls);
        startActivity(in);
    }

    /**
     * 判断app是否在后台
     */
    public boolean isBackground() {
        if (count <= 0) {
            return true;
        } else {
            return false;
        }
    }
}