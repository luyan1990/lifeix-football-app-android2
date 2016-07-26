package com.l99.chinafootball.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

import com.l99.chinafootball.utils.UIUtils;


/**
 * 作用：自定义viewpager
 */
public class HeadViewPager extends ViewPager {
    private float ratio;
    private int mwidthMeasureSpec;
    private int heightMeasureSpec;

    public HeadViewPager(Context context) {
        super(context);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs   the attribute set
     */
    public HeadViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mwidthMeasureSpec = widthMeasureSpec;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int hight = (int) (width / ratio);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(hight, MeasureSpec.EXACTLY);
        mwidthMeasureSpec = widthMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public void setRatio(float ratio) {
        this.ratio = ratio;
        measure(mwidthMeasureSpec,heightMeasureSpec);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        //UIUtils.getHandler().postDelayed(autoSroll, 3000);
       // post(autoSroll);

    }

    private AutoSrollRunnable autoSroll=new AutoSrollRunnable();
    private class AutoSrollRunnable implements Runnable{
        @Override
        public void run() {
            Log.e("run", "AutoSrollRunnable");

            int count = getAdapter().getCount() / (getCurrentItem() + 1);
                setCurrentItem(count);
            UIUtils.getHandler().postDelayed(this, 3000);
        }
    }
}
