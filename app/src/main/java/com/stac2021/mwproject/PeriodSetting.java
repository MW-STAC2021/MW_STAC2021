package com.stac2021.mwproject;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PeriodSetting extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar tb;
    Button btnEdit, btnComplete;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_period);
        tb = findViewById(R.id.toolbar) ;
        setSupportActionBar(tb);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle("월경주기 설정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnEdit = findViewById(R.id.btnEdit);
        btnComplete = findViewById(R.id.btnComplete);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEdit.setVisibility(View.GONE);
                btnComplete.setVisibility(View.VISIBLE);
            }
        });

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEdit.setVisibility(View.VISIBLE);
                btnComplete.setVisibility(View.GONE);
            }
        });
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
