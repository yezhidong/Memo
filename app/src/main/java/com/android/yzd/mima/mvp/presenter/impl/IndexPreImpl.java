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
import com.android.yzd.mima.mvp.model.Constants;
import com.android.yzd.mima.mvp.model.Realm.RealmHelper;
import com.android.yzd.mima.mvp.model.bean.God;
import com.android.yzd.mima.mvp.presenter.ActivityPresenter;
import com.android.yzd.mima.mvp.ui.activity.EditActivity;
import com.android.yzd.mima.mvp.ui.view.IndexAView;
import com.android.yzd.mima.utils.GetUri;
import com.android.yzd.mima.utils.SPUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

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
    private final ArrayList<God> mQuery;
    private boolean goComment;
    private long mCommentCurrentTimeMillis;

    public IndexPreImpl(Context context, IndexAView view, ActivityIndexBinding dataBinding) {
        this.mContext = context;
        mIndexView = view;
        mDataBinding = dataBinding;
        giveMeFive();
        RealmHelper.getInstances(mContext);
        mQuery = RealmHelper.query(mContext);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mIndexView.initDrawerToggle();
        mIndexView.initXViewPager();
        FloatingActionButton fab = mDataBinding.fab;
        RxView.clicks(fab).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe((new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                boolean isComment = (boolean) SPUtils.get(mContext, Constants.IS_COMMENT, false);
                if (isComment || mQuery != null && mQuery.size() == 3) {
                    mIndexView.readyGoForResult(EditActivity.class);
                } else {
                    showDialogToComment();
                }
            }
        }));
    }

    @Override
    public void getIntent(Intent intent) {
        if (intent != null) {
            boolean fromNotification = intent.getBooleanExtra(com.android.yzd.mima.constant.Constants.INTENT.from_notification, false);
        }
    }

    @Override
    public void onResume() {
        if (goComment && System.currentTimeMillis() - mCommentCurrentTimeMillis > 10000) {
            SPUtils.put(mContext, Constants.IS_COMMENT, true);
        }
        goComment = false;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
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
                || count == 120) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showDialog();
                }
            }, 3000);
        }
    }

    private void showDialogToComment() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("主人");
        builder.setMessage("给应用一个五星好评，就可以无限添加啦！");//
        builder.setPositiveButton(mContext.getString(R.string.dialog_comment_positive_string), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                giveFavor();
                goComment = true;
                mCommentCurrentTimeMillis = System.currentTimeMillis();
                if (mAlertDialog != null) {
                    mAlertDialog.dismiss();
                }
            }
        });
        builder.setNegativeButton(mContext.getString(R.string.dialog_comment_negative_string), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mAlertDialog != null) {
                    mAlertDialog.dismiss();
                }
            }
        });
        mAlertDialog = builder.show();
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

    private void giveFavor() {
        Intent intent = GetUri.getIntent(mContext, mContext.getPackageName());
        boolean b = GetUri.judge(mContext, intent);
        if (b == false) {
            mContext.startActivity(intent);
        } else {
            mIndexView.showSnackBar("你还未安装市场软件。");
        }
    }
}
