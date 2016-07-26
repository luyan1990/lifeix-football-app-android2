package com.l99.chinafootball.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.l99.chinafootball.R;
import com.l99.chinafootball.adapter.PlayerDetailFragmentAdapter;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.bean.BasicPlayerBean;
import com.l99.chinafootball.bean.NationalPlayerBean;
import com.l99.chinafootball.fragment.innerfragment.AppearanceRecordsFragment;
import com.l99.chinafootball.fragment.innerfragment.BestMomentsFragment;
import com.l99.chinafootball.fragment.innerfragment.DataStatisticsFragment;
import com.l99.chinafootball.fragment.innerfragment.SportsCareerFragment;
import com.l99.chinafootball.presenter.PlayerDetailPresenter;
import com.l99.chinafootball.ui.RatioimageView;
import com.l99.chinafootball.utils.ImageUtils;
import com.l99.chinafootball.utils.TimeUtils;
import com.l99.chinafootball.view.IView;

import java.util.ArrayList;
import java.util.List;

public class PlayerDetailPage extends FragmentActivity implements IView.ViewPro<BasicPlayerBean>, View.OnClickListener {

    private ImageView back,share;
    private TextView title;
    private PlayerDetailFragmentAdapter mAdapter;

    private PlayerDetailPresenter presenter;
    private RatioimageView ratioimageView;
    private TextView player_name,player_birthday,player_height_weight,player_position,player_birthplace,player_company;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private NationalPlayerBean.CategoryBean.PlayersBean playerBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail_page);

        back = (ImageView) findViewById(R.id.back_player_detail);
        share = (ImageView) findViewById(R.id.share_player_detail);
        title = (TextView) findViewById(R.id.title_player_detail);

        playerBean = (NationalPlayerBean.CategoryBean.PlayersBean) getIntent().getSerializableExtra("playersBean");
        title.setText(playerBean.getName());
        back.setOnClickListener(this);

        share.setOnClickListener(this);

        presenter = new PlayerDetailPresenter(this);
        presenter.getData(playerBean.getId());

        ratioimageView = (RatioimageView) findViewById(R.id.avatar_player_detail_head1);
        player_name = (TextView) findViewById(R.id.name_player_detail_head1);
        player_birthday = (TextView) findViewById(R.id.birthday_player_detail_head1);
        player_height_weight = (TextView) findViewById(R.id.height_weight_player_detail_head1);
        player_position = (TextView) findViewById(R.id.position_player_detail_head1);
        player_birthplace = (TextView) findViewById(R.id.birthplace_player_detail_head1);
        player_company = (TextView) findViewById(R.id.company_player_detail_head1);

        tabLayout = (TabLayout) findViewById(R.id.tl_player_detail_head2);
        viewPager = (ViewPager) findViewById(R.id.vp_player_detail_head2);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(BasicPlayerBean data) {

        player_name.setText(data.getName());
        player_birthday.setText(TimeUtils.getBirthday(data.getBirthday()));
        if(data.getHeight() == 0 || data.getWeight() == 0) {
            player_height_weight.setText("暂无数据");
        }else {
            player_height_weight.setText(data.getHeight() + "cm / " + data.getWeight() + "kg");
        }

        if("".equals(playerBean.getPosition()) || "null".equals(playerBean.getPosition()) || playerBean.getPosition() == null ) {
            player_position.setText("暂无数据");
        }else {
            player_position.setText(playerBean.getPosition());
        }

        if("".equals(data.getBirthplace()) || "null".equals(data.getBirthplace()) || data.getBirthplace() == null ) {
            player_birthplace.setText("暂无数据");
        }else {

            player_birthplace.setText(data.getBirthplace());
        }

        if("".equals(data.getClub().getName()) || "null".equals(data.getClub().getName()) || data.getClub().getName()==null) {
            player_company.setText("暂无数据");
        }else {
            player_company.setText(data.getClub().getName());
        }

        ratioimageView.setRatio(157f / 237f);

        ImageUtils.playerAvatarCompress(data.getAvatar(), 0, ratioimageView);

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new SportsCareerFragment(this,data));
        fragments.add(new AppearanceRecordsFragment(this,data));
        fragments.add(new DataStatisticsFragment(this,data));
        fragments.add(new BestMomentsFragment(this));

        mAdapter = new PlayerDetailFragmentAdapter(getSupportFragmentManager(),fragments);
        //给viewpager设置适配器
        viewPager.setAdapter(mAdapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void refresh(BasicPlayerBean menuBeen) {

    }

    @Override
    public void loadMoreData(BasicPlayerBean menuBeen) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_player_detail:
                finish();
                break;
            case R.id.share_player_detail:
                Toast.makeText(PlayerDetailPage.this, "你要分享到哪里去啊", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
