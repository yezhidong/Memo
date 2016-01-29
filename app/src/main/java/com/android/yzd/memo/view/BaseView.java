package com.android.yzd.memo.view;

/**
 * Created by yezhidong on 2016/1/14.
 */
public interface BaseView {

    void readyGoThenKill(Class clazz);
    void kill();
    void showSnackBar(String msg);
}
