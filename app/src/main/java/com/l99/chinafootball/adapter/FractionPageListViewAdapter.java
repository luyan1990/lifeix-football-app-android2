package com.l99.chinafootball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.FractionPageBean;

import java.util.List;

/**
 * Created by lifeix-101 on 2016/7/26.
 */
public class FractionPageListViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private  FractionPageBean.DataBean dataBean;

    public FractionPageListViewAdapter(Context context, FractionPageBean.DataBean dataBean) {
        this.context = context;
        this.dataBean = dataBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FractionPageViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fraction_page,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FractionPageViewHolder fractionPageViewHolder = (FractionPageViewHolder) holder;
        List<FractionPageBean.DataBean.ContestsBean> contests = dataBean.getContests();
        FractionPageBean.DataBean.ContestsBean contestsBean = contests.get(position);
        int awayScore = contestsBean.getAway_scores();
        int hostScore = contestsBean.getHome_scores();
        String awayTeam_name = contestsBean.getA_t().getName();
        String hostTeam_name = contestsBean.getH_t().getName();
        if(awayScore==0 && hostScore == 0) {
            fractionPageViewHolder.score.setText("VS");
        }else {
            String str = hostScore + " : " +awayScore;
            fractionPageViewHolder.score.setText(str);
        }

        fractionPageViewHolder.away_team_name.setText(awayTeam_name);
        fractionPageViewHolder.host_team_name.setText(hostTeam_name);
    }

    @Override
    public int getItemCount() {
        return dataBean.getContests().size();
    }

    private class FractionPageViewHolder extends RecyclerView.ViewHolder {

        private TextView startdate,competition_info_name,host_team_name,score,away_team_name;
        private ImageView host_team_flag,away_team_flag;
        public FractionPageViewHolder(View view) {
            super(view);
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
