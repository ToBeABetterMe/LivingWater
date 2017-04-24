package com.livingwater.comment.fragment;

/**
 * Created by Guoquan on 2016/11/24.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livingwater.comment.ui.UIHelper;
import com.livingwater.comment.ui.pulltozoomview.PullToZoomScrollViewEx;
import com.squareup.picasso.Picasso;
import com.livingwater.comment.R;
import com.livingwater.comment.activity.ImageGalleryActivity;
import com.livingwater.comment.ui.loopviewpager.AutoLoopViewPager;
import com.livingwater.comment.ui.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Firstfragment2 extends Fragment {
    private Activity context;

    @Bind(R.id.btnBack)
    Button btnBack;
    @Bind(R.id.textHeadTitle)
    TextView textHeadTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        initView();
        //initEvent();
    }
    void initView() {
        textHeadTitle.setText("活水评课");
        //btnBack.setVisibility(View.VISIBLE);

    }

    @Override
    public void onResume() {
        super.onResume();
        Picasso.with(context).resumeTag(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        Picasso.with(context).pauseTag(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Picasso.with(context).cancelTag(context);
    }

}