package com.stac2021.mwproject.mainTabLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.stac2021.mwproject.ExpandableHeightGridView;
import com.stac2021.mwproject.Favorite;
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

public class allInfo extends Fragment {

    //retrofit
    private ServiceApi service;

    //id, title, thumbnail  list
    ArrayList<String> infoId = new ArrayList<>();
    ArrayList<String> infoTitle = new ArrayList<>();
    ArrayList<String> infoThumbNail = new ArrayList<>();
    ArrayList<Boolean> infoIsChecked = new ArrayList<>();
    ArrayList<String> infoType = new ArrayList<>();

    ViewFlipper viewFlip;
    ExpandableHeightGridView gridView;
    MainCardViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.frag1, container, false);
        Favorite.getInstance().keepList();
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
                        boolean isCheck = Favorite.getInstance().isChecked(String.valueOf(info.getId()));
//                        Log.d("myapp", String.valueOf(isCheck));
                        infoIsChecked.add(isCheck);
                        infoType.add(info.getToolBarType());

                    }
                    Log.d("myapp", "allInfo - success");
                } else {
                    Log.d("myapp", "allInfo - else err");
                }

                // 베너
                viewFlip = v.findViewById(R.id.viewFlip);
                viewFlip.setFlipInterval(2500);
                viewFlip.startFlipping();

                // 카드뷰
                gridView = (ExpandableHeightGridView) (v.findViewById(R.id.gridView));
                adapter = new MainCardViewAdapter(getContext(), infoThumbNail, infoTitle, infoId, infoIsChecked, infoType);
                gridView.setAdapter(adapter);
                gridView.setExpanded(true);
            }

            @Override
            public void onFailure(Call<List<AllInfoResponse>> call, Throwable t) {
                Log.d("myapp", "allInfo - Failure error");
                Log.e("myapp", "에러 : " + t.getMessage());
                Toast.makeText(getContext(), "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }

}
