package com.l99.chinafootball.fragment.innerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.andview.refreshview.XRefreshView;
import com.l99.chinafootball.R;
import com.l99.chinafootball.adapter.TrainingFragmentListViewAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.ElearningTrainingCategoriesTreeBean;
import com.l99.chinafootball.model.ElearningApi;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.utils.LogUtil;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/4.
 */
@SuppressLint("ValidFragment")
public class TrainingFragment extends BaseFragment implements IModel.ModelApiListener<ArrayList<ElearningTrainingCategoriesTreeBean>> {

    private XRefreshView xRefreshView;
    private ListView listView;
    private ImageView errorImage;
    private ProgressBar progressBar;
    private ElearningApi elearningApi;

    private TrainingFragmentListViewAdapter adapter;

    public TrainingFragment(Context context) {
        super(context);
    }


    @Override
    public String getTAG() {
        return TrainingFragment.class.getSimpleName();
    }

    @Override
    public void initView(View view) {
        xRefreshView = (XRefreshView) view.findViewById(R.id.xfv_fragment_training);
        listView = (ListView) view.findViewById(R.id.lv_fragment_training);
        errorImage = (ImageView) view.findViewById(R.id.iv_fragment_training);
        progressBar = (ProgressBar) view.findViewById(R.id.pb_fragment_training);

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
        elearningApi = new ElearningApi();
        elearningApi.getElearningTrainingCategoriesTree("visitor",this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_training;
    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
        errorImage.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(ArrayList<ElearningTrainingCategoriesTreeBean> elearningTrainingCategoriesTreeBeans) {
        progressBar.setVisibility(View.GONE);
        errorImage.setVisibility(View.GONE);

        adapter = new TrainingFragmentListViewAdapter(context,elearningTrainingCategoriesTreeBeans);
        listView.setAdapter(adapter);
    }

    @Override
    public void onError() {
        progressBar.setVisibility(View.GONE);
        errorImage.setVisibility(View.VISIBLE);
    }
}
