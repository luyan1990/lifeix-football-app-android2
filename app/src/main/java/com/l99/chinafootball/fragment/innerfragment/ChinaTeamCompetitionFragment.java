package com.l99.chinafootball.fragment.innerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.l99.chinafootball.R;
import com.l99.chinafootball.adapter.ChinaTeamCompetitionListViewAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.CompetitionTeamBean;
import com.l99.chinafootball.presenter.ChinaTeamCompetitionPresenter;
import com.l99.chinafootball.ui.FullyLinearLayoutManager;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/7.
 * 中国队赛程
 */
@SuppressLint("ValidFragment")
public class ChinaTeamCompetitionFragment extends BaseFragment implements IView.ViewPro<ArrayList<CompetitionTeamBean>> {

    private final ChinaTeamCompetitionPresenter presenter;
    private RecyclerView recyclerview;
    private ChinaTeamCompetitionListViewAdapter adapter;

    public ChinaTeamCompetitionFragment(Context context) {
        super(context);
        presenter = new ChinaTeamCompetitionPresenter(this);
    }


    @Override
    public String getTAG() {
        return ChinaTeamCompetitionFragment.class.getSimpleName();
    }

    @Override
    public void initView(View view) {
        recyclerview = (RecyclerView) view.findViewById(R.id.rcy_fragment_chinateamcompetition);
        FullyLinearLayoutManager manager = new FullyLinearLayoutManager(context);
        manager.setOrientation(FullyLinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
    }

    @Override
    public void initData() {
        LogUtil.e("ChinaTeamCompetitionFragment---initData");
        presenter.getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chinateamcompetition;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(ArrayList<CompetitionTeamBean> data) {

        adapter = new ChinaTeamCompetitionListViewAdapter(context,data);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void refresh(ArrayList<CompetitionTeamBean> menuBeen) {

    }

    @Override
    public void loadMoreData(ArrayList<CompetitionTeamBean> menuBeen) {

    }

    @Override
    public void onError() {

    }
}
