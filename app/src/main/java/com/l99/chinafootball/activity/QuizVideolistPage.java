package com.l99.chinafootball.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.l99.chinafootball.R;

public class QuizVideolistPage extends FragmentActivity{


    private ImageView back;
    private FrameLayout frameLayout;
    private TextView title;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_videolist_page);

        back = (ImageView) findViewById(R.id.back_quiz_videolist);
        frameLayout = (FrameLayout) findViewById(R.id.fl_quiz_videolist);
        title = (TextView) findViewById(R.id.title_quiz_videolist);
        tabLayout = (TabLayout) findViewById(R.id.tl_quiz_videolist);
        viewPager = (ViewPager) findViewById(R.id.vp_quiz_videolist);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        title.setText(name);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Log.e("ly","传来的规则测试的名字"+name);

    }
}
