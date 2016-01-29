package com.android.yzd.memo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.yzd.memo.R;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;
import com.android.yzd.memo.mvp.ui.fragment.SettingFragment;


import butterknife.Bind;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.common_toolbar) Toolbar mToolBar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragment();
    }

    private void setFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SettingFragment())
                .commit();
    }

    @Override protected void initToolBar(Toolbar toolbar) {
        super.initToolBar(toolbar);
        setTitle("设置");
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override protected int getContentView() {
        return R.layout.activity_setting;
    }

    @Override protected void initToolbar() {
        super.initToolBar(mToolBar);
    }

    @Override protected boolean isApplyTranslucency() {
        return true;
    }

    @Override protected boolean isApplyButterKnife() {
        return true;
    }

    @Override
    protected boolean isApplyEventBus() {
        return false;
    }

}
