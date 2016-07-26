package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.CompetitionTeamBean;
import com.l99.chinafootball.model.CompetitionTeamApi;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

/**
 * Created by 78101 on 2016/7/5.
 */
public class ChinaTeamCompetitionPresenter implements IModel.ModelApiListener<ArrayList<CompetitionTeamBean>> {

    CompetitionTeamApi competitionTeamApi;
    IView.ViewPro viewpro;
    private boolean isloadMore;


    public ChinaTeamCompetitionPresenter(IView.ViewPro viewpro) {
        this.viewpro = viewpro;
        competitionTeamApi=new CompetitionTeamApi();
    }

    public void getData() {
        isloadMore=false;
        competitionTeamApi.getCompetitionTeam("visitor", 5, 1, this);
    }

    @Override
    public void onSuccess(ArrayList<CompetitionTeamBean> competitionTeamBeans) {
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(competitionTeamBeans);
        } else {
            viewpro.setData(competitionTeamBeans);
        }
    }


    @Override
    public void onError() {
        viewpro.onError();
    }

    public  void getMoreData(){
        isloadMore=true;
    }

    @Override
    public void onLoading() {
        viewpro.showLoading();
    }
}
