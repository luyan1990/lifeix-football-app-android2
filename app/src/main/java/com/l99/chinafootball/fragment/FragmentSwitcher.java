package com.l99.chinafootball.fragment;

import android.util.Log;

import com.l99.chinafootball.R;
import com.l99.chinafootball.activity.MainActivity;
import com.l99.chinafootball.base.BaseFragment;
import com.l99.chinafootball.page.CoachlistPage;
import com.l99.chinafootball.page.CompetitionPage;
import com.l99.chinafootball.page.FractionPage;
import com.l99.chinafootball.page.ManPlayerlistPage;
import com.l99.chinafootball.page.MedialistPage;
import com.l99.chinafootball.page.QuizCategroyPage;
import com.l99.chinafootball.page.RefeerlistPage;
import com.l99.chinafootball.page.TrainingCategoryPage;
import com.l99.chinafootball.page.WomanPlayerlistPage;
import com.l99.chinafootball.utils.LogUtil;

import java.util.HashMap;

/**
 * Created by 78101 on 2016/7/4.
 */
public class FragmentSwitcher {

    public static final String COMPETITION_PAGE = "competition_page";//'十二强赛'
    public static final String MEDIALIST_PAGE = "medialist_page";//资讯
    public static final String REFEERLIST_PAGE = "refeerlist_page";//裁判员
    public static final String COACHLIST_PAGE = "coachlist_page";//教练员
    public static final String MAN_PLAYERLIST_PAGE = "playerlist_page";// 男足
    public static final String WOMAN_PLAYERLIST_PAGE = "woman_playerlist_page";//女足
    public static final String TRAINING_CATEGORY_PAGE = "training_category_page";//培训规则
    public static final String QUIZ_CATEGROY_PAGE = "quiz_categroy_page";//培训测试
    public static  final String FRACTION_PAGE="fraction_page";//实时比分
    private  MainActivity mActivity;
    private static HashMap<String,BaseFragment> fragments ;

    //在mainActivity中注册

    public FragmentSwitcher(MainActivity activity) {
        mActivity = activity;
        fragments= new HashMap<>();
    }

    /**
     * @param id fragment id
     * @return
     */
    private BaseFragment createFragment(String id) {
        LogUtil.e("FragmentSwitcher---createFragment");
        BaseFragment fragment = fragments.get(id);
        if (fragment != null) {
            return fragment;
        }
        switch (id) {
            case COMPETITION_PAGE:
                // 十二强赛
                fragments.put(id, new CompetitionPage(mActivity));
                break;
            case MEDIALIST_PAGE:
                // 资讯
                fragments.put(id, new MedialistPage(mActivity));
                break;
            case REFEERLIST_PAGE:
                // 裁判员
                fragments.put(id, new RefeerlistPage(mActivity));
                break;
            case COACHLIST_PAGE:
                // 教练员
                fragments.put(id, new CoachlistPage(mActivity));
                break;
            case MAN_PLAYERLIST_PAGE:
                // 男足
                fragments.put(id, new ManPlayerlistPage(mActivity));
                break;
            case WOMAN_PLAYERLIST_PAGE:
                // 女足
                fragments.put(id, new WomanPlayerlistPage(mActivity));
                break;
            case TRAINING_CATEGORY_PAGE:
                // 规则培训
                fragments.put(id, new TrainingCategoryPage(mActivity));
                break;
            case QUIZ_CATEGROY_PAGE:
                // 规则测试
                fragments.put(id, new QuizCategroyPage(mActivity));
                break;
            case FRACTION_PAGE:
                // 实时比分
                fragments.put(id, new FractionPage(mActivity));
                break;
        }

        return fragments.get(id);
    }


    public void switchFragmentContent(String tag) {

        LogUtil.e("FragmentSwitcher---switchFragmentContent");
        BaseFragment fragment = createFragment(tag);

        if (mActivity == null) {
            throw new RuntimeException("FragmentFactory 没有注册");
        }

        try{  try {
            mActivity.getSupportFragmentManager().beginTransaction().replace(R.id.main_content,fragment,tag ).commit();
        } catch (Exception e) {
            mActivity.getSupportFragmentManager().beginTransaction().replace(R.id.main_content,fragment,tag).commitAllowingStateLoss();
        }}catch (Exception b){

            Log.e("TAG", "id---呀 空指针啊---"+tag);
        }




        //FragmentTransaction fragmentTransaction =
           /* if (toleft) {
                fragmentTrasaction.SetCustomAnimations(R.anim.push_left_in,
                        R.anim.push_left_out);
            } else {
                fragmentTrasaction.setCustomAnimations(R.anim.push_right_in,
                        R.anim.push_right_out);
            }*/
        //先判断是否添加过
           /* if (!toFragment.isAdded()) {
                //隐藏当前fragment,add下一个fragment
                fragmentTransaction.hide(mCurrentFragment);
                fragmentTransaction.add(R.id.mian_content, toFragment,tag);
                fragmentTransaction.commit();
            } else {
                //隐藏当前fragment，show下一个fragment
                fragmentTransaction.hide(mCurrentFragment);
                fragmentTransaction.show(toFragment);
                fragmentTransaction.commit();
            }
            mCurrentFragment = toFragment;*/
    }
}

