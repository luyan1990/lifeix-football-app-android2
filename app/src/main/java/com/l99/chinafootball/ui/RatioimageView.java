package com.l99.chinafootball.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by lifeix-101 on 2016/7/15.
 */
public class RatioimageView extends ImageView {
    private  float ratio;
    public RatioimageView(Context context) {
        super(context);
    }

    public RatioimageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatioimageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int hight = (int) (width / ratio);

        Log.e("onMeasure", width+"-----------"+hight);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(hight, MeasureSpec.EXACTLY);//明确高度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    public void setRatio(float ratio) {
        this.ratio = ratio;
        measure(0,0);
    }
}
