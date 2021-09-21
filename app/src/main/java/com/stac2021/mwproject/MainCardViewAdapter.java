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

import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class MainCardViewAdapter extends BaseAdapter {
    ArrayList<String> img;
    ArrayList<String> title;
    ArrayList<String> id;
    Context context;
    LinearLayout infoDetails;

    public MainCardViewAdapter(Context context, ArrayList<String> img, ArrayList<String> title, ArrayList<String> id) {
        this.context = context;
        this.img = img;
        this.title = title;
        this.id = id;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ImageView viewImage;
        TextView viewTitle;

        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.cardview_item, null);
        }

        final Context context = viewGroup.getContext();

        //Log.d("myapp", "getView success");

        viewImage = view.findViewById(R.id.img_card_view);
        viewTitle = view.findViewById(R.id.title_card_view);
        viewImage.setClipToOutline(true);
        String img_path = null;

        try {
            //title 넣기
            title.get(i);
            viewTitle.setText(title.get(i));

            //서버 url로 이미지 불러오기
            img_path = "http://54.89.236.27:3000/infoThumbnail/" + img.get(i);
            Glide.with(view.getContext()).load(img_path).into(viewImage);


        } catch (IndexOutOfBoundsException e) {
            Log.d("myapp", String.valueOf(e));
        }

        //cardView 누를 시 해당 정보화면 불러옴
        infoDetails = view.findViewById(R.id.info_details);
        infoDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InfoViewActivity.class);
                Log.d("myapp", id.get(i));
                String setId = id.get(i);
                intent.putExtra("id", setId);
                //Toast.makeText(v.getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
        return view;
    }
}
