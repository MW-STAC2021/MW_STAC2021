package com.stac2021.mwproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar tb;
    TextView textView;

    TextView toolbar_title;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        tb = findViewById(R.id.toolbar) ;
        setSupportActionBar(tb);
        //getSupportActionBar().setHomeAsUpIndicator(true);


        textView = findViewById(R.id.textView);

        // 서버에서 가져오기

        toolbar_title = findViewById(R.id.toolbar_title);
        btnBack = findViewById(R.id.btnBack);

        toolbar_title.setText("개인정보 처리방침");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
