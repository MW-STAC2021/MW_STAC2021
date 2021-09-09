package com.stac2021.mwproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MypageFragment extends Fragment {

    androidx.appcompat.widget.Toolbar tb;
    RecyclerView recyclerView;
    MypageCardViewAdapter adapter;
    String[] itemTitle = {"생리대 사이즈 종류", "쓰레기 분리수거 하는법", "세탁기 돌리는 방법", "전구 갈아끼우는 방법", "생리 용퓸 종류들을 알려줄게!", "피임약 복용 방법을 알려줄게!",
            "월경 주기 계산 방법을 알려줄게!", "생리대 사용 방법을 알려줄게!", "생리컵 사용 방법을 알려줄게"};
    Integer[] itemImage = {R.drawable.thumbnail1, R.drawable.thumbnail01, R.drawable.thumbnail2, R.drawable.thumbnail02, R.drawable.thumbnail3, R.drawable.thumbnail03, R.drawable.thumbnail4, R.drawable.thumbnail04, R.drawable.thumbnail5};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);
        tb = view.findViewById(R.id.toolbar) ;
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new MypageCardViewAdapter(itemImage, itemTitle);
        recyclerView.setAdapter(adapter);


        return view;
    }
}