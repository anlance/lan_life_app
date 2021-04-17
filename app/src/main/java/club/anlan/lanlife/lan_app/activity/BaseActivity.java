package club.anlan.lanlife.lan_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    public Context context;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       context = this;
       setContentView(initLayout());
       initView();
       initData();
    }

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    /**
     * toast
     * @param msg 消息
     */
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * toast 异步
     * @param msg 消息
     */
    public void showToastSync(String msg) {
        Looper.prepare();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    /**
     * 跳转 activity
     * @param cls activity
     */
    public void navigateTo(Class cls) {
        Intent in = new Intent(context, cls);
        startActivity(in);
    }
}