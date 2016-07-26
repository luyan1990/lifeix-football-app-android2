package com.l99.chinafootball.model;

import com.l99.chinafootball.bean.ElearningQuizCategoriesTreeBean;
import com.l99.chinafootball.bean.ElearningQuizPageListBean;
import com.l99.chinafootball.bean.ElearningTrainingCategoriesTreeBean;
import com.l99.chinafootball.bean.ElearningTrainingPageListBean;
import com.l99.chinafootball.bean.ElearningTrainingSubCategoriesBean;
import com.l99.chinafootball.utils.HttpUtils;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/6/27.
 */
public class ElearningApi {

    private String url = Url.BASE_URL + "/elearning";

    private ArrayList<ElearningQuizCategoriesTreeBean> elearningQuizCategoriesTreeBeans;

    private ArrayList<ElearningTrainingCategoriesTreeBean> elearningTrainingCategoriesTreeBeans;
    private ArrayList<ElearningQuizPageListBean> elearningQuizPageListBeans;
    private ArrayList<ElearningTrainingPageListBean> elearningTrainingPageListBeans;
    private ArrayList<ElearningTrainingSubCategoriesBean> elearningTrainingSubCategoriesBeans;


    public void getElearningQuizCategoriesTree(String key, final IModel.ModelApiListener<ArrayList<ElearningQuizCategoriesTreeBean>> listener) {
//     http://192.168.50.154:8000/football/elearning/quiz_categories?key=visitor

        String url = this.url + "/quiz_categories" + "?key=" + key;

        LogUtil.e(url);
        listener.onLoading();
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                elearningQuizCategoriesTreeBeans = new ArrayList<>();
                elearningQuizCategoriesTreeBeans = processElearningQuizCategoriesTree(json);
                listener.onSuccess(elearningQuizCategoriesTreeBeans);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });

    }

    private ArrayList<ElearningQuizCategoriesTreeBean> processElearningQuizCategoriesTree(String json) {

        elearningQuizCategoriesTreeBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String id = jsonObject.optString("id");
                String image = jsonObject.optString("image");
                String name = jsonObject.optString("name");
                String text = jsonObject.optString("text");
                int type = jsonObject.optInt("type");
                int rightCount = jsonObject.optInt("rightCount");

                ElearningQuizCategoriesTreeBean elearningQuizCategoriesTreeBean = new ElearningQuizCategoriesTreeBean();
                elearningQuizCategoriesTreeBean.setId(id);
                elearningQuizCategoriesTreeBean.setImage(image);
                elearningQuizCategoriesTreeBean.setName(name);
                elearningQuizCategoriesTreeBean.setText(text);
                elearningQuizCategoriesTreeBean.setType(type);

                elearningQuizCategoriesTreeBeans.add(elearningQuizCategoriesTreeBean);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return elearningQuizCategoriesTreeBeans;
    }

    public void getElearningTrainingCategoriesTree(String key, final IModel.ModelApiListener<ArrayList<ElearningTrainingCategoriesTreeBean>> listener) {
//    http://192.168.50.154:8000/football/elearning/training_categories?key=visitor
        String url = this.url + "/training_categories" + "?key=" + key;
        LogUtil.e(url);
        listener.onLoading();
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                elearningTrainingCategoriesTreeBeans = new ArrayList<>();
                elearningTrainingCategoriesTreeBeans = processElearningTrainingCategoriesTree(json);
                listener.onSuccess(elearningTrainingCategoriesTreeBeans);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
    }

    private ArrayList<ElearningTrainingCategoriesTreeBean> processElearningTrainingCategoriesTree(String json) {
        ArrayList<ElearningTrainingCategoriesTreeBean.CatsBean> catsBeans = null;
        elearningTrainingCategoriesTreeBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String id = jsonObject.optString("id");
                String image = jsonObject.optString("image");
                String name = jsonObject.optString("name");
                int pageCount = jsonObject.optInt("pageCount");
                int type = jsonObject.optInt("type");

                JSONArray cats = jsonObject.optJSONArray("cats");
                if (cats != null && cats.length() > 0) {
                    catsBeans = new ArrayList<>();
                    for (int j = 0; j < cats.length(); j++) {
                        JSONObject cat_obj = (JSONObject) cats.get(j);
                        String cat_id = cat_obj.optString("id");
                        String cat_name = cat_obj.optString("name");
                        int cat_pageCount = cat_obj.optInt("pageCount");
                        int cat_type = cat_obj.optInt("type");

                        String cat_contentUri = cat_obj.optString("contentUri");


                        ElearningTrainingCategoriesTreeBean.CatsBean cat = new ElearningTrainingCategoriesTreeBean.CatsBean();
                        cat.setId(cat_id);
                        cat.setName(cat_name);
                        cat.setPageCount(cat_pageCount);
                        cat.setType(cat_type);
                        if(cat_contentUri != null) {
                            cat.setContentUri(cat_contentUri);
                        }
                        catsBeans.add(cat);

                    }
                }

                ElearningTrainingCategoriesTreeBean elearningTrainingCategoriesTreeBean = new ElearningTrainingCategoriesTreeBean();
                elearningTrainingCategoriesTreeBean.setId(id);
                elearningTrainingCategoriesTreeBean.setImage(image);
                elearningTrainingCategoriesTreeBean.setName(name);
                elearningTrainingCategoriesTreeBean.setPageCount(pageCount);
                elearningTrainingCategoriesTreeBean.setType(type);
                elearningTrainingCategoriesTreeBean.setCats(catsBeans);

                elearningTrainingCategoriesTreeBeans.add(elearningTrainingCategoriesTreeBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return elearningTrainingCategoriesTreeBeans;
    }

    public void getElearningQuizPageList(String key, String categoryId, final IModel.ModelApiListener<ArrayList<ElearningQuizPageListBean>> listener) {
//      http://192.168.50.154:8000/football/elearning/quiz_categories/elearning_q_fmc2014/video_pages
       String url = this.url + "/quiz_categories/" + categoryId + "/video_pages?key=" + key;
        LogUtil.e(url);
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                elearningQuizPageListBeans = new ArrayList<>();
                elearningQuizPageListBeans = processElearningQuizPageList(json);
                listener.onSuccess(elearningQuizPageListBeans);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });

    }

    private ArrayList<ElearningQuizPageListBean> processElearningQuizPageList(String json) {
        elearningQuizPageListBeans = new ArrayList<>();
        ElearningQuizPageListBean.VideosBean.R2Bean r2Bean = new ElearningQuizPageListBean.VideosBean.R2Bean();
        ElearningQuizPageListBean.VideosBean.R1Bean r1Bean = new ElearningQuizPageListBean.VideosBean.R1Bean();
        ElearningQuizPageListBean.VideosBean videosBean = new ElearningQuizPageListBean.VideosBean();
        ElearningQuizPageListBean elearningQuizPageListBean = new ElearningQuizPageListBean();


        ArrayList<ElearningQuizPageListBean.VideosBean.R1Bean> r1Beans = new ArrayList<>();
        ArrayList<ElearningQuizPageListBean.VideosBean.R2Bean> r2Beans = new ArrayList<>();
        ArrayList<ElearningQuizPageListBean.VideosBean> videosBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String id = jsonObject.optString("id");
                int index = jsonObject.optInt("index");
                int type = jsonObject.optInt("type");

                JSONArray videos = jsonObject.optJSONArray("videos");
                for (int j = 0; j < videos.length(); j++) {
                    JSONObject video = (JSONObject) videos.get(j);
                    int video_duration = video.optInt("duration");
                    String video_id = video.optString("id");
                    String video_videoPath = video.optString("videoPath");
                    JSONArray video_r1 = video.optJSONArray("r1");

                    for (int z = 0; z < video_r1.length(); z++) {
                        JSONObject video_r1_obj = (JSONObject) video_r1.get(z);
                        int video_r1_index = video_r1_obj.optInt("index");
                        boolean video_r1_right = video_r1_obj.optBoolean("right");
                        String video_r1_text = video_r1_obj.optString("text");

                        r1Bean.setIndex(video_r1_index);
                        r1Bean.setRight(video_r1_right);
                        r1Bean.setText(video_r1_text);
                        r1Beans.add(r1Bean);
                    }

                    JSONArray video_r2 = video.optJSONArray("r2");
                    for (int z = 0; z < video_r2.length(); z++) {
                        JSONObject video_r2_obj = (JSONObject) video_r2.get(z);
                        int video_r2_index = video_r2_obj.optInt("index");
                        boolean video_r2_right = video_r2_obj.optBoolean("right");
                        String video_r2_text = video_r2_obj.optString("text");


                        r2Bean.setIndex(video_r2_index);
                        r2Bean.setRight(video_r2_right);
                        r2Bean.setText(video_r2_text);
                        r2Beans.add(r2Bean);
                    }

                    videosBean.setDuration(video_duration);
                    videosBean.setId(video_id);
                    videosBean.setVideoPath(video_videoPath);
                    videosBean.setR1(r1Beans);
                    videosBean.setR2(r2Beans);

                    videosBeans.add(videosBean);

                }

                elearningQuizPageListBean.setId(id);
                elearningQuizPageListBean.setIndex(index);
                elearningQuizPageListBean.setType(type);
                elearningQuizPageListBean.setVideos(videosBeans);

                elearningQuizPageListBeans.add(elearningQuizPageListBean);
            }


        } catch (JSONException e) {

        }

        return elearningQuizPageListBeans;
    }

    public void getElearningTrainingPageList(String key, int type , String catId, int start,int limit,final IModel.ModelApiListener<ArrayList<ElearningTrainingPageListBean>> listener) {
        if(type == 1) {
            //请求视频
            // http://192.168.50.154:8000/football/elearning/training_categories/elearning_t_fmc2014_01/video_pages?start=1&limit=120
            //视频的路径
            String url = this.url + "/training_categories/" + catId + "/video_pages?start="+start+"&limit="+limit+"&key=" + key;

            LogUtil.e(url);
            HttpUtils.get(url, new HttpUtils.HttpListener() {
                @Override
                public void onSuccess(String json) {
                    elearningTrainingPageListBeans = new ArrayList<>();
                    elearningTrainingPageListBeans = processElearningTrainingPageList(json);
                    listener.onSuccess(elearningTrainingPageListBeans);
                }

                @Override
                public void onError() {
                    listener.onError();
                }
            });
        }else if(type == 2) {
            // http://o8rg11ywr.bkt.clouddn.com/elearning/training_categories/articles/list.html?type=elearning_t_htc2015_01
            String url = "http://o8rg11ywr.bkt.clouddn.com/elearning/training_categories/articles/list.html?type=" + catId;
        }


    }

    private ArrayList<ElearningTrainingPageListBean> processElearningTrainingPageList(String json) {

        elearningTrainingPageListBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String id = jsonObject.optString("id");
                String title = jsonObject.optString("title");

                JSONObject video = jsonObject.optJSONObject("video");

                String video_id = video.optString("id");
                String video_imagePath = video.optString("imagePath");

                ElearningTrainingPageListBean.VideoBean videoBean = new ElearningTrainingPageListBean.VideoBean();
                videoBean.setId(video_id);
                videoBean.setImagePath(video_imagePath);



                ElearningTrainingPageListBean elearningTrainingPageListBean = new ElearningTrainingPageListBean();
                elearningTrainingPageListBean.setId(id);
                elearningTrainingPageListBean.setTitle(title);
                elearningTrainingPageListBean.setVideo(videoBean);

                elearningTrainingPageListBeans.add(elearningTrainingPageListBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return elearningTrainingPageListBeans;
    }

    public void getElearningTrainingSubCategories(String key, String categoryId, final IModel.ModelApiListener<ArrayList<ElearningTrainingSubCategoriesBean>> listener) {
        // http://192.168.50.154:8000/football/elearning/training_categories/elearning_t_fmc2014/subCats
        String url = this.url + "/training_categories/" + categoryId + "/subCats?key=" + key;
        LogUtil.e(url);
        listener.onLoading();
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                elearningTrainingSubCategoriesBeans = new ArrayList<>();
                elearningTrainingSubCategoriesBeans = processElearningTrainingSubCategories(json);
                listener.onSuccess(elearningTrainingSubCategoriesBeans);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });

    }

    private ArrayList<ElearningTrainingSubCategoriesBean> processElearningTrainingSubCategories(String json) {
        ElearningTrainingSubCategoriesBean elearningTrainingSubCategoriesBean = new ElearningTrainingSubCategoriesBean();
        elearningTrainingSubCategoriesBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String id = jsonObject.optString("id");
                String name = jsonObject.optString("name");
                int pageCount = jsonObject.optInt("pageCount");
                int type = jsonObject.optInt("type");

                elearningTrainingSubCategoriesBean.setId(id);
                elearningTrainingSubCategoriesBean.setName(name);
                elearningTrainingSubCategoriesBean.setPageCount(pageCount);
                elearningTrainingSubCategoriesBean.setType(type);

                elearningTrainingSubCategoriesBeans.add(elearningTrainingSubCategoriesBean);
            }
        } catch (JSONException e) {

        }

        return elearningTrainingSubCategoriesBeans;
    }

}
