package com.stac2021.mwproject.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stac2021.mwproject.InformationActivity;
import com.stac2021.mwproject.MypageCardViewAdapter;
import com.stac2021.mwproject.NoticeActivity;
import com.stac2021.mwproject.R;
import com.stac2021.mwproject.RecentActivity;

public class MypageFragment extends Fragment {

    androidx.appcompat.widget.Toolbar tb;
    RecyclerView recyclerView;
    MypageCardViewAdapter adapter;
    String[] itemTitle = {"생리대 사이즈 종류", "쓰레기 분리수거 하는법", "세탁기 돌리는 방법", "전구 갈아끼우는 방법", "생리 용퓸 종류들을 알려줄게!", "피임약 복용 방법을 알려줄게!",
            "월경 주기 계산 방법을 알려줄게!", "생리대 사용 방법을 알려줄게!", "생리컵 사용 방법을 알려줄게"};
    Integer[] itemImage = {R.drawable.thumbnail1, R.drawable.thumbnail01, R.drawable.thumbnail2, R.drawable.thumbnail02, R.drawable.thumbnail3, R.drawable.thumbnail03, R.drawable.thumbnail4, R.drawable.thumbnail04, R.drawable.thumbnail5};
    Button btnRecent;

    Button btn1, btn2, btn4, btn5;

    TextView toolbar_title;
    ImageView toolbar_icon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);
        tb = view.findViewById(R.id.toolbar) ;
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new MypageCardViewAdapter(itemImage, itemTitle);
        recyclerView.setAdapter(adapter);

        btnRecent = view.findViewById(R.id.btnRecent);

        btn1 = view.findViewById(R.id.mypage_btn1);
        btn2 = view.findViewById(R.id.mypage_btn2);

        btn4 = view.findViewById(R.id.mypage_btn4);
        btn5 = view.findViewById(R.id.mypage_btn5);

        toolbar_title = view.findViewById(R.id.toolbar_title);
        toolbar_icon = view.findViewById(R.id.toolbar_icon);

        toolbar_title.setText("마이페이지");
        toolbar_icon.setImageResource(R.drawable.icon_mypage);

        btnRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), RecentActivity.class);
                startActivity(in);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), NoticeActivity.class);
                startActivity(in);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), InformationActivity.class);
                startActivity(in);
            }
        });


        return view;
    }
}