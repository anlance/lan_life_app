package club.anlan.lanlife.lan_app.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 类
 *
 * @author lan
 * @version 1.0
 * @date 2021/4/17 17:23
 */
public class pagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private ArrayList<Fragment> fragments;

    public pagerAdapter(FragmentManager fm, String[] titles, ArrayList<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
