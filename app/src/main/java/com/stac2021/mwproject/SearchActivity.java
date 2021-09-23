package com.stac2021.mwproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import server_info_data.AllInfoResponse;
import server_info_data.InfoListResponse;
import server_info_data.InfoSearchResponse;

public class SearchActivity extends AppCompatActivity {


    ImageButton btnBack;
    EditText editSearch;

    ListView listView;
    private ServiceApi service;
    private FavoriteAdapter adapter;
    private List<Favorite> infoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editSearch = findViewById(R.id.editSearch);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        infoList = new ArrayList<Favorite>();
        listView = findViewById(R.id.list);
        adapter = new FavoriteAdapter(getApplicationContext(), infoList);//로 수정됨
        listView.setAdapter(adapter);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();

                search(text);
            }
        });
    }

    public void search(String searchText) {
        infoList.clear(); // 재검색할 때마다 리스트 비우기
        if (searchText.length() != 0) {
            Call<List<InfoSearchResponse>> call = service.InfoSearch(searchText);
            call.enqueue(new Callback<List<InfoSearchResponse>>() {
                @Override
                public void onResponse(Call<List<InfoSearchResponse>> call, Response<List<InfoSearchResponse>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<InfoSearchResponse> result = response.body();

                        for (InfoSearchResponse info : result) {
                            Favorite data = new Favorite(
                                    info.thumbnailPath, info.title, info.getToolBarType(), String.valueOf(info.id), false);
                            infoList.add(data);
                        }
                    } else {
                        Log.d("myapp", "serach - else err");
                    }
                }

                @Override
                public void onFailure(Call<List<InfoSearchResponse>> call, Throwable t) {
                    Log.d("myapp", "search - Failure error");
                    Log.e("myapp", "에러 : " + t.getMessage());
                    Toast.makeText(getApplicationContext(), "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        adapter.notifyDataSetChanged();
    }
}
