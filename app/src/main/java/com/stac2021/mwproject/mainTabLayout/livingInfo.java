package com.stac2021.mwproject.mainTabLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.stac2021.mwproject.ExpandableHeightGridView;
import com.stac2021.mwproject.MainCardViewAdapter;
import com.stac2021.mwproject.R;
import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import server_info_data.AllInfoResponse;

public class livingInfo extends Fragment {

    //retrofit
    private ServiceApi service;
    AllInfoResponse item;
    //id, title, thumbnail  list
    ArrayList<String> infoId = new ArrayList<>();
    ArrayList<String> infoTitle = new ArrayList<>();
    ArrayList<String> infoThumbNail = new ArrayList<>();

    ExpandableHeightGridView gridView;
    MainCardViewAdapter adapter;
    String[] itemTitle = {"생리대 사이즈 종류", "쓰레기 분리수거 하는법", "세탁기 돌리는 방법", "전구 갈아끼우는 방법", "생리 용퓸 종류들을 알려줄게!", "피임약 복용 방법을 알려줄게!",
            "월경 주기 계산 방법을 알려줄게!", "생리대 사용 방법을 알려줄게!", "생리컵 사용 방법을 알려줄게"};
    Integer[] itemImage = {R.drawable.thumbnail1, R.drawable.thumbnail01, R.drawable.thumbnail2, R.drawable.thumbnail02,
            R.drawable.thumbnail3, R.drawable.thumbnail03, R.drawable.thumbnail4, R.drawable.thumbnail04, R.drawable.thumbnail5};
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frag3, container, false);
        // infalte : xml 코드를 java에서 보여주는 메소드
        gridView = (ExpandableHeightGridView)(v.findViewById(R.id.gridView));
        adapter = new MainCardViewAdapter(itemImage, itemTitle);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);
        return v;
    }

    private void infoData(final LayoutInflater inflater, final ViewGroup container) {
        service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<List<AllInfoResponse>> call = service.listAllInfo("1");
        call.enqueue(new Callback<List<AllInfoResponse>>() {
            @Override
            public void onResponse(Call<List<AllInfoResponse>> call, Response<List<AllInfoResponse>> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    List<AllInfoResponse> result = response.body();
                    //id
                    for(AllInfoResponse info : result){
                        infoId.add(String.valueOf(info.getId()));
                    }
                    //title
                    for(AllInfoResponse info : result){
                        infoTitle.add(info.getTitle());
                    }
                    //thumbnail
                    for(AllInfoResponse info : result){
                        infoThumbNail.add(info.getThumbnailPath());
                    }
                    Log.d("myapp", "living - success");
                    Log.d("myapp", "living : " + infoId);
                    setView(inflater, container);
                    //Log.d("myapp", String.valueOf(result));
                }else {
                    Log.d("myapp", "living - else err");
                }
            }

            @Override
            public void onFailure(Call<List<AllInfoResponse>> call, Throwable t) {
                Log.d("myapp", "living - Failure error");
                Log.e("myapp", "에러 : " + t.getMessage());

            }
        });
    }

    private void setView(LayoutInflater inflater, ViewGroup container) {
        View v = inflater.inflate(R.layout.frag1, container, false);
    }

}
