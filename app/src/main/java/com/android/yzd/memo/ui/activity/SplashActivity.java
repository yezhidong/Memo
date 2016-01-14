package com.android.yzd.memo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.android.yzd.memo.R;
import com.android.yzd.memo.utils.SPUtils;

public class SplashActivity extends BaseActivity {

    private static final String CREATE_LOCK_SUCCESS = "CREATE_LOCK_SUCCESS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        new Handler().postDelayed( () ->  pullActivity() , 1000);
    }

    private void pullActivity() {
        Boolean isSuccess = (Boolean) SPUtils.get(this, CREATE_LOCK_SUCCESS, false);
        Intent intent = null;
        if (!isSuccess) {
            intent = new Intent(this, CreateLockActivity.class);
        } else {
            intent = new Intent(this, CheckLockActivity.class);
        }
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
