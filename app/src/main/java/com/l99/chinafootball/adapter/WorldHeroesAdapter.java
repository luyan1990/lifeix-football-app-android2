package com.l99.chinafootball.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.WorldHeroesBean;
import com.l99.chinafootball.utils.ImageUtils;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/6.
 */
public class WorldHeroesAdapter extends RecyclerView.Adapter  <WorldHeroesAdapter.HeroesViewHolder> {



    private ArrayList<WorldHeroesBean> worldHeroesBeans;

    public WorldHeroesAdapter(ArrayList<WorldHeroesBean> worldHeroesBeans) {
        this.worldHeroesBeans = worldHeroesBeans;
    }


    @Override
    public HeroesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new HeroesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_world_heroes_gridview,parent,false));
    }

    @Override
    public void onBindViewHolder(HeroesViewHolder holder, int position) {
        WorldHeroesBean worldHeroesBean = worldHeroesBeans.get(position);
        String name = worldHeroesBean.getName();
        String avatar = worldHeroesBean.getAvatar();
        //+"?imageView/1/w/70/h/90"
        String position1 = worldHeroesBean.getPosition();

        holder.name.setText("[ " + position1 + " ] " + name);
        //ImageUtils.setImage(holder.avatar, avatar);
        
        if("".equals(avatar) || "null".equals(avatar) || avatar == null) {
            holder.avatar.setImageResource(R.drawable.placehold_player);
        }else {
            ImageUtils.compressHeroesImage(avatar, 1, holder.avatar);
        }

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return  worldHeroesBeans.size();
    }


    class HeroesViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;


        public HeroesViewHolder(View itemView) {
            super(itemView);
          avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
