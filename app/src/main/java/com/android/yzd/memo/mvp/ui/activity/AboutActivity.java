package com.android.yzd.memo.mvp.ui.activity;

import android.support.v7.widget.Toolbar;

import com.android.yzd.memo.R;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;
import com.android.yzd.memo.mvp.ui.activity.base.Base;
import com.android.yzd.memo.mvp.ui.activity.base.BaseSwipeBackActivity;

import butterknife.Bind;

public class AboutActivity extends BaseSwipeBackActivity {

    @Bind(R.id.common_toolbar) Toolbar mToolBar;
    @Override protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_about;
    }

    @Override
    protected void initToolbar() {
        initToolBar(mToolBar);
        setTitle("关于");
    }

    @Override
    protected boolean isApplyTranslucency() {
        return true;
    }

    @Override
    protected boolean isApplyButterKnife() {
        return true;
    }

    @Override protected boolean isApplyEventBus() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }
}
