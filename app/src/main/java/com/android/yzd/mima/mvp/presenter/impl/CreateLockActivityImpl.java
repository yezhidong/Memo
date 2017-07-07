package com.android.yzd.mima.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.yzd.mima.mvp.model.Constants;
import com.android.yzd.mima.R;
import com.android.yzd.mima.databinding.ActivityCreateLockBinding;
import com.android.yzd.mima.mvp.model.bean.LockBean;
import com.android.yzd.mima.mvp.presenter.ActivityPresenter;
import com.android.yzd.mima.mvp.ui.activity.IndexActivity;
import com.android.yzd.mima.utils.LockPatternUtils;
import com.android.yzd.mima.widget.LockPatternView;
import com.android.yzd.mima.utils.SPUtils;
import com.android.yzd.mima.utils.ShortLockPatternUtils;
import com.android.yzd.mima.mvp.ui.view.CreateLockAView;

import java.util.List;

/**
 * Created by Clearlove on 16/1/14.
 */
public class CreateLockActivityImpl implements ActivityPresenter {

    private static final String CREATE_LOCK_SUCCESS = "CREATE_LOCK_SUCCESS";
    private final Context mContext;
    private final ActivityCreateLockBinding mBinding;
    private LockBean lockBeanText;
    private final CreateLockAView mCreateLockAView;
    private boolean isFinishOnce = false;
    private int createMode;

    public CreateLockActivityImpl(Context context, CreateLockAView view,ActivityCreateLockBinding binding) {
        mContext = context;
        mCreateLockAView = view;
        mBinding = binding;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        lockBeanText = new LockBean(mContext.getString(R.string.create_activity_warn));
        mBinding.setLockWarn(lockBeanText);
        mCreateLockAView.initLockPatternView();
    }


    @Override
    public void getIntent(Intent intent) {
        createMode = intent.getIntExtra("CREATE_MODE", Constants.CREATE_MODE);
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

    public void fingerPress() {
        lockBeanText.setWarn(mContext.getString(R.string.finger_press));
        mBinding.setLockWarn(lockBeanText);
    }

    public void fingerFirstUpError() {
        lockBeanText.setWarn(mContext.getString(R.string.finger_up_first_error));
        mBinding.setLockWarn(lockBeanText);
    }

    public void fingerFirstUpSuccess() {
        lockBeanText.setWarn(mContext.getString(R.string.finger_up_first_success));
        mBinding.setLockWarn(lockBeanText);
    }

    public void fingerSecondUpError() {
        lockBeanText.setWarn(mContext.getString(R.string.finger_up_second_error));
        mBinding.setLockWarn(lockBeanText);
    }

    public void fingerSecondUpSucess() {
        lockBeanText.setWarn(mContext.getString(R.string.finger_up_second_success));
        mBinding.setLockWarn(lockBeanText);
    }

    public void check(List<LockPatternView.Cell> pattern) {
        if (pattern == null) return;

        if (pattern.size() < LockPatternUtils.MIN_PATTERN_REGISTER_FAIL) {

            if (!isFinishOnce) {
                fingerFirstUpError();
            } else {
                fingerSecondUpError();
            }
            mCreateLockAView.lockDisplayError();

        } else {

            if (!isFinishOnce) {
                fingerFirstUpSuccess();
                LockPatternUtils instances = LockPatternUtils.getInstances(mContext);
                ShortLockPatternUtils shortLockPatternUtils = ShortLockPatternUtils.getInstances(mContext);
                if (createMode == Constants.CREATE_GESTURE) {
                    instances.saveLockPattern(pattern);
                } else if (createMode == Constants.UPDATE_GESTURE) {
                    shortLockPatternUtils.saveLockPattern(pattern);
                }
                mCreateLockAView.clearPattern();
                isFinishOnce = true;
            } else {
                LockPatternUtils instances = LockPatternUtils.getInstances(mContext);
                ShortLockPatternUtils shortLockPatternUtils = ShortLockPatternUtils.getInstances(mContext);
                if (createMode == Constants.CREATE_GESTURE) {

                    if (instances.checkPattern(pattern)) {
                        fingerSecondUpSucess();
                        SPUtils.put(mContext, CREATE_LOCK_SUCCESS, true);
                        mCreateLockAView.readyGoThenKill(IndexActivity.class);
                    } else {
                        fingerSecondUpError();
                        mCreateLockAView.lockDisplayError();
                    }
                    isFinishOnce = false;
                } else if (createMode == Constants.UPDATE_GESTURE) {
                    if (shortLockPatternUtils.checkPattern(pattern)) {
                        instances.saveLockPattern(pattern);
                        fingerSecondUpSucess();
                        SPUtils.put(mContext, CREATE_LOCK_SUCCESS, true);
                        mCreateLockAView.setResults(1);
                        mCreateLockAView.kill();
                    } else {
                        fingerSecondUpError();
                        mCreateLockAView.lockDisplayError();
                    }
                    isFinishOnce = false;
                }
            }
        }
    }

    public void onBack() {
        if (createMode == Constants.UPDATE_GESTURE) {
            mCreateLockAView.setResults(0);
        }
    }
}
