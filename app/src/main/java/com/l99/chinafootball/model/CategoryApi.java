package com.l99.chinafootball.model;

import com.l99.chinafootball.bean.CategoryBean;
import com.l99.chinafootball.utils.HttpUtils;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/6/24.
 */
public class CategoryApi {

    private String url =  Url.BASE_URL+"/category/categories";
    private ArrayList<CategoryBean> categoryBeans;

    public void getCategoryList(String key, final IModel.ModelApiListener<ArrayList<CategoryBean>> listener) {
//       http://192.168.50.154:8000/football/category/categories?key=visitor
        String url = this.url +"?key="+key;
        LogUtil.e(url);
        listener.onLoading();
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                categoryBeans = processCategoryList(json);
                listener.onSuccess(categoryBeans);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });

    }

    private ArrayList<CategoryBean> processCategoryList(String json) {
        ArrayList<CategoryBean.CategoriesBean> categoriesBeans = new ArrayList<>();
        ArrayList<CategoryBean> categoryBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++) {
               JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String iconUrl = jsonObject.optString("iconUrl");
                String id = jsonObject.optString("id");
                String name = jsonObject.optString("name");

                JSONArray categories = jsonObject.optJSONArray("categories");
                if(categories!=null && categories.length()>0) {
                    for (int j=0 ; j<categories.length() ; j++) {
                        JSONObject object = (JSONObject) categories.get(j);
                        String categories_iconUrl = object.getString("iconUrl");
                        String categories_id = object.getString("id");
                        String categories_name = object.getString("name");

                        CategoryBean.CategoriesBean categoriesBean = new CategoryBean.CategoriesBean();
                        categoriesBean.setIconUrl(categories_iconUrl);
                        categoriesBean.setId(categories_id);
                        categoriesBean.setName(categories_name);
                        categoriesBeans.add(categoriesBean);
                    }
                }

                CategoryBean categoryBean = new CategoryBean();
                categoryBean.setName(name);
                categoryBean.setIconUrl(iconUrl);
                categoryBean.setId(id);
                categoryBean.setCategories(categoriesBeans);
                categoryBeans.add(categoryBean);

            }

            LogUtil.e(categoryBeans.size()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categoryBeans;
    }

}
