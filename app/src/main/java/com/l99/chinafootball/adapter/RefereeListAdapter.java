package com.l99.chinafootball.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.RefereesBean;
import com.l99.chinafootball.utils.ImageUtils;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by lifeix-101 on 2016/7/5.
 * 裁判员 -- 裁判页面 适配器
 */
public class RefereeListAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private  ArrayList<RefereesBean.CategoryBean.RefereeBean> ZhuLiCaiPanNan;
    private  ArrayList<RefereesBean.CategoryBean.RefereeBean> ZhuCaiPanNv;
    private  ArrayList<RefereesBean.CategoryBean.RefereeBean> ZhuLiCaiPanNv;
    private  ArrayList<RefereesBean.CategoryBean.RefereeBean> ZhuCaiPanNan;
    private  ArrayList<RefereesBean.CategoryBean.RefereeBean> zong;

    private  ArrayList<ArrayList<RefereesBean.CategoryBean.RefereeBean>> list;

    public RefereeListAdapter(ArrayList<ArrayList<RefereesBean.CategoryBean.RefereeBean>> list) {

        this.list = list;
        ZhuCaiPanNan = list.get(0);
        ZhuLiCaiPanNan = list.get(1);
        ZhuCaiPanNv = list.get(2);
        ZhuLiCaiPanNv = list.get(3);
        zong=new ArrayList<>();
        zong.addAll(ZhuCaiPanNan);
        zong.addAll(ZhuLiCaiPanNan);
        zong.addAll(ZhuCaiPanNv);
        zong.addAll(ZhuLiCaiPanNv);

    }

    private String getHeadTittle(int position){
        String tittle="";
        if(position<ZhuCaiPanNan.size()) {

            tittle="主裁判(男)";

        }else if(position>=ZhuCaiPanNan.size()&&position<(ZhuLiCaiPanNan.size()+ZhuCaiPanNan.size())) {

            tittle="助理裁判(男)";

        }else if(position>=(ZhuLiCaiPanNan.size()+ZhuCaiPanNan.size())&&position<(ZhuLiCaiPanNan.size()+ZhuCaiPanNan.size()+ZhuCaiPanNv.size())) {

            tittle="主裁判(女)";

        }else {

            tittle="助理裁判(女)";

        }

        return tittle;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(parent.getContext(), R.layout.referee_header, null);
        TextView textView = (TextView) convertView.findViewById(R.id.tv_referee_header);
        textView.setText(getHeadTittle(position));

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        if(position < ZhuCaiPanNan.size()) {
            return 1;
        }
        else if(position>=ZhuCaiPanNan.size()&&position<(ZhuLiCaiPanNan.size()+ZhuCaiPanNan.size())) {
            return 2;
        }
        else if(position>=(ZhuLiCaiPanNan.size()+ZhuCaiPanNan.size())&&position<(ZhuLiCaiPanNan.size()+ZhuCaiPanNan.size()+ZhuCaiPanNv.size())) {
            return 3;
        }else {
            return 4;
        }
    }

    @Override
    public int getCount() {

        return zong.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.item_referee_listview,null);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar_referee_listview);
            viewHolder.association = (TextView) convertView.findViewById(R.id.association_referee_listview);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name_referee_listview);
            viewHolder.birthday = (TextView) convertView.findViewById(R.id.birthday_referee_listview);
            viewHolder.fifaTopANum = (TextView) convertView.findViewById(R.id.fifaTopANum_referee_listview);
            viewHolder.topLeagueNum = (TextView) convertView.findViewById(R.id.topLeagueNum_referee_listview);
            viewHolder.sinceInternational = (TextView) convertView.findViewById(R.id.sinceInternational_referee_listview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        RefereesBean.CategoryBean.RefereeBean refereeBean = zong.get(position);
        viewHolder.name.setText("姓名：" + refereeBean.getName());
        if("".equals(refereeBean.getAssociation()) || "null".equals(refereeBean.getAssociation()) || refereeBean.getAssociation()==null) {
            viewHolder.association.setText("所属协会：暂无数据");
        }else {
            viewHolder.association.setText("所属协会："+refereeBean.getAssociation());
        }

        if(refereeBean.getBirthday()== 0 || "null".equals(refereeBean.getBirthday())) {
            viewHolder.birthday.setText("生日：暂无数据");
        }else {
            viewHolder.birthday.setText("生日："+refereeBean.getBirthday());
        }

        if("".equals(refereeBean.getAvatar()) || "null".equals(refereeBean.getAvatar()) || refereeBean.getAvatar()== null) {
            viewHolder.avatar.setImageResource(R.drawable.placehold_player);
        }else {
            ImageUtils.refereesAvatarCompress(refereeBean.getAvatar(), viewHolder.avatar);
        }
        
        if(refereeBean.getFifaTopANum() == 0 || "null".equals(refereeBean.getFifaTopANum()) ) {
            viewHolder.fifaTopANum.setText("国际A级赛事场次：—");
        }else {
            viewHolder.fifaTopANum.setText("国际A级赛事场次：" + refereeBean.getFifaTopANum());
        }
        
        if("".equals(refereeBean.getSinceInternational()) || "null".equals(refereeBean.getSinceInternational())) {
            viewHolder.sinceInternational.setText("FIFA起始年份：—");
        }else {
            viewHolder.sinceInternational.setText("FIFA起始年份："+refereeBean.getSinceInternational());
        }

        if(refereeBean.getTopLeagueNum()==0 || "null".equals(refereeBean.getTopLeagueNum()) ) {
           viewHolder.topLeagueNum.setText("中国顶级联赛场次：—");
        }else {
            viewHolder.topLeagueNum.setText("中国顶级联赛场次："+refereeBean.getTopLeagueNum());
        }
        
        return convertView;
    }

    class ViewHolder {
        ImageView avatar;
        TextView association,birthday,name,fifaTopANum,topLeagueNum,sinceInternational;
    }

    class HeaderViewHolder {
        TextView title;
    }
}
