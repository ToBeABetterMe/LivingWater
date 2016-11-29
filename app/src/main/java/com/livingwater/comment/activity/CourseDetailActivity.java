package com.livingwater.comment.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.livingwater.comment.R;
import com.livingwater.comment.fragment.CAdapter;
import com.livingwater.comment.ui.UIHelper;
import com.livingwater.comment.ui.doubleScroll.DoubleScrollView;
import com.livingwater.comment.ui.doubleScroll.DoubleScrollViewPager;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

public class CourseDetailActivity extends BaseFragmentActivity {
    private DoubleScrollView scrollView;
    private DoubleScrollViewPager viewPager;
    private CAdapter adapter;
    private Button btnBack;
    private TextView textHeadTitle;
    private ImageView teacherLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        btnBack = (Button) findViewById(R.id.btnBack);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("课程评价");
        btnBack.setVisibility(View.VISIBLE);
        teacherLogo = (ImageView) findViewById(R.id.teacher_logo);
        teacherLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showTeacherDetailActivity(CourseDetailActivity.this);
            }
        });
        BarChart mBarChart = (BarChart) findViewById(R.id.barchart);

        mBarChart.addBar(new BarModel("专业",5.0f, 0xFF123456));
        mBarChart.addBar(new BarModel("表达",3.0f, 0xFF343456));
        mBarChart.addBar(new BarModel("友好",4.0f, 0xFF563456));

        mBarChart.startAnimation();
        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("好评率", 65, Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("差评率", 35, Color.parseColor("#CDA67F")));

        mPieChart.startAnimation();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        scrollView = (DoubleScrollView) findViewById(R.id.layoutContent);
        scrollView.setupTitleView(findViewById(R.id.layoutTop));
        scrollView.setContentView(findViewById(R.id.layoutContentBottom));
        viewPager = (DoubleScrollViewPager) findViewById(R.id.viewPager);
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
        adapter = new CAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.setContentInnerScrollableView(adapter.getSlidableView(0));
                viewPager.setTagHeight(scrollView.getMeasuredHeight());
            }
        },100);

    }

}
