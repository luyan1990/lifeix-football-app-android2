package com.l99.chinafootball.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by 78101 on 2016/7/13.
 */
public class TrapeziumView extends LinearLayout {

    private Paint paint;

    public TrapeziumView(Context context) {
        super(context);
    }

    public TrapeziumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setARGB(255,240,240,240);
        paint.setAntiAlias(true);
        
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        float width = getWidth();
        Path path=new Path();
        int hight = getChildAt(0).getHeight() + getChildAt(1).getHeight();
        path.moveTo(0, 0);
        path.lineTo(width,0);
        path.lineTo(width,getChildAt(0).getHeight());
        path.lineTo(0.7f*width,getChildAt(0).getHeight());
        path.lineTo(0.60625f*width,hight);
        path.lineTo(0.39375f*width,hight);
        path.lineTo(0.3f*width,getChildAt(0).getHeight());
        path.lineTo(0,getChildAt(0).getHeight());
        path.close();
        canvas.drawPath(path,paint );
        super.dispatchDraw(canvas);
    }

}
