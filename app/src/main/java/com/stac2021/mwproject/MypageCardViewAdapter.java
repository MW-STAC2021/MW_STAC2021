package com.stac2021.mwproject;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MypageCardViewAdapter extends RecyclerView.Adapter<MypageCardViewAdapter.ViewHolder> {
    ArrayList<String> img_arr;
    ArrayList<String> title_arr;
    ArrayList<String> id;
    LinearLayout infoDetails;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView title;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            img = itemView.findViewById(R.id.mypage_img);
            title = itemView.findViewById(R.id.mypage_title);
        }
    }
    public MypageCardViewAdapter(ArrayList<String> id, ArrayList<String> img, ArrayList<String> title) {
        this.id = id;
        this.img_arr = img;
        this.title_arr = title;
    }

    @NonNull
    @Override
    public MypageCardViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_mypage_cardview, parent, false) ;
        MypageCardViewAdapter.ViewHolder vh = new MypageCardViewAdapter.ViewHolder(view) ;
        infoDetails = parent.findViewById(R.id.info_details);


        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull MypageCardViewAdapter.ViewHolder holder, final int position) {
        String img_path = "http://54.89.236.27:3000/infoThumbnail/" + img_arr.get(position);
        Log.d("myapp", img_path);
        Glide.with(holder.itemView.getContext())
                .load(img_path)
                .into(holder.img);
        holder.title.setText(title_arr.get(position));


//        infoDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), InfoViewActivity.class);
//                Log.d("myapp", id.get(position));
//                String setId = id.get(position);
//                intent.putExtra("id", setId);
//                //Toast.makeText(v.getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return img_arr.size();
    }

}