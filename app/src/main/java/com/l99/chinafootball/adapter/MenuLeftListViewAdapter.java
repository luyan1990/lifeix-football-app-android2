package com.l99.chinafootball.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.base.BaseListViewAdapter;
import com.l99.chinafootball.bean.MenuBean;
import com.l99.chinafootball.utils.ImageUtils;

/**
 * Created by lifeix-101 on 2016/6/22.
 */


public class MenuLeftListViewAdapter extends BaseListViewAdapter <MenuBean> {
        
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.item_left_menu, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img_item_left_menu);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.catagory_item_left_menu);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MenuBean item = getItem(position);


        ImageUtils.setImage(viewHolder.imageView,item.getIconUrl());
        viewHolder.textView.setText(item.getName());
        return convertView;
    }
    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
