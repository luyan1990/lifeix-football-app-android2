package com.l99.chinafootball.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.adapter.TrainingVideolistFragmentAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.ElearningTrainingCategoriesTreeBean;
import com.l99.chinafootball.fragment.innerfragment.TrainingVideoFragment;

import java.util.ArrayList;
import java.util.List;
//培训规则列表页面
public class TrainingVideolistPage extends FragmentActivity {

    private ImageView back;
    private FrameLayout frameLayout;
    private TextView title;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TrainingVideolistFragmentAdapter mAdapter;
    private List<ElearningTrainingCategoriesTreeBean.CatsBean> cats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_videolist_page);

        back = (ImageView) findViewById(R.id.back_training_videolist);
        frameLayout = (FrameLayout) findViewById(R.id.fl_training_videolist);
        title = (TextView) findViewById(R.id.title_training_videolist);
        tabLayout = (TabLayout) findViewById(R.id.tl_training_videolist);
        viewPager = (ViewPager) findViewById(R.id.vp_training_videolist);

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


        List<BaseFragment> fragments = new ArrayList<>();
        if(cats != null && cats.size() > 0) {
            for(int i = 0; i < cats.size(); i++) {
                fragments.add(new TrainingVideoFragment(this,cats.get(i).getId()));
            }
        }

        mAdapter = new TrainingVideolistFragmentAdapter(getSupportFragmentManager(),fragments,cats);
        //给viewpager设置适配器
        viewPager.setAdapter(mAdapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }

}
