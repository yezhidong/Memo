package com.android.yzd.memo.mvp.model.bean;

/**
 * Created by yezhidong on 2016/2/4.
 */
public class AboutDB {
    private String version;

    public AboutDB(String version) {
        this.version = version;
    }

    public String getVersion() {
        return "V"+this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
