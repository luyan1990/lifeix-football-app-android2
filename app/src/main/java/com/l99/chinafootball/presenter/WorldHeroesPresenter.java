package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.HeroesBean;
import com.l99.chinafootball.model.CompetitionTeamApi;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.view.IView;

/**
 * Created by lifeix-101 on 2016/7/18.
 */
public class WorldHeroesPresenter implements IModel.ModelApiListener<HeroesBean>{

    CompetitionTeamApi competitionTeamApi;
    IView.ViewPro viewpro;
    private boolean isloadMore;

    public WorldHeroesPresenter(IView.ViewPro viewpro) {
        this.viewpro = viewpro;
        competitionTeamApi = new CompetitionTeamApi();
    }

    public void getData() {
        isloadMore=false;
        competitionTeamApi.getWorldHeroes("visitor", 5, 1, this);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onSuccess(HeroesBean heroesBean) {
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(heroesBean);
        } else {
            viewpro.setData(heroesBean);
        }
    }

    @Override
    public void onError() {

    }
}
