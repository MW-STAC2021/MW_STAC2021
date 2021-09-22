package com.stac2021.mwproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {
    ListView mList;
    NoticeAdapter mAdapter;
    ArrayList<Notice> mArray;
    Notice mItem;

    String[] title = {"리뷰 및 별점주기", "5월의 공지사항", "안녕하세요. 엄마의 잔소리 개발팀입니다.", "오류 신고하기"};
    String[] date = {"2021.07.19", "2021.08.31", "2021.09.02", "2021.09.12"};

    androidx.appcompat.widget.Toolbar tb;

    TextView toolbar_title;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        tb = findViewById(R.id.toolbar) ;

        mList = findViewById(R.id.list);
        mArray = new ArrayList<>();

        for(int i=0; i<title.length; i++) {
            mItem = new Notice(title[i], date[i]);
            mArray.add(mItem);
        }
        mAdapter = new NoticeAdapter(this, mArray);
        mList.setAdapter(mAdapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Notice item = (Notice)mList.getItemAtPosition(i);
                String title = item.getTitle();
                Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("공지사항");
    }
}
