package com.android.yzd.memo.presenter.impl;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.view.View;

import com.android.yzd.memo.R;
import com.android.yzd.memo.databinding.ActivityIndexBinding;
import com.android.yzd.memo.presenter.ActivityPresenter;
import com.android.yzd.memo.view.IndexAView;

/**
 * Created by Clearlove on 16/1/13.
 */
public class IndexPreImpl implements ActivityPresenter, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private final Context mContext;
    private final ActivityIndexBinding mDataBinding;
    private final IndexAView mIndexView;
    private int currentSelectedItem = 0;

    public IndexPreImpl(Context context, IndexAView view, ActivityIndexBinding dataBinding) {
        this.mContext = context;
        mIndexView = view;
        mDataBinding = dataBinding;
    }

    @Override public void onCreate() {
        mIndexView.initDrawerToggle();
        mIndexView.initXViewPager();
        mDataBinding.fab.setOnClickListener(this);
        mDataBinding.navigationView.setNavigationItemSelectedListener(this);
    }

    @Override public void onResume() {

    }

    @Override public void onStart() {

    }

    @Override public void onPause() {

    }

    @Override public void onStop() {

    }

    @Override public void onDestroy() {

    }

    @Override
    public void onClick(View v) {
        Snackbar.make(v, "sasasasasa",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_login_type:
                currentSelectedItem = 0;
                mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
                mDataBinding.content.setCurrentItem(currentSelectedItem, false);
                break;
            case R.id.nav_mail_type:
                currentSelectedItem = 1;
                mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
                mDataBinding.content.setCurrentItem(currentSelectedItem, false);
                break;
            case R.id.nav_note_type:
                currentSelectedItem = 2;
                mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
                mDataBinding.content.setCurrentItem(currentSelectedItem, false);
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_setting:
                break;
            default:break;
        }
        return true;
    }
}
