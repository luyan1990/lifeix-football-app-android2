package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.FractionPageBean;
import com.l99.chinafootball.model.FractionPageApi;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.view.IView;

/**
 * Created by lifeix-101 on 2016/7/26.
 */
public class FractionPresenter implements IModel.ModelApiListener<FractionPageBean.DataBean>{

    FractionPageApi fractionPageApi;
    IView.ViewPro viewpro;
    private boolean isloadMore;

    public FractionPresenter(IView.ViewPro viewpro) {
        this.viewpro = viewpro;
        fractionPageApi = new FractionPageApi();
    }

    public void getData(String start_time,String end_time) {
        isloadMore=false;
        fractionPageApi.getRealtimeFraction("visitor", start_time, end_time, this);
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

    @Override
    public void onSuccess(FractionPageBean.DataBean dataBean) {
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(dataBean);
        } else {
            viewpro.setData(dataBean);
        }
    }
}
