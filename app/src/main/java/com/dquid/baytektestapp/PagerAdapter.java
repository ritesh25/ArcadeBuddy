package com.dquid.baytektestapp;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by shobhitg on 2/28/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = { "Users", "Devices" };

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position == 0) {
            fragment = UserListFragment.newInstance(position);
        } else if(position == 1) {
            fragment = DeviceListFragment.newInstance(position);
        }
        return fragment;
    }

}