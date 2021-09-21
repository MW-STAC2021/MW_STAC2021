package com.stac2021.mwproject;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stac2021.mwproject.keep_data.KeepResponse;
import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import server_info_data.InfoSearchResponse;

public class BookmarkFragment extends Fragment {
/*    ListView mList;
    FavoriteAdapter mAdapter;
    ArrayList<Favorite> mArray;
    Favorite mItem;

    String[] itemTitle = {"성장에 좋은 음식", "쌀 보관 방법"};
    Integer[] itemImage = {R.drawable.thumbnail_milk, R.drawable.thumbnail9};
    String[] itemCategory = {"생활정보", "생활정보"};*/

    Context context;
    String userId;
    TextView noDataText;
    androidx.appcompat.widget.Toolbar tb;

    ListView listView;
    private ServiceApi service;
    private FavoriteAdapter adapter;
    private List<Favorite> infoList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
/*
        mList = view.findViewById(R.id.list);
        mArray = new ArrayList<>();
        tb = view.findViewById(R.id.toolbar) ;
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);*/
        service = RetrofitClient.getClient().create(ServiceApi.class);
        context = this.getActivity();
        noDataText = (TextView) view.findViewById(R.id.no_keep);
        userId = ((app)getActivity().getApplication()).getUserId();

        listView = view.findViewById(R.id.list);

        Call<List<KeepResponse>> call = service.KeepList(userId);
        call.enqueue(new Callback<List<KeepResponse>>() {
            @Override
            public void onResponse(Call<List<KeepResponse>> call, Response<List<KeepResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<KeepResponse> result = response.body();
                    if(result.size() == 0){
                        noDataText.setVisibility(View.VISIBLE);
                    }
                    else{
                        for (KeepResponse info : result) {
                            Favorite data = new Favorite(
                                    info.thumbnailPath, info.title, info.getToolBarType(), String.valueOf(info.id), true);
                            infoList.add(data);
                        }
                        adapter = new FavoriteAdapter(context, infoList);
                        listView.setAdapter(adapter);
                    }
                }
                else{
                    Log.d("myapp", "bookMark - else err");
                }
            }

            @Override
            public void onFailure(Call<List<KeepResponse>> call, Throwable t) {
                Toast.makeText(context, "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}