package com.stac2021.mwproject;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class MainCardViewAdapter extends BaseAdapter {
    Integer[] img;
    String[] title;
    public MainCardViewAdapter(Integer[] img, String[] title) {
        this.img = img;
        this.title = title;
    }
    public int getCount() {
        return img.length;
    }
    public Object getItem(int i) {
        return null;
    }
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imgv;
        TextView titlev;
        if(view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.cardview_item, null);
        }
        imgv = view.findViewById(R.id.imgCardView);
        titlev = view.findViewById(R.id.titleCardView);
        imgv.setImageResource(img[i]);
        imgv.setClipToOutline(true);
        titlev.setText(title[i]);
        return view;
    }
}
