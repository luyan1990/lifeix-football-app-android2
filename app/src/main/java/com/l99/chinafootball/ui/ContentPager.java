package com.l99.chinafootball.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.l99.chinafootball.R;

public  class ContentPager extends FrameLayout {
    private View mSuccess_view,mPage_loading,page_error;
    private OnRefreshListener mRefreshListener;
    public interface PageState {
        int STATE_LOADING=1;
        int STATE_ERROR=2;
        int STATE_SUCCESS=3;
    }


    private int mPageState = PageState.STATE_LOADING;

    public ContentPager(Context context) {
        super(context);
        initContentPage(context);
    }
    /**
     * @param context
     * @param attrs
     */
    public ContentPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContentPage(context);
    }

    public ContentPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initContentPage(context);
    }
    /**
     * 管理加载的三种不同的界面
     */
    public void initContentPage(Context context) {
        if(getChildCount()>1) {
            throw new IllegalArgumentException("这个content只能有一个子布局");
        }
        if (mPage_loading == null) {
            mPage_loading = View.inflate(context, R.layout.loading, null);
        }
        addView(mPage_loading);
        if (page_error == null) {
            page_error = View.inflate(context, R.layout.error, null);
            page_error.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mRefreshListener!=null) {
                        mPageState = PageState.STATE_LOADING;
                        shoePage();
                    }
                }
            });
        }
        addView(page_error);
        if (mPage_loading == null) {
            mPage_loading = View.inflate(context, R.layout.error, null);
        }
        if (mSuccess_view == null) {
            mSuccess_view = getChildAt(0);
        }
        if (mSuccess_view != null) {
            addView(mSuccess_view);
        } 
        shoePage();
    }
    private void shoePage() {
        page_error.setVisibility(View.GONE);
        mPage_loading.setVisibility(View.GONE);
        mSuccess_view.setVisibility(View.GONE);
        switch (mPageState) {
            case PageState.STATE_SUCCESS:
                mSuccess_view.setVisibility(VISIBLE);
                break;
            case PageState.STATE_ERROR:
                page_error.setVisibility(VISIBLE);
                break;
            case PageState.STATE_LOADING:
                mPage_loading.setVisibility(VISIBLE);
                break;
        }
    }
    public void setOnRefreshListener(OnRefreshListener mRefreshListener) {
        this.mRefreshListener = mRefreshListener;
    }

    /**
     * @param mPageState STATE_LOADING，STATE_ERROR，STATE_SUCCESS
     */
    public void setPageState(int mPageState) {
        this.mPageState = mPageState;
        shoePage();
    }
    //重新加载监听
    public interface OnRefreshListener{
        void  onRefresh();
    }
}
