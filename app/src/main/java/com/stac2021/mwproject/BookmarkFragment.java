package com.stac2021.mwproject;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class BookmarkFragment extends Fragment {
    ListView mList;
    FavoriteAdapter mAdapter;
    ArrayList<Favorite> mArray;
    Favorite mItem;

    String[] itemTitle = {"생리대 사이즈 종류", "쓰레기 분리수거 하는법", "세탁기 돌리는 방법", "전구 갈아끼우는 방법", "생리 용퓸 종류들을 알려줄게!", "피임약 복용 방법을 알려줄게!",
            "월경 주기 계산 방법을 알려줄게!", "생리대 사용 방법을 알려줄게!", "생리컵 사용 방법을 알려줄게"};
    Integer[] itemImage = {R.drawable.thumbnail1, R.drawable.thumbnail01, R.drawable.thumbnail2, R.drawable.thumbnail02, R.drawable.thumbnail3, R.drawable.thumbnail03, R.drawable.thumbnail4, R.drawable.thumbnail04, R.drawable.thumbnail5};
    String[] itemCategory = {"생활정보", "여성정보", "여성정보", "여성정보", "생활정보", "여성정보", "생활정보", "여성정보", "여성정보"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        mList = view.findViewById(R.id.list);
        mArray = new ArrayList<>();

        for(int i=0; i<itemImage.length; i++) {
            mItem = new Favorite(ContextCompat.getDrawable(getContext(), itemImage[i]), itemTitle[i], itemCategory[i], true);
            mArray.add(mItem);
        }
        mAdapter = new FavoriteAdapter(getContext(), mArray);
        mList.setAdapter(mAdapter);


        return view;
    }
}