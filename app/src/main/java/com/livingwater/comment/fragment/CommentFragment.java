package com.livingwater.comment.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

import com.squareup.picasso.Picasso;
import com.livingwater.comment.R;
import com.livingwater.comment.model.SearchParam;
import com.livingwater.comment.model.SearchShop;
import com.livingwater.comment.ui.quickadapter.QuickAdapter;

import butterknife.ButterKnife;


public class CommentFragment extends Fragment {

    private Activity context;

    private SearchParam param;
    private int pno = 1;
    private boolean isLoadAll;
    private TagContainerLayout mTagContainerLayout;
    QuickAdapter<SearchShop> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        ButterKnife.bind(this, view);
        mTagContainerLayout = (TagContainerLayout) view.findViewById(R.id.tagcontainerLayout);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
//        initData();
//          initView();
//        loadData();
        initTag();

    }

    private void initTag(){
        mTagContainerLayout.setTags(new String[]{"引人入胜", "富有创新", "治学严谨"});
        mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {

            @Override
            public void onTagClick(int position, String text) {
                // ...
            }

            @Override
            public void onTagLongClick(final int position, String text) {
                // ...
            }

            @Override
            public void onTagCrossClick(int position) {
                // ...
            }
        });
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