package com.example.baiyuanwei.nicecopy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baiyuanwei.nicecopy.R;
import com.example.baiyuanwei.nicecopy.adapters.NormalFragmentAdapter;
import com.example.baiyuanwei.nicecopy.views.NiceCopySwipeRefreshLayout;

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
    private NiceCopySwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_list_fragment, container, false);
        swipeRefreshLayout = (NiceCopySwipeRefreshLayout) view.findViewById(R.id.second_list_swipe_refresh_layout);
        mContextWeakReference = new WeakReference<Context>(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        addRefreshData();
                        adapter.notifyDataSetChanged();
                    }
                }, 3000);
            }
        });
        addData();
        adapter = new NormalFragmentAdapter(mContextWeakReference.get(), datas);
        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void addData() {
        for (int i = 0; i < 20; i++) {
            datas.add("第二个listFragment: " + i);
        }
    }

    private void addRefreshData() {
        ArrayList<String> refreshDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            refreshDatas.add("2-刷新数据: " + i);
        }
        datas.addAll(0, refreshDatas);
    }
}
