package com.stac2021.mwproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MypageCardViewAdapter extends RecyclerView.Adapter<MypageCardViewAdapter.ViewHolder> {
    Integer[] img_arr;
    String[] title_arr;
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
    public MypageCardViewAdapter(Integer[] img, String[] title) {
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

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull MypageCardViewAdapter.ViewHolder holder, int position) {
        Integer imgv = img_arr[position];
        String titlev = title_arr[position];
        holder.img.setImageResource(imgv);
        holder.title.setText(titlev);
    }

    @Override
    public int getItemCount() {
        return img_arr.length;
    }

}
