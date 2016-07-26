package com.l99.chinafootball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.CompetitionTeamBean;
import com.l99.chinafootball.utils.ImageUtils;
import com.l99.chinafootball.utils.TimeUtils;

import java.util.ArrayList;



/**
 * Created by lifeix-101 on 2016/7/13.
 */
public class ChinaTeamCompetitionListViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<CompetitionTeamBean> competitionTeamBeans;

    public ChinaTeamCompetitionListViewAdapter(Context context, ArrayList<CompetitionTeamBean> competitionTeamBeans) {

        this.context =context;
        this.competitionTeamBeans = competitionTeamBeans;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChinaViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_china_team_competition,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChinaViewHolder chinaViewHolder = (ChinaViewHolder) holder;
        CompetitionTeamBean competitionTeamBean = competitionTeamBeans.get(position);
        long startDate = competitionTeamBean.getStartDate();
        int awayScore = competitionTeamBean.getAwayScore();
        int hostScore = competitionTeamBean.getHostScore();
        String competitionInfo_name = competitionTeamBean.getCompetitionInfo().getName();
        String awayTeam_flag = competitionTeamBean.getAwayTeam().getTeamInfoBean().getFlag();
        String awayTeam_name = competitionTeamBean.getAwayTeam().getTeamInfoBean().getName();
        String hostTeam_flag = competitionTeamBean.getHostTeamBean().getTeamInfoBean().getFlag();
        String hostTeam_name = competitionTeamBean.getHostTeamBean().getTeamInfoBean().getName();

        chinaViewHolder.away_team_name.setText(awayTeam_name);
        chinaViewHolder.host_team_name.setText(hostTeam_name);
        chinaViewHolder.startdate.setText(TimeUtils.getDate(startDate));
        chinaViewHolder.competition_info_name.setText(competitionInfo_name);
        //chinaViewHolder.competition_info_name.setText(TimeUtils.getHour(startDate)+competitionInfo_name);
        if(awayScore==0 && hostScore == 0) {
            chinaViewHolder.score.setText("VS");
        }else {
            String str = hostScore + " : " +awayScore;
            chinaViewHolder.score.setText(str);
        }

        ImageUtils.flagCompress(awayTeam_flag, 1, chinaViewHolder.away_team_flag);
        ImageUtils.flagCompress(hostTeam_flag,1,chinaViewHolder.host_team_flag);
    }

    @Override
    public int getItemCount() {
        return competitionTeamBeans.size();
    }

    class ChinaViewHolder extends RecyclerView.ViewHolder {

        private TextView startdate,competition_info_name,host_team_name,score,away_team_name;
        private ImageView host_team_flag,away_team_flag;
        public ChinaViewHolder(View itemView) {
            super(itemView);
            startdate = (TextView) itemView.findViewById(R.id.startdate);
            competition_info_name = (TextView) itemView.findViewById(R.id.competition_info_name);
            host_team_name = (TextView) itemView.findViewById(R.id.host_team_name);
            away_team_name = (TextView) itemView.findViewById(R.id.away_team_name);
            score = (TextView) itemView.findViewById(R.id.score);
            host_team_flag = (ImageView) itemView.findViewById(R.id.host_team_flag);
            away_team_flag = (ImageView) itemView.findViewById(R.id.away_team_flag);
        }
    }
}
