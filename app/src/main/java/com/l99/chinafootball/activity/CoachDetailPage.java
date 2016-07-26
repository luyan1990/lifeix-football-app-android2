package com.l99.chinafootball.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.NationalCoachBean;
import com.l99.chinafootball.ui.RatioimageView;
import com.l99.chinafootball.utils.ContentUtils;
import com.l99.chinafootball.utils.ImageUtils;
import com.l99.chinafootball.utils.TimeUtils;

public class CoachDetailPage extends Activity implements View.OnClickListener {

    private NationalCoachBean.CategoryBean.CoachesBean coachBean;

    private ImageView back,share;
    private TextView title;
    private RatioimageView ratioimageView;
    private TextView coach_name,coach_birthday,coach_birthplace,coach_position,coach_country,coach_company;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_detail_page);

        coachBean = (NationalCoachBean.CategoryBean.CoachesBean) getIntent().getSerializableExtra("coachBean");
        Log.e("ly", "传递过来的教练名：" + coachBean.getName());

        back = (ImageView) findViewById(R.id.back_coach_detail);
        share = (ImageView) findViewById(R.id.share_coach_detail);
        title = (TextView) findViewById(R.id.title_coach_detail);
        ratioimageView = (RatioimageView) findViewById(R.id.avatar_coach_detail_head1);
        coach_name = (TextView) findViewById(R.id.name_coach_detail_head1);
        coach_birthday = (TextView) findViewById(R.id.birthday_coach_detail_head1);
        coach_birthplace = (TextView) findViewById(R.id.birthplace_coach_detail_head1);
        coach_position = (TextView) findViewById(R.id.position_coach_detail_head1);
        coach_country= (TextView) findViewById(R.id.country_coach_detail_head1);
        coach_company = (TextView) findViewById(R.id.company_coach_detail_head1);
        webView = (WebView) findViewById(R.id.wv_coach_detail_head2);

        title.setText(coachBean.getName());
        back.setOnClickListener(this);
        share.setOnClickListener(this);

        coach_name.setText(coachBean.getName());
        if(coachBean.getBirthday()==0) {
            coach_birthday.setText("暂无数据");
        }else {
            coach_birthday.setText(TimeUtils.getBirthday(coachBean.getBirthday()));
        }

        if(coachBean.getBirthplace() ==null || "".equals(coachBean.getBirthplace()) || "null".equals(coachBean.getBirthplace())) {
            coach_birthplace.setText("暂无数据");
        }else {
            coach_birthplace.setText(coachBean.getBirthplace());
        }

        if("".equals(coachBean.getPosition()) || "null".equals(coachBean.getPosition()) || coachBean.getPosition() == null ) {
            coach_position.setText("暂无数据");
        }else {
            coach_position.setText(coachBean.getPosition());
        }

        if("".equals(coachBean.getCompany()) || "null".equals(coachBean.getCompany()) || coachBean.getCompany()==null) {
            coach_company.setText("暂无数据");
        }else {
            coach_company.setText(coachBean.getCompany());
        }

        if("".equals(coachBean.getCountry()) || "null".equals(coachBean.getCountry()) || coachBean.getCountry()==null) {
            coach_country.setText("暂无数据");
        }else {
            coach_country.setText(coachBean.getCountry());
        }

        ratioimageView.setRatio(157f / 237f);

        ImageUtils.playerAvatarCompress(coachBean.getAvatar(), 0, ratioimageView);

        if("".equals(coachBean.getIntroduce()) || "null".equals(coachBean.getIntroduce()) || coachBean.getIntroduce()==null) {
            String introduce = "暂无数据";
            webView.getSettings().setDefaultTextEncodingName("UTF-8");
            webView.loadData(introduce, "text/html;charset=UTF-8", null);
        }else {
            //
            String introduce = "<html><body>" + ContentUtils.htmlSpace(coachBean.getIntroduce()) + "</body></html>";
            webView.getSettings().setDefaultTextEncodingName("UTF-8");
            webView.loadData(introduce, "text/html;charset=UTF-8", null);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_coach_detail:
                finish();
                break;
            case R.id.share_coach_detail:
                Toast.makeText(CoachDetailPage.this, "你要分享到哪里去啊", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
