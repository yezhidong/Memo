package com.android.yzd.memo.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.android.yzd.memo.R;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;
import com.android.yzd.memo.mvp.presenter.impl.IndexFImpl;
import com.android.yzd.memo.mvp.ui.view.LoginTypeFView;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * @author yezhidong
 */
public class IndexFragment extends BaseFragment implements LoginTypeFView{

    private static final int INDEX_FRAGMENT_REQUEST_CODE = 2;
    private static final int EDIT_SAVE = 1;
    private static final int SUCCESS = 1;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.exception) LinearLayout mException;
    private IndexFImpl mIndexFImpl;

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIndexFImpl = new IndexFImpl(mActivity, this);
        mIndexFImpl.getArgus(getArguments());
    }


    @Override protected void onFirstUserVisible() {
        mIndexFImpl.onFirstUserVisible();
    }

    @Override protected void onUserVisible() {
        mIndexFImpl.onUserVisible();
    }

    @Override protected void onUserInvisible() {
        mIndexFImpl.onUserInvisible();
    }

    @Override protected int getContentViewLayoutID() {
        return R.layout.fragment_login_type;
    }

    @Override protected boolean isApplyButterKnife() {
        return true;
    }

    @Override protected boolean isApplyEventBus() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        mIndexFImpl.onEventComing(eventCenter);
    }

    @Override public void initRecycler(LinearLayoutManager linearLayoutManager, RecyclerView.Adapter adapter) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void readGo(Class clazz, int type, int position, int positionType) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtra("CREATE_MODE", type);
        intent.putExtra("position", position);
        intent.putExtra("positionType", positionType);
        startActivityForResult(intent, INDEX_FRAGMENT_REQUEST_CODE);
    }

    @Override
    public void hideException() {
        mException.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showException() {
        mException.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INDEX_FRAGMENT_REQUEST_CODE) {
            if (resultCode == EDIT_SAVE && resultCode == SUCCESS) {
                EventCenter eventCenter = new EventCenter(EDIT_SAVE, true);
                EventBus.getDefault().post(eventCenter);
            }
        }
    }
}
