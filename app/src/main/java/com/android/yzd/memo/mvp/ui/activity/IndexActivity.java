package com.android.yzd.memo.mvp.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.yzd.memo.R;
import com.android.yzd.memo.databinding.ActivityIndexBinding;
import com.android.yzd.memo.mvp.model.Constants;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;
import com.android.yzd.memo.mvp.presenter.impl.IndexPreImpl;
import com.android.yzd.memo.mvp.ui.activity.base.BaseActivity;
import com.android.yzd.memo.mvp.ui.adapter.IndexContentAdapter;
import com.android.yzd.memo.mvp.ui.view.IndexAView;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;

public class IndexActivity extends BaseActivity implements IndexAView{

    private static final int INDEX_REQUEST_CODE = 1;
    private static final int SETTING_REQUEST_CODE = 2;
    private static final int EDIT_SAVE = 1;
    private int SUCCESS = 1;
    @Bind(R.id.common_toolbar) Toolbar mToolBar;
    private IndexPreImpl mIndexPre;
    private ActivityIndexBinding mDataBinding;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private int INDEX_EVENT_SUCCESS = 1;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = (ActivityIndexBinding) super.mDataBinding;
        mIndexPre = new IndexPreImpl(this, this, mDataBinding);
        mIndexPre.onCreate(savedInstanceState);
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override protected int getContentView() {
        return R.layout.activity_index;
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
        return true;
    }

    @Override public void initDrawerToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDataBinding.drawerLayout, mDataBinding.commonToolbar, 0, 0){
            @Override public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDataBinding.drawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }

    @Override public void initXViewPager() {
        mDataBinding.content.setOffscreenPageLimit(3);
        IndexContentAdapter indexContentAdapter = new IndexContentAdapter(getSupportFragmentManager());
        mDataBinding.content.setAdapter(indexContentAdapter);
    }

    @Override public void readyGoForResult(Class clazz) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("CREATE_MODE", Constants.CREATE_MODE);
        startActivityForResult(intent, INDEX_REQUEST_CODE);
    }

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.syncState();
        }
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.setting:
                go2Setting();
                return true;
            case R.id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void go2Setting() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivityForResult(intent, SETTING_REQUEST_CODE);
    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar.make(mToolBar, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void kill() {
        finish();
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        if (eventCenter.getEventCode() == Constants.EVEN_BUS.CHANGE_THEME) {
            reload(false);
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INDEX_REQUEST_CODE) {
            if (resultCode == EDIT_SAVE && resultCode == SUCCESS) {
                EventCenter eventCenter = new EventCenter(INDEX_EVENT_SUCCESS, true);
                EventBus.getDefault().post(eventCenter);
            }
        } else if (requestCode == SETTING_REQUEST_CODE) {

        }
    }

    @Override
    public void onBackPressed() {
        if (mIndexPre.onBackPress()) {
            super.onBackPressed();
        }
    }
}
