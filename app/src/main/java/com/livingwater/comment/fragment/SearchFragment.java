
package com.livingwater.comment.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.livingwater.comment.R;
import com.livingwater.comment.dialog.BaseProgressDialog;

import com.livingwater.comment.ui.UIHelper;
import com.livingwater.comment.utils.myViewUtil;
import com.livingwater.comment.ui.search.SearchBox;
import com.livingwater.comment.ui.search.SearchBox.OnSearchBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFragment extends BaseFragment implements OnSearchBox {


    private Button btnBack;
    private TextView textHeadTitle;

    private SearchBox mSearchBox;
    private ListView mContactsLv;

    private TextView mSearchResultPromptTv;

    private BaseProgressDialog mBaseProgressDialog;

    @Override
    public void onResume() {
        refreshView();
        super.onResume();
    }

    @Override
    protected void initData() {
        setContext(getActivity());
//        ContactsHelper.getInstance().setOnContactsLoad(this);
//        boolean startLoad = ContactsHelper.getInstance().startLoadContacts();
//        if (true == startLoad) {
//
//            getBaseProgressDialog().show(getContext().getString(R.string.loading_contacts));
//        }
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mSearchBox = (SearchBox) view.findViewById(R.id.search_box);
        mSearchBox.setOnSearchBox(this);
        mContactsLv = (ListView) view.findViewById(R.id.contacts_list_view);
        btnBack = (Button) view.findViewById(R.id.btnBack);
        textHeadTitle = (TextView) view.findViewById(R.id.textHeadTitle);

        textHeadTitle.setText("搜索");
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

//        mContactsAdapter = new ContactsAdapter(getContext(),
//                R.layout.contacts_list_item, ContactsHelper.getInstance()
//                        .getSearchContacts());
//        mContactsLv.setAdapter(mContactsAdapter);
        SimpleAdapter adapter = new SimpleAdapter(getContext(),getdata(),R.layout.contacts_list_item,
                new String[]{"name","num"},
                new int[]{R.id.teacher_name,R.id.course_name});
        mContactsLv.setAdapter(adapter);

        mSearchResultPromptTv = (TextView) view.findViewById(R.id.search_result_prompt_text_view);

        myViewUtil.showView(mContactsLv);
        myViewUtil.hideView(mSearchResultPromptTv);
        return view;
    }
    private List<Map<String, Object>> getdata(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> map;
        for (int i=0; i<30; i++){
            map = new HashMap<String,Object>();
            map.put("name","蒋朝根");
            map.put("num","单片机原理"+(i+1));
            list.add(map);
        }
        return list;
    }

    @Override
    protected void initListener() {

        mContactsLv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
//                Contacts contacts = ContactsHelper.getInstance().getSearchContacts().get(position);
//                String uri = "tel:" + contacts.getPhoneNumber();
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
//                // intent.setData(Uri.parse(uri));
//                startActivity(intent);
                UIHelper.showTeacherDetailActivity(getActivity());
            }
        });

    }

    /* start: OnSearchBox */
    @Override
    public void onSearchTextChanged(String curCharacter) {
    //    search(curCharacter);
        refreshView();

    }

    /* end: OnSearchBox */

    public BaseProgressDialog getBaseProgressDialog() {
        if (null == mBaseProgressDialog) {
            mBaseProgressDialog = new BaseProgressDialog(getContext());
        }
        return mBaseProgressDialog;
    }

    public void setBaseProgressDialog(BaseProgressDialog baseProgressDialog) {
        mBaseProgressDialog = baseProgressDialog;
    }

    public void refreshView() {

        refreshContactsLv();
    }

    private void refreshContactsLv() {
        if (null == mContactsLv) {
            return;
        }

        BaseAdapter contactsAdapter = (BaseAdapter) mContactsLv.getAdapter();
        if (null != contactsAdapter) {
            contactsAdapter.notifyDataSetChanged();
            if (contactsAdapter.getCount() > 0) {
                myViewUtil.showView(mContactsLv);
                myViewUtil.hideView(mSearchResultPromptTv);

            } else {
                myViewUtil.hideView(mContactsLv);
                myViewUtil.showView(mSearchResultPromptTv);

            }
        }
    }

    private void search(String keyword) {
        String curCharacter;
        if (null == keyword) {
            curCharacter = keyword;
        } else {
            curCharacter = keyword.trim();
        }

//        if (TextUtils.isEmpty(keyword)) {
//            ContactsHelper.getInstance().qwertySearch(null);
//        } else {
//            ContactsHelper.getInstance().qwertySearch(curCharacter);
//        }
    }
}
