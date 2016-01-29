package com.android.yzd.memo.mvp.ui.activity;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.WindowManager;

import com.android.yzd.memo.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by Clearlove on 16/1/15.
 */
public abstract class BaseSwipeBackActivity extends AppCompatActivity {

    protected ViewDataBinding mDataBinding;
    @Override protected void onCreate(Bundle savedInstanceState) {
        if (isApplyTranslucency()) initWindow();
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, getContentView());
        if (isApplyButterKnife()) ButterKnife.bind(this);
        initToolbar();
//        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }
    protected void initToolBar(Toolbar toolbar) {

        if (toolbar == null) return;

        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitle(getString(com.android.yzd.memo.R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     *  api大于19的时候，实现沉浸式状态栏
     */
    @TargetApi(19) protected void initWindow() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getStatusBarColor());
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    protected int getStatusBarColor() {
        return getColorPrimary();
    }

    private int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override protected void onDestroy() {
        if (isApplyButterKnife()) ButterKnife.unbind(this);
        super.onDestroy();
    }

    protected abstract int getContentView();

    protected abstract void initToolbar();

    protected abstract boolean isApplyTranslucency();

    protected abstract boolean isApplyButterKnife();
}
