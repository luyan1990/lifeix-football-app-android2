package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.NationalPlayerBean;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.model.PlayerApi;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 78101 on 2016/7/5.
 */
public class PlayerlistPresenter implements IModel.ModelApiListener<ArrayList<Map<String, ArrayList<NationalPlayerBean.CategoryBean.PlayersBean>>>> {

    PlayerApi playerApi;
    IView.ViewPro viewpro;
    private boolean isloadMore;


    public PlayerlistPresenter(IView.ViewPro viewpro) {
        this.viewpro = viewpro;
        playerApi=new PlayerApi();
    }

    public void getData() {
        isloadMore=false;
        playerApi.getNationalPlayer("visitor",this);
    }

    public  void getMoreData(){
        isloadMore=true;
    }

    @Override
    public void onLoading() {
        viewpro.showLoading();
    }

    @Override
    public void onSuccess(ArrayList<Map<String, ArrayList<NationalPlayerBean.CategoryBean.PlayersBean>>> maps) {
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(maps);

        } else {
            viewpro.setData(maps);
        }
    }



    @Override
    public void onError() {
        viewpro.onError();
    }
}
