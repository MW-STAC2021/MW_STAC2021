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

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class FavoriteAdapter extends BaseAdapter {
    Context context;
    LinearLayout infoDetails;

    private List<Favorite> infoList;

    public FavoriteAdapter(Context context, List<Favorite> userList) {
        this.context = context;
        this.infoList = userList;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int i) {
        return infoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        String img_path = null;

        if (view == null) {
            view = View.inflate(context, R.layout.favorite_listitem, null);
        }
        ImageView viewImg = view.findViewById(R.id.favorite_image);
        TextView viewTitle = view.findViewById(R.id.favorite_title);
        TextView viewType = view.findViewById(R.id.favorite_category);
        final CheckBox checked = view.findViewById(R.id.favorite_check);

        Boolean what;
        checked.setChecked(true);

        //title 넣기
        viewTitle.setText(infoList.get(i).getTitle());

        //이미지 넣기
        img_path = "https://mom.emirim.kr/infoThumbnail/" + infoList.get(i).getImage();
        Glide.with(view.getContext()).load(img_path).into(viewImg);
        viewImg.setClipToOutline(true);

        //타입 넣기
        viewType.setText(infoList.get(i).getType());

        // 클릭 시 정보 화면으로 이동
        infoDetails = view.findViewById(R.id.favorite_info_details);
        infoDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InfoViewActivity.class);
                String setId = infoList.get(i).getId();
                intent.putExtra("id", setId);
                //Toast.makeText(v.getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
                context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
            }
        });

        checked.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("myapp", "-- 좋아요 온클릭 --");
                        if(checked.isChecked()){
                            Log.d("myapp", "@@ 즐겨찾기 다시 추가하러 감 @@");
                            Favorite.getInstance().insertKeep(infoList.get(i).getId());
                            checked.setChecked(true);
                        }else{
                            Log.d("myapp", "@@ 즐겨찾기 해제하러 감 @@");
                            Favorite.getInstance().deleteKeep(infoList.get(i).getId());
                            checked.setChecked(false);
                        }
                    }

                }
        );

        return view;
    }
}
