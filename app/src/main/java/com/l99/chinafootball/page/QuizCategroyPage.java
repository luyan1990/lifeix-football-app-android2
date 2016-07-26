package com.l99.chinafootball.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.l99.chinafootball.activity.QuizVideolistPage;
import com.l99.chinafootball.adapter.QuizPageListViewAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.ElearningQuizCategoriesTreeBean;
import com.l99.chinafootball.fragment.FragmentSwitcher;
import com.l99.chinafootball.presenter.QuizCategroyPresenter;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

/**
 * Created by 78101 on 2016/7/5.
 * 规则测试页面
 */
@SuppressLint("ValidFragment")
public class QuizCategroyPage extends BaseFragment implements IView.ViewPro<ArrayList<ElearningQuizCategoriesTreeBean>> {

    private XRefreshView xRefreshView;
    private ListView listView;
    private ImageView errorImage;
    private ProgressBar progressBar;

    private QuizPageListViewAdapter adapter;
    private final QuizCategroyPresenter presenter;

    public QuizCategroyPage(Context context) {
        super(context);
        presenter = new QuizCategroyPresenter(this);

    }

    @Override
    public String getTAG() {
        return FragmentSwitcher.QUIZ_CATEGROY_PAGE;
    }

    @Override
    public void initView(View view) {
        LogUtil.e("QuizCategroyPage---initView");
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.closeRightMenu();
        xRefreshView = (XRefreshView) view.findViewById(R.id.xfv_quiz_category_page);
        listView = (ListView) view.findViewById(R.id.lv_quiz_category_page);
        errorImage = (ImageView) view.findViewById(R.id.iv_quiz_category_page);
        progressBar = (ProgressBar) view.findViewById(R.id.pb_quiz_category_page);

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
        presenter.getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.quiz_category_page;
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
    public void setData(final ArrayList<ElearningQuizCategoriesTreeBean> data) {
        adapter = new QuizPageListViewAdapter(data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "点击：" + position, Toast.LENGTH_SHORT).show();
                ElearningQuizCategoriesTreeBean elearningQuizCategoriesTreeBean = data.get(position);

                String name = elearningQuizCategoriesTreeBean.getName();

                Intent intent = new Intent(context, QuizVideolistPage.class);
                intent.putExtra("name",name);
                startActivity(intent);

            }
        });
    }


    @Override
    public void refresh(ArrayList menuBeen) {

    }

    @Override
    public void loadMoreData(ArrayList menuBeen) {

    }

    @Override
    public void onError() {
        progressBar.setVisibility(View.GONE);
        errorImage.setVisibility(View.VISIBLE);
    }
}
