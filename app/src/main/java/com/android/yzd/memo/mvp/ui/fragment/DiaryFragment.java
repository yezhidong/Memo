package com.android.yzd.memo.mvp.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.android.yzd.memo.R;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;
import com.android.yzd.memo.mvp.ui.view.DiaryFragmentView;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends BaseFragment implements DiaryFragmentView {


    @Bind(R.id.exception) LinearLayout exception;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    public DiaryFragment() {
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_diary;
    }

    @Override
    protected boolean isApplyButterKnife() {
        return true;
    }

    @Override
    protected boolean isApplyEventBus() {
        return false;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    public void initRecycler(LinearLayoutManager linearLayoutManager, RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void readGo(Class clazz, int type, int position, int positionType) {

    }

    @Override
    public void hideException() {
        exception.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showException() {
        exception.setVisibility(View.VISIBLE);
    }
}
