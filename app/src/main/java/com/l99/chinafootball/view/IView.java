package com.l99.chinafootball.view;

import com.l99.chinafootball.bean.MenuBean;

import java.util.ArrayList;

/**
 *
 */
public class IView <T>{
    /**
     * Mainactivity V 层接口
     */
    public interface MainIView{
        void setTitle(String title);
        void switchFragment(String TAG);
    }
    /**
     * 左侧菜单 V 层接口
     */
    public interface MenuLeftView{
       void setData(ArrayList<MenuBean> menuBeen);
    }

    /**
     * @param <T>只加载一次的View 层接口
     */
    public interface BaseView <T>{
        void showLoading();
        void hideLoading();
        void setData(ArrayList<T> data);
    }
    /**
     * @param <T>多次加载的View 层接口
     */
    public interface ViewPro<T> {
        void showLoading();
        void hideLoading();
        void setData(T data);
        void refresh(T menuBeen);
        void loadMoreData(T menuBeen);
        void onError();
    }


}
