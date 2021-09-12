package com.stac2021.mwproject;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends BaseAdapter {
    Context mContext;
    List<Favorite> mData;
    public FavoriteAdapter(Context mContext, List<Favorite> mData) {
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = View.inflate(mContext, R.layout.favorite_listitem, null);
        }
        ImageView image = view.findViewById(R.id.favorite_image);
        TextView title = view.findViewById(R.id.favorite_title);
        TextView category = view.findViewById(R.id.favorite_category);
        CheckBox checked = view.findViewById(R.id.favorite_check);

        image.setImageDrawable(mData.get(i).image);
        image.setClipToOutline(true);
        title.setText(mData.get(i).title);
        category.setText(mData.get(i).category);
        checked.setChecked(mData.get(i).checked);
        return view;
    }
}
