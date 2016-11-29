package com.livingwater.comment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livingwater.comment.R;

/**
 * Created by yangsp on 2016/11/14.
 */
public class Fragment1 extends BaseListFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_normal,null);
    }

    @Override
    View getSlidableView() {
        return null;
    }
}
