package com.android.yzd.memo.ui.activity;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.yzd.memo.R;
import com.android.yzd.memo.model.evenbus.EventCenter;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.fragment.FeedbackFragment;

import butterknife.Bind;

public class FeedBackActivity extends BaseActivity {

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
        setTitle("用户反馈");
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
