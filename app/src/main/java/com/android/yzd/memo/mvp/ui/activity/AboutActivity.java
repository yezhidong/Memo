package com.android.yzd.memo.mvp.ui.activity;

import com.android.yzd.memo.R;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;

public class AboutActivity extends BaseActivity {


    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_about;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected boolean isApplyTranslucency() {
        return true;
    }

    @Override
    protected boolean isApplyButterKnife() {
        return false;
    }

    @Override
    protected boolean isApplyEventBus() {
        return false;
    }
}
