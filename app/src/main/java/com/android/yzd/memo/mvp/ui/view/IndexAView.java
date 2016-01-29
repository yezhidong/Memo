package com.android.yzd.memo.mvp.ui.view;

/**
 * Created by yezhidong on 2016/1/15.
 */
public interface IndexAView {

    void initDrawerToggle();

    void initXViewPager();

    void readyGoForResult(Class clazz);

    void go2Setting();

    void showSnackBar(String msg);

    void kill();
}
