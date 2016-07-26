package com.l99.chinafootball.model;

import com.l99.chinafootball.bean.RefereesBean;
import com.l99.chinafootball.bean.RefereesByLeagueBean;
import com.l99.chinafootball.utils.HttpUtils;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lifeix-101 on 2016/7/5.
 */
public class RefereeApi {

    private String url =  Url.BASE_URL+"/games/referees";


    private Map<String, ArrayList<RefereesBean.CategoryBean.RefereeBean>> map;


    public void getReferees(String key,final IModel.ModelApiListener<Map<String, ArrayList<RefereesBean.CategoryBean.RefereeBean>>> listener){
      /*  String assets = AssetsUtils.getFromAssets("getreferees");
        map = new HashMap<>();
        map = processReferees(assets);
        listener.onSuccess(map);
       if(map!=null) {
           back;
       }*/
        listener.onLoading();
        // http://192.168.50.154:8000/football/games/referees?level=fifa&key=visitor
        String url = this.url +"?level=fifa&key="+key;
        LogUtil.e(url);
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                map = new HashMap<>();
                map = processReferees(json);
                listener.onSuccess(map);
            }

            @Override
            public void onError() {
        listener.onError();
            }
        });
    }

    private Map<String, ArrayList<RefereesBean.CategoryBean.RefereeBean>> processReferees(String json) {

        ArrayList<RefereesBean.CategoryBean.RefereeBean> refereesList = null;
        Map<String, ArrayList<RefereesBean.CategoryBean.RefereeBean>> map = new HashMap<>();
        try {
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String topName = jsonObject.optString("topName");
                JSONArray categorys = jsonObject.optJSONArray("category");

                for(int j = 0; j < categorys.length(); j++) {

                    JSONObject category = (JSONObject) categorys.get(j);
                    String category_categoryName = category.optString("categoryName");
                    JSONArray category_referees = category.optJSONArray("referees");

                    refereesList = new ArrayList();

                    for(int z = 0; z < category_referees.length() ; z++) {

                        JSONObject referee = (JSONObject)category_referees.get(z);
                        String referee_association = referee.optString("association");
                        String referee_avatar = referee.optString("avatar");
                        long referee_birthday = referee.optLong("birthday");
                        String referee_birthplace = referee.optString("birthplace");
                        String referee_category = referee.optString("category");
                        String referee_country = referee.optString("country");
                        int referee_fifaTopANum = referee.optInt("fifaTopANum");
                        String referee_function = referee.optString("function");
                        int referee_id = referee.optInt("id");
                        String referee_level = referee.optString("level");
                        String referee_name = referee.optString("name");
                        String referee_sex = referee.optString("sex");
                        String referee_sinceInternational = referee.optString("sinceInternational");
                        int referee_topLeagueNum = referee.optInt("topLeagueNum");

                        RefereesBean.CategoryBean.RefereeBean refereeBean = new RefereesBean.CategoryBean.RefereeBean();
                        refereeBean.setAssociation(referee_association);
                        refereeBean.setAvatar(referee_avatar);
                        refereeBean.setBirthday(referee_birthday);
                        refereeBean.setBirthplace(referee_birthplace);
                        refereeBean.setCategory(referee_category);
                        refereeBean.setCountry(referee_country);
                        refereeBean.setFifaTopANum(referee_fifaTopANum);
                        refereeBean.setFunction(referee_function);
                        refereeBean.setId(referee_id);
                        refereeBean.setLevel(referee_level);
                        refereeBean.setName(referee_name);
                        refereeBean.setSex(referee_sex);
                        refereeBean.setSinceInternational(referee_sinceInternational);
                        refereeBean.setTopLeagueNum(referee_topLeagueNum);

                        refereesList.add(refereeBean);

                    }

                    RefereesBean.CategoryBean categoryBean = new RefereesBean.CategoryBean();
                    categoryBean.setCategoryName(category_categoryName);
                    categoryBean.setReferees(refereesList);
                    map.put(category_categoryName,refereesList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return map;
    }

    public void getRefereeLeagueCategory(String key,final IModel.ModelApiListener<ArrayList<String>> listener){

        listener.onLoading();
        // http://192.168.50.154:8000/football/games/referees/leagueCategory?key=visitor
        String url = this.url +"/leagueCategory?key="+key;
        LogUtil.e(url);
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                ArrayList<String> strings = processRefereesRightMenu(json);
                listener.onSuccess(strings);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
    }

    private ArrayList<String> processRefereesRightMenu(String json) {
        ArrayList<String> leagueCategorys = new ArrayList<>();
        try {

                JSONArray jsonArray = new JSONArray(json);
                for(int i = 0; i < jsonArray.length(); i++) {
                    String  leagueCategory = (String) jsonArray.get(i);
                    leagueCategorys.add(leagueCategory);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return leagueCategorys;
    }

    public void getRefereesByLeague(String key,String league,final IModel.ModelApiListener<Map<String, ArrayList<RefereesByLeagueBean.RefereesBean>>> listener){

        listener.onLoading();
        // http://192.168.50.154:8000/football/games/referees/league?league=中超联赛&key=visitor
        String url = this.url +"/league?league="+league+"&key="+key;
        LogUtil.e(url);
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                Map<String, ArrayList<RefereesByLeagueBean.RefereesBean>> map = processRefereesByLeague(json);
                listener.onSuccess(map);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
    }

    private Map<String ,ArrayList<RefereesByLeagueBean.RefereesBean>> processRefereesByLeague(String json) {
        Map<String ,ArrayList<RefereesByLeagueBean.RefereesBean>> map = new HashMap<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++) {
               JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String categoryName = jsonObject.optString("categoryName");
                JSONArray referees = jsonObject.optJSONArray("referees");
                ArrayList<RefereesByLeagueBean.RefereesBean> refereesBeans = new ArrayList<>();
                for(int j = 0; j < referees.length(); j++) {
                    JSONObject referee = (JSONObject) referees.get(j);
                    RefereesByLeagueBean.RefereesBean refereesBean = new RefereesByLeagueBean.RefereesBean();
                    String referee_association = referee.optString("association");
                    String referee_avatar = referee.optString("avatar");
                    long referee_birthday = referee.optLong("birthday");
                    String referee_birthplace = referee.optString("birthplace");
                    String referee_category = referee.optString("category");
                    String referee_country = referee.optString("country");
                    String referee_function = referee.optString("function");
                    int referee_id = referee.optInt("id");
                    String referee_league = referee.optString("league");
                    String referee_level = referee.optString("level");
                    String referee_name = referee.optString("name");
                    String referee_sex = referee.optString("sex");
                    int referee_sinceInternational = referee.optInt("sinceInternational");
                    String referee_position = referee.optString("position");
                    int referee_topLeagueNum = referee.optInt("topLeagueNum");
                    int referee_fifiaTopANum = referee.optInt("fifiaTopANum");

                    refereesBean.setAssociation(referee_association);
                    refereesBean.setAvatar(referee_avatar);
                    refereesBean.setBirthday(referee_birthday);
                    refereesBean.setBirthplace(referee_birthplace);
                    refereesBean.setCategory(referee_category);
                    refereesBean.setCountry(referee_country);
                    refereesBean.setFunction(referee_function);
                    refereesBean.setId(referee_id);
                    refereesBean.setLeague(referee_league);
                    refereesBean.setLevel(referee_level);
                    refereesBean.setName(referee_name);
                    refereesBean.setSex(referee_sex);
                    refereesBean.setSinceInternational(referee_sinceInternational);
                    refereesBean.setPosition(referee_position);
                    refereesBean.setTopLeagueNum(referee_topLeagueNum);
                    refereesBean.setFifaTopANum(referee_fifiaTopANum);

                    refereesBeans.add(refereesBean);
                }

                map.put(categoryName,refereesBeans);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return map;
    }
}
