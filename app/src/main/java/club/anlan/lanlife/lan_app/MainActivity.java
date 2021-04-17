package club.anlan.lanlife.lan_app;
import club.anlan.lanlife.lan_app.activity.BaseActivity;
import club.anlan.lanlife.lan_app.activity.HomeActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        navigateTo(HomeActivity.class);
    }
}