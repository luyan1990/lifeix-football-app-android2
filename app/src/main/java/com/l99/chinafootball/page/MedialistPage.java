package com.l99.chinafootball.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.andview.refreshview.XRefreshView;
import com.l99.chinafootball.R;
import com.l99.chinafootball.activity.MainActivity;
import com.l99.chinafootball.activity.MedialistDetailPage;
import com.l99.chinafootball.adapter.MediaListFragmentListViewAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.WemediaPostSearchBean;
import com.l99.chinafootball.fragment.FragmentSwitcher;
import com.l99.chinafootball.presenter.MedialistPagePresenter;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 78101 on 2016/7/5.
 * 资讯页面
 */
@SuppressLint("ValidFragment")
public class MedialistPage  extends BaseFragment implements IView.ViewPro<ArrayList<WemediaPostSearchBean>> {

    private final MedialistPagePresenter presenter;
    private XRefreshView xRefreshView;
    private ListView listView;
    private ImageView errorImage;
    private ProgressBar progressBar;

    private MediaListFragmentListViewAdapter adapter;

    public MedialistPage(Context context) {
        super(context);
        presenter = new MedialistPagePresenter(this);
    }

    @Override
    public String getTAG() {
        return FragmentSwitcher.MEDIALIST_PAGE;
    }

    @Override
    public void initView(View view) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.openRightMenu();
        xRefreshView = (XRefreshView) view.findViewById(R.id.xfv_medialist_page);
        listView = (ListView) view.findViewById(R.id.lv_medialist_page);
        errorImage = (ImageView) view.findViewById(R.id.iv_medialist_page);
        progressBar = (ProgressBar) view.findViewById(R.id.pb_medialist_page);


        // 设置是否可以下拉刷新
        xRefreshView.setPullRefreshEnable(true);
        // 设置静默加载模式
        xRefreshView.setSlienceLoadMore();
        // 设置是否可以上拉加载
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        // 设置可以自动刷新
        xRefreshView.setAutoRefresh(false);
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
                        presenter.getMoreData();
                    }
                }, 1000);

            }
        });
    }

    @Override
    public void initData() {
        LogUtil.e("MedialistPage---initData");
        presenter.getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.medialist_page;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        errorImage.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        errorImage.setVisibility(View.GONE);
    }

    @Override
    public void setData(final ArrayList<WemediaPostSearchBean> data) {
        adapter = new MediaListFragmentListViewAdapter(data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WemediaPostSearchBean wemediaPostSearchBean = data.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("wemediaPostSearchBean", (Serializable) wemediaPostSearchBean);
                Intent intent = new Intent(context, MedialistDetailPage.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    @Override
    public void refresh(ArrayList<WemediaPostSearchBean> menuBeen) {

    }

    @Override
    public void loadMoreData(ArrayList<WemediaPostSearchBean> menuBeen) {
       adapter.addWemediaPostSearchBeans(menuBeen);
    }

    @Override
    public void onError() {
        progressBar.setVisibility(View.GONE);
        errorImage.setVisibility(View.VISIBLE);
    }


}
