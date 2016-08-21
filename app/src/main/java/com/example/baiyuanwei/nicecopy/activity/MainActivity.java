package com.example.baiyuanwei.nicecopy.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.baiyuanwei.nicecopy.R;
import com.example.baiyuanwei.nicecopy.base.BaseListActivity;
import com.example.baiyuanwei.nicecopy.fragments.FirstFragment;
import com.example.baiyuanwei.nicecopy.fragments.FirstFragment_;
import com.example.baiyuanwei.nicecopy.fragments.SecondFragment;
import com.example.baiyuanwei.nicecopy.fragments.SecondFragment_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity
public class MainActivity extends BaseListActivity {

    @ViewById(R.id.list_0)
    TextView list0Text;
    @ViewById(R.id.list_1)
    TextView list1Text;

    private List<TextView> textList = new ArrayList<>();

    private Fragment fragment;

    private int currentFlag = 0;
    private int clickFlag = 0;
    private int preClickFlag = clickFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_activity);

        textList.add(list0Text);
        textList.add(list1Text);

        fragment = FirstFragment_.builder().build();
        initFragment(R.id.fragment, fragment);
    }

    @Click(R.id.list_0)
    protected void list0Listener() {
        clickFlag = 0;
        if (!(fragment instanceof FirstFragment)) {
            fragment = null;
            fragment = FirstFragment_.builder().build();
            initFragment(R.id.fragment, fragment);
            currentFlag = 0;
        }

        resetTagStyle();
    }

    @Click(R.id.list_1)
    protected void list1Listener() {
        clickFlag = 1;
        if (!(fragment instanceof SecondFragment)) {
            fragment = null;
            fragment = SecondFragment_.builder().build();
            initFragment(R.id.fragment, fragment);
            currentFlag = 1;
        }
        resetTagStyle();
    }

    private void resetTagStyle() {
        if (preClickFlag == clickFlag) {
            return;
        }
        for (int i = 0; i < textList.size(); i++) {
            TextView textView = textList.get(i);
            if (currentFlag == i) {
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setBackgroundColor(getResources().getColor(R.color.black));
            } else {
                textView.setTextColor(getResources().getColor(R.color.black));
                textView.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
        preClickFlag = clickFlag;
    }
}
