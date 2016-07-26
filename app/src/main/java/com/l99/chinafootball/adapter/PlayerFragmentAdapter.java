package com.l99.chinafootball.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.l99.chinafootball.fragment.innerfragment.PlayerFragment;

import java.util.List;


/**
 * Created by lifeix-101 on 2016/7/4.
 */
public class PlayerFragmentAdapter extends FragmentPagerAdapter {

    private List<PlayerFragment> fragments;

    private String categories[]= new String[]{"国家队","国奥队","国青队","五人制","沙滩足球"};

    public PlayerFragmentAdapter(FragmentManager fm, List<PlayerFragment> fragments) {
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
