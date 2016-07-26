package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.ElearningTrainingPageListBean;
import com.l99.chinafootball.model.ElearningApi;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/19.
 */
public class TrainingVideoPresenter implements IModel.ModelApiListener<ArrayList<ElearningTrainingPageListBean>> {

    ElearningApi elearningApi;
    IView.ViewPro viewpro;
    private boolean isloadMore;
    private int start=0;
    private int limit=21;

    public TrainingVideoPresenter(IView.ViewPro viewpro, String id) {
        this.viewpro = viewpro;
        elearningApi = new ElearningApi();
    }

    public void getData(String id) {
        isloadMore=false;
        start=0;
        elearningApi.getElearningTrainingPageList("visitor",1,id,start,limit, this);

    }

    public void getMoreData(String id) {
        isloadMore=true;
        start=start+limit;
        elearningApi.getElearningTrainingPageList("visitor",1,id,start,limit, this);

    }

    @Override
    public void onLoading() {
        viewpro.showLoading();
    }

    @Override
    public void onSuccess(ArrayList<ElearningTrainingPageListBean> elearningTrainingPageListBeans) {
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(elearningTrainingPageListBeans);

        } else {
            viewpro.setData(elearningTrainingPageListBeans);
        }
    }


    @Override
    public void onError() {
        viewpro.onError();
    }
}
