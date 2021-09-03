package com.stac2021.mwproject.mainTabLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.stac2021.mwproject.ExpandableHeightGridView;
import com.stac2021.mwproject.MainCardViewAdapter;
import com.stac2021.mwproject.R;

public class Frag3 extends Fragment {
    ExpandableHeightGridView gridView;
    MainCardViewAdapter adapter;
    String[] itemTitle = {"생리대 사이즈 종류", "쓰레기 분리수거 하는법", "세탁기 돌리는 방법", "전구 갈아끼우는 방법", "생리 용퓸 종류들을 알려줄게!", "피임약 복용 방법을 알려줄게!",
            "월경 주기 계산 방법을 알려줄게!", "생리대 사용 방법을 알려줄게!", "생리컵 사용 방법을 알려줄게"};
    Integer[] itemImage = {R.drawable.thumbnail1, R.drawable.thumbnail01, R.drawable.thumbnail2, R.drawable.thumbnail02,
            R.drawable.thumbnail3, R.drawable.thumbnail03, R.drawable.thumbnail4, R.drawable.thumbnail04, R.drawable.thumbnail5};
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frag3, container, false);
        // infalte : xml 코드를 java에서 보여주는 메소드
        gridView = (ExpandableHeightGridView)(v.findViewById(R.id.gridView));
        adapter = new MainCardViewAdapter(itemImage, itemTitle);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);
        return v;
    }
}
