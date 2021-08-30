package com.stac2021.mwproject.mainTabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.stac2021.mwproject.mainTabLayout.Frag1;
import com.stac2021.mwproject.mainTabLayout.Frag2;
import com.stac2021.mwproject.mainTabLayout.Frag3;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    public FragmentAdapter(FragmentManager fm) { // 생성자 오버로딩
        super(fm);
        fragmentList.add(new Frag1());
        fragmentList.add(new Frag2());
        fragmentList.add(new Frag3());
    }
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
    public int getCount() {
        return fragmentList.size();
    }
}