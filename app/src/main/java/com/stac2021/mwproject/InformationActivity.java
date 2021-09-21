package com.stac2021.mwproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar tb;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        tb = findViewById(R.id.toolbar) ;
        setSupportActionBar(tb);
        //getSupportActionBar().setHomeAsUpIndicator(true);
        getSupportActionBar().setTitle("개인정보 처리방침");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = findViewById(R.id.textView);

        // 서버에서 가져오기
    }
    public boolean onCreateOptionsMenu(Menu menu) { getMenuInflater().inflate(R.menu.menu_periodsetting, menu); return true; }
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
