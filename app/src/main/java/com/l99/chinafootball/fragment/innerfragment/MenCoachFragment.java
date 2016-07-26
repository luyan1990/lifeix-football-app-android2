package com.l99.chinafootball.fragment.innerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.l99.chinafootball.R;
import com.l99.chinafootball.activity.CoachDetailPage;
import com.l99.chinafootball.adapter.MenCoachGridViewAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.NationalCoachBean;
import com.l99.chinafootball.presenter.CoachlistPresenter;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lifeix-101 on 2016/7/1.
 * 男足教练页面
 */
@SuppressLint("ValidFragment")
public class MenCoachFragment extends BaseFragment implements IView.ViewPro<Map<String,ArrayList<NationalCoachBean.CategoryBean>>> {

    private final CoachlistPresenter presenter;
    private XRefreshView xRefreshView;
    private StickyGridHeadersGridView stickyGridHeadersGridView;
    private MenCoachGridViewAdapter mAdapter;
    private List<NationalCoachBean.CategoryBean.CoachesBean> coachesBeans=new ArrayList<>();

    @Override
    public String getTAG() {
        return MenCoachFragment.class.getSimpleName();
    }

    public MenCoachFragment(Context context) {
        super(context);
        presenter = new CoachlistPresenter(this);
    }

    @Override
    public void initView(View view) {
        xRefreshView = (XRefreshView) view.findViewById(R.id.xfv_fragment_mencoach);
        stickyGridHeadersGridView = (StickyGridHeadersGridView) view.findViewById(R.id.sghgv_fragment_mencoach);

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
                        presenter.getData();
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
        return R.layout.fragment_mencoach;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(Map<String, ArrayList<NationalCoachBean.CategoryBean>> data) {
        final ArrayList<NationalCoachBean.CategoryBean> men = data.get("男足教练");

        mAdapter = new MenCoachGridViewAdapter(context,men);
        stickyGridHeadersGridView.setAdapter(mAdapter);

        for(int i = 0; i < men.size(); i++) {
            NationalCoachBean.CategoryBean categoryBean = men.get(i);
            List<NationalCoachBean.CategoryBean.CoachesBean> coaches = categoryBean.getCoaches();
            for(int j = 0; j < coaches.size(); j++) {
                NationalCoachBean.CategoryBean.CoachesBean coachesBean = coaches.get(j);
                coachesBeans.add(coachesBean);
            }
        }

        stickyGridHeadersGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "点击" + position, Toast.LENGTH_SHORT).show();

                NationalCoachBean.CategoryBean.CoachesBean coachesBean = coachesBeans.get(position);
                Intent intent = new Intent(context, CoachDetailPage.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("coachBean", coachesBean);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void refresh(Map<String, ArrayList<NationalCoachBean.CategoryBean>> menuBeen) {

    }

    @Override
    public void loadMoreData(Map<String, ArrayList<NationalCoachBean.CategoryBean>> menuBeen) {

    }

    @Override
    public void onError() {

    }
}
