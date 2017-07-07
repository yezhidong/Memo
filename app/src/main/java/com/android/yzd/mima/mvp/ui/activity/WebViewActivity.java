package com.android.yzd.mima.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.webkit.WebView;

import com.android.yzd.mima.R;
import com.android.yzd.mima.constant.Constants;
import com.android.yzd.mima.mvp.model.evenbus.EventCenter;
import com.android.yzd.mima.mvp.ui.activity.base.BaseSwipeBackActivity;
import com.android.yzd.mima.widget.BrowserLayout;

import butterknife.Bind;

public class WebViewActivity extends BaseSwipeBackActivity {

    @Bind(R.id.common_toolbar) Toolbar mToolBar;
    @Bind(R.id.brower) BrowserLayout mBrowser;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            String url = bundle.getString(Constants.WEBVIEW.url);
            mBrowser.loadUrl(url);
        }
        mBrowser.setOnLoadListener(new BrowserLayout.WebViewLoadListener() {
            @Override
            public void onFinish(WebView view, String url) {
                if (!TextUtils.isEmpty(view.getTitle())) {
                    mToolBar.setTitle(view.getTitle());
                }
            }
        });

    }

    @Override protected void onEventComing(EventCenter eventCenter) {

    }

    @Override protected int getContentView() {
        return R.layout.activity_web_view;
    }

    @Override protected void initToolbar() {
        initToolBar(mToolBar);
        mToolBar.setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public void onBackPressed() {
        if (mBrowser.canGoBack()) {
            mBrowser.getWebView().goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override protected boolean isApplyTranslucency() {
        return true;
    }

    @Override protected boolean isApplyButterKnife() {
        return true;
    }

    @Override protected boolean isApplyEventBus() {
        return false;
    }

    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override protected boolean toggleOverridePendingTransition() {
        return false;
    }
}
