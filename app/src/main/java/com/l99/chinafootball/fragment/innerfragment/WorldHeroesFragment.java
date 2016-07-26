package com.l99.chinafootball.fragment.innerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.l99.chinafootball.R;
import com.l99.chinafootball.adapter.WorldHeroesAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.HeroesBean;
import com.l99.chinafootball.bean.WorldHeroesBean;
import com.l99.chinafootball.presenter.WorldHeroesPresenter;
import com.l99.chinafootball.ui.FullyGridLayoutManager;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lifeix-101 on 2016/7/6.
 * 英雄榜
 */
@SuppressLint("ValidFragment")
public class WorldHeroesFragment extends BaseFragment implements IView.ViewPro<HeroesBean> {

    private final WorldHeroesPresenter presenter;
    private RecyclerView recyclerview;

    private WorldHeroesAdapter adapter;

    private ArrayList<WorldHeroesBean> worldHeroesBeans;

    public WorldHeroesFragment(Context context) {
        super(context);
        presenter = new WorldHeroesPresenter(this);
        worldHeroesBeans = new ArrayList<>();
    }

    @Override
    public String getTAG() {
        return WorldHeroesFragment.class.getSimpleName();
    }

    @Override
    public void initView(View view) {
        recyclerview = (RecyclerView) view.findViewById(R.id.rv_fragment_worldheroes);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(context, 3);
        manager.setOrientation(FullyGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
    }

    @Override
    public void initData() {
        LogUtil.e("WorldHeroesFragment---initData");
        presenter.getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_worldheroes;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(HeroesBean data) {
        LogUtil.e("WorldHeroesFragment---onSuccess");
        // 主教练
        HeroesBean.ChiefCoachBean chiefCoach = data.getChiefCoachBean();
        worldHeroesBeans.add(new WorldHeroesBean(chiefCoach.getAvatar(),
                chiefCoach.getBirthday(),chiefCoach.getBirthplace(),"组织",chiefCoach.getCountry(),"",chiefCoach.getId()+"",
                0,0,0,"介绍",chiefCoach.getLevel(),chiefCoach.getName(),chiefCoach.getPosition(),"性别"));

        //助理教练
        List<HeroesBean.AssistantCoachBean> assistantCoaches = data.getAssistantCoachBeanList();

        for(int i = 0; i < assistantCoaches.size(); i++) {
            HeroesBean.AssistantCoachBean assistantCoachBean = assistantCoaches.get(i);

            worldHeroesBeans.add(new WorldHeroesBean(assistantCoachBean.getAvatar(),
                    assistantCoachBean.getBirthday(),assistantCoachBean.getBirthplace(),"组织",assistantCoachBean.getCountry(),""
                    ,assistantCoachBean.getId()+"",0,0,0,assistantCoachBean.getIntroduce(),assistantCoachBean.getLevel(),assistantCoachBean.getName(),assistantCoachBean.getPosition(),"男"));
        }

        List<HeroesBean.PlayerBean> players = data.getPlayerBeanList();

        for(int i = 0; i < players.size(); i++) {
            HeroesBean.PlayerBean playerBean = players.get(i);
            worldHeroesBeans.add(new WorldHeroesBean(
                    playerBean.getAvatar(),playerBean.getBirthday(),playerBean.getBirthplace(),"",playerBean.getCountry(),
                    playerBean.getEnglishName(),playerBean.getId(),playerBean.getHeight(),playerBean.getWeight(),playerBean.getJerseysNum(),
                    playerBean.getIntroduce(),"",playerBean.getName(),playerBean.getPosition(),playerBean.getSex()
            ));

        }

        adapter = new WorldHeroesAdapter(worldHeroesBeans);
        recyclerview.setAdapter(adapter);

    }

    @Override
    public void refresh(HeroesBean menuBeen) {

    }

    @Override
    public void loadMoreData(HeroesBean menuBeen) {

    }

    @Override
    public void onError() {

    }

}
