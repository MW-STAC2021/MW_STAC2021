package com.stac2021.mwproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import server_userActivity.JoinActivity;

public class MainCardViewAdapter extends BaseAdapter {
    ArrayList<String> img;
    ArrayList<String> title;
    Context context;
    LinearLayout infoDetails;

    public MainCardViewAdapter(Context context, ArrayList<String> img, ArrayList<String> title) {
        this.context = context;
        this.img = img;
        this.title = title;
    }

    public int getCount() {
        //return img.length;
        //Log.d("myapp", "img.size() : " + img.size());
        return img.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView viewImage;
        TextView viewTitle;

        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.cardview_item, null);
        }

        final Context context = viewGroup.getContext();

        //Log.d("myapp", "getView success");

        viewImage = view.findViewById(R.id.imgCardView);
        viewTitle = view.findViewById(R.id.titleCardView);
        String img_path = null;

        try {
            //title 넣기
            title.get(i);
            titlev.setText(title.get(i));
            img_path = img.get(i);
        } catch (IndexOutOfBoundsException e) {
            Log.d("myapp", String.valueOf(e));
        }

        //cardView 누를 시 해당 정보화면 불러옴
        infoDetails = view.findViewById(R.id.info_details);
        infoDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CardViewActivity.class);
                Toast.makeText(v.getContext(), "Position:", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
        //imgv.setImageResource(bitmap);
        return view;
    }
//
//    public void setClickListener(allInfo allInfo) {
//        this.clicklistener = allInfo;
//
//    }
}
