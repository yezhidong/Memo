package com.android.yzd.memo.presenter.impl;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.yzd.memo.presenter.FragmentPresenter;
import com.android.yzd.memo.ui.adapter.IndexViewAdapter;
import com.android.yzd.memo.view.LoginTypeFView;

/**
 * Created by Administrator on 2016/1/15.
 */
public class LoginTypeFImpl implements FragmentPresenter {

    private final Context mContext;
    private final LoginTypeFView mLoginTypeFView;

    public LoginTypeFImpl(Context context, LoginTypeFView view) {
        mContext = context;
        mLoginTypeFView = view;
    }
    @Override
    public void onFirstUserVisible() {
        RecyclerView.Adapter mAdapter = new IndexViewAdapter(mContext);
        mLoginTypeFView.initRecycler(new LinearLayoutManager(mContext), mAdapter);
    }

    @Override
    public void onUserVisible() {

    }

    @Override
    public void onUserInvisible() {

    }
}
