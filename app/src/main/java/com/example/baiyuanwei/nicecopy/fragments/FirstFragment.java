package com.example.baiyuanwei.nicecopy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baiyuanwei.nicecopy.R;
import com.example.baiyuanwei.nicecopy.adapters.NormalFragmentAdapter;

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
    private NormalFragmentAdapter adapter;
    private WeakReference<Context> contextWeakReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contextWeakReference = new WeakReference<Context>(getActivity());
        View view = inflater.inflate(R.layout.first_list_fragment, container, false);
        addData();
        adapter = new NormalFragmentAdapter(contextWeakReference.get(), datas);
        setListAdapter(adapter);
        return view;
    }
    private void addData() {
        for (int i = 0; i < 20; i++) {
            datas.add("第一个listFragment: " + i);
        }
    }
}
