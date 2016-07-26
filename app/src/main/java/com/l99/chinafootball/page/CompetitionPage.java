package com.l99.chinafootball.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.bumptech.glide.Glide;
import com.l99.chinafootball.R;
import com.l99.chinafootball.activity.MainActivity;
import com.l99.chinafootball.adapter.CompetitionFragmentAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.WemediaTopBean;
import com.l99.chinafootball.fragment.FragmentSwitcher;
import com.l99.chinafootball.fragment.innerfragment.ChinaTeamCompetitionFragment;
import com.l99.chinafootball.fragment.innerfragment.CompetitionIntroduceFragment;
import com.l99.chinafootball.fragment.innerfragment.WorldHeroesFragment;
import com.l99.chinafootball.presenter.CompetitionPagePresenter;
import com.l99.chinafootball.ui.HeadViewPager;
import com.l99.chinafootball.ui.PointView;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.utils.UIUtils;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 78101 on 2016/7/5.
 * 十二强赛页面
 */
@SuppressLint("ValidFragment")
public class CompetitionPage extends BaseFragment implements IView.ViewPro<ArrayList<WemediaTopBean>> {


    private final CompetitionPagePresenter presenter;

    private ProgressBar progressBar;
    private ImageView imageView;
    private XRefreshView xRefreshView;
    private HeadViewPager advertisement;
    private ViewPager content;
    private TabLayout tabLayout;
    private TextView title;
    private PointView pv_point;


    private CompetitionFragmentAdapter mAdapter;

    private int preSelectPosition;
    private int mPreviousPos;// 上一个圆点位置

    public CompetitionPage(Context context) {
        super(context);
        presenter = new CompetitionPagePresenter(this);

    }

    @Override
    public String getTAG() {
        return FragmentSwitcher.COMPETITION_PAGE;
    }

    @Override
    public void initView(View view) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.closeRightMenu();
        progressBar = (ProgressBar) view.findViewById(R.id.pb_competition_page);
        imageView = (ImageView) view.findViewById(R.id.iv_competition_page);
        //   xRefreshView = (XRefreshView) view.findViewById(R.id.xfv_fragment_competition);
        advertisement = (HeadViewPager) view.findViewById(R.id.vp_advertisement);
        pv_point = (PointView) view.findViewById(R.id.pv_advertisement);
        title = (TextView) view.findViewById(R.id.tv_advertisement);
        tabLayout = (TabLayout) view.findViewById(R.id.tl_competition_page);
        advertisement.setRatio(1.504f);
        content = (ViewPager) view.findViewById(R.id.vp2_competition_page);

    }

    @Override
    public void initData() {
        LogUtil.e("CompetitionPage---initData");
        presenter.getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.competition_page;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(final ArrayList<WemediaTopBean> data) {

        pv_point.setPointCount(data.size());
        //装配广播轮播条的数据
        advertisement.setAdapter(new AdvertisementViewPagerAdapter(data));

        //设置无限次滚动
        advertisement.setCurrentItem(data.size() * 10000);

        advertisement.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                position = position % data.size();
                mPreviousPos = position;
                int currentPosition = position % data.size();
                //重新赋值
                preSelectPosition = currentPosition;
                WemediaTopBean wemediaTopBean = data.get(position);
                title.setText(wemediaTopBean.getTitle());
                pv_point.setPointindex(position+1);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 启动自动轮播效果
        new Task().start();

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new ChinaTeamCompetitionFragment(context));
        fragments.add(new CompetitionIntroduceFragment(context));
        fragments.add(new WorldHeroesFragment(context));
        mAdapter = new CompetitionFragmentAdapter(getChildFragmentManager(),fragments);
        //给viewpager设置适配器
        content.setAdapter(mAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(content);
    }

    @Override
    public void refresh(ArrayList<WemediaTopBean> menuBeen) {

    }

    @Override
    public void loadMoreData(ArrayList<WemediaTopBean> menuBeen) {

    }

    @Override
    public void onError() {

    }


    class Task implements Runnable {
        // 启动轮播动画
        public void start() {
            // 要将之前发送的消息移除掉,避免发送重复消息
            UIUtils.getHandler().removeCallbacksAndMessages(null);// 移除所有消息和Runnable(post)
            UIUtils.getHandler().postDelayed(this, 3000);// 发送延时3秒的Runnable
        }

        @Override
        public void run() {
            // 每个3秒会走run方法
            // 跳到下一个页面
            int currentItem = advertisement.getCurrentItem();
            currentItem++;

            advertisement.setCurrentItem(currentItem);
            // 继续发消息
            UIUtils.getHandler().postDelayed(this, 3000);
        }

    }

    class AdvertisementViewPagerAdapter extends PagerAdapter {

        private ArrayList<WemediaTopBean> wemediaTopBeans;

        public AdvertisementViewPagerAdapter(ArrayList<WemediaTopBean> wemediaTopBeans) {
            this.wemediaTopBeans = wemediaTopBeans;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            position = position % wemediaTopBeans.size();
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            Glide.with(context).load(wemediaTopBeans.get(position).getImage()).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
