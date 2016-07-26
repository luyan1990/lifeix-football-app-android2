package com.l99.chinafootball.fragment.innerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.l99.chinafootball.R;
import com.l99.chinafootball.adapter.TrainingVideoGridViewAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.ElearningTrainingPageListBean;
import com.l99.chinafootball.presenter.TrainingVideoPresenter;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/19.
 */
@SuppressLint("ValidFragment")
public class TrainingVideoFragment extends BaseFragment implements IView.ViewPro<ArrayList<ElearningTrainingPageListBean>> {

    private String id;
    private TrainingVideoPresenter presenter;
    private RecyclerView recyclerView;
    private XRefreshView xRefreshView;
    private TrainingVideoGridViewAdapter adapter;

    @Override
    public String getTAG() {
        return TrainingVideoFragment.class.getSimpleName();
    }

    public TrainingVideoFragment(Context context, String id) {
        super(context);
        this.id = id;
        presenter = new TrainingVideoPresenter(this,id);
    }

    @Override
    public void initView(View view) {
        xRefreshView = (XRefreshView) view.findViewById(R.id.xfv_fragment_training_video);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_training_video);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void initData() {
        presenter.getData(id);
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
                        presenter.getData(id);
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
                        presenter.getMoreData(id);
                    }
                }, 1000);

            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_training_video;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(ArrayList<ElearningTrainingPageListBean> data) {
        adapter = new TrainingVideoGridViewAdapter(context,data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void refresh(ArrayList<ElearningTrainingPageListBean> menuBeen) {

    }

    @Override
    public void loadMoreData(ArrayList<ElearningTrainingPageListBean> menuBeen) {
      adapter.addElearningTrainingPageListBeans(menuBeen);
    }

    @Override
    public void onError() {

    }
}
