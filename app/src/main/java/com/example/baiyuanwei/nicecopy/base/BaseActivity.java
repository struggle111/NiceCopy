package com.example.baiyuanwei.nicecopy.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.baiyuanwei.nicecopy.interfaces.IScrollable;

/**
 * Created by baiyuanwei on 16/8/14.
 */
public class BaseActivity extends FragmentActivity implements IScrollable {

    private boolean isScrolling;

    protected void initFragment(int containerId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void setIsScroll(boolean isScroll) {
        isScrolling = isScroll;
    }

    @Override
    public boolean isScroll() {
        return isScrolling;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
