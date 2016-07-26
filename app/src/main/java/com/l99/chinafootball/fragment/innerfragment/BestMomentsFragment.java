package com.l99.chinafootball.fragment.innerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import com.l99.chinafootball.R;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.utils.LogUtil;

/**
 * Created by lifeix-101 on 2016/7/21.
 * 高光时刻 页面
 */
@SuppressLint("ValidFragment")
public class BestMomentsFragment extends BaseFragment {
    @Override
    public String getTAG() {
        return BestMomentsFragment.class.getSimpleName();
    }

    public BestMomentsFragment(Context context) {
        super(context);
    }

    @Override
    public void initView(View view) {
        LogUtil.e("BestMomentsFragment --- initView");
    }

    @Override
    public void initData() {
        LogUtil.e("BestMomentsFragment --- initData");
    }

    @Override
    public int getLayoutId() {
        LogUtil.e("BestMomentsFragment --- getLayoutId");
        return R.layout.fragment_best_moments;
    }
}
