package com.indcoders.pcbuilder;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Akki on 12/08/15.
 */
public class PopularAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"Popular", "Recent"};

    public PopularAdapter(FragmentManager fm) {
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
        return RigsListFragment.newInstance(null, null);
    }
}
