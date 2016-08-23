package com.example.baiyuanwei.nicecopy.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.ViewConfiguration;

/**
 * Created by baiyuanwei on 16/8/22.
 * 1、防
 */

public class NiceCopySwipeRefreshLayout extends SwipeRefreshLayout {


    private int mTouchSlop;
    private float downX;

    public NiceCopySwipeRefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public NiceCopySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

//    /**
//     * 进入界面自动刷新
//     */
//    public void setEntryAutoRefresh(){
//        setProgressViewOffset(false,0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,64,getResources().getDisplayMetrics()));
//    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                downX = ev.getX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float moveX = ev.getX();
//                int distance = (int) Math.abs(moveX-downX);
//                if (distance > mTouchSlop+ ScreenUtils.dp2px(getContext(),30)){
//                    return false;
//                }
//        }
//        return super.onInterceptTouchEvent(ev);
//    }
}
