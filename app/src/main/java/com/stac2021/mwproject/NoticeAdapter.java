package com.stac2021.mwproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoticeAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Notice> mData;

    public NoticeAdapter(Context mContext, ArrayList<Notice> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = View.inflate(mContext, R.layout.item_notice_list, null);

        }
        TextView title = view.findViewById(R.id.noticeTitle);
        TextView date = view.findViewById(R.id.noticeDate);

        title.setText(mData.get(i).title);
        date.setText(mData.get(i).date);

        return view;
    }
}
