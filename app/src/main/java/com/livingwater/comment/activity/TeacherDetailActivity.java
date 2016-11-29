package com.livingwater.comment.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.livingwater.comment.R;
import com.livingwater.comment.fragment.SAdapter;
import com.livingwater.comment.ui.doubleScroll.DoubleScrollView;
import com.livingwater.comment.ui.doubleScroll.DoubleScrollViewPager;
import com.livingwater.comment.ui.swipebacklayout.SwipeBackActivity;
import com.livingwater.comment.ui.tabstrip.PagerSlidingTabStrip;


public class TeacherDetailActivity extends SwipeBackActivity {

    private PagerSlidingTabStrip tabLayout;
    private DoubleScrollView scrollView;
    private DoubleScrollViewPager viewPager;
    private SAdapter adapter;
    private View layoutTabTitle;
    private Button btnBack;
    private TextView textHeadTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);


        btnBack = (Button) findViewById(R.id.btnBack);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("教师档案");
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        scrollView = (DoubleScrollView) findViewById(R.id.layoutContent);
        scrollView.setupTitleView(findViewById(R.id.layoutTop));
        scrollView.setContentView(findViewById(R.id.layoutContentBottom));
        tabLayout = (PagerSlidingTabStrip) findViewById(R.id.tabLayout);

        viewPager = (DoubleScrollViewPager) findViewById(R.id.viewPager);
        layoutTabTitle = findViewById(R.id.layoutTabTitle);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                scrollView.setContentInnerScrollableView(adapter.getSlidableView(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adapter = new SAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setViewPager(viewPager);

        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.setContentInnerScrollableView(adapter.getSlidableView(0));
                viewPager.setTagHeight(scrollView.getMeasuredHeight() - layoutTabTitle.getMeasuredHeight());
            }
        },100);

    }
}
