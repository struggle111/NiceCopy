package com.example.baiyuanwei.nicecopy.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.baiyuanwei.nicecopy.R;
import com.example.baiyuanwei.nicecopy.base.BaseActivity;
import com.example.baiyuanwei.nicecopy.fragments.FirstFragment_;

import org.androidannotations.annotations.EActivity;

@EActivity
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_activity);
        Fragment fragment = FirstFragment_.builder().build();
        initFragment(R.id.fragment, fragment);
    }
}
