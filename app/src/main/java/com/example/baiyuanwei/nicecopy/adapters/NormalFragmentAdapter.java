package com.example.baiyuanwei.nicecopy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.baiyuanwei.nicecopy.R;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by baiyuanwei on 16/8/19.
 */

public class NormalFragmentAdapter extends BaseAdapter {

    private WeakReference<Context> contextWeakReference;
    private List<String> datas;

    public NormalFragmentAdapter(Context context, List<String> datas) {
        this.contextWeakReference = new WeakReference<Context>(context);
        this.datas = datas;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FirstViewHolder viewHolder;
        if (null == view) {
            view = LayoutInflater.from(contextWeakReference.get()).inflate(R.layout.first_list_fragment_item, viewGroup, false);
            viewHolder = new FirstViewHolder();

            viewHolder.textView = (TextView) view.findViewById(R.id.first_list_fragment_item_content_tesxt);

            view.setTag(viewHolder);
        } else {
            viewHolder = (FirstViewHolder) view.getTag();
        }

        viewHolder.textView.setText(datas.get(i));

        return view;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    public class FirstViewHolder {
        private TextView textView;
    }
}
