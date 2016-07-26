package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.CompetitionBean;
import com.l99.chinafootball.model.CompetitionApi;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.view.IView;

/**
 * Created by lifeix-101 on 2016/7/18.
 */
public class CompetitionIntroducePresenter implements IModel.ModelApiListener<CompetitionBean>{


    CompetitionApi competitionApi;

    IView.ViewPro viewpro;
    private boolean isloadMore;

    public CompetitionIntroducePresenter(IView.ViewPro viewpro) {
        this.viewpro = viewpro;
        competitionApi = new CompetitionApi();
    }

    public void getData() {
        isloadMore=false;
        competitionApi.getCompetition("visitor", 8089916318445l, this);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onSuccess(CompetitionBean competitionBean) {
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(competitionBean);
        } else {
            viewpro.setData(competitionBean);
        }
    }

    @Override
    public void onError() {

    }
}
