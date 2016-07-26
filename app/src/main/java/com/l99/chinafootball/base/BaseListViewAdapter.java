package com.l99.chinafootball.base;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by 78101 on 2016/7/5.
 */
public abstract class BaseListViewAdapter <T>extends BaseAdapter {

    private ArrayList<T> mdata=new ArrayList<T>();
    @Override
    public int getCount() {
        return mdata.size();
    }
    @Override
    public T getItem(int position) {
        return mdata.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void setData(ArrayList<T> data) {
        Log.e("data", data.size()+"");
        mdata.clear();
        mdata.addAll(data);
        notifyDataSetChanged();
    }
    public void addItem(T t){
        mdata.add(t);
        notifyDataSetChanged();
    }
    public void addItem(T t,int index){
        mdata.add(index,t);
        notifyDataSetChanged();
    }
    public void deleteItem(T t){
        mdata.remove(t);
        notifyDataSetChanged();
    }
    public void deleteItem(int index){
        mdata.remove(index);
        notifyDataSetChanged();
    }
    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
