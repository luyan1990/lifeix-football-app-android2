package com.l99.chinafootball.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.l99.chinafootball.base.BaseFragment;

import java.util.List;

/**
 * Created by lifeix-101 on 2016/7/18.
 */
public class CoachlistFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    private String categories[]= new String[]{"男足教练","女足教练"};

    public CoachlistFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
