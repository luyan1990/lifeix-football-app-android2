package com.l99.chinafootball.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.l99.chinafootball.R;
import com.l99.chinafootball.activity.MainActivity;
import com.l99.chinafootball.adapter.FractionPageListViewAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.FractionPageBean;
import com.l99.chinafootball.fragment.FragmentSwitcher;
import com.l99.chinafootball.presenter.FractionPresenter;
import com.l99.chinafootball.ui.FullyLinearLayoutManager;
import com.l99.chinafootball.utils.TimeUtils;
import com.l99.chinafootball.view.IView;

import java.util.List;

/**
 * Created by lifeix-101 on 2016/7/6.
 * 实时比分
 */
@SuppressLint("ValidFragment")
public class FractionPage extends BaseFragment implements IView.ViewPro<FractionPageBean.DataBean> {

    private FractionPresenter presenter;
    private int year,month,day;
    private String start_time,end_time;
    private RecyclerView recyclerview;
    private ProgressBar progressBar;
    private ImageView error;
    private FractionPageListViewAdapter adapter;

    public FractionPage(Context context) {
        super(context);
        presenter = new FractionPresenter(this);
    }

    @Override
    public String getTAG() {
        return FragmentSwitcher.FRACTION_PAGE;
    }

    @Override
    public void initView(View view) {

        MainActivity mainActivity = (MainActivity) context;
        mainActivity.closeRightMenu();

        recyclerview = (RecyclerView) view.findViewById(R.id.rcy_fraction_page);
        progressBar = (ProgressBar) view.findViewById(R.id.pb_fraction_page);
        error = (ImageView) view.findViewById(R.id.iv_fraction_page);
        FullyLinearLayoutManager manager = new FullyLinearLayoutManager(context);
        manager.setOrientation(FullyLinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
    }

    @Override
    public void initData() {
        int[] systemTime = TimeUtils.getSystemTime();
        year = systemTime[0];
        month = systemTime[1];
        day = systemTime[2];
        start_time = year+"-"+month+"-"+day;
        end_time = year+"-"+month+"-"+(day+2);
        presenter.getData(start_time,end_time);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fraction_page;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }

    @Override
    public void setData(FractionPageBean.DataBean data) {
        List<FractionPageBean.DataBean.ContestsBean> contests = data.getContests();
        FractionPageBean.DataBean.ContestsBean contestsBean = contests.get(0);

        Log.e("ly",contestsBean.getA_t().getName());
        Log.e("ly",contestsBean.getH_t().getName());

        adapter = new FractionPageListViewAdapter(context,data);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void refresh(FractionPageBean.DataBean menuBeen) {

    }

    @Override
    public void loadMoreData(FractionPageBean.DataBean menuBeen) {

    }


    @Override
    public void onError() {
        progressBar.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
    }
}
