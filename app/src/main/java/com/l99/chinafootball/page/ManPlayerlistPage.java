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
import com.l99.chinafootball.adapter.PlayerFragmentAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.NationalPlayerBean;
import com.l99.chinafootball.fragment.FragmentSwitcher;
import com.l99.chinafootball.fragment.innerfragment.PlayerFragment;
import com.l99.chinafootball.presenter.PlayerlistPresenter;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 78101 on 2016/7/5.
 * 男足页面
 */
@SuppressLint("ValidFragment")
public class ManPlayerlistPage extends BaseFragment implements IView.ViewPro<ArrayList<Map<String, ArrayList<NationalPlayerBean.CategoryBean.PlayersBean>>> > {

    private PlayerlistPresenter presenter;
    private ProgressBar progressBar;
    private ImageView error_image;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private PlayerFragmentAdapter mAdapter;

    public ManPlayerlistPage(Context context) {
        super(context);
        presenter=new PlayerlistPresenter(this);
    }

    @Override
    public String getTAG() {
        return FragmentSwitcher.MAN_PLAYERLIST_PAGE;
    }

    @Override
    public void initView(View view) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.closeRightMenu();
        progressBar = (ProgressBar) view.findViewById(R.id.pb_playerlist_page);
        error_image = (ImageView) view.findViewById(R.id.error_playerlist_page);
        viewPager = (ViewPager) view.findViewById(R.id.vp_playerlist_page);
        tabLayout = (TabLayout) view.findViewById(R.id.tl_playlist_page);
    }

    @Override
    public void initData() {
        presenter.getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.playerlist_page;
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
    public void setData(ArrayList<Map<String, ArrayList<NationalPlayerBean.CategoryBean.PlayersBean>>> data) {
        Map<String, ArrayList<NationalPlayerBean.CategoryBean.PlayersBean>> map1 = data.get(0);
        ArrayList<NationalPlayerBean.CategoryBean.PlayersBean> playersBeans1 = map1.get("国家队");
        ArrayList<NationalPlayerBean.CategoryBean.PlayersBean> playersBeans2 = map1.get("国奥队");
        ArrayList<NationalPlayerBean.CategoryBean.PlayersBean> playersBeans3 = map1.get("国青队");
        ArrayList<NationalPlayerBean.CategoryBean.PlayersBean> playersBeans4 = map1.get("五人制");
        ArrayList<NationalPlayerBean.CategoryBean.PlayersBean> playersBeans5 = map1.get("沙滩足球");

        List<PlayerFragment> fragments = new ArrayList<PlayerFragment>();
        fragments.add(new PlayerFragment(context,playersBeans1));
        fragments.add(new PlayerFragment(context,playersBeans2));
        fragments.add(new PlayerFragment(context,playersBeans3));
        fragments.add(new PlayerFragment(context,playersBeans4));
        fragments.add(new PlayerFragment(context,playersBeans5));

        mAdapter = new PlayerFragmentAdapter(getChildFragmentManager(),fragments);
        //给viewpager设置适配器
        viewPager.setAdapter(mAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void refresh(ArrayList<Map<String, ArrayList<NationalPlayerBean.CategoryBean.PlayersBean>>> menuBeen) {

    }

    @Override
    public void loadMoreData(ArrayList<Map<String, ArrayList<NationalPlayerBean.CategoryBean.PlayersBean>>> menuBeen) {

    }

    @Override
    public void onError() {
        progressBar.setVisibility(View.GONE);
        error_image.setVisibility(View.VISIBLE);
    }


}
