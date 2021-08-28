package com.stac2021.mwproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment fragmentHome = new HomeFragment();
    private CalenderFragment fragmentCalender = new CalenderFragment();
    private BookmarkFragment fragmentBookmark = new BookmarkFragment();
    private CallFragment fragmentCall = new CallFragment();
    private MypageFragment fragmentMypage = new MypageFragment();

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new HomeFragment()).commit();
        bottomNavigationView = findViewById(R.id.navigationView);
        // FrameLayout에 fragment.xml 띄우기
        // 바텀 네비게이션뷰 안의 아이템 설정
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //item을 클릭시 id값을 가져와 FrameLayout에 fragment.xml띄우기
                    case R.id.navigation_home: getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
                        break;
                    case R.id.navigation_calender: getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CalenderFragment()).commit();
                        break;
                    case R.id.navigation_call: getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CallFragment()).commit();
                        break;
                    case R.id.navigation_bookmark: getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new BookmarkFragment()).commit();
                        break;
                    case R.id.navigation_mypage: getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MypageFragment()).commit();
                        break;
                }
                return true;
            }
        });
    }

}
