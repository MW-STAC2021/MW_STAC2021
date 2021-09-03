package com.stac2021.mwproject.mainTabLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.stac2021.mwproject.BannerAdapter;
import com.stac2021.mwproject.ExpandableHeightGridView;
import com.stac2021.mwproject.MainCardViewAdapter;
import com.stac2021.mwproject.R;

import java.util.Timer;
import java.util.TimerTask;

public class Frag1 extends Fragment {

    ViewFlipper viewFlip;
    ExpandableHeightGridView gridView;
    MainCardViewAdapter adapter;
    String[] itemTitle = {"생리대 사이즈 종류", "쓰레기 분리수거 하는법", "세탁기 돌리는 방법", "전구 갈아끼우는 방법", "생리 용퓸 종류들을 알려줄게!", "피임약 복용 방법을 알려줄게!",
    "월경 주기 계산 방법을 알려줄게!", "생리대 사용 방법을 알려줄게!", "생리컵 사용 방법을 알려줄게"};
    Integer[] itemImage = {R.drawable.thumbnail1, R.drawable.thumbnail01, R.drawable.thumbnail2, R.drawable.thumbnail02, R.drawable.thumbnail3, R.drawable.thumbnail03, R.drawable.thumbnail4, R.drawable.thumbnail04, R.drawable.thumbnail5};
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
        gridView = (ExpandableHeightGridView)(v.findViewById(R.id.gridView));
        adapter = new MainCardViewAdapter(itemImage, itemTitle);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);

        return v;
    }

}
