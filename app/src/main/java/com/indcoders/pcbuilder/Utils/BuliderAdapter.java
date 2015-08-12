package com.indcoders.pcbuilder.Utils;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.indcoders.pcbuilder.Fragments.CompListFragment;

/**
 * Created by Akki on 12/08/15.
 */
public class BuliderAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"Processor", "Motherboard", "Graphics Card", "RAM", "Power Supply", "SSD", "HDD", "Monitor"};

    public BuliderAdapter(FragmentManager fm) {
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
    public android.support.v4.app.Fragment getItem(int position) {
        return CompListFragment.newInstance(null, null);
    }

}
