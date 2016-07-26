package com.l99.chinafootball.model;

/**
 * Created by 78101 on 2016/7/1.
 */
public class IModel {

    public interface ModelApiListener<T>{
        void onLoading();
        void onSuccess(T t);
        void onError();
    }
}


