package com.l99.chinafootball.model;

import com.l99.chinafootball.bean.WemediaPostSearchBean;
import com.l99.chinafootball.utils.HttpUtils;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/6.
 */
public class WemediaPostApi {

    // http://192.168.50.154:8000/football/wemedia/posts/search?key=visitor
    private String url = Url.BASE_URL + "/wemedia/posts/search";

    private ArrayList<WemediaPostSearchBean> wemediaPostSearchBeans;


    public void search(String key,final IModel.ModelApiListener<ArrayList<WemediaPostSearchBean>> listener) {

        listener.onLoading();
        //   http://192.168.50.154:8000/football/wemedia/posts/search?key=visitor
        String url = this.url + "?&key=" + key;
        LogUtil.e(url);
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                wemediaPostSearchBeans = processSearch(json);
                listener.onSuccess(wemediaPostSearchBeans);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
    }

    private ArrayList<WemediaPostSearchBean> processSearch(String json) {
        ArrayList<String> categories1 = new ArrayList<>();
        ArrayList<String> images1 = new ArrayList<>();
        wemediaPostSearchBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                JSONObject author = jsonObject.optJSONObject("author");
                String author_image = author.optString("image");
                String author_name = author.optString("name");

                WemediaPostSearchBean.Author author1 = new WemediaPostSearchBean.Author();
                author1.setImage(author_image);
                author1.setName(author_name);

                JSONArray categories = jsonObject.optJSONArray("categories");
//
//                if(categories!=null && categories.length() >0) {
//
//                    for (int j = 0; j < categories.length(); j++) {
//                    JSONObject category = (JSONObject) categories.get(j);
//
//                   }
//                }


                boolean containVideo = jsonObject.optBoolean("containVideo");
                long createTime = jsonObject.optLong("createTime");
                String id = jsonObject.optString("id");
                String image = jsonObject.optString("image");

                JSONArray images = jsonObject.optJSONArray("images");
                if(images != null && images.length() > 0) {
                    for (int j = 0; j < images.length(); j++) {
                        String images_image = (String) images.get(j);
                        images1.add(images_image);
                    }
                }


                String shareUrl = jsonObject.optString("shareUrl");
                String source = jsonObject.optString("source");
                int status = jsonObject.optInt("status");
                String title = jsonObject.optString("title");
                String url = jsonObject.optString("url");

                WemediaPostSearchBean wemediaPostSearchBean = new WemediaPostSearchBean();
                wemediaPostSearchBean.setAuthor(author1);
                wemediaPostSearchBean.setCategoryIds(categories1);
                wemediaPostSearchBean.setContainVideo(containVideo);
                wemediaPostSearchBean.setCreateTime(createTime);
                wemediaPostSearchBean.setId(id);
                wemediaPostSearchBean.setImage(image);
                wemediaPostSearchBean.setImages(images1);

                wemediaPostSearchBean.setSource(source);
                wemediaPostSearchBean.setStatus(status);
                wemediaPostSearchBean.setTitle(title);
                wemediaPostSearchBean.setUrl(url);
                wemediaPostSearchBean.setShareUrl(shareUrl);

                wemediaPostSearchBeans.add(wemediaPostSearchBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return wemediaPostSearchBeans;
    }

    public void getPostDetail(String key, int categoryId ,final IModel.ModelApiListener<ArrayList<WemediaPostSearchBean>> listener) {

        listener.onLoading();
        //  http://192.168.50.154:8000/football/wemedia/posts/search?categoryId=80891115649354&key=visitor
        String url = this.url + "?categoryId="+categoryId+ "&key=" + key;
        LogUtil.e(url);
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                listener.onSuccess(processPostDetail(json));
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
    }

    private ArrayList<WemediaPostSearchBean> processPostDetail(String json) {
        return processSearch(json);
    }
}
