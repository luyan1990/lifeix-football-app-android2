package com.l99.chinafootball.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.ElearningQuizCategoriesTreeBean;
import com.l99.chinafootball.utils.ImageUtils;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/4.
 */
public class QuizPageListViewAdapter extends BaseAdapter {

    private ArrayList<ElearningQuizCategoriesTreeBean> elearningQuizCategoriesTreeBeans;


    public QuizPageListViewAdapter( ArrayList<ElearningQuizCategoriesTreeBean> elearningQuizCategoriesTreeBeans) {
        this.elearningQuizCategoriesTreeBeans = elearningQuizCategoriesTreeBeans;
    }

    @Override
    public int getCount() {
        return elearningQuizCategoriesTreeBeans.size();
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
            convertView = View.inflate(parent.getContext(), R.layout.item_training,null);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.iv_item_training);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_item_training);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //http://o8rg11ywr.bkt.clouddn.com/mobile/elearning_q_iovt.jpg
        ElearningQuizCategoriesTreeBean elearningQuizCategoriesTreeBean = elearningQuizCategoriesTreeBeans.get(position);
        Log.e("TAG", elearningQuizCategoriesTreeBean.toString());
        viewHolder.name.setText(elearningQuizCategoriesTreeBean.getName());
        String image = elearningQuizCategoriesTreeBean.getImage();
        Log.e("TAG", image);
        image = "http://o8rg11ywr.bkt.clouddn.com/mobile/" + image;
        Log.e("TAG", image);
        ImageUtils.setImage(viewHolder.img,image);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView name;
    }
}
