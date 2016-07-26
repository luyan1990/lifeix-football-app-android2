package com.l99.chinafootball.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.l99.chinafootball.R;
import com.l99.chinafootball.fragment.FragmentSwitcher;
import com.l99.chinafootball.fragment.MenuLeftFragment;
import com.l99.chinafootball.fragment.MenuRightFragment;
import com.l99.chinafootball.utils.LogUtil;
import com.l99.chinafootball.view.IView;
import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView.MainIView {

    private DrawerLayout mDrawerLayout;
    private Handler mHandler;
    private Button mBtnRightMenu;
    private Toolbar mToolBar;
    private MenuLeftFragment mLeftMenu;
    private MenuRightFragment mRightMenu;
    private FragmentSwitcher switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initEvents();
        switchFragment(FragmentSwitcher.COMPETITION_PAGE);
    }
    private void initView() {
        LogUtil.e("MainActivity---initView");
        setContentView(R.layout.activity_main);
        switcher = new FragmentSwitcher(this);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mHandler = new Handler();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);
        mBtnRightMenu = (Button) findViewById(R.id.btn_right_menu);
        mBtnRightMenu.setOnClickListener(this);

    }


    private void initEvents() {
        LogUtil.e("MainActivity---initEvents");
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;
                if( drawerView.getTag().equals("RIGHT")) {
                    rightScale=1;
                    //右侧滑动不缩小
                }
                if (drawerView.getTag().equals("LEFT")) {
                    //左侧滑动时的动画
                    float leftScale = 1 - 0.3f * scale;
                    ViewHelper.setScaleX(mMenu, leftScale);
                    ViewHelper.setScaleY(mMenu, leftScale);
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                    ViewHelper.setTranslationX(mContent,
                            mMenu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                } else {
                    ViewHelper.setTranslationX(mContent,
                            -mMenu.getMeasuredWidth() * slideOffset);
                    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }
            }
            @Override
            public void onDrawerOpened(View drawerView) {
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }
        });
    }
    @Override
    public void setTitle(String title) {
        LogUtil.e("MainActivity---setTitle");
        mToolBar.setTitle(title);
    }
    @Override
   public void switchFragment(String TAG) {
        LogUtil.e("MainActivity---switchFragment");
        switcher.switchFragmentContent(TAG);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDrawerLayout.closeDrawers();
            }
        }, 50);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_right_menu:
                openRightMenu();
                break;
        }
    }

    public void openRightMenu() {
        mBtnRightMenu.setVisibility(View.VISIBLE);
        LogUtil.e("MainActivity---openRightMenu");
        mDrawerLayout.openDrawer(Gravity.RIGHT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
    }


    public void closeRightMenu(){
        mBtnRightMenu.setVisibility(View.GONE);
        LogUtil.e("MainActivity---closeRightMenu");
        mDrawerLayout.closeDrawer(Gravity.RIGHT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
    }

}
