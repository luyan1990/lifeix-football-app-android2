package com.l99.chinafootball.model;

import com.l99.chinafootball.bean.MenuBean;
import com.l99.chinafootball.bean.WemediaRightListBean;
import com.l99.chinafootball.utils.HttpUtils;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/6/28.
 */
public class MenuApi {


    private String url =  Url.BASE_URL+"/category/menus";


    private ArrayList<MenuBean> menuBeans;
    private ArrayList<WemediaRightListBean> wemediaRightListBeans;


    public void getMenuList(String key, String platform, final IModel.ModelApiListener<ArrayList<MenuBean>> listener){
        //http://192.168.50.154:8000/football/category/menus/app?key=visitor
        String url = this.url +"/"+platform+"?key="+key;
        LogUtil.e(url);
        listener.onLoading();
      /*  String app = AssetsUtils.getFromAssets("app.txt");
        Log.e("TAG", app);
        menuBeans = processMenuList(app);
        listener.onSuccess(menuBeans);*/
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                menuBeans = new ArrayList<>();
                menuBeans = processMenuList(json);
                listener.onSuccess(menuBeans);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
    }

    private ArrayList<MenuBean> processMenuList(String json) {
        menuBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++) {
              JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String iconUrl = jsonObject.optString("iconUrl");
                String id = jsonObject.optString("id");
                String name = jsonObject.optString("name");
                String page = jsonObject.optString("page");

                MenuBean menuBean = new MenuBean();
                menuBean.setIconUrl(iconUrl);
                menuBean.setId(id);
                menuBean.setName(name);
                menuBean.setPage(page);
                menuBeans.add(menuBean);

            }

        } catch (JSONException e) {

        }

        return menuBeans;
    }

    public void getWemediaRightList(String key, String platform, final IModel.ModelApiListener<ArrayList<WemediaRightListBean>> listener){
        // http://192.168.50.154:8000/football/category/menus/app_wemedia?key=visitor
        String url = this.url +"/"+platform+"?key="+key;
        LogUtil.e(url);
        listener.onLoading();
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                wemediaRightListBeans = new ArrayList<WemediaRightListBean>();
                wemediaRightListBeans = processWemediaRightList(json);
                listener.onSuccess(wemediaRightListBeans);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
    }

    private ArrayList<WemediaRightListBean> processWemediaRightList(String json) {

        ArrayList<WemediaRightListBean> wemediaRightListBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String iconUrl = jsonObject.optString("iconUrl");
                String id = jsonObject.optString("id");
                String name = jsonObject.optString("name");
                String page = jsonObject.optString("page");
                WemediaRightListBean wemediaRightListBean = new WemediaRightListBean();
                wemediaRightListBean.setIconUrl(iconUrl);
                wemediaRightListBean.setId(id);
                wemediaRightListBean.setName(name);
                wemediaRightListBean.setPage(page);

                wemediaRightListBeans.add(wemediaRightListBean);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return wemediaRightListBeans;
    }


}
