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
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.l99.chinafootball.R;
import com.l99.chinafootball.activity.MainActivity;
import com.l99.chinafootball.activity.TrainingArticlePage;
import com.l99.chinafootball.activity.TrainingVideolistPage;
import com.l99.chinafootball.adapter.TrainingPageListViewAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.ElearningTrainingCategoriesTreeBean;
import com.l99.chinafootball.fragment.FragmentSwitcher;
import com.l99.chinafootball.presenter.TrainingCategoryPresenter;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 78101 on 2016/7/5.
 * 培训规则页面
 */
@SuppressLint("ValidFragment")
public class TrainingCategoryPage extends BaseFragment  implements IView.ViewPro<ArrayList<ElearningTrainingCategoriesTreeBean>> {

    private XRefreshView xRefreshView;
    private ListView listView;
    private ImageView errorImage;
    private ProgressBar progressBar;
    private TrainingCategoryPresenter presenter;
    private TrainingPageListViewAdapter adapter;

    public TrainingCategoryPage(Context context) {
        super(context);
        presenter = new TrainingCategoryPresenter(this);
    }

    @Override
    public String getTAG() {
        return FragmentSwitcher.TRAINING_CATEGORY_PAGE;
    }

    @Override
    public void initView(View view) {
        LogUtil.e("QuizCategroyPage---initView");
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.closeRightMenu();
        xRefreshView = (XRefreshView) view.findViewById(R.id.xfv_trainging_category_page);
        listView = (ListView) view.findViewById(R.id.lv_trainging_category_page);
        errorImage = (ImageView) view.findViewById(R.id.iv_trainging_category_page);
        progressBar = (ProgressBar) view.findViewById(R.id.pb_trainging_category_page);

// .......
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
        return R.layout.trainging_category_page;
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
    public void setData(final ArrayList<ElearningTrainingCategoriesTreeBean> data) {
        adapter = new TrainingPageListViewAdapter(data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "点击："+position, Toast.LENGTH_SHORT).show();
                ElearningTrainingCategoriesTreeBean elearningTrainingCategoriesTreeBean = data.get(position);
                String name = elearningTrainingCategoriesTreeBean.getName();
                List<ElearningTrainingCategoriesTreeBean.CatsBean> cats = elearningTrainingCategoriesTreeBean.getCats();
                int type = elearningTrainingCategoriesTreeBean.getType();
                if(type == 1) {
                    //跳转到培训视频列表页面
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cats", (Serializable) cats);
                    Intent intent = new Intent(context, TrainingVideolistPage.class);
                    intent.putExtra("name",name);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(type == 2) {
                    //跳转到 培训文章页面
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(context, TrainingArticlePage.class);
                    intent.putExtra("name",name);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }


            }
        });
    }

    @Override
    public void refresh(ArrayList<ElearningTrainingCategoriesTreeBean> menuBeen) {

    }

    @Override
    public void loadMoreData(ArrayList<ElearningTrainingCategoriesTreeBean> menuBeen) {

    }

    @Override
    public void onError() {
        progressBar.setVisibility(View.GONE);
        errorImage.setVisibility(View.GONE);
    }
}
