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
import com.l99.chinafootball.adapter.RefeerlistFragmentAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.RefereesBean;
import com.l99.chinafootball.fragment.FragmentSwitcher;
import com.l99.chinafootball.fragment.innerfragment.FivePeopleFragment;
import com.l99.chinafootball.fragment.innerfragment.RefereeFragment;
import com.l99.chinafootball.fragment.innerfragment.ShaTanFootballFragment;
import com.l99.chinafootball.presenter.RefereelistPresenter;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 78101 on 2016/7/5.
 * 裁判员页面
 */
@SuppressLint("ValidFragment")
public class RefeerlistPage extends BaseFragment implements IView.ViewPro<ArrayList<ArrayList<RefereesBean.CategoryBean.RefereeBean>>> {

    private final RefereelistPresenter presenter;

    private ProgressBar progressBar;
    private ImageView error_image;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private RefeerlistFragmentAdapter mAdapter;


    public RefeerlistPage(Context context) {
        super(context);
        presenter = new RefereelistPresenter(this);
    }

    @Override
    public String getTAG() {
        return FragmentSwitcher.REFEERLIST_PAGE;
    }

    @Override
    public void initView(View view) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.openRightMenu();
        progressBar = (ProgressBar) view.findViewById(R.id.pb_refeerlist_page);
        error_image = (ImageView) view.findViewById(R.id.error_refeerlist_page);
        viewPager = (ViewPager) view.findViewById(R.id.vp_refeerlist_page);
        tabLayout = (TabLayout) view.findViewById(R.id.tl_refeerlist_page);

    }

    @Override
    public void initData() {
        presenter.getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.refeerlist_page;
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
    public void setData(ArrayList<ArrayList<RefereesBean.CategoryBean.RefereeBean>> data) {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new RefereeFragment(context));
        fragments.add(new FivePeopleFragment(context));
        fragments.add(new ShaTanFootballFragment(context));

        mAdapter = new RefeerlistFragmentAdapter(getChildFragmentManager(),fragments);
        //给viewpager设置适配器
        viewPager.setAdapter(mAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void refresh(ArrayList<ArrayList<RefereesBean.CategoryBean.RefereeBean>> menuBeen) {

    }

    @Override
    public void loadMoreData(ArrayList<ArrayList<RefereesBean.CategoryBean.RefereeBean>> menuBeen) {

    }

    @Override
    public void onError() {

    }


}
