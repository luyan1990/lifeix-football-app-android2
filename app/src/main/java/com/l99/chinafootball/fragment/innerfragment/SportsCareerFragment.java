package com.l99.chinafootball.fragment.innerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.BasicPlayerBean;
import com.l99.chinafootball.utils.LogUtil;

/**
 * Created by lifeix-101 on 2016/7/21.
 * 运动生涯 页面
 */
@SuppressLint("ValidFragment")
public class SportsCareerFragment extends BaseFragment {

    private WebView webView;
    private BasicPlayerBean playersBean;
    private TextView textView;

    @Override
    public String getTAG() {
        return SportsCareerFragment.class.getSimpleName();
    }

    public SportsCareerFragment(Context context, BasicPlayerBean playersBean) {
        super(context);
        this.playersBean = playersBean;
    }

    @Override
    public void initView(View view) {
        LogUtil.e("SportsCareerFragment --- initView");
        webView = (WebView) view.findViewById(R.id.wv_fragment_sports_career);
        textView = (TextView) view.findViewById(R.id.tv_fragment_sports_career);

    }

    @Override
    public void initData() {
        LogUtil.e("SportsCareerFragment --- initData");
        String introduce = playersBean.getSports_career();
//        introduce = "<html><body>" + introduce + "</body></html>";
//        webView.getSettings().setDefaultTextEncodingName("UTF-8");
//        webView.loadData(introduce,"text/html;charset=UTF-8",null);
        if("".equals(introduce) || "null".equals(introduce) || introduce == null) {
            textView.setVisibility(View.VISIBLE);
        }else {
            webView.loadUrl(introduce);
        }
    }

    @Override
    public int getLayoutId() {
        LogUtil.e("SportsCareerFragment --- getLayoutId");
        return R.layout.fragment_sports_career;
    }
}
