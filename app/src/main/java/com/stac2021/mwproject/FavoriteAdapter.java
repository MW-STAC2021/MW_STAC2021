package com.stac2021.mwproject;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class FavoriteAdapter extends BaseAdapter {
//    ArrayList<String> img;
//    ArrayList<String> title;
//    ArrayList<String> id;
//    ArrayList<String> type;
    Context context;
    LinearLayout infoDetails;

    private List<Favorite> userList;
    private List<Favorite> saveList;

    public FavoriteAdapter(Context context, List<Favorite> userList, List<Favorite> saveList) {
        this.context = context;
        this.userList = userList;
        this.saveList = saveList;

    }
    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        String img_path = null;

        if(view == null) {
            view = View.inflate(context, R.layout.favorite_listitem, null);
        }
        ImageView viewImg = view.findViewById(R.id.favorite_image);
        TextView viewTitle = view.findViewById(R.id.favorite_title);
        TextView viewType = view.findViewById(R.id.favorite_category);
        CheckBox checked = view.findViewById(R.id.favorite_check);

        //title 넣기
        viewTitle.setText(userList.get(i).getTitle());
Log.d("myapp", userList.get(i).getTitle());
        //이미지 넣기
        img_path = "http://54.89.236.27:3000/infoThumbnail/" + userList.get(i).getImage();
        Glide.with(view.getContext()).load(img_path).into(viewImg);

        //생활 / 여성 넣기
        viewType.setText(userList.get(i).getType());
        Log.d("myapp", userList.get(i).getId());
        infoDetails = view.findViewById(R.id.favorite_info_details);
        infoDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CardViewActivity.class);
                Log.d("myapp", userList.get(i).getId());
                String setId = userList.get(i).getId();
                intent.putExtra("id", setId);
                //Toast.makeText(v.getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
                context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
            }
        });

        return view;
    }
}
