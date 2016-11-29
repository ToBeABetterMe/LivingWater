package com.livingwater.comment.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livingwater.comment.R;
import com.livingwater.comment.adapter.RecyclerAdapter;
import com.livingwater.comment.model.ModelBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by tiansj on 15/9/4.
 */
public class CardListFragment extends Fragment {
    private Activity context;
    private String content;
    private View view;
    private RecyclerView recyclerView;

    private List<ModelBean> beanList;
    private RecyclerAdapter adapter;

    private String des[] = {"在交大的四年，我收获了理想", "在交大的四年，我收获了理想", "在交大的四年，我收获了理想", "在交大的四年，我收获了理想", "在交大的四年，我收获了理想"
            , "在交大的四年，我收获了理想", "在交大的四年，我收获了理想", "在交大的四年，我收获了理想", "在交大的四年，我收获了理想", "在交大的四年，我收获了理想", "在交大的四年，我收获了理想"};

    private int resId[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item, container, false);
        ButterKnife.bind(this, view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = (Activity) getActivity();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initData();
    }
    private void initData() {
        beanList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ModelBean bean = new ModelBean();
            bean.setResId(resId[i]);
            bean.setTitle(des[i]);
            beanList.add(bean);
        }
        adapter = new RecyclerAdapter(context, beanList);
        recyclerView.setAdapter(adapter);
        // adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
        //     @Override
        //     public void onItemClick(int position, Object object) {
        //        startActivity(new Intent(getActivity(), TwoActivity.class));
        //    }
        // });
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
