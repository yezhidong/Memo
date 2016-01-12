package com.android.yzd.memo.ui.activity;

import android.os.Bundle;
import android.view.Window;

import com.android.yzd.memo.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initToolbar() {

    }
}
