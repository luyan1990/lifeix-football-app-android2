package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.RefereesBean;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.model.RefereeApi;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 78101 on 2016/7/14.
 */
public class RefereelistPresenter implements IModel.ModelApiListener<Map<String,ArrayList<RefereesBean.CategoryBean.RefereeBean>>> {

    private IView.ViewPro viewpro;
    private boolean isloadMore;
    private final RefereeApi refereeApi;

    public RefereelistPresenter(IView.ViewPro viewpro) {
        this.viewpro = viewpro;
        refereeApi = new RefereeApi();
    }


    public void getData() {
        isloadMore =false;
        refereeApi.getReferees("visitor",this);
    }

    public void getMoreData(){
        isloadMore =true;
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onSuccess(Map<String, ArrayList<RefereesBean.CategoryBean.RefereeBean>> map) {
        ArrayList<ArrayList<RefereesBean.CategoryBean.RefereeBean>> playersBeens = new ArrayList<>();
        playersBeens.add(map.get("主裁判(男)"));
        playersBeens.add(map.get("助理裁判(男)"));
        playersBeens.add(map.get("主裁判(女)"));
        playersBeens.add(map.get("助理裁判(女)"));
        playersBeens.add(map.get("五人制足球(男)"));
        playersBeens.add(map.get("五人制足球(女)"));
        playersBeens.add(map.get("沙滩足球"));
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(playersBeens);
        } else {
            viewpro.setData(playersBeens);
        }
    }

    @Override
    public void onError() {
        viewpro.onError();
    }
}
