package com.l99.chinafootball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.ElearningTrainingPageListBean;
import com.l99.chinafootball.utils.ImageUtils;
import com.l99.chinafootball.utils.Url;

import java.util.ArrayList;


/**
 * Created by lifeix-101 on 2016/7/4.
 */
public class TrainingVideoGridViewAdapter extends BaseRecyclerAdapter<TrainingVideoGridViewAdapter.TrainingVideoHolder> {

    private Context context;
    private  ArrayList<ElearningTrainingPageListBean> elearningTrainingPageListBeans ;

    public TrainingVideoGridViewAdapter(Context context, ArrayList<ElearningTrainingPageListBean> elearningTrainingPageListBeans) {
        this.context = context;
        this.elearningTrainingPageListBeans = elearningTrainingPageListBeans;
    }


    @Override
    public TrainingVideoHolder getViewHolder(View view) {
        return null;
    }

    @Override
    public TrainingVideoHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
          return new TrainingVideoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trainging_video_gridview,parent,false));
    }

     //
    public void addElearningTrainingPageListBeans(ArrayList<ElearningTrainingPageListBean> elearningTrainingPageListBeans) {
        this.elearningTrainingPageListBeans.addAll(elearningTrainingPageListBeans);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(TrainingVideoHolder holder, int position, boolean isItem) {
        ElearningTrainingPageListBean elearningTrainingPageListBean = elearningTrainingPageListBeans.get(position);
        holder.name.setText(elearningTrainingPageListBean.getTitle());
        ElearningTrainingPageListBean.VideoBean video = elearningTrainingPageListBean.getVideo();
        String imagePath = video.getImagePath();
        imagePath = Url.IMAGE_URL + imagePath;
        ImageUtils.setImage(holder.image,imagePath);

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return elearningTrainingPageListBeans.size();
    }

    @Override
    public int getAdapterItemCount() {
        return 0;
    }


    class TrainingVideoHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        public TrainingVideoHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_item_training_video_gridview);
            name = (TextView) itemView.findViewById(R.id.tv_item_training_video_gridview);
        }
    }
}
