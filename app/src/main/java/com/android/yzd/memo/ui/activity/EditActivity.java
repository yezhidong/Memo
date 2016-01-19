package com.android.yzd.memo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.yzd.memo.R;
import com.android.yzd.memo.presenter.impl.EditAImpl;
import com.android.yzd.memo.view.EditAView;

import butterknife.Bind;

public class EditActivity extends BaseActivity implements EditAView{

    @Bind(R.id.common_toolbar) Toolbar mToolBar;

    private EditAImpl mEditImpl;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditImpl = new EditAImpl(this);
        mEditImpl.onCreate();
    }

    @Override protected int getContentView() {
        return R.layout.activity_edit;
    }

    @Override protected void initToolbar() {
        initToolBar(mToolBar);
        mToolBar.setTitle("添加");
    }

    @Override protected boolean isApplyTranslucency() {
        return true;
    }

    @Override protected boolean isApplyButterKnife() {
        return true;
    }

}
