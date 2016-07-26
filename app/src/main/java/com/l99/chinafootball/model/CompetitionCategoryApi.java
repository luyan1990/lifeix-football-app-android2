package com.l99.chinafootball.model;

import com.l99.chinafootball.bean.CompetitionCategoryBean;
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
public class CompetitionCategoryApi {

    private String url =  Url.BASE_URL+"/games/competitionCategory";

    private ArrayList<CompetitionCategoryBean> competitionCategoryBeans;

    public CompetitionCategoryApi(String url) {
        this.url = url;
    }

    public void getCompetitionCategories(String key, final IModel.ModelApiListener<ArrayList<CompetitionCategoryBean>> listener) {
//      http://192.168.50.154:8000/football/games/competitionCategory?key=visitor
        String url = this.url +"?key="+key;
        LogUtil.e(url);
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                competitionCategoryBeans = new ArrayList<>();
                competitionCategoryBeans = processCompetitionCategories(json);
                listener.onSuccess(competitionCategoryBeans);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
    }

    private ArrayList<CompetitionCategoryBean> processCompetitionCategories(String json) {
        competitionCategoryBeans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i=0; i < jsonArray.length() ; i++) {
              JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String flag = jsonObject.getString("flag");
                long id = jsonObject.getLong("id");
                String name = jsonObject.getString("name");
                int scheduleType = jsonObject.optInt("scheduleType");
                int teamType = jsonObject.optInt("teamType");

                CompetitionCategoryBean competitionCategoryBean = new CompetitionCategoryBean();
                competitionCategoryBean.setFlag(flag);
                competitionCategoryBean.setId(id);
                competitionCategoryBean.setName(name);
                competitionCategoryBean.setScheduleType(scheduleType);
                competitionCategoryBean.setTeamType(teamType);

                competitionCategoryBeans.add(competitionCategoryBean);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return competitionCategoryBeans;
    }

}
