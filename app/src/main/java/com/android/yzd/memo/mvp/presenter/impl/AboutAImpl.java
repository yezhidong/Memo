package com.android.yzd.memo.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.android.yzd.memo.databinding.ActivityAboutBinding;
import com.android.yzd.memo.mvp.model.bean.AboutDB;
import com.android.yzd.memo.mvp.presenter.ActivityPresenter;
import com.android.yzd.memo.mvp.ui.activity.AboutActivity;

/**
 * Created by yezhidong on 2016/2/4.
 */
public class AboutAImpl implements ActivityPresenter {

    private final Context mContext;
    private final ActivityAboutBinding mDataBinding;

    public AboutAImpl(Context context, ActivityAboutBinding dataBinding) {
        mContext = context;
        mDataBinding = dataBinding;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mDataBinding.setAboutInfo(new AboutDB(getVersion()));
    }

    @Override
    public void getIntent(Intent intent) {

    }

    @Override
    public void onResume() {

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

    public String getVersion() {
        try {
             PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
             return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
             e.printStackTrace();
         }
        return "1.0.0";
    }
}
