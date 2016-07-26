package com.l99.chinafootball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.NationalPlayerBean;
import com.l99.chinafootball.ui.RatioimageView;
import com.l99.chinafootball.utils.ImageUtils;

import java.util.ArrayList;


/**
 * Created by lifeix-101 on 2016/7/4.
 */
public class PlayerGridViewAdapter extends BaseRecyclerAdapter<PlayerGridViewAdapter.PlayerHolder> {

    private Context context;
    private ArrayList<NationalPlayerBean.CategoryBean.PlayersBean> playersBeans;

    private PlayerHolder.MyItemClickListener mItemClickListener;

    public PlayerGridViewAdapter(Context context, ArrayList<NationalPlayerBean.CategoryBean.PlayersBean> playersBeans) {
        this.context = context;
        this.playersBeans = playersBeans;
    }


    @Override
    public PlayerHolder getViewHolder(View view) {
        return null;
    }

    @Override
    public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
          View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gridview, parent, false);
          PlayerHolder playerHolder = new PlayerHolder(itemView, mItemClickListener);
         // itemView.setOnClickListener(this);
          return playerHolder;
    }

    @Override
    public void onBindViewHolder(PlayerHolder holder, int position, boolean isItem) {
        NationalPlayerBean.CategoryBean.PlayersBean playersBean = playersBeans.get(position);
        String coachesBean_name = playersBean.getName(); //高洪波
        String coachesBean_position = playersBean.getPosition(); //主教练
        holder.name.setText("[ " + coachesBean_position + " ] " + " " + coachesBean_name);

        if("".equals(playersBean.getAvatar()) || playersBean.getAvatar() == null || "null".equals(playersBean.getAvatar())) {
            holder.image.setBackgroundResource(R.drawable.placehold_player);
        }else {
            ImageUtils.playerAvatarCompress(playersBean.getAvatar(), 0, holder.image);
        }

    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(PlayerHolder.MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return playersBeans.size();
    }

    @Override
    public int getAdapterItemCount() {
        return 0;
    }


    public static class PlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RatioimageView image;
        TextView name;
        MyItemClickListener mListener;
        public PlayerHolder(View itemView , MyItemClickListener mListener ) {
            super(itemView);
            image = (RatioimageView) itemView.findViewById(R.id.pic);
            name = (TextView) itemView.findViewById(R.id.name);
            image.setRatio(157f/237f);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null) {
                mListener.onItemClick(v,getPosition());
            }
        }


        public interface MyItemClickListener {
            public void onItemClick(View view ,int position);
        }
    }
}
