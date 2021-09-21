package com.stac2021.mwproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PeriodSetting extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar tb;
    Button btnEdit, btnComplete;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    EditText editText1, editText2;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_period);
        tb = findViewById(R.id.toolbar) ;
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("월경주기 설정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sp = getApplicationContext().getSharedPreferences("pref", 0);
        spe = sp.edit();

        btnEdit = findViewById(R.id.btnEdit);
        btnComplete = findViewById(R.id.btnComplete);
        editText1 = findViewById(R.id.setting_period1);
        editText2 = findViewById(R.id.setting_period2);

        editText1.setText(sp.getString("period", ""));
        editText2.setText(sp.getString("term", ""));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEdit.setVisibility(View.GONE);
                btnComplete.setVisibility(View.VISIBLE);
                editText1.setFocusableInTouchMode(true);
                editText2.setFocusableInTouchMode(true);
            }
        });

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEdit.setVisibility(View.VISIBLE);
                btnComplete.setVisibility(View.GONE);
                spe.putString("period", editText1.getText().toString());
                spe.putString("term", editText2.getText().toString());
                spe.commit();
                editText1.setFocusableInTouchMode(false);
                editText2.setFocusableInTouchMode(false);
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
