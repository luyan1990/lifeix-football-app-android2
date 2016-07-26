package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.BasicPlayerBean;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.model.PlayerApi;
import com.l99.chinafootball.view.IView;

/**
 * Created by lifeix-101 on 2016/7/22.
 */
public class PlayerDetailPresenter implements IModel.ModelApiListener<BasicPlayerBean>{

    PlayerApi playerApi;
    IView.ViewPro viewpro;
    private boolean isloadMore;

    public PlayerDetailPresenter(IView.ViewPro viewpro) {
        this.viewpro = viewpro;
        playerApi = new PlayerApi();
    }

    public void getData(String playerId) {
        isloadMore=false;
        playerApi.getBasicPlayer("visitor",playerId ,this);
    }



    @Override
    public void onSuccess(BasicPlayerBean basicPlayerBean) {
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(basicPlayerBean);
        } else {
            viewpro.setData(basicPlayerBean);
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
