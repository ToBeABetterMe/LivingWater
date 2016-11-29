/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.livingwater.comment.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


import com.livingwater.comment.R;

import co.lujun.androidtagview.TagContainerLayout;
import me.gujun.android.taggroup.TagGroup;


public class CommentActivity extends Activity {

    private TagGroup.OnTagClickListener mTagClickListener = new TagGroup.OnTagClickListener() {
        @Override
        public void onTagClick(String tag) {
            Toast.makeText(CommentActivity.this, tag, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comment);
        TagContainerLayout mTagContainerLayout = (TagContainerLayout) findViewById(R.id.tagcontainerLayout);
        mTagContainerLayout.setTags(new String[]{"引人入胜", "富有创新", "治学严谨"});


    }

}
