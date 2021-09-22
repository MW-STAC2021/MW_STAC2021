package com.stac2021.mwproject;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecentActivity extends AppCompatActivity {
    String[] itemTitle = {"생리대 사이즈 종류", "쓰레기 분리수거 하는법", "세탁기 돌리는 방법", "전구 갈아끼우는 방법", "생리 용퓸 종류들을 알려줄게!", "피임약 복용 방법을 알려줄게!",
            "월경 주기 계산 방법을 알려줄게!", "생리대 사용 방법을 알려줄게!", "생리컵 사용 방법을 알려줄게"};
    Integer[] itemImage = {R.drawable.thumbnail1, R.drawable.thumbnail01, R.drawable.thumbnail2, R.drawable.thumbnail02,
            R.drawable.thumbnail3, R.drawable.thumbnail03, R.drawable.thumbnail4, R.drawable.thumbnail04, R.drawable.thumbnail5};
    ExpandableHeightGridView gridView;
    MainCardViewAdapter adapter;
    androidx.appcompat.widget.Toolbar tb;

    TextView toolar_title;
    ImageButton btnBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_recent);
        gridView = (ExpandableHeightGridView)findViewById(R.id.gridView);
        //adapter = new MainCardViewAdapter(itemImage, itemTitle);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);

        tb = findViewById(R.id.toolbar) ;
        setSupportActionBar(tb);


        toolar_title = findViewById(R.id.toolbar_title);
        btnBack = findViewById(R.id.btnBack);

        toolar_title.setText("최근 살펴본 정보");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
