package com.stac2021.mwproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class InformationActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar tb;
    TextView textView;

    TextView toolbar_title;
    ImageButton btnBack;
    TextView txtRead;
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/files/privacyPolicy.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        tb = findViewById(R.id.toolbar) ;
        setSupportActionBar(tb);
        toolbar_title = findViewById(R.id.toolbar_title);
        btnBack = findViewById(R.id.btnBack);

        toolbar_title.setText("개인정보 처리방침");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textView = findViewById(R.id.textView);
    }
}
