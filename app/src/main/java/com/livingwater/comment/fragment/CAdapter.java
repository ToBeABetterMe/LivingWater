package com.livingwater.comment.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangsp on 2016/11/14.
 */
public class CAdapter extends FragmentStatePagerAdapter {
    private List<BaseListFragment> list;
    private static final String[] titles = new String[]{"基本资料"};

    public CAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        list.add(new Fragment2());
    }

    public View getSlidableView (int index) {
        return list.get(index).getSlidableView();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
