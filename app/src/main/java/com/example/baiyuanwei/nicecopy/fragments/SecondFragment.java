package com.example.baiyuanwei.nicecopy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by baiyuanwei on 16/8/21.
 */
@EFragment
public class SecondFragment extends ListFragment {

    private WeakReference<Context> mContextWeakReference;
    private List<String> datas = new ArrayList<>();
    private NormalFragmentAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContextWeakReference = new WeakReference<Context>(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContextWeakReference.get()).inflate(R.layout.second_list_fragment, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addData();
        adapter = new NormalFragmentAdapter(mContextWeakReference.get(), datas);
        setListAdapter(adapter);
    }

    private void addData() {
        for (int i = 0; i < 20; i++) {
            datas.add("第二个listFragment: " + i);
        }
    }
}
