package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.NationalCoachBean;
import com.l99.chinafootball.model.CoachApi;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 78101 on 2016/7/5.
 */
public class CoachlistPresenter implements IModel.ModelApiListener<Map<String,ArrayList<NationalCoachBean.CategoryBean>>> {

    private IView.ViewPro viewpro;
    private boolean isloadMore;
    CoachApi coachApi;

    public CoachlistPresenter(IView.ViewPro viewpro) {
        this.viewpro = viewpro;
        coachApi = new CoachApi();
    }

    public void getData() {
        isloadMore =false;
        coachApi.getNationalCoach("visitor", this);
    }

    public void getMoreData(){
        isloadMore =true;
    }


    @Override
    public void onLoading() {
        viewpro.showLoading();
    }

    @Override
    public void onSuccess(Map<String,ArrayList<NationalCoachBean.CategoryBean>> map) {
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(map);

        } else {
            viewpro.setData(map);
        }
    }

    @Override
    public void onError() {
        viewpro.onError();
    }


}
