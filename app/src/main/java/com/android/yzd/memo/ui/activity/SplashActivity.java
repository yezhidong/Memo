package com.android.yzd.memo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.android.yzd.memo.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        new Handler().postDelayed( () ->  pullActivity() , 0);
    }

    private void pullActivity() {
        Intent intent = new Intent(this, IndexActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected boolean isApplyTranslucency() {
        return false;
    }

    @Override
    protected boolean isApplyButterKnife() {
        return false;
    }
}
