package com.example.baiyuanwei.nicecopy.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.baiyuanwei.nicecopy.R;
import com.example.baiyuanwei.nicecopy.interfaces.IScrollable;

import java.lang.ref.WeakReference;

/**
 * Created by baiyuanwei on 16/8/19.
 */

public class FooterListView extends ListView {

    private final static String TAG = FooterListView.class.getSimpleName();

    private View footerView;

    private boolean isFooterViewShow = true;


    private WeakReference<Context> mContextWeakReference;

    private OnScrollListener onScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (mContextWeakReference.get() instanceof IScrollable) {
                IScrollable iScrollable = (IScrollable) mContextWeakReference.get();
                if (i == OnScrollListener.SCROLL_STATE_FLING) {
                    iScrollable.setIsScroll(true);
                } else {
                    iScrollable.setIsScroll(false);
                }
            }

        }

        @Override
        public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            if (isFooterViewShow) {

                //只有一页不显示footer
                if (i1 == i2) {
                    hideFooterView();
                } else {

                }
            }
        }
    };

    public FooterListView(Context context) {
        super(context);
        init(context);
    }

    public FooterListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FooterListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContextWeakReference = new WeakReference<Context>(context);

        try {
            footerView = LayoutInflater.from(context).inflate(R.layout.load_more_footer, this, false);
            addFooterView(footerView);
            hideFooterView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideFooterView() {
        try {
            if (null != footerView) {
                footerView.setVisibility(GONE);
                removeFooterView(footerView);
            }
        } catch (Exception e) {
            Log.e(TAG, "隐藏底部view失败");
        }
    }

    private void showFooterView() {
        try {
            if (null != footerView) {
                addFooterView(footerView);
                footerView.setVisibility(VISIBLE);
            }
        } catch (Exception e) {
            Log.e(TAG, "显示底部view失败");
        }
    }

    public void setFooterViewShow(boolean isShow) {
        isFooterViewShow = isShow;
        try {
            if (!isFooterViewShow && null != footerView) {
                removeFooterView(footerView);
            } else {
                addFooterView(footerView);
                hideFooterView();
            }
        } catch (Exception e) {
            Log.e(TAG, "isFooterViewShow error");
        }
    }

    /**
     * case by
     * else if (mItemCount != mAdapter.getCount())
     */
    @Override
    protected void layoutChildren() {
        try {
            super.layoutChildren();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "mItemCount != mAdapter.getCount()  exception");
        }
    }
}
