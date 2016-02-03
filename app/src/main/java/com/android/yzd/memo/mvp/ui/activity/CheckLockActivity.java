package com.android.yzd.memo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.android.yzd.memo.R;
import com.android.yzd.memo.databinding.ActivityCheckLockBinding;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;
import com.android.yzd.memo.mvp.presenter.impl.CheckLockAImpl;
import com.android.yzd.memo.mvp.ui.activity.base.BaseActivity;
import com.android.yzd.memo.mvp.ui.view.CheckLockAView;
import com.android.yzd.memo.widget.LockPatternView;

import java.util.List;

import butterknife.Bind;


public class CheckLockActivity extends BaseActivity implements CheckLockAView, LockPatternView.OnPatternListener {

    @Bind(R.id.lockPatternView) LockPatternView mLockPatternView;
    private CheckLockAImpl mCheckLockA;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCheckLockA = new CheckLockAImpl(this, this, (ActivityCheckLockBinding) mDataBinding);
        mCheckLockA.onCreate(savedInstanceState);
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override protected int getContentView() {
        return R.layout.activity_check_lock;
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

    @Override
    public void initLockPatternView() {
        mLockPatternView.setOnPatternListener(this);
    }

    @Override
    public void lockDisplayError() {
        mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
    }

    @Override
    public void readyGoThenKill(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    @Override
    public void kill() {

    }

    @Override
    public void showSnackBar(String msg) {

    }

    @Override
    public void onPatternStart() {
    }

    @Override
    public void onPatternCleared() {

    }

    @Override
    public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {

    }

    @Override
    public void onPatternDetected(List<LockPatternView.Cell> pattern) {
        mCheckLockA.check(pattern);
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(0, 0);
    }
}
