package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.ElearningQuizCategoriesTreeBean;
import com.l99.chinafootball.model.ElearningApi;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

/**
 * Created by 78101 on 2016/7/5.
 */
public class QuizCategroyPresenter implements IModel.ModelApiListener<ArrayList<ElearningQuizCategoriesTreeBean>> {

    private  ElearningApi elearningApi;
    private IView.ViewPro mViewpro;
    private boolean isloadMore;

    public QuizCategroyPresenter(IView.ViewPro viewpro) {
        elearningApi = new ElearningApi();
        mViewpro=viewpro;
    }
    public void getData() {
        isloadMore =false;
        elearningApi.getElearningQuizCategoriesTree("visitor", this);

    }

    public  void getMoreData(){
        isloadMore =true;
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onSuccess(ArrayList<ElearningQuizCategoriesTreeBean> elearningQuizCategoriesTreeBeen) {
        mViewpro.setData(elearningQuizCategoriesTreeBeen);
    }


    @Override
    public void onError() {

    }
}
