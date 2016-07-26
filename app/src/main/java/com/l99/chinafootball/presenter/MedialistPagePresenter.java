package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.WemediaPostSearchBean;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.model.WemediaPostApi;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/18.
 */
public class MedialistPagePresenter implements IModel.ModelApiListener<ArrayList<WemediaPostSearchBean>>{

    WemediaPostApi wemediaPostApi;
    IView.ViewPro viewpro;
    private boolean isloadMore;

    private int start=0;
    private int limit=31;


    public MedialistPagePresenter(IView.ViewPro viewpro) {
        this.viewpro = viewpro;
        wemediaPostApi = new WemediaPostApi();
    }

    public void getData() {
        isloadMore=false;
        start=0;
        wemediaPostApi.search("visitor",this);
    }

    public void getMoreData() {
        isloadMore=true;
        start=start+limit;
//        wemediaPostApi.searchMore("visitor",start,limit,this);
    }

    @Override
    public void onLoading() {
        viewpro.showLoading();
    }

    @Override
    public void onSuccess(ArrayList<WemediaPostSearchBean> wemediaPostSearchBeans) {
        viewpro.hideLoading();
        if (isloadMore) {
            viewpro.loadMoreData(wemediaPostSearchBeans);
        } else {
            viewpro.setData(wemediaPostSearchBeans);
        }
    }

    @Override
    public void onError() {
        viewpro.onError();
    }
}
