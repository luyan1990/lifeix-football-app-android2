package com.l99.chinafootball.model;

import com.l99.chinafootball.bean.FractionPageBean;
import com.l99.chinafootball.utils.HttpUtils;
import com.l99.chinafootball.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lifeix-101 on 2016/7/26.
 */
public class FractionPageApi {

    private String url =  "http://api.caibisai.com/cbs/fb/contest/list";
    public void getRealtimeFraction(String key,String start_time,String end_time, final IModel.ModelApiListener<FractionPageBean.DataBean> listener) {
//      http://api.caibisai.com/cbs/fb/contest/list?start_time=2016-07-26&end_time=2016-07-28
        String url = this.url +"?start_time="+start_time+"&end_time=" + end_time;
        LogUtil.e(url);
        listener.onLoading();
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                FractionPageBean.DataBean dataBean = processRealtimeFraction(json);
                listener.onSuccess(dataBean);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });

    }

    private FractionPageBean.DataBean processRealtimeFraction(String json) {
        FractionPageBean.DataBean dataBean = new FractionPageBean.DataBean();
        List<FractionPageBean.DataBean.ContestsBean> contestsBeans = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            int code = jsonObject.optInt("code");
            JSONObject data = jsonObject.optJSONObject("data");
            JSONArray contests = data.optJSONArray("contests");
            contestsBeans = new ArrayList<>();
            for(int i = 0; i < contests.length(); i++) {
                FractionPageBean.DataBean.ContestsBean contestsBean = new FractionPageBean.DataBean.ContestsBean();

                JSONObject object = (JSONObject) contests.get(i);
                JSONObject a_t = object.optJSONObject("a_t");
                FractionPageBean.DataBean.ContestsBean.ATBean atBean = new FractionPageBean.DataBean.ContestsBean.ATBean();
                String a_t_logo = a_t.optString("logo");
                String a_t_name = a_t.optString("name");
                int a_t_id = a_t.optInt("t_id");
                atBean.setLogo(a_t_logo);
                atBean.setName(a_t_name);
                atBean.setT_id(a_t_id);

                int away_count = object.optInt("away_count");
                double away_ratio = object.optDouble("away_ratio");
                int away_scores = object.optInt("away_scores");
                int away_team = object.optInt("away_team");
                boolean belong_five = object.optBoolean("belong_five");
                int bet_count = object.optInt("bet_count");
                String color = object.optString("color");
                int contest_id = object.optInt("contest_id");
                int contest_type = object.optInt("contest_type");
                int cup_id = object.optInt("cup_id");
                String cup_name = object.optString("cup_name");
                int ext_flag = object.optInt("ext_flag");

                JSONObject h_t = object.optJSONObject("h_t");
                FractionPageBean.DataBean.ContestsBean.HTBean htBean = new FractionPageBean.DataBean.ContestsBean.HTBean();

                String h_t_logo = h_t.optString("logo");
                String h_t_name = h_t.optString("name");
                int h_t_id = h_t.optInt("t_id");
                htBean.setLogo(h_t_logo);
                htBean.setName(h_t_name);
                htBean.setT_id(h_t_id);

                int home_count = object.optInt("home_count");
                double home_ratio = object.optDouble("home_ratio");
                int home_scores = object.optInt("home_scores");
                int home_team = object.optInt("home_team");
                int init_count = object.optInt("init_count");
                int level = object.optInt("level");
                boolean lock_flag = object.optBoolean("lock_flag");
                boolean longbi = object.optBoolean("longbi");
                int odds_type = object.optInt("odds_type");
                int room_id = object.optInt("room_id");
                int settle = object.optInt("settle");
                int settle_statu = object.optInt("settle_statu");
                String start_time = object.optString("start_time");
                int status = object.optInt("status");
                int target_id = object.optInt("target_id");


                contestsBean.setA_t(atBean);
                contestsBean.setAway_count(away_count);
                contestsBean.setAway_ratio(away_ratio);
                contestsBean.setAway_scores(away_scores);
                contestsBean.setAway_team(away_team);
                contestsBean.setBelong_five(belong_five);
                contestsBean.setBet_count(bet_count);
                contestsBean.setColor(color);
                contestsBean.setContest_id(contest_id);
                contestsBean.setContest_type(contest_type);
                contestsBean.setCup_id(cup_id);
                contestsBean.setCup_name(cup_name);
                contestsBean.setExt_flag(ext_flag);
                contestsBean.setH_t(htBean);
                contestsBean.setHome_count(home_count);
                contestsBean.setHome_ratio(home_ratio);
                contestsBean.setHome_scores(home_scores);
                contestsBean.setHome_team(home_team);
                contestsBean.setInit_count(init_count);
                contestsBean.setLevel(level);
                contestsBean.setLock_flag(lock_flag);
                contestsBean.setLongbi(longbi);
                contestsBean.setOdds_type(odds_type);
                contestsBean.setRoom_id(room_id);
                contestsBean.setSettle(settle);
                contestsBean.setSettle_statu(settle_statu);
                contestsBean.setStart_time(start_time);
                contestsBean.setStatus(status);
                contestsBean.setTarget_id(target_id);

                contestsBeans.add(contestsBean);
            }

            String next_time = data.optString("next_time");
            int number = data.optInt("number");
            String prev_time = data.optString("prev_time");

            dataBean.setNext_time(next_time);
            dataBean.setNumber(number);
            dataBean.setPrev_time(prev_time);
            dataBean.setContests(contestsBeans);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dataBean;

    }
}
