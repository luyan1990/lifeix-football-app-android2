package com.l99.chinafootball.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.ElearningQuizCategoriesTreeBean;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/4.
 */
public class QuizFragmentListViewAdapter extends BaseAdapter {

    private ArrayList<ElearningQuizCategoriesTreeBean> elearningQuizCategoriesTreeBeans;

    private Context context;

    public QuizFragmentListViewAdapter(Context context, ArrayList<ElearningQuizCategoriesTreeBean> elearningQuizCategoriesTreeBeans) {
        this.context = context;
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
            convertView = View.inflate(context, R.layout.item_training,null);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.iv_item_training);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_item_training);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //http://o8rg11ywr.bkt.clouddn.com/mobile/elearning_q_iovt.jpg
        ElearningQuizCategoriesTreeBean elearningQuizCategoriesTreeBean = elearningQuizCategoriesTreeBeans.get(position);
        viewHolder.name.setText(elearningQuizCategoriesTreeBean.getName());
        String image = elearningQuizCategoriesTreeBean.getImage();
        image = "http://o8rg11ywr.bkt.clouddn.com/mobile/" + image;
        Glide.with(context).load(image).into(viewHolder.img);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView name;
    }
}
