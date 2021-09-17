package com.stac2021.mwproject.mainTabLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.stac2021.mwproject.ExpandableHeightGridView;
import com.stac2021.mwproject.MainCardViewAdapter;
import com.stac2021.mwproject.R;
import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import server_info_data.AllInfoResponse;
import server_userActivity.JoinActivity;

public class allInfo extends Fragment {

    //retrofit
    private ServiceApi service;
    AllInfoResponse item;
    //id, title, thumbnail  list
    ArrayList<String> infoId = new ArrayList<>();
    ArrayList<String> infoTitle = new ArrayList<>();
    ArrayList<String> infoThumbNail = new ArrayList<>();

    private ImageView testImg;

    ViewFlipper viewFlip;
    ExpandableHeightGridView gridView;
    MainCardViewAdapter adapter;
    String[] itemTitle = {"생리대 사이즈 종류", "쓰레기 분리수거 하는법", "세탁기 돌리는 방법", "전구 갈아끼우는 방법", "생리 용퓸 종류들을 알려줄게!", "피임약 복용 방법을 알려줄게!",
            "월경 주기 계산 방법을 알려줄게!", "생리대 사용 방법을 알려줄게!", "생리컵 사용 방법을 알려줄게"};
    //Integer[] itemImage = {R.drawable.thumbnail1, R.drawable.thumbnail01, R.drawable.thumbnail2, R.drawable.thumbnail02, R.drawable.thumbnail3, R.drawable.thumbnail03, R.drawable.thumbnail4, R.drawable.thumbnail04, R.drawable.thumbnail5};
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frag1, container, false);

        infoData(inflater, container, v);

        //testImg = v.findViewById(R.id.image_test);
//        Log.d("myapp", "title : " + array.getTitle());
//        Log.d("myapp", "thumbnail : " + array.getThumbnailPath());

        // infalte : xml 코드를 java에서 보여주는 메소드

        return v;
    }

    private void infoData(final LayoutInflater inflater, final ViewGroup container, final View v) {
        service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<List<AllInfoResponse>> call = service.listAllInfo("all");
        call.enqueue(new Callback<List<AllInfoResponse>>() {

            @Override
            public void onResponse(Call<List<AllInfoResponse>> call, Response<List<AllInfoResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AllInfoResponse> result = response.body();

                    for (AllInfoResponse info : result) {
                        infoId.add(String.valueOf(info.getId()));
                        infoTitle.add(info.getTitle());
                        infoThumbNail.add(info.getThumbnailPath());
                    }
//                    Log.d("myapp", "allInfo - success");
//                    Log.d("myapp", "allInfo : " + infoId);
//                    Log.d("myapp", infoThumbNail.get(0));
                    //Log.d("myapp", String.valueOf(result));
                } else {
                    Log.d("myapp", "allInfo - else err");
                }

                viewFlip = v.findViewById(R.id.viewFlip);

                viewFlip.setFlipInterval(2500);
                viewFlip.startFlipping();
                gridView = (ExpandableHeightGridView) (v.findViewById(R.id.gridView));
                //adapter = new MainCardViewAdapter(itemImage, itemTitle);
                adapter = new MainCardViewAdapter(infoThumbNail, infoTitle);
                gridView.setAdapter(adapter);
                gridView.setExpanded(true);

                viewFlip.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "클릭 리스너", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getActivity(), JoinActivity.class);
//                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<AllInfoResponse>> call, Throwable t) {
                Log.d("myapp", "allInfo - Failure error");
                Log.e("myapp", "에러 : " + t.getMessage());
                Toast.makeText(getContext(), "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
