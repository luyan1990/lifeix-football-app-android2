package com.l99.chinafootball.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.ElearningTrainingCategoriesTreeBean;

import java.util.List;

/**
 * Created by 78101 on 2016/7/18.
 */
public class TrainingVideolistFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    private String categories[]= new String[]{"比赛管理","裁判技巧","越位","视频分析"};

    private List<ElearningTrainingCategoriesTreeBean.CatsBean> cats;

    public TrainingVideolistFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments, List<ElearningTrainingCategoriesTreeBean.CatsBean> cats) {
        super(fm);
        this.fragments = fragments;
        this.cats = cats;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return cats.get(position).getName();
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
