package com.stac2021.mwproject;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

public class Frag1 extends Fragment {
    ViewFlipper viewFlip;
    private ViewPager bannerPager;
    private BannerAdapter bannerAdapter;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frag1, container, false);
        // infalte : xml 코드를 java에서 보여주는 메소드
        viewFlip = v.findViewById(R.id.viewFlip);
        viewFlip.setFlipInterval(2500);
        viewFlip.startFlipping();
        /*
        bannerPager = v.findViewById(R.id.banner);
        bannerAdapter = new BannerAdapter(v.getContext());
        bannerPager.setAdapter(bannerAdapter);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if(currentPage == 3) {
                    currentPage = 0;
                }
                bannerPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
         */
        return v;
    }

}
