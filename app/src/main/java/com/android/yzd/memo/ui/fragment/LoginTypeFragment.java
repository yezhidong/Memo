package com.android.yzd.memo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.yzd.memo.R;
import com.android.yzd.memo.otto.Success;
import com.android.yzd.memo.presenter.impl.LoginTypeFImpl;
import com.android.yzd.memo.view.LoginTypeFView;
import com.squareup.otto.Subscribe;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * @author yezhidong
 */
public class LoginTypeFragment extends BaseFragment implements LoginTypeFView{

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private LoginTypeFImpl mLoginTypeFImpl;

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoginTypeFImpl = new LoginTypeFImpl(mActivity, this);
    }


    @Override protected void onFirstUserVisible() {
        mLoginTypeFImpl.onFirstUserVisible();
    }

    @Override protected void onUserVisible() {
        mLoginTypeFImpl.onUserVisible();
    }

    @Override protected void onUserInvisible() {
        mLoginTypeFImpl.onUserInvisible();
    }

    @Override protected int getContentViewLayoutID() {
        return R.layout.fragment_login_type;
    }

    @Override protected boolean isApplyButterKnife() {
        return true;
    }

    @Override public void initRecycler(LinearLayoutManager linearLayoutManager, RecyclerView.Adapter adapter) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Subscribe public void isSuccess(Success success) {
        if (success.isSuccess()) {

        }
    }

}
