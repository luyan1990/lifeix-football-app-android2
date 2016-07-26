package com.l99.chinafootball.presenter;

import com.l99.chinafootball.bean.MenuBean;
import com.l99.chinafootball.model.IModel;
import com.l99.chinafootball.model.MenuApi;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

/**
 * Created by 78101 on 2016/7/4.
 */
public class LeftMenuPresenter implements IModel.ModelApiListener<ArrayList<MenuBean>>{

    private MenuApi menuApi;
    private IView.MenuLeftView mMenuleftview;

    public LeftMenuPresenter(IView.MenuLeftView menuLeftView) {
        menuApi = new MenuApi();
        mMenuleftview = menuLeftView;
    }
    public void getData(){

        //这里需要你修改，等你完全接手了最后再改吧 model层里面accests要删除
        LogUtil.e("LeftMenuPresenter---getData");
        menuApi.getMenuList("visitor","android",this);
    }

    @Override
    public void onLoading() {
        LogUtil.e("LeftMenuPresenter---onLoading");
    }

    @Override
    public void onSuccess(ArrayList<MenuBean> menuBeen) {
        LogUtil.e("LeftMenuPresenter---onSuccess");
            mMenuleftview.setData(menuBeen);
        //回调View层 LeftFragment
    }

    @Override
    public void onError() {
        LogUtil.e("LeftMenuPresenter---onError");
    }
}
