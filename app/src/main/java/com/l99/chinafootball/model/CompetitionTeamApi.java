package com.l99.chinafootball.model;

import com.l99.chinafootball.bean.CompetitionTeamBean;
import com.l99.chinafootball.bean.HeroesBean;
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
public class CompetitionTeamApi {

    private String url =  Url.BASE_URL+"/games";
    private ArrayList<CompetitionTeamBean> competitionTeamBeans;
    private HeroesBean heroesBean;


    //中国队赛事介绍
    public void getCompetitionTeam(String key,int competitionId , int teamId , final IModel.ModelApiListener<ArrayList<CompetitionTeamBean>> listener) {
//    http://192.168.50.154:8000/football/games/competitions/5/matches?teamId=1&key=visitor

        String url = this.url +"/competitions" + "/"+competitionId+"/matches?teamId="+teamId+"&key="+key;
        LogUtil.e(url);
        listener.onLoading();
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                competitionTeamBeans = processCompetitionTeam(json);
                listener.onSuccess(competitionTeamBeans);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });

    }

    private ArrayList<CompetitionTeamBean> processCompetitionTeam(String json) {

        ArrayList<CompetitionTeamBean> competitionTeamBeans = new ArrayList<>();
        try {

            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                int awayScore = jsonObject.optInt("awayScore");
                JSONObject awayTeam = jsonObject.optJSONObject("awayTeam");
                int awayTeam_competitionId = awayTeam.optInt("competitionId");
                int awayTeam_competitionTeamId = awayTeam.optInt("competitionTeamId");
                String awayTeam_desc = awayTeam.optString("desc");
                int awayTeam_id = awayTeam.optInt("id");
                int awayTeam_jerseyId = awayTeam.optInt("jerseyId");
                long awayTeam_setupDate = awayTeam.optLong("setupDate");



                JSONObject awayTeam_teamInfo = awayTeam.optJSONObject("teamInfo");
                String awayTeam_teamInfo_flag = awayTeam_teamInfo.optString("flag");
                int awayTeam_teamInfo_id = awayTeam_teamInfo.optInt("id");
                String awayTeam_teamInfo_name = awayTeam_teamInfo.optString("name");

                JSONObject awayTeam_teamInfo_teamCategory = awayTeam_teamInfo.optJSONObject("teamCategory");
                long awayTeam_teamInfo_teamCategory_fId = awayTeam_teamInfo_teamCategory.optLong("fId");
                long awayTeam_teamInfo_teamCategory_id = awayTeam_teamInfo_teamCategory.optLong("id");
                int awayTeam_teamInfo_teamCategory_isLeaf = awayTeam_teamInfo_teamCategory.optInt("isLeaf");
                String awayTeam_teamInfo_teamCategory_name = awayTeam_teamInfo_teamCategory.optString("name");

                CompetitionTeamBean.AwayTeamBean.TeamInfoBean.TeamCategoryBean teamCategoryBean = new CompetitionTeamBean.AwayTeamBean.TeamInfoBean.TeamCategoryBean();
                teamCategoryBean.setfId(awayTeam_teamInfo_teamCategory_fId);
                teamCategoryBean.setId(awayTeam_teamInfo_teamCategory_id);
                teamCategoryBean.setIsLeaf(awayTeam_teamInfo_teamCategory_isLeaf);
                teamCategoryBean.setName(awayTeam_teamInfo_teamCategory_name);

                CompetitionTeamBean.AwayTeamBean.TeamInfoBean teamInfoBean = new CompetitionTeamBean.AwayTeamBean.TeamInfoBean();
                teamInfoBean.setFlag(awayTeam_teamInfo_flag);
                teamInfoBean.setId(awayTeam_teamInfo_id);
                teamInfoBean.setName(awayTeam_teamInfo_name);
                teamInfoBean.setTeamCategoryBean(teamCategoryBean);

                CompetitionTeamBean.AwayTeamBean awayTeamBean = new CompetitionTeamBean.AwayTeamBean();
                awayTeamBean.setCompetitionId(awayTeam_competitionId);
                awayTeamBean.setCompetitionTeamId(awayTeam_competitionTeamId);
                awayTeamBean.setDesc(awayTeam_desc);
                awayTeamBean.setId(awayTeam_id);
                awayTeamBean.setJerseyId(awayTeam_jerseyId);
                awayTeamBean.setSetupDate(awayTeam_setupDate);
                awayTeamBean.setTeamInfoBean(teamInfoBean);

                JSONObject competitionInfo = jsonObject.optJSONObject("competitionInfo");
                long competitionInfo_endDate = competitionInfo.optLong("endDate");
                String competitionInfo_id = competitionInfo.optString("id");
                String competitionInfo_name = competitionInfo.optString("name");
                long competitionInfo_startDate = competitionInfo.optLong("startDate");

                CompetitionTeamBean.CompetitionInfoBean competitionInfoBean = new CompetitionTeamBean.CompetitionInfoBean();
                competitionInfoBean.setEndDate(competitionInfo_endDate);
                competitionInfoBean.setId(competitionInfo_id);
                competitionInfoBean.setName(competitionInfo_name);
                competitionInfoBean.setStartDate(competitionInfo_startDate);

                JSONObject court = jsonObject.optJSONObject("court");
                CompetitionTeamBean.CourtBean courtBean = new CompetitionTeamBean.CourtBean();
                if(court != null) {
                    long court_buildTime = court.optLong("buildTime");
                    int court_capacity = court.optInt("capacity");
                    String court_country = court.optString("country");
                    int court_id = court.optInt("id");
                    String court_images = court.optString("images");
                    String court_name = court.optString("name");
                    String court_position = court.optString("position");


                    courtBean.setBuildTime(court_buildTime);
                    courtBean.setCapacity(court_capacity);
                    courtBean.setCountry(court_country);
                    courtBean.setId(court_id);
                    courtBean.setImages(court_images);
                    courtBean.setName(court_name);
                    courtBean.setPosition(court_position);
                }


                String group = jsonObject.optString("group");
                int hostScore = jsonObject.optInt("hostScore");

                JSONObject hostTeam = jsonObject.optJSONObject("hostTeam");
                int hostTeam_competitionId = hostTeam.optInt("competitionId");
                int hostTeam_competitionTeamId = hostTeam.optInt("competitionTeamId");
                String hostTeam_desc = hostTeam.optString("desc");
                int hostTeam_id = hostTeam.optInt("id");
                long hostTeam_setupDate = hostTeam.optLong("setupDate");
                JSONObject hostTeam_teamInfo = hostTeam.optJSONObject("teamInfo");
                String hostTeam_teamInfo_flag = hostTeam_teamInfo.optString("flag");
                int hostTeam_teamInfo_id = hostTeam_teamInfo.optInt("id");
                String hostTeam_teamInfo_name = hostTeam_teamInfo.optString("name");
                JSONObject hostTeam_teamInfo_teamCategory = hostTeam_teamInfo.optJSONObject("teamCategory");
                long hostTeam_teamInfo_teamCategory_fId = hostTeam_teamInfo_teamCategory.optLong("fId");
                long hostTeam_teamInfo_teamCategory_id = hostTeam_teamInfo_teamCategory.optLong("id");
                int hostTeam_teamInfo_teamCategory_isLeaf = hostTeam_teamInfo_teamCategory.optInt("isLeaf");
                String hostTeam_teamInfo_teamCategory_name = hostTeam_teamInfo_teamCategory.optString("name");

                CompetitionTeamBean.HostTeamBean.TeamInfoBean.TeamCategoryBean teamCategoryBean1 = new CompetitionTeamBean.HostTeamBean.TeamInfoBean.TeamCategoryBean();
                teamCategoryBean1.setfId(hostTeam_teamInfo_teamCategory_fId);
                teamCategoryBean1.setId(hostTeam_teamInfo_teamCategory_id);
                teamCategoryBean1.setIsLeaf(hostTeam_teamInfo_teamCategory_isLeaf);
                teamCategoryBean1.setName(hostTeam_teamInfo_teamCategory_name);

                CompetitionTeamBean.HostTeamBean.TeamInfoBean teamInfoBean1 = new CompetitionTeamBean.HostTeamBean.TeamInfoBean();
                teamInfoBean1.setFlag(hostTeam_teamInfo_flag);
                teamInfoBean1.setId(hostTeam_teamInfo_id);
                teamInfoBean1.setName(hostTeam_teamInfo_name);
                teamInfoBean1.setTeamCategoryBean(teamCategoryBean1);

                CompetitionTeamBean.HostTeamBean hostTeamBean = new CompetitionTeamBean.HostTeamBean();
                hostTeamBean.setCompetitionId(hostTeam_competitionId);
                hostTeamBean.setCompetitionTeamId(hostTeam_competitionTeamId);
                hostTeamBean.setDesc(hostTeam_desc);
                hostTeamBean.setId(hostTeam_id);
                hostTeamBean.setSetupDate(hostTeam_setupDate);
                hostTeamBean.setTeamInfoBean(teamInfoBean1);

                int id = jsonObject.optInt("id");
                String position = jsonObject.optString("position");
                String stage = jsonObject.optString("stage");
                long startDate = jsonObject.optLong("startDate");
                long startTime = jsonObject.optLong("startTime");
                int state = jsonObject.optInt("state");
                String url = jsonObject.optString("url");

                CompetitionTeamBean competitionTeamBean = new CompetitionTeamBean();
                competitionTeamBean.setAwayScore(awayScore);
                competitionTeamBean.setAwayTeam(awayTeamBean);
                competitionTeamBean.setCompetitionInfo(competitionInfoBean);
                competitionTeamBean.setCourtBean(courtBean);
                competitionTeamBean.setGroup(group);
                competitionTeamBean.setHostScore(hostScore);
                competitionTeamBean.setHostTeamBean(hostTeamBean);
                competitionTeamBean.setId(id);
                competitionTeamBean.setPosition(position);
                competitionTeamBean.setStage(stage);
                competitionTeamBean.setStartDate(startDate);
                competitionTeamBean.setStartTime(startTime);
                competitionTeamBean.setState(state);
                competitionTeamBean.setUrl(url);

                competitionTeamBeans.add(competitionTeamBean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return competitionTeamBeans;
    }

    //英雄榜
    public void getWorldHeroes(String key,int competitionId , int teamId , final IModel.ModelApiListener<HeroesBean> listener) {
//    http://192.168.50.154:8000/football/games/competitions/5/teams/1/competitionTeam?key=visitor

        String url = this.url +"/competitions" + "/"+competitionId+"/teams/"+teamId+"/competitionTeam?key="+key;
        LogUtil.e(url);
        listener.onLoading();
        HttpUtils.get(url, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String json) {
                heroesBean = processWorldHeroes(json);
                listener.onSuccess(heroesBean);
            }

            @Override
            public void onError() {
                listener.onError();
            }
        });

    }

    private HeroesBean processWorldHeroes(String json) {
        HeroesBean heroesBean = new HeroesBean();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray assistantCoach_array = jsonObject.optJSONArray("assistantCoach");

            ArrayList<HeroesBean.AssistantCoachBean> assistantCoachBeans = new ArrayList<>();
            for(int i = 0; i < assistantCoach_array.length(); i++) {
                JSONObject  assistantCoach = (JSONObject) assistantCoach_array.get(i);
                String assistantCoach_avatar = assistantCoach.optString("avatar");
                long assistantCoach_birthday = assistantCoach.optLong("birthday");
                String assistantCoach_birthplace = assistantCoach.optString("birthplace");
                String assistantCoach_country = assistantCoach.optString("country");
                int assistantCoach_id = assistantCoach.optInt("id");
                String assistantCoach_introduce = assistantCoach.optString("introduce");
                String assistantCoach_level = assistantCoach.optString("level");
                String assistantCoach_name = assistantCoach.optString("name");
                String assistantCoach_position = assistantCoach.optString("position");

                HeroesBean.AssistantCoachBean assistantCoachBean = new HeroesBean.AssistantCoachBean();
                assistantCoachBean.setAvatar(assistantCoach_avatar);
                assistantCoachBean.setBirthday(assistantCoach_birthday);
                assistantCoachBean.setBirthplace(assistantCoach_birthplace);
                assistantCoachBean.setCountry(assistantCoach_country);
                assistantCoachBean.setId(assistantCoach_id);
                assistantCoachBean.setIntroduce(assistantCoach_introduce);
                assistantCoachBean.setLevel(assistantCoach_level);
                assistantCoachBean.setName(assistantCoach_name);
                assistantCoachBean.setPosition(assistantCoach_position);

                assistantCoachBeans.add(assistantCoachBean);
            }

            JSONObject chiefCoach = jsonObject.optJSONObject("chiefCoach");
            String chiefCoach_avatar = chiefCoach.optString("avatar");
            long chiefCoach_birthday = chiefCoach.optLong("birthday");
            String chiefCoach_birthplace = chiefCoach.optString("birthplace");
            String chiefCoach_company = chiefCoach.optString("company");
            String chiefCoach_country = chiefCoach.optString("country");
            int chiefCoach_id = chiefCoach.optInt("id");
            String chiefCoach_introduce = chiefCoach.optString("introduce");
            String chiefCoach_level = chiefCoach.optString("level");
            String chiefCoach_name = chiefCoach.optString("name");
            String chiefCoach_position = chiefCoach.optString("position");

            HeroesBean.ChiefCoachBean chiefCoachBean = new HeroesBean.ChiefCoachBean();
            chiefCoachBean.setAvatar(chiefCoach_avatar);
            chiefCoachBean.setBirthday(chiefCoach_birthday);
            chiefCoachBean.setBirthplace(chiefCoach_birthplace);
            chiefCoachBean.setCompany(chiefCoach_company);
            chiefCoachBean.setCountry(chiefCoach_country);
            chiefCoachBean.setId(chiefCoach_id);
            chiefCoachBean.setIntroduce(chiefCoach_introduce);
            chiefCoachBean.setLevel(chiefCoach_level);
            chiefCoachBean.setName(chiefCoach_name);
            chiefCoachBean.setPosition(chiefCoach_position);

            JSONObject competitionInfo = jsonObject.optJSONObject("competitionInfo");
            long competitionInfo_endDate = competitionInfo.optLong("endDate");
            String competitionInfo_id = competitionInfo.optString("id");
            String competitionInfo_name = competitionInfo.optString("name");
            long competitionInfo_startDate = competitionInfo.optLong("startDate");

            HeroesBean.CompetitionInfoBean competitionInfoBean = new HeroesBean.CompetitionInfoBean();
            competitionInfoBean.setEndDate(competitionInfo_endDate);
            competitionInfoBean.setId(competitionInfo_id);
            competitionInfoBean.setName(competitionInfo_name);
            competitionInfoBean.setStartDate(competitionInfo_startDate);

            String group = jsonObject.optString("group");
            int id = jsonObject.optInt("id");
            String introduce = jsonObject.optString("introduce");
            String name = jsonObject.optString("name");

            JSONArray players_array = jsonObject.optJSONArray("players");
            ArrayList<HeroesBean.PlayerBean> playerBeans = new ArrayList<>();
            for(int i = 0; i < players_array.length(); i++) {
               JSONObject player = (JSONObject) players_array.get(i);
                String player_avatar = player.optString("avatar");
                long player_birthday = player.optLong("birthday");
                String player_birthplace = player.optString("birthplace");
                String player_country = player.optString("country");
                String player_englishName = player.optString("englishName");
                int player_height = chiefCoach.optInt("height");
                String player_id = player.optString("id");
                String player_introduce = player.optString("introduce");
                int player_jerseysNum = player.optInt("jerseysNum");
                String player_name = player.optString("name");
                String player_position = player.optString("position");

                JSONObject player_record = player.optJSONObject("record");
                HeroesBean.PlayerBean.RecordBean recordBean = new HeroesBean.PlayerBean.RecordBean();
                if(player_record != null) {
                    int player_record_assists = player_record.optInt("assists");
                    int player_record_bookings = player_record.optInt("bookings");
                    int player_record_dismissals = player_record.optInt("dismissals");
                    int player_record_gamesplayed = player_record.optInt("gamesplayed");
                    int player_record_goals = player_record.optInt("goals");
                    int player_record_playerId = player_record.optInt("playerId");
                    int player_record_starts = player_record.optInt("starts");
                    int player_record_substitution = player_record.optInt("substitution");
                    recordBean.setAssists(player_record_assists);
                    recordBean.setBookings(player_record_bookings);
                    recordBean.setDismissals(player_record_dismissals);
                    recordBean.setGamesplayed(player_record_gamesplayed);
                    recordBean.setGoals(player_record_goals);
                    recordBean.setPlayerId(player_record_playerId);
                    recordBean.setStarts(player_record_starts);
                    recordBean.setSubstitution(player_record_substitution);
                }

                HeroesBean.PlayerBean playerBean = new HeroesBean.PlayerBean();
                playerBean.setAvatar(player_avatar);
                playerBean.setBirthday(player_birthday);
                playerBean.setBirthplace(player_birthplace);
                playerBean.setCountry(player_country);
                playerBean.setEnglishName(player_englishName);
                playerBean.setHeight(player_height);
                playerBean.setId(player_id);
                playerBean.setIntroduce(player_introduce);
                playerBean.setJerseysNum(player_jerseysNum);
                playerBean.setName(player_name);
                playerBean.setPosition(player_position);
                playerBean.setRecordBean(recordBean);
                String player_sex = player.optString("sex");
                int player_weight = player.optInt("weight");
                playerBean.setSex(player_sex);
                playerBean.setWeight(player_weight);

                playerBeans.add(playerBean);

            }

            long setupDate = jsonObject.optLong("setupDate");
            JSONObject teamInfo = jsonObject.optJSONObject("teamInfo");
            String teamInfo_flag = teamInfo.optString("flag");
            int teamInfo_id = teamInfo.optInt("id");
            String teamInfo_name = teamInfo.optString("name");
            JSONObject teamInfo_teamCategory = teamInfo.optJSONObject("teamCategory");
            long teamInfo_teamCategory_fId = teamInfo_teamCategory.optLong("fId");
            long teamInfo_teamCategory_id = teamInfo_teamCategory.optLong("id");
            int teamInfo_teamCategory_isLeaf = teamInfo_teamCategory.optInt("isLeaf");
            String teamInfo_teamCategory_name = teamInfo_teamCategory.optString("name");

            HeroesBean.TeamInfoBean.TeamCategoryBean teamCategoryBean = new HeroesBean.TeamInfoBean.TeamCategoryBean();
            teamCategoryBean.setfId(teamInfo_teamCategory_fId);
            teamCategoryBean.setId(teamInfo_teamCategory_id);
            teamCategoryBean.setIsLeaf(teamInfo_teamCategory_isLeaf);
            teamCategoryBean.setName(teamInfo_teamCategory_name);

            HeroesBean.TeamInfoBean teamInfoBean = new HeroesBean.TeamInfoBean();
            teamInfoBean.setFlag(teamInfo_flag);
            teamInfoBean.setId(teamInfo_id);
            teamInfoBean.setName(teamInfo_name);
            teamInfoBean.setTeamCategoryBean(teamCategoryBean);

            JSONObject teamLeader = jsonObject.optJSONObject("teamLeader");
            String teamLeader_avatar = teamLeader.optString("avatar");
            long teamLeader_birthday = teamLeader.optLong("birthday");
            String teamLeader_company = teamLeader.optString("company");
            String teamLeader_country = teamLeader.optString("country");
            int teamLeader_id = teamLeader.optInt("id");
            String teamLeader_introduce = teamLeader.optString("introduce");
            String teamLeader_name = teamLeader.optString("name");
            String teamLeader_position = teamLeader.optString("position");

            HeroesBean.TeamLeaderBean teamLeaderBean = new HeroesBean.TeamLeaderBean();
            teamLeaderBean.setAvatar(teamLeader_avatar);
            teamLeaderBean.setBirthday(teamLeader_birthday);
            teamLeaderBean.setCompany(teamLeader_company);
            teamLeaderBean.setCountry(teamLeader_country);
            teamLeaderBean.setId(teamLeader_id);
            teamLeaderBean.setIntroduce(teamLeader_introduce);
            teamLeaderBean.setName(teamLeader_name);
            teamLeaderBean.setPosition(teamLeader_position);

            heroesBean.setAssistantCoachBeanList(assistantCoachBeans);
            heroesBean.setChiefCoachBean(chiefCoachBean);
            heroesBean.setCompetitionInfoBean(competitionInfoBean);
            heroesBean.setGroup(group);
            heroesBean.setId(id);
            heroesBean.setIntroduce(introduce);
            heroesBean.setName(name);
            heroesBean.setPlayerBeanList(playerBeans);
            heroesBean.setSetupDate(setupDate);
            heroesBean.setTeamInfoBean(teamInfoBean);
            heroesBean.setTeamLeaderBean(teamLeaderBean);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return heroesBean;

    }

}
