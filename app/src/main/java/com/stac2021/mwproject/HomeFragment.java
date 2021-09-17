package com.stac2021.mwproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.stac2021.mwproject.mainTabLayout.allInfo;
import com.stac2021.mwproject.mainTabLayout.femaleInfo;
import com.stac2021.mwproject.mainTabLayout.livingInfo;
import com.stac2021.mwproject.mainTabLayout.FragmentAdapter;
import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

public class HomeFragment extends Fragment {

    int i = 0;
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentAdapter adapter;
    TextView textSearch;
    private allInfo frag1;
    private femaleInfo frag2;
    private livingInfo frag3;
    private BannerAdapter bannerAdapter;

    public HomeFragment() {
    }

    private ServiceApi service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        service = RetrofitClient.getClient().create(ServiceApi.class);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textSearch = view.findViewById(R.id.textSearch);

        textSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), SearchActivity.class);
                startActivity(in);
            }
        });

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        adapter = new FragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter); //뷰페이지와 어댑터 연결
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("통합 정보"); //탭이름생성
        tabLayout.getTabAt(1).setText("여성 정보");
        tabLayout.getTabAt(2).setText("생활 정보");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }


}