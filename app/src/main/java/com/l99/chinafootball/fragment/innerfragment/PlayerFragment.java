package com.l99.chinafootball.fragment.innerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.l99.chinafootball.R;
import com.l99.chinafootball.activity.PlayerDetailPage;
import com.l99.chinafootball.adapter.PlayerGridViewAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.NationalPlayerBean;
import com.l99.chinafootball.utils.LogUtil;

import java.util.ArrayList;

/**
 * Created by lifeix-101 on 2016/7/4.
 * 中国男足 、中国女足
 */
@SuppressLint("ValidFragment")
public class PlayerFragment extends BaseFragment {

    private XRefreshView xRefreshView;
    private RecyclerView recyclerview;
    private ImageView imageView;

    private ArrayList<NationalPlayerBean.CategoryBean.PlayersBean> playersBeans;

    public PlayerFragment(Context context) {
        super(context);

    }
    public PlayerFragment(Context context, ArrayList<NationalPlayerBean.CategoryBean.PlayersBean> playersBeans) {
        super(context);
        this.playersBeans = playersBeans;
    }

    @Override
    public String getTAG() {
        return PlayerFragment.class.getSimpleName();
    }

    @Override
    public void initView(View view) {
        xRefreshView = (XRefreshView) view.findViewById(R.id.xfv_fragment_player);
        recyclerview = (RecyclerView) view.findViewById(R.id.gv_fragment_player);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);

        imageView = (ImageView) view.findViewById(R.id.iv_fragment_player);

    }


    @Override
    public void initData() {

        if(playersBeans.size() > 0) {
            // 设置是否可以下拉刷新
            xRefreshView.setPullRefreshEnable(true);
            // 设置静默加载模式
            xRefreshView.setSlienceLoadMore();
            // 设置是否可以上拉加载
            xRefreshView.setPullLoadEnable(true);
            xRefreshView.setAutoLoadMore(false);
            // 设置可以自动刷新
            xRefreshView.setAutoRefresh(false);
            //头部固定的时间
            xRefreshView.setPinnedTime(1000);

            xRefreshView.setMoveForHorizontal(true);

            xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
                @Override
                public void onRefresh() {
                    super.onRefresh();
                    LogUtil.e("下拉刷新");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            xRefreshView.stopRefresh();
                        }
                    }, 1000);
                }

                @Override
                public void onLoadMore(boolean isSlience) {
                    super.onLoadMore(isSlience);
                    LogUtil.e("加载更多");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            xRefreshView.stopLoadMore();
                        }
                    }, 1000);

                }
            });


            PlayerGridViewAdapter adapter =  new PlayerGridViewAdapter(context,playersBeans);
            recyclerview.setAdapter(adapter);

            adapter.setOnItemClickListener(new PlayerGridViewAdapter.PlayerHolder.MyItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(context, "点击："+position, Toast.LENGTH_SHORT).show();
                    NationalPlayerBean.CategoryBean.PlayersBean playersBean = playersBeans.get(position);
                    Intent intent = new Intent(context, PlayerDetailPage.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("playersBean", playersBean);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        else {
            // 设置是否可以下拉刷新
            xRefreshView.setPullRefreshEnable(false);
            // 设置静默加载模式
            xRefreshView.setSlienceLoadMore();
            // 设置是否可以上拉加载
            xRefreshView.setPullLoadEnable(false);

            xRefreshView.setVisibility(View.GONE);
            recyclerview.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getLayoutId() {
        return  R.layout.fragment_player;
    }

}
