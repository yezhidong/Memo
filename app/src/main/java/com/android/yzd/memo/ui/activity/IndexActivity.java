package com.android.yzd.memo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.yzd.memo.R;

import butterknife.Bind;

public class IndexActivity extends BaseActivity {

    @Bind(R.id.common_toolbar) Toolbar mToolbar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override protected int getContentView() {
        return R.layout.activity_index;
    }

    @Override protected void initToolbar() {
        super.initToolBar(mToolbar);
    }

    @Override protected boolean isApplyTranslucency() {
        return true;
    }

    @Override protected boolean isApplyButterKnife() {
        return true;
    }
}
