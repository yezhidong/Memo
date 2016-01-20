package com.android.yzd.memo.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.android.yzd.memo.R;
import com.android.yzd.memo.databinding.ActivityCreateLockBinding;
import com.android.yzd.memo.presenter.impl.CreateLockActivityImpl;
import com.android.yzd.memo.ui.widget.LockPatternView;
import com.android.yzd.memo.view.CreateLockAView;

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

    @Override public void initLockPatternView() {
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

}
