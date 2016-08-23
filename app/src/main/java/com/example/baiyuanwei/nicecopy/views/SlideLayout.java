package com.example.baiyuanwei.nicecopy.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baiyuanwei.nicecopy.R;
import com.example.baiyuanwei.nicecopy.utils.ScreenUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by baiyuanwei on 16/8/23.
 */
@EViewGroup(R.layout.content_tag_layout)
public class SlideLayout extends LinearLayout {

    @ViewById(R.id.content_layout)
    LinearLayout contentLayout;

    @ViewById(R.id.text)
    TextView leftView;

    private VelocityTracker velocityTracker;
    private int maxVelocity;
    private float velocityX;
    private int pointerId;

    // 大于等于此距离,就自动显示或隐藏
    private int minDistance;

    private int leftViewWidth;

    private float downX;
    private float downY;
    private float x;
    private float y;
    private int tan = 2;

    //0-左侧view隐藏,1-左测view显示
    private int type = 0;

    public SlideLayout(Context context) {
        super(context);
        init(context);

    }

    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        leftViewWidth = ScreenUtils.dp2px(context, 100);
        minDistance = leftViewWidth / 5;
        maxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    @AfterViews
    protected void initView() {

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                createVelocityTracker(event);

                final VelocityTracker vt = velocityTracker;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        clickDown(event);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        clickMove(event, vt);
                        break;
                    case MotionEvent.ACTION_UP:
                        clickUp(event);
                        break;
                    default:
                        releaseVelocityTracker();
                        hideLeftView();
                        break;
                }
                return true;
            }
        });
    }

    private void clickDown(MotionEvent event) {
        pointerId = event.getPointerId(0);
        downX = event.getRawX();
        downY = event.getRawY();
        x = event.getRawX();
        y = event.getRawY();
    }

    private void clickMove(MotionEvent event, VelocityTracker vt) {
        vt.computeCurrentVelocity(1000, maxVelocity);
        velocityX = vt.getXVelocity(pointerId);

        float moveX = event.getRawX();
        float moveY = event.getRawY();
        if (judgeScroll((int) Math.abs(moveX - downX), (int) Math.abs(moveY - downY))) {
            int disX = (int) (downX - moveX);
            if (Math.abs(disX) < leftViewWidth) {
                moveParamsListener(disX);
            }
        }
        downX = moveX;
        downY = moveY;
    }

    private void clickUp(MotionEvent event) {
        float upX = event.getRawX();
        float upY = event.getRawY();
        if (judgeScroll((int) Math.abs(upX - x), (int) Math.abs(upY - y))) {
            int disX = (int) (x - upX);
            if (Math.abs(disX) > minDistance) {
                if (disX < 0) {
                    showLeftView();
                } else {
                    hideLeftView();
                }
            } else {
                hideLeftView();
            }
        } else {
            hideLeftView();
        }
        releaseVelocityTracker();
    }

    private void velocityListener() {
        if (velocityX > 1500) {

        }
    }

    /**
     * 滑动过程中监听事件
     *
     * @param dx = downX-moveX
     */
    private void moveParamsListener(int dx) {
        LayoutParams params = (LayoutParams) leftView.getLayoutParams();
        if (params.leftMargin <= 0 && params.leftMargin >= -leftViewWidth) {
            params.leftMargin -= dx;
            if (params.leftMargin > 0) {
                params.leftMargin = 0;
            } else if (params.leftMargin < -leftViewWidth) {
                params.leftMargin = -leftViewWidth;
            }
        }
        leftView.setLayoutParams(params);

        LayoutParams contentParams = (LayoutParams) contentLayout.getLayoutParams();
        contentParams.rightMargin = -(leftViewWidth + params.leftMargin);
        contentLayout.setLayoutParams(contentParams);
    }

    private void hideLeftView() {
        setLayoutParamsForViews(-leftViewWidth);
    }

    private void showLeftView() {
        setLayoutParamsForViews(0);
    }

    private void setLayoutParamsForViews(int dx) {
        LayoutParams params = (LayoutParams) leftView.getLayoutParams();
        params.leftMargin = dx;
        leftView.setLayoutParams(params);

        LayoutParams contentParams = (LayoutParams) contentLayout.getLayoutParams();
        contentParams.rightMargin = -leftViewWidth - dx;
        contentLayout.setLayoutParams(contentParams);
    }

    private boolean judgeScroll(int dx, int dy) {
        return dx > tan * dy;
    }

    private void createVelocityTracker(MotionEvent event) {
        if (null == velocityTracker) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
    }

    private void releaseVelocityTracker() {
        if (null != velocityTracker) {
            velocityTracker.clear();
            velocityTracker.recycle();
            velocityTracker = null;
        }
    }
}
