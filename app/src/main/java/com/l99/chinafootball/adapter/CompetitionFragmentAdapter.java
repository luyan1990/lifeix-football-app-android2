package com.l99.chinafootball.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.l99.chinafootball.base.BaseFragment;

import java.util.List;


/**
 * Created by lifeix-101 on 2016/7/7.
 */
public class CompetitionFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    private String categories[]= new String[]{"中国队赛程","赛事介绍","英雄榜"};

    public CompetitionFragmentAdapter(FragmentManager manager, List<BaseFragment> fragments) {
        super(manager);
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
