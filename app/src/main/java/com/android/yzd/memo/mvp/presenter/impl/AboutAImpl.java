package com.android.yzd.memo.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.android.yzd.memo.databinding.ActivityAboutBinding;
import com.android.yzd.memo.mvp.model.bean.AboutDB;
import com.android.yzd.memo.mvp.presenter.ActivityPresenter;
import com.android.yzd.memo.mvp.ui.activity.AboutActivity;
import com.android.yzd.memo.mvp.ui.activity.WebViewActivity;
import com.android.yzd.memo.mvp.ui.view.AboutAView;

/**
 * Created by yezhidong on 2016/2/4.
 */
public class AboutAImpl implements ActivityPresenter {

    private final Context mContext;
    private final ActivityAboutBinding mDataBinding;
    private final AboutAView mAboutAView;

    public AboutAImpl(Context context, AboutAView view, ActivityAboutBinding dataBinding) {
        mContext = context;
        mAboutAView = view;
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

    public void codeClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("URL", "https://github.com/yezhidong/Memo");
        mAboutAView.go2Activity(WebViewActivity.class, bundle);
    }
}
