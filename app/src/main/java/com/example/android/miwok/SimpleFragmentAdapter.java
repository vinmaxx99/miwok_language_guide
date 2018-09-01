package com.example.android.miwok;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentAdapter extends FragmentPagerAdapter {

    private String tabTitles[] =new String[]{"Number","Colors","family","phrases"};

    public SimpleFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        if (position==0)
            return new NumbersFragment();
        else if (position==1)
            return new ColorsFragment();
        else if (position==2)
            return new FamilyFragment();
        else
            return new PhrasesFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
    @Override
    public int getCount() {
        return 4;
    }
}
