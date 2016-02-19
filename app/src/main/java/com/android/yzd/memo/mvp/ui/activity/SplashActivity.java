package com.android.yzd.memo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.android.yzd.memo.R;
import com.android.yzd.memo.mvp.model.Constants;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;
import com.android.yzd.memo.mvp.ui.activity.base.BaseActivity;
import com.android.yzd.memo.utils.SPUtils;

public class SplashActivity extends BaseActivity {

    private static final String CREATE_LOCK_SUCCESS = "CREATE_LOCK_SUCCESS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        new Handler().postDelayed( () ->  pullActivity() , 0);
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    private void pullActivity() {
        Boolean isSuccess = (Boolean) SPUtils.get(this, CREATE_LOCK_SUCCESS, false);
        Intent intent = null;
        if (!isSuccess) {
            intent = new Intent(this, CreateLockActivity.class);
            intent.putExtra("CREATE_MODE", Constants.CREATE_GESTURE);
        } else {
            boolean isOpen = (boolean) SPUtils.get(this, Constants.SETTING.OPEN_GESTURE, true);
            if (isOpen) {
                intent = new Intent(this, CheckLockActivity.class);
            } else {
                intent = new Intent(this, IndexActivity.class);
            }
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

    @Override
    protected boolean isApplyEventBus() {
        return false;
    }
}
