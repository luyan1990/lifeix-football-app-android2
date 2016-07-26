package com.l99.chinafootball.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.WemediaPostSearchBean;
import com.l99.chinafootball.ui.RatioimageView;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/6.
 */
public class MediaListFragmentListViewAdapter extends BaseAdapter {

    private ArrayList<WemediaPostSearchBean> wemediaPostSearchBeans;

    public MediaListFragmentListViewAdapter(ArrayList<WemediaPostSearchBean> wemediaPostSearchBeans) {
        this.wemediaPostSearchBeans = wemediaPostSearchBeans;
    }

    public void addWemediaPostSearchBeans(ArrayList<WemediaPostSearchBean> wemediaPostSearchBeans) {
        this.wemediaPostSearchBeans.addAll(wemediaPostSearchBeans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return wemediaPostSearchBeans.size();
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
            convertView = View.inflate(parent.getContext(), R.layout.item_medialist,null);
            viewHolder.img = (RatioimageView) convertView.findViewById(R.id.iv_item_medialist);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_item_medialist);
            viewHolder.img.setRatio(Float.parseFloat(parent.getWidth()+"")/Float.parseFloat(parent.getWidth()+""));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        WemediaPostSearchBean wemediaPostSearchBean = wemediaPostSearchBeans.get(position);

        viewHolder.name.setText(wemediaPostSearchBean.getTitle());
//        ImageUtils.flagCompress(wemediaPostSearchBean.getImage(),2,viewHolder.img);
        String url = wemediaPostSearchBean.getImage() + "?imageView/1/w/" + parent.getWidth() + "/h/" + parent.getHeight();
        Log.e("url", url);
        Glide.with(parent.getContext()).load(url).into(viewHolder.img);

        return convertView;
    }

    class ViewHolder {
        RatioimageView img;
        TextView name;
    }
}
