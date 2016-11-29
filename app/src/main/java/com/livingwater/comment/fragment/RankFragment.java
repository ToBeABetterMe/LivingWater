package com.livingwater.comment.fragment;

/**
 * Created by Guoquan on 2016/5/31.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.squareup.picasso.Picasso;
import com.livingwater.comment.R;
import com.livingwater.comment.model.SearchParam;
import com.livingwater.comment.model.SearchShop;
import com.livingwater.comment.ui.UIHelper;
import com.livingwater.comment.ui.quickadapter.QuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RankFragment extends Fragment {

    private Activity context;

    private SearchParam param;
    private int pno = 1;
    private boolean isLoadAll;

    @Bind(R.id.listView)
    ListView listView;
    QuickAdapter<SearchShop> adapter;

    //@Bind(R.id.textHeadTitle)
    //TextView textHeadTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_types_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        initData();
        initView();
//        loadData();
    }

    void initView() {
        //textHeadTitle.setText("排行榜");
//        adapter = new QuickAdapter<SearchShop>(context, R.layout.ranking_types_list_item) {
//            @Override
//            protected void convert(BaseAdapterHelper helper, SearchShop shop) {
//                helper.setText(R.id.name, shop.getName())
//                        .setImageUrl(R.id.logo, shop.getLogo()); // 自动异步加载图片
//            }
//        };
        SimpleAdapter adapter = new SimpleAdapter(getContext(),getdata(),R.layout.contacts_list_item,
                new String[]{"name","exp"},
                new int[]{R.id.teacher_name,R.id.course_name});
        listView.setAdapter(adapter);

        // 点击事件
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UIHelper.showRankActivity(context);
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
    private List<Map<String, Object>> getdata(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> map;
        for (int i=0; i<30; i++){
            map = new HashMap<String,Object>();
            map.put("name","综合榜"+(i+1));
            map.put("exp","最受欢迎的课程都在这里哦"+(i+1));
            list.add(map);
        }
        return list;
    }


    private void initData() {
        param = new SearchParam();
        pno = 1;
        isLoadAll = false;
    }

//    private void loadData() {
//        if (isLoadAll) {
//            return;
//        }
//        param.setPno(pno);
//        HttpClient.getRecommendShops(param, new HttpResponseHandler() {
//
//            @Override
//            public void onSuccess(String body) {
//                //listView.onRefreshComplete();
//                JSONObject object = JSON.parseObject(body);
//                List<SearchShop> list = JSONArray.parseArray(object.getString("body"), SearchShop.class);
//                isLoadAll = list.size() < HttpClient.PAGE_SIZE;
//                if(pno == 1) {
//                    adapter.clear();
//                }
//                adapter.addAll(list);
//                pno++;
//            }
//        });
//    }

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