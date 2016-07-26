package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.ElearningTrainingCategoriesTreeBean;
import com.l99.chinafootball.model.ElearningApi;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

/**
 * Created by 78101 on 2016/7/5.
 */
public class TrainingCategoryPresenter implements IModel.ModelApiListener<ArrayList<ElearningTrainingCategoriesTreeBean>>{

    ElearningApi elearningApi;
    IView.ViewPro viewpro;
    private boolean isloadMore;

    public TrainingCategoryPresenter(IView.ViewPro viewpro) {
        this.viewpro = viewpro;
        elearningApi = new ElearningApi();
    }

    public void getData() {
        isloadMore=false;
        elearningApi.getElearningTrainingCategoriesTree("visitor",this);
    }

    public  void getMoreData(){
        isloadMore=true;
    }

    @Override
    public void onLoading() {
        viewpro.showLoading();
    }

    @Override
    public void onSuccess(ArrayList<ElearningTrainingCategoriesTreeBean> elearningTrainingCategoriesTreeBeans) {
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(elearningTrainingCategoriesTreeBeans);

        } else {
            viewpro.setData(elearningTrainingCategoriesTreeBeans);
        }
    }


    @Override
    public void onError() {
        viewpro.onError();
    }
}
