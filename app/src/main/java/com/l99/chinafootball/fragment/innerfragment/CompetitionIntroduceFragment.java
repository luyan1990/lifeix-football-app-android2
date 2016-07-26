package com.l99.chinafootball.fragment.innerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.l99.chinafootball.R;
import com.l99.chinafootball.adapter.CompetitionIntroduceListViewAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.CompetitionBean;
import com.l99.chinafootball.bean.CompetitionIntroduceBean;
import com.l99.chinafootball.presenter.CompetitionIntroducePresenter;
import com.l99.chinafootball.ui.FullyLinearLayoutManager;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lifeix-101 on 2016/7/7.
 * 赛事介绍
 */
@SuppressLint("ValidFragment")
public class CompetitionIntroduceFragment extends BaseFragment implements IView.ViewPro<CompetitionBean> {

    private final CompetitionIntroducePresenter presenter;
    private RecyclerView recyclerview;

    public CompetitionIntroduceFragment(Context context) {
        super(context);
        presenter = new CompetitionIntroducePresenter(this);
    }


    @Override
    public String getTAG() {
        return CompetitionIntroduceFragment.class.getSimpleName();
    }

    @Override
    public void initView(View view) {
        recyclerview = (RecyclerView) view.findViewById(R.id.rcy_fragment_competitionintroduce);
        FullyLinearLayoutManager manager = new FullyLinearLayoutManager(context);
        manager.setOrientation(FullyLinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
    }

    @Override
    public void initData() {
        LogUtil.e("CompetitionIntroduceFragment---initData");
        presenter.getData();
    }

    @Override
    public int getLayoutId() {
        return  R.layout.fragment_competitionintroduce;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(CompetitionBean data) {

        List<CompetitionBean.MatchesBean> matches = data.getMatches();

        ArrayList<CompetitionIntroduceBean> AcompetitionIntroduceBeans = new ArrayList<>();
        ArrayList<CompetitionIntroduceBean> BcompetitionIntroduceBeans = new ArrayList<>();

        Map<String,ArrayList<CompetitionIntroduceBean>> map = new HashMap<>();

        for(int i = 0; i < matches.size(); i++) {

            CompetitionBean.MatchesBean matchesBean = matches.get(i);
            String group = matchesBean.getGroup();
            long startTime = matchesBean.getStartTime();
            long startDate = matchesBean.getStartDate();
            //客场
            CompetitionBean.MatchesBean.AwayTeamBean awayTeam = matchesBean.getAwayTeam();
            CompetitionBean.MatchesBean.AwayTeamBean.TeamInfoBean away_teamInfo = awayTeam.getTeamInfo();
            String away_flag = away_teamInfo.getFlag();
            String away_name = away_teamInfo.getName();

            CompetitionBean.MatchesBean.HostTeamBean hostTeam = matchesBean.getHostTeam();
            CompetitionBean.MatchesBean.HostTeamBean.TeamInfoBean host_teamInfo = hostTeam.getTeamInfo();
            String host_flag = host_teamInfo.getFlag();
            String host_name = host_teamInfo.getName();

            if(group.equals("A")) {
                AcompetitionIntroduceBeans.add(new CompetitionIntroduceBean(host_name,host_flag,away_name,away_flag,startDate,startTime));
            }else {
                BcompetitionIntroduceBeans.add(new CompetitionIntroduceBean(host_name,host_flag,away_name,away_flag,startDate,startTime));
            }

        }

        map.put("A", AcompetitionIntroduceBeans);
        map.put("B", BcompetitionIntroduceBeans);

        CompetitionIntroduceListViewAdapter adapter = new CompetitionIntroduceListViewAdapter(context,map);
        adapter.setCategory(data.getCompetitionCategory());
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void refresh(CompetitionBean menuBeen) {

    }

    @Override
    public void loadMoreData(CompetitionBean menuBeen) {

    }

    @Override
    public void onError() {

    }
}
