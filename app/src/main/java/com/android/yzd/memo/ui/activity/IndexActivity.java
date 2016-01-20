package com.android.yzd.memo.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.yzd.memo.R;
import com.android.yzd.memo.databinding.ActivityIndexBinding;
import com.android.yzd.memo.otto.OttoBus;
import com.android.yzd.memo.otto.Success;
import com.android.yzd.memo.presenter.impl.IndexPreImpl;
import com.android.yzd.memo.ui.adapter.IndexContentAdapter;
import com.android.yzd.memo.view.IndexAView;
import com.squareup.otto.Produce;

import butterknife.Bind;

public class IndexActivity extends BaseActivity implements IndexAView{

    private static final int INDEX_REQUEST_CODE = 1;
    private static final int EDIT_SAVE = 1;
    private int SUCCESS = 1;
    @Bind(R.id.common_toolbar) Toolbar mToolBar;
    private IndexPreImpl mIndexPre;
    private ActivityIndexBinding mDataBinding;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = (ActivityIndexBinding) super.mDataBinding;
        mIndexPre = new IndexPreImpl(this, this, mDataBinding);
        mIndexPre.onCreate(savedInstanceState);
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
        startActivityForResult(new Intent(this, clazz), INDEX_REQUEST_CODE);
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
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                return true;
            case R.id.about:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INDEX_REQUEST_CODE) {
            if (resultCode == EDIT_SAVE && resultCode == SUCCESS) {
                OttoBus.getInstances().post(new Success(true));
            }
        }
    }

}
