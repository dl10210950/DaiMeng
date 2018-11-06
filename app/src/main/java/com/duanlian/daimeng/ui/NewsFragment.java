package com.duanlian.daimeng.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.adapter.CommonFragPagerAdapter;
import com.duanlian.daimeng.base.BaseFragment;
import com.duanlian.daimeng.database.DataBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 段炼 on 2017/9/13.
 * Description :
 */

public class NewsFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.add_channel_iv)
    ImageView addChannelIv;
    private CommonFragPagerAdapter mPagerAdapter;
    private List<String> titleList;
    private List<BaseFragment> framList;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        framList = new ArrayList<>();
        titleList = new ArrayList<>();
        titleList.clear();
        titleList.add("头条");
        titleList.add("国内");
        titleList.add("国际");
        titleList.addAll(getData());
        for (int i = 0; i < titleList.size(); i++) {
            NewsListFragment listFragments = createListFragments(titleList.get(i));
            framList.add(listFragments);
        }
        if (titleList.size() <= 3) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        tabLayout.setupWithViewPager(viewPager);
        mPagerAdapter = new CommonFragPagerAdapter(getChildFragmentManager());
        mPagerAdapter.addTitles(titleList);
        mPagerAdapter.addPagers(framList);
        viewPager.setAdapter(mPagerAdapter);

    }
    private NewsListFragment createListFragments(String channelName) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("channelName", channelName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 通过查找数据库,拿到里面的数据
     */
    private List<String> getData() {
        List<String> list = new ArrayList<>();
        Cursor query = DataBase.getInstances(getBaseActivity()).query();
        if (query.moveToFirst()) {
            do {
                String channel = query.getString(query.getColumnIndex("channel"));
                list.add(channel);
            } while (query.moveToNext());
        }
        //关闭查询游标
        query.close();
        return list;
    }

    @OnClick(R.id.add_channel_iv)
    public void onViewClicked() {
//        Intent intent = new Intent(getActivity(), AddChannelActivity.class);
//        startActivity(intent);
    }
}
