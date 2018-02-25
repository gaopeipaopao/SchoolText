package com.example.school.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 泡泡 on 2018/2/25.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String[]  titles=new String[]{"课表","成绩","课程","个人"};

    private List<Fragment> mFragment=new ArrayList<>();


    public ViewPagerAdapter(List<Fragment> mfragment, FragmentManager fm) {
        super(fm);
        this.mFragment=mfragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
