package com.stac2021.mwproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import server_userActivity.JoinActivity;

public class MainCardViewAdapter extends BaseAdapter {
    ArrayList<String> img;
    ArrayList<String> title;
    Bitmap bitmap;

    // Integer[] img;
//    String[] title;
    public MainCardViewAdapter(ArrayList<String> img, ArrayList<String> title) {
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imgv;
        TextView titlev;


        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.cardview_item, null);
        }

        //Log.d("myapp", "getView success");

        imgv = view.findViewById(R.id.imgCardView);
        titlev = view.findViewById(R.id.titleCardView);
        String img_path = null;

        try {
            title.get(i);
            titlev.setText(title.get(i));
            img_path = img.get(i);
        } catch (IndexOutOfBoundsException e) {
            Log.d("myapp", String.valueOf(e));
        }
        
        //서버 주소 이미지 -> 이미지로 변환
        final String finalImg_path = "http://54.89.236.27:3000/infoThumbnail/" + img_path;
        //Log.d("myapp", "url path : " + finalImg_path);
        Thread uThread = new Thread() {
            @Override
            public void run() {
                try {
                    //서버에 올려둔 이미지 URL
                    URL url = new URL(finalImg_path);
                    //Log.d("myapp", "url : " + String.valueOf(url));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                    conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
                    InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                    bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        uThread.start();

        // .join()을 쓰면 사진이 뜨는데, 로딩 시간이 굉장히 오래 걸림

//        try {
//            uThread.join(); // 작업 Thread 실행
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        imgv.setImageBitmap(bitmap);
        imgv.setClipToOutline(true);

        //imgv.setImageResource(bitmap);

        return view;
    }
}