package com.stac2021.mwproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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

    String[] itemTitle = {"성장에 좋은 음식", "쌀 보관 방법"};
    Integer[] itemImage = {R.drawable.thumbnail_milk, R.drawable.thumbnail9};
    String[] itemCategory = {"생활정보", "생활정보"};

    androidx.appcompat.widget.Toolbar tb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        mList = view.findViewById(R.id.list);
        mArray = new ArrayList<>();
        tb = view.findViewById(R.id.toolbar) ;
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);

        for(int i=0; i<itemImage.length; i++) {
//            mItem = new Favorite(ContextCompat.getDrawable(getContext(), itemImage[i]), itemTitle[i], itemCategory[i], true);
//            mArray.add(mItem);
        }
//        mAdapter = new FavoriteAdapter(getContext(), mArray);
//        mList.setAdapter(mAdapter);


        return view;
    }
}