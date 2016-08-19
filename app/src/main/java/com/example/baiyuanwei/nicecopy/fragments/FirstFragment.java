package com.example.baiyuanwei.nicecopy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baiyuanwei.nicecopy.R;
import com.example.baiyuanwei.nicecopy.adapters.FirstFragmentAdapter;

import org.androidannotations.annotations.EFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiyuanwei on 16/8/19.
 */
@EFragment
public class FirstFragment extends ListFragment {

    private List<String> datas = new ArrayList<>();
    private FirstFragmentAdapter adapter;
    private WeakReference<Context> contextWeakReference;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        contextWeakReference = new WeakReference<Context>(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_list_fragment, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addData();
        adapter = new FirstFragmentAdapter(contextWeakReference.get(), datas);
        setListAdapter(adapter);
    }

    private void addData() {
        for (int i = 0; i < 20; i++) {
            datas.add("第一个listFragment: " + i);
        }
    }
}
