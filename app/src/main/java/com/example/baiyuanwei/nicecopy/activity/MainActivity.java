package com.example.baiyuanwei.nicecopy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.baiyuanwei.nicecopy.R;

import org.androidannotations.annotations.EActivity;

@EActivity
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
