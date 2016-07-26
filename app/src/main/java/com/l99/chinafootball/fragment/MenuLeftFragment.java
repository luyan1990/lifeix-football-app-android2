package com.l99.chinafootball.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.adapter.MenuLeftListViewAdapter;
import com.l99.chinafootball.base.BaseListViewAdapter;
import com.l99.chinafootball.bean.MenuBean;
import com.l99.chinafootball.presenter.LeftMenuPresenter;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;

/**
 * 左侧菜单
 */
public class MenuLeftFragment extends Fragment implements IView.MenuLeftView, AdapterView.OnItemClickListener {

    private ListView lv_leftmenu_fragment;
    private BaseListViewAdapter<MenuBean> adapter;
    private LeftMenuPresenter leftMenuPresenter;
    private IView.MainIView mainIView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainIView = (IView.MainIView) getActivity();
        return inflater.inflate(R.layout.leftmenu_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LogUtil.e("MenuLeftFragment---onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        lv_leftmenu_fragment = (ListView) view.findViewById(R.id.lv_leftmenu_fragment);
        adapter = new MenuLeftListViewAdapter();
        leftMenuPresenter = new LeftMenuPresenter(this);//P层
        lv_leftmenu_fragment.setAdapter(adapter);
        lv_leftmenu_fragment.setOnItemClickListener(this);
        leftMenuPresenter.getData();
    }

    @Override
    public void setData(ArrayList<MenuBean> menuBeen) {
        LogUtil.e("MenuLeftFragment---setData");
         adapter.setData(menuBeen);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LogUtil.e("MenuLeftFragment---onItemClick");
        MenuBean item = adapter.getItem(position);

        if(position==5) {
            mainIView.switchFragment(FragmentSwitcher.MAN_PLAYERLIST_PAGE);
        }else if(position==6) {
            mainIView.switchFragment(FragmentSwitcher.WOMAN_PLAYERLIST_PAGE);
        }else {
            mainIView.switchFragment(item.getPage());
        }

        mainIView.setTitle(item.getName());
    }
}