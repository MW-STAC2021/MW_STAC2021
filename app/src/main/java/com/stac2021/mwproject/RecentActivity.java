package com.stac2021.mwproject;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;
import com.stac2021.mwproject.other_data.RecentlyResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import server_info_data.AllInfoResponse;

public class RecentActivity extends AppCompatActivity {
    ArrayList<String> infoId = new ArrayList<>();
    ArrayList<String> infoTitle = new ArrayList<>();
    ArrayList<String> infoThumbNail = new ArrayList<>();
    ArrayList<Boolean> infoIsChecked = new ArrayList<>();
    ExpandableHeightGridView gridView;
    MainCardViewAdapter adapter;
    androidx.appcompat.widget.Toolbar tb;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_recent);
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

        Call<List<RecentlyResponse>> call = service.RecentlyList("all");
        call.enqueue(new Callback<List<RecentlyResponse>>() {
            @Override
            public void onResponse(Call<List<RecentlyResponse>> call, Response<List<RecentlyResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<RecentlyResponse> result = response.body();
                    for (RecentlyResponse info : result) {
                        infoId.add(String.valueOf(info.id));
                        infoTitle.add(info.title);
                        infoThumbNail.add(info.thumbnailPath);
                        boolean isCheck = Favorite.getInstance().isChecked(String.valueOf(info.id));
                        infoIsChecked.add(isCheck);
                    }
                    Log.d("myapp", "allInfo - success");
                } else {
                    Log.d("myapp", "allInfo - else err");
                }
                // 카드뷰
                gridView = (ExpandableHeightGridView)findViewById(R.id.gridView);
                adapter = new MainCardViewAdapter(getApplicationContext(), infoThumbNail, infoTitle, infoId, infoIsChecked);
                gridView.setAdapter(adapter);
                gridView.setExpanded(true);
            }

            @Override
            public void onFailure(Call<List<RecentlyResponse>> call, Throwable t) {
                Log.d("myapp", "allInfo - Failure error");
                Log.e("myapp", "에러 : " + t.getMessage());

            }
        });

        tb = findViewById(R.id.toolbar) ;
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("최근 살펴본 정보");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
