package club.anlan.lanlife.lan_app.activity;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import club.anlan.lanlife.lan_app.R;
import club.anlan.lanlife.lan_app.adapter.pagerAdapter;
import club.anlan.lanlife.lan_app.domain.TabEntity;
import club.anlan.lanlife.lan_app.fragment.FileFragment;
import club.anlan.lanlife.lan_app.fragment.RecyclerFragment;

public class HomeActivity extends BaseActivity {

    private String[] titles = {"文件夹", "回收站"};

    private int[] iconUnselectIds = {
            R.mipmap.file_unselect, R.mipmap.recycler_unselect};
    private int[] iconSelectIds = {
            R.mipmap.file_selected, R.mipmap.recycler_selected};

    private ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;

    @Override
    protected int initLayout() {
        return R.layout.home;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewpager);
        commonTabLayout = findViewById(R.id.commonTabLayout);
    }

    @Override
    protected void initData() {
        fragments.add(FileFragment.newInstance(titles[0]));
        fragments.add(RecyclerFragment.newInstance(titles[1]));
        for (int i = 0; i < titles.length; i++) {
            tabEntities.add(new TabEntity(titles[i], iconSelectIds[i], iconUnselectIds[i]));
        }
        commonTabLayout.setTabData(tabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new pagerAdapter(getSupportFragmentManager(), titles, fragments));
    }
}