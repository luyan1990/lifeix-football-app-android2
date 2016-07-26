package com.l99.chinafootball.model;

import com.l99.chinafootball.bean.WemediaTopBean;
import com.l99.chinafootball.utils.HttpUtils;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/6/30.
 */
public class WemediaTopApi {

    private String url =  Url.BASE_URL+"/wemedia/tops";

    private ArrayList<WemediaTopBean> wemediaTopBeans;

    public void getTopPosts(String key ,long categoryIds ,final IModel.ModelApiListener listener) {
//  //http://192.168.50.154:8000/football/wemedia/tops?key=visitor&categoryIds=8089916318445

        listener.onLoading();
        //   // http://192.168.50.154:8000/football/wemedia/posts/search?key=visitor
        String url = this.url +"?key="+key+"&categoryIds="+categoryIds;
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                wemediaTopBeans = processTopsPosts(json);
                listener.onSuccess(wemediaTopBeans);

            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
        LogUtil.e(url);
    }

    private ArrayList<WemediaTopBean> processTopsPosts(String json) {

        WemediaTopBean.Author author = new WemediaTopBean.Author();
        wemediaTopBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                JSONObject author_obj = jsonObject.optJSONObject("author");
                String author_image = author_obj.optString("image");
                String author_name = author_obj.optString("name");

                author.setName(author_name);
                author.setImage(author_image);

                JSONArray categoryIds = jsonObject.optJSONArray("categoryIds");
                if(categoryIds != null && categoryIds.length() > 0) {
                    for(int j = 0; j < categoryIds.length(); j++) {

                     }
                }


                boolean containVideo = jsonObject.optBoolean("containVideo");
                long createTime = jsonObject.optLong("createTime");
                String id = jsonObject.optString("id");
                String image = jsonObject.optString("image");

                JSONArray images = jsonObject.optJSONArray("images");
                for(int j = 0; j < images.length(); j++) {

                }

                String source = jsonObject.optString("source");
                int status = jsonObject.optInt("status");
                String title = jsonObject.optString("title");
                String url = jsonObject.optString("url");

                WemediaTopBean wemediaTopBean = new WemediaTopBean();
                wemediaTopBean.setAuthor(author);
                wemediaTopBean.setContainVideo(containVideo);
                wemediaTopBean.setCreateTime(createTime);
                wemediaTopBean.setId(id);
                wemediaTopBean.setImage(image);
                wemediaTopBean.setSource(source);
                wemediaTopBean.setStatus(status);
                wemediaTopBean.setTitle(title);
                wemediaTopBean.setUrl(url);

                wemediaTopBeans.add(wemediaTopBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return wemediaTopBeans;
    }
}
