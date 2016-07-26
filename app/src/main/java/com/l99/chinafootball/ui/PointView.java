package com.l99.chinafootball.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class PointView extends View {
    private int pointCount=10;
    private int pointindex =1;
    private int width;
    private int height;
    private Paint paint;
    private Paint point;
    private int pointR=10;

    public PointView(Context context) {
        super(context);
        initview();
    }

    private void initview() {
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        point=new Paint();
        point.setColor(Color.WHITE);
        point.setAntiAlias(true);
    }

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=getMeasuredWidth();
        height=getMeasuredHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(pointCount!=0) {

            for (int i= 0;i<pointCount;i++){

                if(i==(pointCount-pointindex)) {
                    canvas.drawCircle(width - (i + 1) * (pointR * 3), height / 2, pointR, point);
                }else {
                    canvas.drawCircle(width - (i + 1) * (pointR * 3), height / 2, pointR, paint);
                }

            }
        }



    }



    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
        invalidate();
    }


    public void setPointindex(int pointindex) {
        this.pointindex = pointindex;
    
        invalidate();
    }


}
