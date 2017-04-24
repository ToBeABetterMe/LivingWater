/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.livingwater.comment.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;


import com.livingwater.comment.R;
import com.livingwater.comment.ui.sectionbar.SectionProgressBar;

import co.lujun.androidtagview.TagContainerLayout;
import me.gujun.android.taggroup.TagGroup;


public class CommentActivity extends Activity {

    private TagGroup.OnTagClickListener mTagClickListener = new TagGroup.OnTagClickListener() {
        @Override
        public void onTagClick(String tag) {
            Toast.makeText(CommentActivity.this, tag, Toast.LENGTH_SHORT).show();
        }
    };
    private SectionProgressBar mSectionBar1;
    private SectionProgressBar mSectionBar2;
    private SectionProgressBar mSectionBar3;
    private String[] mLevels = {"1", "2", "3", "4", "5"};
    private int[] mLevelValues = {0, 1000, 2000, 4000, 8000};

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_comment_new);
        //TagContainerLayout mTagContainerLayout = (TagContainerLayout) findViewById(R.id.tagcontainerLayout);
        //mTagContainerLayout.setTags(new String[]{"引人入胜", "富有创新", "治学严谨"});
        mSectionBar1 = (SectionProgressBar) findViewById(R.id.section_1);
        mSectionBar2 = (SectionProgressBar) findViewById(R.id.section_2);
        mSectionBar3 = (SectionProgressBar) findViewById(R.id.section_3);
        mSectionBar1.setLevels(mLevels);
        mSectionBar1.setLevelValues(mLevelValues);
        mSectionBar2.setLevels(mLevels);
        mSectionBar2.setLevelValues(mLevelValues);
        mSectionBar3.setLevels(mLevels);
        mSectionBar3.setLevelValues(mLevelValues);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSectionBar1.setCurrent(3000);
                mSectionBar2.setCurrent(3000);
            }
        }, 2000);

    }

}
