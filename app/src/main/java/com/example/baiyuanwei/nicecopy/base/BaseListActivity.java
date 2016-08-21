package com.example.baiyuanwei.nicecopy.base;

/**
 * Created by baiyuanwei on 16/8/21.
 * <p>
 * 需要用到ListFragment的activity继承此activity
 */

public class BaseListActivity extends BaseActivity {

    @Override
    public void onBackPressed() {
        finish();
    }
}
