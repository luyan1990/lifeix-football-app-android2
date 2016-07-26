package com.l99.chinafootball.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.WemediaPostSearchBean;

public class MedialistDetailPage extends Activity implements View.OnClickListener {

    private WemediaPostSearchBean wemediaPostSearchBean;

    private ImageView back,share,support;
    private WebView webView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medialist_detail_page);
        back = (ImageView) findViewById(R.id.back_medialist_detail_page);
        share = (ImageView) findViewById(R.id.share_medialist_detail_page);
        support = (ImageView) findViewById(R.id.support_medialist_detail_page);
        webView = (WebView) findViewById(R.id.wv_medialist_detail_page);
        progressBar = (ProgressBar) findViewById(R.id.pb_medialist_detail_page);
        Bundle bundle = this.getIntent().getExtras();
        wemediaPostSearchBean = (WemediaPostSearchBean) bundle.getSerializable("wemediaPostSearchBean");

        back.setOnClickListener(this);
        share.setOnClickListener(this);
        support.setOnClickListener(this);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                support.setVisibility(View.VISIBLE);

            }
        });

        webView.loadUrl(wemediaPostSearchBean.getUrl());



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_medialist_detail_page:
                finish();
                break;
            case R.id.share_medialist_detail_page:
                Toast.makeText(MedialistDetailPage.this, "你要分享到哪儿去呢", Toast.LENGTH_SHORT).show();
                break;
            case R.id.support_medialist_detail_page:
                Toast.makeText(MedialistDetailPage.this, "亲，感谢你的支持", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
