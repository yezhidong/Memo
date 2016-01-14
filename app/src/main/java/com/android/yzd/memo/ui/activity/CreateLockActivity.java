package com.android.yzd.memo.ui.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import com.android.yzd.memo.R;
import com.android.yzd.memo.databinding.ActivityCreateLockBinding;
import com.android.yzd.memo.model.bean.LockWarn;

public class CreateLockActivity extends BaseActivity {

    private ActivityCreateLockBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityCreateLockBinding) this.mDataBinding;
        binding.setLockWarn(new LockWarn(getString(R.string.create_activity_warn)));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_create_lock;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected boolean isApplyTranslucency() {
        return true;
    }

    @Override
    protected boolean isApplyButterKnife() {
        return false;
    }
}
