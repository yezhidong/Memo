package com.android.yzd.memo.model.bean;

import com.android.yzd.memo.presenter.impl.IndexPreImpl;

/**
 * Created by yezhidong on 2016/1/15.
 */
public class IndexBean {

    private String toolBarTitle;

    public IndexBean(String title) {
        this.toolBarTitle = title;
    }

    public void setToolBarTitle(String title) {
        this.toolBarTitle = title;
    }

    public String getToolBarTitle() {
        return this.toolBarTitle;
    }
}
