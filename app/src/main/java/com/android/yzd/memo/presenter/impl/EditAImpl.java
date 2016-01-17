package com.android.yzd.memo.presenter.impl;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.yzd.memo.presenter.ActivityPresenter;
import com.android.yzd.memo.ui.adapter.EditAdapter;
import com.android.yzd.memo.view.EditAView;

/**
 * Created by Clearlove on 16/1/17.
 */
public class EditAImpl implements ActivityPresenter {

    private final Context mContext;
    private final EditAView mEditAView;

    public EditAImpl(Context context, EditAView view) {
        mContext = context;
        mEditAView = view;

    }
    @Override
    public void onCreate() {
        EditAdapter mEditAdapter = new EditAdapter(mContext);
        mEditAView.initRecyclerView(new LinearLayoutManager(mContext), mEditAdapter);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
