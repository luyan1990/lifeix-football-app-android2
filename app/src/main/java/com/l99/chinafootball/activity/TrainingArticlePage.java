package com.l99.chinafootball.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.ElearningTrainingCategoriesTreeBean;

import java.util.List;

public class TrainingArticlePage extends Activity {

    private ImageView back;
    private TextView title;
    private WebView webView;

    private List<ElearningTrainingCategoriesTreeBean.CatsBean> cats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_article_page);

        back = (ImageView) findViewById(R.id.back_training_article);
        title = (TextView) findViewById(R.id.title_training_article);
        webView = (WebView) findViewById(R.id.wv_trainging_article);

        Bundle bundle = this.getIntent().getExtras();
        cats = (List<ElearningTrainingCategoriesTreeBean.CatsBean>) bundle.getSerializable("cats");
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        title.setText(name);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        String contentUri = cats.get(0).getContentUri();
//        if(contentUri != null) {
//            contentUri = "http://o8rg11ywr.bkt.clouddn.com/" + contentUri;
//            // http://o8rg11ywr.bkt.clouddn.com/elearning/training_categories/articles/list.html?type=elearning_t_htc2015_01
//            webView.loadUrl(contentUri);
//        }

    }
}
