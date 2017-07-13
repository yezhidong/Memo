package com.android.yzd.mima.mvp.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.yzd.mima.R;
import com.android.yzd.mima.mvp.model.evenbus.EventCenter;

import butterknife.Bind;

/**
 * <p>Title:        MainFragment
 * <p>Description:
 * <p>@author:      yezd
 * <p>Copyright:    Copyright (c) 2010-2017
 * <p>Company:      @咪咕动漫
 * <p>Create Time:  2017/7/11 下午8:02
 * <p>@author:
 * <p>Update Time:
 * <p>Updater:
 * <p>Update Comments:
 */
public class MainFragment extends BaseFragment {

    @Bind(R.id.tab)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected void onFirstUserVisible() {
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.tab_chat)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.tab_game)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.tab_shop)));
        mTabLayout.setupWithViewPager(mViewPager);
//        mViewPager.setAdapter();
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main;
    }

    @Override
    protected boolean isApplyButterKnife() {
        return false;
    }

    @Override
    protected boolean isApplyEventBus() {
        return false;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }
}
