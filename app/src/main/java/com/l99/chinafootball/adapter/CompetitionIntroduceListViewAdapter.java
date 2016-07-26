package com.l99.chinafootball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.CompetitionBean;
import com.l99.chinafootball.bean.CompetitionIntroduceBean;
import com.l99.chinafootball.utils.ImageUtils;
import com.l99.chinafootball.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Map;


/**
 * Created by lifeix-101 on 2016/7/8.
 */
public class CompetitionIntroduceListViewAdapter extends RecyclerView.Adapter{
    private final int TITTLE_01=0;
    private final int CONTENTA=1;
    private final int TITTLE_02=2;
    private final int CONTENTB=3;
    private final int WEBVIEW=-1;

    private Context context;
    private Map<String, ArrayList<CompetitionIntroduceBean>> map;
    private int size;
    private final ArrayList<CompetitionIntroduceBean> competitionIntroduceBeansA;
    private final ArrayList<CompetitionIntroduceBean> competitionIntroduceBeansB;
    private CompetitionBean.CompetitionCategoryBean category;
    
    public void setCategory(CompetitionBean.CompetitionCategoryBean category){
        this.category=category;
    }

    public CompetitionIntroduceListViewAdapter(Context context, Map<String, ArrayList<CompetitionIntroduceBean>> map) {
        this.context = context;
        this.map = map;
        competitionIntroduceBeansA = map.get("A");
        competitionIntroduceBeansB = map.get("B");
        size= competitionIntroduceBeansA.size()+competitionIntroduceBeansB.size()+2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case WEBVIEW:
                return new ViewHolder_WEB( LayoutInflater.from(parent.getContext()).inflate(R.layout.head,parent,false) );
            case TITTLE_01:
                return new ViewHolder_02( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competition_introduce_titile,parent,false) );
            case CONTENTA:
                return new ViewHolder_01( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competition_introduce,parent,false) );
            case TITTLE_02:
                return new ViewHolder_02( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competition_introduce_titile,parent,false) );
            case CONTENTB:
                return new ViewHolder_01( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competition_introduce,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case WEBVIEW:
                String rule;
                ViewHolder_WEB tittleholder_web= (ViewHolder_WEB) holder;
                tittleholder_web.header_title.setText(category.getName());
                rule = "<html><body>" + category.getRule() + "</body></html>";
                tittleholder_web.header_webView.getSettings().setDefaultTextEncodingName("UTF-8");
                tittleholder_web.header_webView.loadData(rule, "text/html;charset=UTF-8",null);
                break;
            case TITTLE_01:
                ViewHolder_02 tittleholder_01= (ViewHolder_02) holder;
                tittleholder_01.tittle.setText("亚洲十二强A组");
                break;
            case CONTENTA:
                ViewHolder_01 contentaholder= (ViewHolder_01) holder;
                CompetitionIntroduceBean competitionIntroduceBeana = competitionIntroduceBeansA.get(position-1);
                contentaholder.competition_introduce_host_name.setText(competitionIntroduceBeana.getHost_name());
                contentaholder.competition_introduce_away_name.setText(competitionIntroduceBeana.getAway_name());
                contentaholder.competition_introduce_startdate.setText(TimeUtils.getDateAndTime(competitionIntroduceBeana.getStartDate())+"");
                //contentaholder.competition_introduce_starttime.setText(competitionIntroduceBeana.getStartTime() + "");
                // ImageUtils.setImage(chinaViewHolder.away_team_flag,ImageUtils.flagCompress(awayTeam_flag, 0));

                ImageUtils.flagCompress(competitionIntroduceBeana.getHost_flag(), 1, contentaholder.competition_introduce_host_flag);
                ImageUtils.flagCompress(competitionIntroduceBeana.getAway_flag(), 1, contentaholder.competition_introduce_away_flag);

                break;
            case TITTLE_02:
                ViewHolder_02 tittleholder_02= (ViewHolder_02) holder;
                tittleholder_02.tittle.setText("亚洲十二强B组");
                break;
            case CONTENTB:
                ViewHolder_01 contentbholder= (ViewHolder_01) holder;
                CompetitionIntroduceBean competitionIntroduceBeanb = competitionIntroduceBeansB.get(position-map.get("A").size()-2);
                contentbholder.competition_introduce_host_name.setText(competitionIntroduceBeanb.getHost_name());
                contentbholder.competition_introduce_away_name.setText(competitionIntroduceBeanb.getAway_name());
                contentbholder.competition_introduce_startdate.setText(TimeUtils.getDateAndTime(competitionIntroduceBeanb.getStartDate()) + "");
                ImageUtils.flagCompress(competitionIntroduceBeanb.getHost_flag(), 1, contentbholder.competition_introduce_host_flag);
                ImageUtils.flagCompress(competitionIntroduceBeanb.getAway_flag(), 1, contentbholder.competition_introduce_away_flag);

                break;
        }

    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0) {
            return WEBVIEW;
        }else if(position==1) {
            return TITTLE_01;
        }else if(position >0 && position <= map.get("A").size()) {
            return CONTENTA;
        }else if(position == map.get("A").size() +1){
            return TITTLE_02;
        }else {
            return CONTENTB;
        }
    }


    @Override
    public int getItemCount() {
        return size;
    }
    public Object getItem(int position) {
        return size;
    }

    class ViewHolder_01 extends RecyclerView.ViewHolder{
        ImageView competition_introduce_host_flag,competition_introduce_away_flag;
        TextView competition_introduce_host_name,competition_introduce_away_name,competition_introduce_startdate,competition_introduce_starttime;

        public ViewHolder_01(View itemView) {
            super(itemView);
            competition_introduce_host_flag = (ImageView) itemView.findViewById(R.id.competition_introduce_host_flag);
            competition_introduce_host_name = (TextView) itemView.findViewById(R.id.competition_introduce_host_name);
            competition_introduce_away_flag = (ImageView) itemView.findViewById(R.id.competition_introduce_away_flag);
            competition_introduce_away_name = (TextView) itemView.findViewById(R.id.competition_introduce_away_name);
            competition_introduce_startdate = (TextView) itemView.findViewById(R.id.competition_introduce_startdate);
        }
    }

    class ViewHolder_02 extends RecyclerView.ViewHolder{

        TextView tittle;

        public ViewHolder_02(View itemView) {
            super(itemView);
            tittle = (TextView) itemView.findViewById(R.id.competition_introduce_title);

        }
    }

    private class ViewHolder_WEB extends RecyclerView.ViewHolder {

          WebView header_webView;
          TextView header_title;

        public ViewHolder_WEB(View header) {
            super(header);
            header_title = (TextView) header.findViewById(R.id.tv_head);
            header_webView = (WebView) header.findViewById(R.id.wv_head);
        }
    }
}
