package com.l99.chinafootball.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.l99.chinafootball.base.BaseFragment;

import java.util.List;


/**
 * Created by lifeix-101 on 2016/7/5.
 */
public class RefereeFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    private String categories[]= new String[]{"裁判员","五人制","沙滩足球"};

    public RefereeFragmentAdapter(FragmentManager manager, List<BaseFragment> fragments) {
        super(manager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories[position];
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
