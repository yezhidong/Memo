package com.android.yzd.memo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.android.yzd.memo.R;
import com.android.yzd.memo.databinding.ActivityCreateLockBinding;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;
import com.android.yzd.memo.mvp.presenter.impl.CreateLockActivityImpl;
import com.android.yzd.memo.mvp.ui.activity.base.BaseActivity;
import com.android.yzd.memo.mvp.ui.view.CreateLockAView;
import com.android.yzd.memo.widget.LockPatternView;

import java.util.List;

import butterknife.Bind;


public class CreateLockActivity extends BaseActivity implements CreateLockAView, LockPatternView.OnPatternListener {

    @Bind(R.id.lockPatternView) LockPatternView mLockPatternView;

    private ActivityCreateLockBinding binding;
    private CreateLockActivityImpl mLockActivity;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityCreateLockBinding) this.mDataBinding;

        mLockActivity = new CreateLockActivityImpl(this, this, binding);
        mLockActivity.onCreate(savedInstanceState);
        mLockActivity.getIntent(getIntent());
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
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override protected int getContentView() {
        return R.layout.activity_create_lock;
    }

    @Override protected void initToolbar() {

    }

    @Override protected boolean isApplyTranslucency() {
        return true;
    }

    @Override protected boolean isApplyButterKnife() {
        return true;
    }

    @Override
    protected boolean isApplyEventBus() {
        return false;
    }

    @Override public void initLockPatternView() {
        mLockPatternView.setOnPatternListener(this);
    }

    @Override
    public void lockDisplayError() {
        mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
    }

    @Override
    public void setResults(int isSuccess) {
        setResult(isSuccess);
    }

    @Override
    public void clearPattern() {
        mLockPatternView.clearPattern();
    }

    @Override
    public void readyGoThenKill(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    @Override
    public void kill() {
        finish();
    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar.make(mLockPatternView, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override public void onPatternStart() {
        mLockActivity.fingerPress();
    }

    @Override public void onPatternCleared() {

    }

    @Override public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {

    }

    @Override public void onPatternDetected(List<LockPatternView.Cell> pattern) {
        mLockActivity.check(pattern);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mLockActivity.onBack();
    }
}
