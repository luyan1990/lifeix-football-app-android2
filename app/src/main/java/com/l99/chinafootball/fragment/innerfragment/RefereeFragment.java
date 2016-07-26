package com.l99.chinafootball.fragment.innerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.l99.chinafootball.R;
import com.l99.chinafootball.adapter.RefereeListAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.RefereesBean;
import com.l99.chinafootball.presenter.RefereelistPresenter;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by lifeix-101 on 2016/7/4.
 * 裁判员 -- 裁判员页面
 */


@SuppressLint("ValidFragment")
public class RefereeFragment extends BaseFragment implements IView.ViewPro<ArrayList<ArrayList<RefereesBean.CategoryBean.RefereeBean>>> {

    private final RefereelistPresenter presenter;
    private XRefreshView xRefreshView;
    private StickyListHeadersListView stickyListHeadersListView;

    private ArrayList<ArrayList<RefereesBean.CategoryBean.RefereeBean>> list;
    private RefereeListAdapter mAdapter;

    public RefereeFragment(Context context) {
        super(context);
        presenter = new RefereelistPresenter(this);
    }


    @Override
    public String getTAG() {
        return RefereeFragment.class.getSimpleName();
    }

    @Override
    public void initView(View view) {
        xRefreshView = (XRefreshView) view.findViewById(R.id.xfv_fragment_referee);
        stickyListHeadersListView = (StickyListHeadersListView) view.findViewById(R.id.slhlv_fragment_referee);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置是否可以下拉刷新
        xRefreshView.setPullRefreshEnable(true);
        // 设置静默加载模式
        xRefreshView.setSlienceLoadMore();
        // 设置是否可以上拉加载
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(true);
        // 设置可以自动刷新
        //xRefreshView.setAutoRefresh(true);
        //头部固定的时间
        xRefreshView.setPinnedTime(1000);

        xRefreshView.setMoveForHorizontal(true);

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                super.onRefresh();
                LogUtil.e("下拉刷新");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xRefreshView.stopRefresh();
                        presenter.getData();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore(boolean isSlience) {
                super.onLoadMore(isSlience);
                LogUtil.e("加载更多");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xRefreshView.stopLoadMore();
                    }
                }, 1000);
            }
        });

    }

    @Override
    public void initData() {
        presenter.getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_referee;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(ArrayList<ArrayList<RefereesBean.CategoryBean.RefereeBean>> data) {
        mAdapter = new RefereeListAdapter(data);
        stickyListHeadersListView.setAdapter(mAdapter);
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
