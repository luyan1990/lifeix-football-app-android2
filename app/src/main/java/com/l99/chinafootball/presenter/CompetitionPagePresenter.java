package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.WemediaTopBean;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.model.WemediaTopApi;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/18.
 */
public class CompetitionPagePresenter implements IModel.ModelApiListener<ArrayList<WemediaTopBean>> {

    WemediaTopApi wemediaTopApi;
    IView.ViewPro viewpro;
    private boolean isloadMore;

    public CompetitionPagePresenter(IView.ViewPro viewpro) {
        this.viewpro = viewpro;
        wemediaTopApi = new WemediaTopApi();
    }

    public void getData() {
        isloadMore=false;
        wemediaTopApi.getTopPosts("visitor", 8089916318445l, this);
    }
    @Override
    public void onLoading() {

    }

    @Override
    public void onSuccess(ArrayList<WemediaTopBean> wemediaTopBeans) {
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(wemediaTopBeans);
        } else {
            viewpro.setData(wemediaTopBeans);
        }
    }

    @Override
    public void onError() {

    }
}
