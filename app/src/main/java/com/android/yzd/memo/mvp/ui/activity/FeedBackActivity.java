package com.android.yzd.memo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.yzd.memo.R;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;
import com.android.yzd.memo.mvp.ui.activity.base.Base;
import com.android.yzd.memo.mvp.ui.activity.base.BaseActivity;
import com.android.yzd.memo.mvp.ui.activity.base.BaseSwipeBackActivity;
import com.umeng.fb.fragment.FeedbackFragment;

import butterknife.Bind;

public class FeedBackActivity extends BaseSwipeBackActivity {

    @Bind(R.id.common_toolbar) Toolbar mToolBar;
    private String mConversationId = null;
    private FeedbackFragment mFeedbackFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        mConversationId = extras.getString(FeedbackFragment.BUNDLE_KEY_CONVERSATION_ID);
        mFeedbackFragment = FeedbackFragment.newInstance(mConversationId);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, mFeedbackFragment)
                .commit();
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected void onNewIntent(android.content.Intent intent) {
        mFeedbackFragment.refresh();
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initToolbar() {
        initToolBar(mToolBar);
        mToolBar.setTitle("用户反馈");
    }

    @Override
    protected boolean isApplyTranslucency() {
        return true;
    }

    @Override
    protected boolean isApplyButterKnife() {
        return true;
    }

    @Override
    protected boolean isApplyEventBus() {
        return false;
    }
}
