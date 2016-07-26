package com.l99.chinafootball.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.RefereesBean;

import java.util.ArrayList;


/**
 * Created by lifeix-101 on 2016/7/5.
 */
public class RefereeListViewAdapter extends BaseAdapter{

    private Context context;

    private ArrayList<RefereesBean.CategoryBean.RefereeBean> refereeBeans;

    public RefereeListViewAdapter(Context context, ArrayList<RefereesBean.CategoryBean.RefereeBean> refereeBeans) {

        this.context = context;
        this.refereeBeans = refereeBeans;
    }

    @Override
    public int getCount() {
        return refereeBeans.size();
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
            convertView = View.inflate(context, R.layout.item_referee_listview,null);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar_referee_listview);
            viewHolder.association = (TextView) convertView.findViewById(R.id.association_referee_listview);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name_referee_listview);
            viewHolder.birthday = (TextView) convertView.findViewById(R.id.birthday_referee_listview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        RefereesBean.CategoryBean.RefereeBean refereeBean = refereeBeans.get(position);
        viewHolder.name.setText("姓名："+refereeBean.getName());
        viewHolder.association.setText("所属协会："+refereeBean.getAssociation());
        viewHolder.birthday.setText("生日："+refereeBean.getBirthday());
        Log.e("aaa", refereeBean.toString());
        return convertView;
    }

    class ViewHolder {
        ImageView avatar;
        TextView association,birthday,name,fifaTopANum,topLeagueNum;
    }
}
