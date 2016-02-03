package com.android.yzd.memo.mvp.ui.view;

/**
 * Created by Clearlove on 16/1/14.
 */
public interface CreateLockAView extends BaseView{

    void initLockPatternView();

    void lockDisplayError();

    void setResults(int isSuccess);

    void clearPattern();
}
