package com.android.yzd.memo.presenter.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.yzd.memo.Constans;
import com.android.yzd.memo.bean.God;
import com.android.yzd.memo.model.Realm.RealmHelper;
import com.android.yzd.memo.model.evenbus.EventCenter;
import com.android.yzd.memo.presenter.FragmentPresenter;
import com.android.yzd.memo.ui.activity.EditActivity;
import com.android.yzd.memo.ui.adapter.IndexViewAdapter;
import com.android.yzd.memo.view.LoginTypeFView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/15.
 */
public class LoginTypeFImpl implements FragmentPresenter, IndexViewAdapter.OnRecyclerItemClickListener {

    private final Context mContext;
    private final LoginTypeFView mLoginTypeFView;
    private IndexViewAdapter mAdapter;
    private ArrayList<God> selector;
    private int position;

    public LoginTypeFImpl(Context context, LoginTypeFView view) {
        mContext = context;
        mLoginTypeFView = view;
    }
    @Override
    public void onFirstUserVisible() {
        selector = selector();
        if (null != selector && selector.size() > 0) {
            mLoginTypeFView.hideException();
            mAdapter = new IndexViewAdapter(mContext, selector);
            mAdapter.setOnRecyclerItemClick(this);
            mLoginTypeFView.initRecycler(new LinearLayoutManager(mContext), mAdapter);
        } else {
            mLoginTypeFView.showException();
        }
    }

    private ArrayList<God> selector() {
        return RealmHelper.getInstances(mContext).selector(mContext, position);
    }

    @Override
    public void onUserVisible() {

    }

    @Override
    public void onUserInvisible() {

    }

    public void onEventComing(EventCenter eventCenter) {
        if (eventCenter.getEventCode() == 1) {
            boolean data = (boolean) eventCenter.getData();
            if (data) {
                selector = selector();
                if (null != selector && selector.size() > 0) {
                    mLoginTypeFView.hideException();
                    mAdapter.addAll(selector);
                    mAdapter.notifyDataSetChanged();
                } else {
                    mLoginTypeFView.showException();
                }
            }
        }
    }


    @Override
    public void onRecyclerItemClick(View view, int position) {
        mLoginTypeFView.readGo(EditActivity.class, Constans.VIEW_MODE, position);
    }

    public void getArgus(Bundle arguments) {
        position = arguments.getInt("position");
    }
}
