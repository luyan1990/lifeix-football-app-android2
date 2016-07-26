package com.l99.chinafootball.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.l99.chinafootball.R;
import com.l99.chinafootball.activity.MainActivity;
import com.l99.chinafootball.adapter.CoachlistFragmentAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.NationalCoachBean;
import com.l99.chinafootball.fragment.FragmentSwitcher;
import com.l99.chinafootball.fragment.innerfragment.MenCoachFragment;
import com.l99.chinafootball.fragment.innerfragment.WomenCoachFragment;
import com.l99.chinafootball.presenter.CoachlistPresenter;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 78101 on 2016/7/5.
 * 教练页面
 */
@SuppressLint("ValidFragment")
public class CoachlistPage extends BaseFragment implements IView.ViewPro<Map<String,ArrayList<NationalCoachBean.CategoryBean>>> {

    private final CoachlistPresenter presenter;
    private ProgressBar progressBar;
    private ImageView error_image;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private CoachlistFragmentAdapter mAdapter;

    public CoachlistPage(Context context) {
        super(context);
        presenter = new CoachlistPresenter(this);
    }

    @Override
    public String getTAG() {
        return FragmentSwitcher.COACHLIST_PAGE;
    }

    @Override
    public void initView(View view) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.closeRightMenu();
        progressBar = (ProgressBar) view.findViewById(R.id.pb_coachlist_page);
        error_image = (ImageView) view.findViewById(R.id.error_coachlist_page);
        viewPager = (ViewPager) view.findViewById(R.id.vp_coachlist_page);
        tabLayout = (TabLayout) view.findViewById(R.id.tl_coachlist_page);
    }

    @Override
    public void initData() {
        presenter.getData();
    }

    @Override
    public int getLayoutId() {

        return R.layout.coachlist_page;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        error_image.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        error_image.setVisibility(View.GONE);
    }

    @Override
    public void setData(Map<String, ArrayList<NationalCoachBean.CategoryBean>> data) {

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new MenCoachFragment(context));
        fragments.add(new WomenCoachFragment(context));

        mAdapter = new CoachlistFragmentAdapter(getChildFragmentManager(),fragments);
        //给viewpager设置适配器
        viewPager.setAdapter(mAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void refresh(Map<String, ArrayList<NationalCoachBean.CategoryBean>> menuBeen) {

    }

    @Override
    public void loadMoreData(Map<String, ArrayList<NationalCoachBean.CategoryBean>> menuBeen) {

    }

    @Override
    public void onError() {
        progressBar.setVisibility(View.GONE);
        error_image.setVisibility(View.VISIBLE);
    }
}
