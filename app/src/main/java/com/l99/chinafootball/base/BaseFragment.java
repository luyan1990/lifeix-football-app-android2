package com.l99.chinafootball.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by 78101 on 2016/7/1.
 */
public abstract class BaseFragment extends Fragment {

    public View rootView;
    public Context context;
    protected String TAG=getTAG();
    private Bundle bundle;


    public abstract String getTAG();

    public BaseFragment(Context context) {
        this.context = context;
    }

    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("BaseFragment", "onCreateView");
        bundle = getArguments();
          rootView=inflater.inflate(getLayoutId(), container, false);

//            TextView textView = new TextView(context);
//            textView.setText(getTAG());
//            textView.setTextSize(20);
//            textView.setTextColor(Color.RED);
//            textView.setGravity(Gravity.CENTER);
//            rootView=textView;

            initView(rootView);
            initData();
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    public abstract void initView(View view);
    public abstract void initData();
    public abstract int getLayoutId();
}
