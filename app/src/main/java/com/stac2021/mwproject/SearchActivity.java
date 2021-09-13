package com.stac2021.mwproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    String[] itemTitle = {"생리대 사이즈 종류", "쓰레기 분리수거 하는법", "세탁기 돌리는 방법", "전구 갈아끼우는 방법", "생리 용퓸 종류들을 알려줄게!", "피임약 복용 방법을 알려줄게!",
            "월경 주기 계산 방법을 알려줄게!", "생리대 사용 방법을 알려줄게!", "생리컵 사용 방법을 알려줄게"};
    Integer[] itemImage = {R.drawable.thumbnail1, R.drawable.thumbnail01, R.drawable.thumbnail2, R.drawable.thumbnail02, R.drawable.thumbnail3, R.drawable.thumbnail03, R.drawable.thumbnail4, R.drawable.thumbnail04, R.drawable.thumbnail5};
    String[] itemCategory = {"생활정보", "여성정보", "여성정보", "여성정보", "생활정보", "여성정보", "생활정보", "여성정보", "여성정보"};

    ListView mList;
    FavoriteAdapter mAdapter;
    ArrayList<Favorite> mArray;
    Favorite mItem;

    ImageButton btnBack;

    EditText editSearch;

    List<Favorite> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        list = new ArrayList<>();
        editSearch = findViewById(R.id.editSearch);

        mList = findViewById(R.id.list);
        mArray = new ArrayList<>();
        for(int i=0; i<itemImage.length; i++) {
            mItem = new Favorite(ContextCompat.getDrawable(getApplicationContext(), itemImage[i]), itemTitle[i], itemCategory[i], true);
            mArray.add(mItem);
            list.add(mItem);
        }


        mAdapter = new FavoriteAdapter(getApplicationContext(), list);
        mList.setAdapter(mAdapter);

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
        list.clear();

        if(searchText.length() == 0) {
            list.addAll(mArray);
        }
        else {
            for(int i=0; i<mArray.size(); i++) {
                if(mArray.get(i).getTitle().toLowerCase().contains(searchText)) {
                    list.add(mArray.get(i));
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
