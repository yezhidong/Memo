package com.android.yzd.mima.mvp.ui.view;

import com.android.yzd.mima.mvp.model.bean.God;

import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * Created by Clearlove on 16/1/17.
 */
public interface EditAView {

    void initSpinner(List<String> data);
    void initCreateModel(int positionType);
    void initEditModel();
    void initViewModel(God god, int positionType);
    String getTitleName();
    String getUserName();
    String getPassWord();
    String getMemoInfo();
    void setTime(String time);
    void showSnackToast(String msg);
    void setItemMenuVisible(boolean visible);
    void finishActivity();
    void setPassWordVisible(boolean visible);
    void hideKeyBoard();
    void setToolBarTitle(int resId);
    void showDialog(String msg, String positiveMsg);
    void hideSaveDialog();
    SwipeBackLayout getSwipeBack();
}
