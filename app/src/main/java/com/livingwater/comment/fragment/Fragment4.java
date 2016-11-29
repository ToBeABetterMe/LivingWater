package com.livingwater.comment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.livingwater.comment.R;

/**
 * Created by yangsp on 2016/11/14.
 */
public class Fragment4 extends BaseListFragment {
    public ScrollView scrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.layout_scrollview,null);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        return view;
    }

    @Override
    View getSlidableView() {
        return scrollView;
    }
}
