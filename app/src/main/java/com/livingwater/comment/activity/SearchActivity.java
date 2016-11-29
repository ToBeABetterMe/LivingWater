package com.livingwater.comment.activity;

import android.support.v4.app.Fragment;

import com.livingwater.comment.fragment.SearchFragment;

public class SearchActivity extends BaseSingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        return new SearchFragment();
    }

    @Override
    protected boolean isRealTimeLoadFragment() {
        // TODO Auto-generated method stub
        return false;
    }

}

