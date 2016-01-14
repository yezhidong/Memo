package com.android.yzd.memo.presenter.impl;

import android.content.Context;

import com.android.yzd.memo.R;
import com.android.yzd.memo.databinding.ActivityCreateLockBinding;
import com.android.yzd.memo.model.bean.LockWarn;
import com.android.yzd.memo.presenter.Presenter;
import com.android.yzd.memo.ui.activity.IndexActivity;
import com.android.yzd.memo.utils.LockPatternUtils;
import com.android.yzd.memo.ui.widget.LockPatternView;
import com.android.yzd.memo.utils.SPUtils;
import com.android.yzd.memo.view.CreateLockAView;

import java.util.List;

/**
 * Created by Clearlove on 16/1/14.
 */
public class CreateLockActivityImpl implements Presenter {

    private static final String CREATE_LOCK_SUCCESS = "CREATE_LOCK_SUCCESS";
    private final Context mContext;
    private final ActivityCreateLockBinding mBinding;
    private LockWarn lockWarnText;
    private final CreateLockAView mCreateLockAView;
    private boolean isFinishOnce = false;

    public CreateLockActivityImpl(Context context, CreateLockAView view,ActivityCreateLockBinding binding) {
        mContext = context;
        mCreateLockAView = view;
        mBinding = binding;
    }

    @Override
    public void onCreate() {
        lockWarnText = new LockWarn(mContext.getString(R.string.create_activity_warn));
        mBinding.setLockWarn(lockWarnText);
        mCreateLockAView.initLockPatternView();
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
        lockWarnText.setWarn(mContext.getString(R.string.finger_press));
        mBinding.setLockWarn(lockWarnText);
    }

    public void fingerFirstUpError() {
        lockWarnText.setWarn(mContext.getString(R.string.finger_up_first_error));
        mBinding.setLockWarn(lockWarnText);
    }

    public void fingerFirstUpSuccess() {
        lockWarnText.setWarn(mContext.getString(R.string.finger_up_first_success));
        mBinding.setLockWarn(lockWarnText);
    }

    public void fingerSecondUpError() {
        lockWarnText.setWarn(mContext.getString(R.string.finger_up_second_error));
        mBinding.setLockWarn(lockWarnText);
    }

    public void fingerSecondUpSucess() {
        lockWarnText.setWarn(mContext.getString(R.string.finger_up_second_success));
        mBinding.setLockWarn(lockWarnText);
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
                instances.saveLockPattern(pattern);
                isFinishOnce = true;
            } else {
                LockPatternUtils instances = LockPatternUtils.getInstances(mContext);
                if (instances.checkPattern(pattern)) {
                    fingerSecondUpSucess();
                    SPUtils.put(mContext, CREATE_LOCK_SUCCESS, true);
                    mCreateLockAView.readyGoThenKill(IndexActivity.class);
                } else {
                    fingerSecondUpError();
                    mCreateLockAView.lockDisplayError();
                }
                isFinishOnce = false;
            }
        }
    }
}
