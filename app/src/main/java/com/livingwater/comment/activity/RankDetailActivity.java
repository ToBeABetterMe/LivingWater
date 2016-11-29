package com.livingwater.comment.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Request;
import com.squareup.picasso.Picasso;
import com.livingwater.comment.R;
import com.livingwater.comment.http.HttpClient;
import com.livingwater.comment.http.HttpResponseHandler;
import com.livingwater.comment.model.SearchParam;
import com.livingwater.comment.model.SearchShop;
import com.livingwater.comment.ui.UIHelper;
import com.livingwater.comment.ui.pulltorefresh.PullToRefreshBase;
import com.livingwater.comment.ui.pulltorefresh.PullToRefreshListView;
import com.livingwater.comment.ui.quickadapter.BaseAdapterHelper;
import com.livingwater.comment.ui.quickadapter.QuickAdapter;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tiansj on 15/6/29.
 */
public class RankDetailActivity extends Activity {

    @Bind(R.id.btnBack)
    Button btnBack;
    @Bind(R.id.textHeadTitle)
    TextView textHeadTitle;

    private Activity context;

    private SearchParam param;
    private int pno = 1;
    private boolean isLoadAll;

    @Bind(R.id.listView)
    PullToRefreshListView listView;
    QuickAdapter<SearchShop> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_detail);
        ButterKnife.bind(this);
        context = RankDetailActivity.this;
        initData();
        initView();
        loadData();
    }

    void initView() {
        textHeadTitle.setText("综合榜");
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //UIHelper.showHouseDetailActivity(context);
                finish();
            }
        });

        adapter = new QuickAdapter<SearchShop>(context, R.layout.ranking_list_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, SearchShop shop) {
                helper.setText(R.id.name, shop.getName());
                       // .setText(R.id.commenter, shop.getAddr());
            }
        };

        listView.withLoadMoreView();
        listView.setAdapter(adapter);
        // 下拉刷新
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.i("Click","Refresh");
                initData();
                loadData();
                UIHelper.showHouseDetailActivity(context);
            }
        });
        // 加载更多
        listView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Log.i("Click","loadMore");
                loadData();
            }
        });
        // 点击事件

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Log.i("Click","keydown");
                UIHelper.showHouseDetailActivity(context);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    Picasso.with(context).pauseTag(context);
                } else {
                    Picasso.with(context).resumeTag(context);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

    }
    private void initData() {
        param = new SearchParam();
        pno = 1;
        isLoadAll = false;
    }

    private void loadData() {
        if (isLoadAll) {
            return;
        }
        param.setPno(pno);
        listView.setLoadMoreViewTextLoading();
        HttpClient.getRecommendShops(param, new HttpResponseHandler() {

            @Override
            public void onSuccess(String body) {
                listView.onRefreshComplete();
                JSONObject object = JSON.parseObject(body);
                List<SearchShop> list = JSONArray.parseArray(object.getString("body"), SearchShop.class);
                listView.updateLoadMoreViewText(list);
                isLoadAll = list.size() < HttpClient.PAGE_SIZE;
                if(pno == 1) {
                    adapter.clear();
                }
                adapter.addAll(list);
                pno++;
            }

            @Override
            public void onFailure(Request request, IOException e) {
                listView.onRefreshComplete();
                listView.setLoadMoreViewTextError();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //      pager.startAutoScroll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //    pager.stopAutoScroll();
    }
}
