package com.l99.chinafootball.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.adapter.MenuLeftListViewAdapter;
import com.l99.chinafootball.bean.MenuBean;

/**
 * Created by 78101 on 2016/7/1.
 */
public class MenuRightFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private MenuLeftListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rightmenu_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.lv_leftmenu_fragment);
        mListView.setDividerHeight(0);
//防止在低版本按下的时候变色
        mListView.setCacheColorHint(Color.TRANSPARENT);
//设置把选择某条的时候，没有任何颜色变化
        mListView.setSelector(android.R.color.transparent);

        adapter = new MenuLeftListViewAdapter();
        adapter.addItem(new MenuBean());

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            
    }
}
