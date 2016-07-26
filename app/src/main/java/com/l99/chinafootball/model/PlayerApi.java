package com.l99.chinafootball.model;

import android.util.Log;

import com.l99.chinafootball.bean.BasicPlayerBean;
import com.l99.chinafootball.bean.NationalPlayerBean;
import com.l99.chinafootball.bean.PlayerBean;
import com.l99.chinafootball.utils.HttpUtils;
import com.l99.chinafootball.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lifeix-101 on 2016/6/28.
 */
public class PlayerApi {

    private String url =  Url.BASE_URL+"/games/players";


    private ArrayList<Map<String,ArrayList<NationalPlayerBean.CategoryBean.PlayersBean>>> mapList;
    private BasicPlayerBean basicPlayerBean;
    private PlayerBean playerBean;

    public void getNationalPlayer(String key,final IModel.ModelApiListener listener){
        listener.onLoading();
    // http://192.168.50.154:8000/football/games/players/national?key=visitor
        String url = this.url +"/national?key="+key;
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                mapList = new ArrayList<>();
                mapList = processNationalPlayer(json);
                listener.onSuccess(mapList);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
    }

    private ArrayList<Map<String, ArrayList<NationalPlayerBean.CategoryBean.PlayersBean>>> processNationalPlayer(String json) {

        Map<String,ArrayList<NationalPlayerBean.CategoryBean.PlayersBean>> player_map = null;
        mapList = new ArrayList<>();
        ArrayList<NationalPlayerBean.CategoryBean.PlayersBean> playersBeans = null;

        try {
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++) {

               JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String topName = jsonObject.optString("topName");

                JSONArray category_obj = jsonObject.optJSONArray("category");

                player_map = new HashMap<>();
                for(int j = 0; j < category_obj.length(); j++) {

                    JSONObject jsonObject1 = (JSONObject)category_obj.get(j);
                    String category_categoryName = jsonObject1.optString("categoryName"); //国家队
                    JSONArray category_players = jsonObject1.optJSONArray("players");

                    playersBeans = new ArrayList<>();
                    for(int z = 0; z < category_players.length(); z++) {

                        JSONObject player_obj = (JSONObject)category_players.get(z);

                        String play_avatar = player_obj.optString("avatar");
                        long play_birthday = player_obj.optLong("birthday");
                        String play_birthplace = player_obj.optString("birthplace");
                        String play_country = player_obj.optString("country");
                        String play_englishName = player_obj.optString("englishName");
                        int play_height = player_obj.optInt("height");
                        String play_id = player_obj.optString("id");
                        String play_introduce = player_obj.optString("introduce");
                        int play_jeserysNum = player_obj.optInt("jeserysNum");
                        String play_name = player_obj.optString("name");
                        String play_position = player_obj.optString("position");

                        JSONObject play_record = player_obj.optJSONObject("record");
                        NationalPlayerBean.CategoryBean.PlayersBean.RecordBean recordBean = new NationalPlayerBean.CategoryBean.PlayersBean.RecordBean();
                        if(play_record != null) {
                            int play_record_assists = play_record.optInt("assists");
                            int play_record_bookings = play_record.optInt("bookings");
                            int play_record_dismissals = play_record.optInt("dismissals");
                            int play_record_gamesplayed = play_record.optInt("gamesplayed");
                            int play_record_goals = play_record.optInt("goals");
                            int play_record_playerId = play_record.optInt("playerId");
                            int play_record_starts = play_record.optInt("starts");
                            int play_record_substitution = play_record.optInt("substitution");


                            recordBean.setAssists(play_record_assists);
                            recordBean.setBookings(play_record_bookings);
                            recordBean.setDismissals(play_record_dismissals);
                            recordBean.setGamesplayed(play_record_gamesplayed);
                            recordBean.setGoals(play_record_goals);
                            recordBean.setPlayerId(play_record_playerId);
                            recordBean.setStarts(play_record_starts);
                            recordBean.setSubstitution(play_record_substitution);
                        }


                        String play_sex = player_obj.optString("sex");
                        int play_weight = player_obj.optInt("weight");

                        NationalPlayerBean.CategoryBean.PlayersBean playersBean = new NationalPlayerBean.CategoryBean.PlayersBean();
                        playersBean.setAvatar(play_avatar);
                        playersBean.setBirthday(play_birthday);
                        playersBean.setBirthplace(play_birthplace);
                        playersBean.setCountry(play_country);
                        playersBean.setEnglishName(play_englishName);
                        playersBean.setHeight(play_height);
                        playersBean.setId(play_id);
                        playersBean.setIntroduce(play_introduce);
                        playersBean.setJeserysNum(play_jeserysNum);
                        playersBean.setName(play_name);
                        playersBean.setPosition(play_position);
                        playersBean.setSex(play_sex);
                        playersBean.setWeight(play_weight);
                        playersBean.setRecordBean(recordBean);

                        playersBeans.add(playersBean);

                    }

                    player_map.put(category_categoryName,playersBeans);

                }

                mapList.add(player_map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mapList;
    }

    //查看球员基础信息
    public void getBasicPlayer(String key,String playerId, final IModel.ModelApiListener listener){
        listener.onLoading();
        // http://192.168.50.154:8000/football/games/players/11/basic?key=visitor
        String url = this.url +"/"+playerId+"/basic?key="+key;
        Log.e("ly",url);
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                basicPlayerBean = new BasicPlayerBean();
                basicPlayerBean = processBasicPlayer(json);
                listener.onSuccess(basicPlayerBean);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
    }

    private BasicPlayerBean processBasicPlayer(String json) {
        BasicPlayerBean basicPlayerBean = new BasicPlayerBean();

        try {

            JSONObject jsonObject = new JSONObject(json);
            String avatar = jsonObject.optString("avatar");
            basicPlayerBean.setAvatar(avatar);
            long birthday = jsonObject.optLong("birthday");
            basicPlayerBean.setBirthday(birthday);
            String birthplace = jsonObject.optString("birthplace");
            basicPlayerBean.setBirthplace(birthplace);

            JSONArray career_Array = jsonObject.optJSONArray("career");
            ArrayList<BasicPlayerBean.CareerBean> careerBeans = new ArrayList<>();
            for(int i = 0; i < career_Array.length() ; i++) {
                JSONObject career_obj = (JSONObject) career_Array.get(i);
                BasicPlayerBean.CareerBean careerBean = new BasicPlayerBean.CareerBean();
                String career_flag = career_obj.optString("flag");
                int career_id = career_obj.optInt("id");
                String career_name = career_obj.optString("name");
                String career_position = career_obj.optString("position");

                BasicPlayerBean.CareerBean.TeamCategoryBean teamCategoryBean = new BasicPlayerBean.CareerBean.TeamCategoryBean();
                JSONObject career_teamCategory = career_obj.optJSONObject("teamCategory");
                long career_teamCategory_fId = career_teamCategory.optLong("fId");
                long career_teamCategory_id = career_teamCategory.optLong("id");
                int career_teamCategory_isLeaf = career_teamCategory.optInt("isLeaf");
                String career_teamCategory_name = career_teamCategory.optString("name");
                int career_teamType = career_obj.optInt("teamType");

                teamCategoryBean.setName(career_teamCategory_name);
                teamCategoryBean.setFId(career_teamCategory_fId);
                teamCategoryBean.setId(career_teamCategory_id);
                teamCategoryBean.setIsLeaf(career_teamCategory_isLeaf);
                teamCategoryBean.setName(career_teamCategory_name);

                careerBean.setFlag(career_flag);
                careerBean.setId(career_id);
                careerBean.setName(career_name);
                careerBean.setPosition(career_position);
                careerBean.setTeamType(career_teamType);
                careerBean.setTeamCategory(teamCategoryBean);

                careerBeans.add(careerBean);
            }

            basicPlayerBean.setCareer(careerBeans);

            BasicPlayerBean.ClubBean clubBean = new BasicPlayerBean.ClubBean();
            JSONObject club_obj = jsonObject.optJSONObject("club");
            if(club_obj != null) {
                String club_flag = club_obj.optString("flag");
                int club_id = club_obj.optInt("id");
                String club_name = club_obj.optString("name");
                String club_position = club_obj.optString("position");
                JSONObject club_teamCategory = club_obj.optJSONObject("teamCategory");
                long club_teamCategory_fId = club_teamCategory.optLong("fId");
                long club_teamCategory_id = club_teamCategory.optLong("id");
                int club_teamCategory_isLeaf = club_teamCategory.optInt("isLeaf");
                String club_teamCategory_name = club_teamCategory.optString("name");
                int career_teamType = club_obj.optInt("teamType");

                clubBean.setFlag(club_flag);
                clubBean.setId(club_id);
                clubBean.setName(club_name);
                clubBean.setPosition(club_position);
                BasicPlayerBean.ClubBean.TeamCategoryBean teamCategoryBean = new BasicPlayerBean.ClubBean.TeamCategoryBean();
                teamCategoryBean.setFId(club_teamCategory_fId);
                teamCategoryBean.setId(club_teamCategory_id);
                teamCategoryBean.setIsLeaf(club_teamCategory_isLeaf);
                teamCategoryBean.setName(club_teamCategory_name);
                clubBean.setTeamType(career_teamType);

            }

            basicPlayerBean.setClub(clubBean);

            String country = jsonObject.optString("country");
            basicPlayerBean.setCountry(country);
            String englishName = jsonObject.optString("englishName");
            basicPlayerBean.setEnglishName(englishName);
            int height = jsonObject.optInt("height");
            basicPlayerBean.setHeight(height);
            int id = jsonObject.optInt("id");
            basicPlayerBean.setId(id);
            String introuceUrl = jsonObject.optString("introuceUrl");
            basicPlayerBean.setIntrouceUrl(introuceUrl);
            String matchUrl = jsonObject.optString("matchUrl");
            basicPlayerBean.setMatchUrl(matchUrl);
            String name = jsonObject.optString("name");
            basicPlayerBean.setName(name);


            JSONObject nationTeam = jsonObject.optJSONObject("nationTeam");
            BasicPlayerBean.NationTeamBean nationTeamBean = new BasicPlayerBean.NationTeamBean();
            String nationTeam_flag = nationTeam.optString("flag");
            int nationTeam_id = nationTeam.optInt("id");
            String nationTeam_name = nationTeam.optString("name");
            String nationTeam_position = nationTeam.optString("position");
            JSONObject nationTeam_teamCategory = nationTeam.optJSONObject("teamCategory");
            long nationTeam_teamCategory_fId = nationTeam_teamCategory.optLong("fId");
            long nationTeam_teamCategory_id = nationTeam_teamCategory.optLong("id");
            int nationTeam_teamCategory_isLeaf = nationTeam_teamCategory.optInt("isLeaf");
            String nationTeam_teamCategory_name = nationTeam_teamCategory.optString("name");
            int nationTeam_teamType = nationTeam.optInt("teamType");

            nationTeamBean.setFlag(nationTeam_flag);
            nationTeamBean.setId(nationTeam_id);
            nationTeamBean.setName(nationTeam_name);
            nationTeamBean.setPosition(nationTeam_position);
            BasicPlayerBean.NationTeamBean.TeamCategoryBean teamCategoryBean1 = new BasicPlayerBean.NationTeamBean.TeamCategoryBean();
            teamCategoryBean1.setFId(nationTeam_teamCategory_fId);
            teamCategoryBean1.setId(nationTeam_teamCategory_id);
            teamCategoryBean1.setIsLeaf(nationTeam_teamCategory_isLeaf);
            teamCategoryBean1.setName(nationTeam_teamCategory_name);


            nationTeamBean.setTeamCategory(teamCategoryBean1);
            nationTeamBean.setTeamType(nationTeam_teamType);

            basicPlayerBean.setNationTeam(nationTeamBean);

            String recordUrl = jsonObject.optString("recordUrl");
            basicPlayerBean.setRecordUrl(recordUrl);
            String sex = jsonObject.optString("sex");
            basicPlayerBean.setSex(sex);


            JSONArray urls_Array = jsonObject.optJSONArray("urls");
            JSONObject sports_career = (JSONObject) urls_Array.get(0);
            String sports_career_html = sports_career.optString("运动生涯");
            JSONObject competition_record = (JSONObject) urls_Array.get(1);
            String competition_record_html = competition_record.optString("比赛记录");
            JSONObject data_statistics = (JSONObject) urls_Array.get(2);
            String data_statistics_html = data_statistics.optString("数据统计");

            basicPlayerBean.setSports_career(sports_career_html);
            basicPlayerBean.setCompetition_record(competition_record_html);
            basicPlayerBean.setData_statisitic(data_statistics_html);

            int weight = jsonObject.optInt("weight");
            basicPlayerBean.setWeight(weight);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return basicPlayerBean;
    }

    //查看球员全部信息
    public void getPlayer(String key,int playerId, final IModel.ModelApiListener listener){
        listener.onLoading();
        // http://192.168.50.154:8000/football/games/players/11?key=visitor
        String url = this.url +"/"+playerId+"?key="+key;
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                playerBean = new PlayerBean();
                playerBean = processPlayer(json);
                listener.onSuccess(playerBean);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });
    }

    private PlayerBean processPlayer(String json) {
        PlayerBean playerBean = new PlayerBean();

        try {

            JSONObject jsonObject = new JSONObject(json);
            String avatar = jsonObject.optString("avatar");
            playerBean.setAvatar(avatar);
            long birthday = jsonObject.optLong("birthday");
            playerBean.setBirthday(birthday);
            String birthplace = jsonObject.optString("birthplace");
            playerBean.setBirthplace(birthplace);

            JSONArray career_Array = jsonObject.optJSONArray("career");
            ArrayList<PlayerBean.CareerBean> careerBeans = new ArrayList<>();
            for(int i = 0; i < career_Array.length() ; i++) {
                JSONObject career_obj = (JSONObject) career_Array.get(i);
                PlayerBean.CareerBean careerBean = new PlayerBean.CareerBean();
                String career_flag = career_obj.optString("flag");
                int career_id = career_obj.optInt("id");
                String career_name = career_obj.optString("name");
                String career_position = career_obj.optString("position");

                PlayerBean.CareerBean.TeamCategoryBean teamCategoryBean = new PlayerBean.CareerBean.TeamCategoryBean();
                JSONObject career_teamCategory = career_obj.optJSONObject("teamCategory");
                long career_teamCategory_fId = career_teamCategory.optLong("fId");
                long career_teamCategory_id = career_teamCategory.optLong("id");
                int career_teamCategory_isLeaf = career_teamCategory.optInt("isLeaf");
                String career_teamCategory_name = career_teamCategory.optString("name");
                int career_teamType = career_obj.optInt("teamType");

                teamCategoryBean.setName(career_teamCategory_name);
                teamCategoryBean.setFId(career_teamCategory_fId);
                teamCategoryBean.setId(career_teamCategory_id);
                teamCategoryBean.setIsLeaf(career_teamCategory_isLeaf);
                teamCategoryBean.setName(career_teamCategory_name);

                careerBean.setFlag(career_flag);
                careerBean.setId(career_id);
                careerBean.setName(career_name);
                careerBean.setPosition(career_position);
                careerBean.setTeamType(career_teamType);
                careerBean.setTeamCategory(teamCategoryBean);

                careerBeans.add(careerBean);
            }

            PlayerBean.ClubBean clubBean = new PlayerBean.ClubBean();
            JSONObject club_obj = jsonObject.optJSONObject("club");
            String club_flag = club_obj.optString("flag");
            int club_id = club_obj.optInt("id");
            String club_name = club_obj.optString("name");
            String club_position = club_obj.optString("position");
            JSONObject club_teamCategory = club_obj.optJSONObject("teamCategory");
            long club_teamCategory_fId = club_teamCategory.optLong("fId");
            long club_teamCategory_id = club_teamCategory.optLong("id");
            int club_teamCategory_isLeaf = club_teamCategory.optInt("isLeaf");
            String club_teamCategory_name = club_teamCategory.optString("name");
            int career_teamType = club_obj.optInt("teamType");

            clubBean.setFlag(club_flag);
            clubBean.setId(club_id);
            clubBean.setName(club_name);
            clubBean.setPosition(club_position);
            PlayerBean.ClubBean.TeamCategoryBean teamCategoryBean = new PlayerBean.ClubBean.TeamCategoryBean();
            teamCategoryBean.setFId(club_teamCategory_fId);
            teamCategoryBean.setId(club_teamCategory_id);
            teamCategoryBean.setIsLeaf(club_teamCategory_isLeaf);
            teamCategoryBean.setName(club_teamCategory_name);
            clubBean.setTeamType(career_teamType);



            String country = jsonObject.optString("country");
            playerBean.setCountry(country);
            String englishName = jsonObject.optString("englishName");
            playerBean.setEnglishName(englishName);
            int height = jsonObject.optInt("height");
            playerBean.setHeight(height);
            int id = jsonObject.optInt("id");
            playerBean.setId(id);
            String introduce = jsonObject.optString("introduce");
            playerBean.setIntroduce(introduce);
            String name = jsonObject.optString("name");
            playerBean.setName(name);

            JSONArray matches_Array = jsonObject.optJSONArray("matches");
            ArrayList<PlayerBean.MatchesBean> matchesBeans = new ArrayList<>();
            for(int i = 0; i < matches_Array.length(); i++) {
              JSONObject match_obj = (JSONObject) matches_Array.get(i);
                PlayerBean.MatchesBean matchesBean = new PlayerBean.MatchesBean();
                int match_assists = match_obj.optInt("assists");
                int match_awayScore = match_obj.optInt("awayScore");
                String match_awayTeam = match_obj.optString("awayTeam");
                int match_booking = match_obj.optInt("booking");

                JSONObject match_competition = match_obj.optJSONObject("competition");
                long match_competition_endDate = match_competition.optLong("endDate");
                String match_competition_id = match_competition.optString("id");
                String match_competition_name = match_competition.optString("name");
                long match_competition_startDate = match_competition.optLong("startDate");

                PlayerBean.MatchesBean.CompetitionBean competitionBean = new PlayerBean.MatchesBean.CompetitionBean();
                competitionBean.setEndDate(match_competition_endDate);
                competitionBean.setId(match_competition_id);
                competitionBean.setName(match_competition_name);
                competitionBean.setStartDate(match_competition_startDate);

                int match_dismissal = match_obj.optInt("dismissal");
                int match_first = match_obj.optInt("first");
                int match_goal = match_obj.optInt("goal");
                int match_hostScore = match_obj.optInt("hostScore");
                String match_hostTeam = match_obj.optString("hostTeam");
                int match_id = match_obj.optInt("id");
                int match_matchDate = (int) match_obj.optLong("matchDate");
                int match_playerId = match_obj.optInt("playerId");
                String match_playerName = match_obj.optString("playerName");
                int match_showTime = match_obj.optInt("showTime");

                matchesBean.setAssists(match_assists);
                matchesBean.setAwayScore(match_awayScore);
                matchesBean.setAwayTeam(match_awayTeam);
                matchesBean.setBooking(match_booking);
                matchesBean.setCompetition(competitionBean);
                matchesBean.setDismissal(match_dismissal);
                matchesBean.setFirst(match_first);
                matchesBean.setGoal(match_goal);
                matchesBean.setHostScore(match_hostScore);
                matchesBean.setHostTeam(match_hostTeam);
                matchesBean.setId(match_id);
                matchesBean.setMatchDate(match_matchDate);
                matchesBean.setPlayerId(match_playerId);
                matchesBean.setPlayerName(match_playerName);
                matchesBean.setShowTime(match_showTime);

            }


            JSONObject nationTeam = jsonObject.optJSONObject("nationTeam");
            PlayerBean.NationTeamBean nationTeamBean = new PlayerBean.NationTeamBean();
            String nationTeam_flag = nationTeam.optString("flag");
            int nationTeam_id = nationTeam.optInt("id");
            String nationTeam_name = nationTeam.optString("name");
            String nationTeam_position = nationTeam.optString("position");
            JSONObject nationTeam_teamCategory = nationTeam.optJSONObject("teamCategory");
            long nationTeam_teamCategory_fId = nationTeam_teamCategory.optLong("fId");
            long nationTeam_teamCategory_id = nationTeam_teamCategory.optLong("id");
            int nationTeam_teamCategory_isLeaf = nationTeam_teamCategory.optInt("isLeaf");
            String nationTeam_teamCategory_name = nationTeam_teamCategory.optString("name");
            int nationTeam_teamType = nationTeam.optInt("teamType");

            nationTeamBean.setFlag(nationTeam_flag);
            nationTeamBean.setId(nationTeam_id);
            nationTeamBean.setName(nationTeam_name);
            nationTeamBean.setPosition(nationTeam_position);

            PlayerBean.NationTeamBean.TeamCategoryBean teamCategoryBean1 = new PlayerBean.NationTeamBean.TeamCategoryBean();
            teamCategoryBean1.setFId(nationTeam_teamCategory_fId);
            teamCategoryBean1.setId(nationTeam_teamCategory_id);
            teamCategoryBean1.setIsLeaf(nationTeam_teamCategory_isLeaf);
            teamCategoryBean1.setName(nationTeam_teamCategory_name);


            nationTeamBean.setTeamCategory(teamCategoryBean1);
            nationTeamBean.setTeamType(nationTeam_teamType);

            JSONObject record_obj = jsonObject.optJSONObject("record");
            int record_assists = record_obj.optInt("assists");
            int record_bookings = record_obj.optInt("bookings");
            int record_dismissals = record_obj.optInt("dismissals");
            int record_gamesplayed = record_obj.optInt("gamesplayed");
            int record_goals = record_obj.optInt("goals");
            int record_playerId = record_obj.optInt("playerId");
            int record_starts = record_obj.optInt("starts");
            int record_substitution = record_obj.optInt("substitution");

            PlayerBean.RecordBean recordBean = new PlayerBean.RecordBean();
            recordBean.setAssists(record_assists);
            recordBean.setBookings(record_bookings);
            recordBean.setDismissals(record_dismissals);
            recordBean.setGamesplayed(record_gamesplayed);
            recordBean.setGoals(record_goals);
            recordBean.setPlayerId(record_playerId);
            recordBean.setStarts(record_starts);
            recordBean.setSubstitution(record_substitution);

            playerBean.setRecord(recordBean);

            String sex = jsonObject.optString("sex");
            playerBean.setSex(sex);
            int weight = jsonObject.optInt("weight");
            playerBean.setWeight(weight);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return playerBean;

    }

}
