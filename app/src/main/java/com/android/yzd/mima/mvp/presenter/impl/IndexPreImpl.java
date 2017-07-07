package com.android.yzd.mima.mvp.presenter.impl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;

import com.android.yzd.mima.R;
import com.android.yzd.mima.databinding.ActivityIndexBinding;
import com.android.yzd.mima.module.push.bean.MiPushMessageInfo;
import com.android.yzd.mima.mvp.model.Constants;
import com.android.yzd.mima.mvp.presenter.ActivityPresenter;
import com.android.yzd.mima.mvp.ui.activity.EditActivity;
import com.android.yzd.mima.mvp.ui.view.IndexAView;
import com.android.yzd.mima.utils.GetUri;
import com.android.yzd.mima.utils.SPUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

/**
 * Created by Clearlove on 16/1/13.
 */
public class IndexPreImpl implements ActivityPresenter, NavigationView.OnNavigationItemSelectedListener {

    private final Context mContext;
    private final ActivityIndexBinding mDataBinding;
    private final IndexAView mIndexView;
    private int currentSelectedItem = 0;
    private static long DOUBLE_CLICK_TIME = 0L;
    private AlertDialog mAlertDialog;

    public IndexPreImpl(Context context, IndexAView view, ActivityIndexBinding dataBinding) {
        this.mContext = context;
        mIndexView = view;
        mDataBinding = dataBinding;
        giveMeFive();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        mIndexView.initDrawerToggle();
        mIndexView.initXViewPager();
        FloatingActionButton fab = mDataBinding.fab;
        RxView.clicks(fab).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe((aVoid) ->mIndexView.readyGoForResult(EditActivity.class));
        mDataBinding.navigationView.setCheckedItem(R.id.nav_login_type);
        mDataBinding.navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void getIntent(Intent intent) {
        if (intent != null) {
            boolean fromNotification = intent.getBooleanExtra(com.android.yzd.mima.constant.Constants.INTENT.from_notification, false);
            if (fromNotification) {
                MiPushMessageInfo pushMessageInfo = (MiPushMessageInfo) intent.getSerializableExtra(com.android.yzd.mima.constant.Constants.INTENT.push_message_info);

            }
        }
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
            default:break;
        }
        return true;
    }

    public boolean onBackPress() {
        if (mDataBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }

        if (System.currentTimeMillis() - DOUBLE_CLICK_TIME > 2000) {
            DOUBLE_CLICK_TIME = System.currentTimeMillis();
            mIndexView.showSnackBar("再按一次退出~~");
            return false;
        } else {
            return true;
        }
    }

    private void giveMeFive() {
        int count = (int) SPUtils.get(mContext, Constants.COUNT, 1);
        if (count == 10
                || count == 25
                || count == 50
                || count == 85
                || count == 120
                || count == 200
                || count == 310
                || count == 500
                || count == 1000) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showDialog();
                }
            }, 3000);
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("主人");
        builder.setMessage("麻烦主人给我个五星好评～");//
        builder.setPositiveButton(mContext.getString(R.string.dialog_positive_string), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                giveFavor();
                if (mAlertDialog != null) {
                    mAlertDialog.dismiss();
                }
            }
        });
        builder.setNegativeButton(mContext.getString(R.string.dialog_negative_string), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mAlertDialog != null) {
                    mAlertDialog.dismiss();
                }
            }
        });
        mAlertDialog = builder.show();
    }

    private void giveFavor(){
        Intent intent = GetUri.getIntent(mContext, mContext.getPackageName());
        boolean b = GetUri.judge(mContext, intent);
        if (b == false) {
            mContext.startActivity(intent);
        } else {
            mIndexView.showSnackBar("你还未安装市场软件。");
        }
    }
}
