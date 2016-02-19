package com.android.yzd.memo.mvp.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Clearlove on 16/1/17.
 */
public class God extends RealmObject{

    private int godType;
    @PrimaryKey
    private String title;

    private String userName;

    private String passWord;

    private long time;

    private String memoInfo;

    public God() {}

    public God(int godType, String title, String userName, String passWord, long time, String memoInfo) {
        this.godType = godType;
        this.title = title;
        this.userName = userName;
        this.passWord = passWord;
        this.time = time;
        this.memoInfo = memoInfo;
    }

    public int getGodType() {
        return godType;
    }

    public void setGodType(int godType) {
        this.godType = godType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getMemoInfo() {
        return memoInfo;
    }

    public void setMemoInfo(String memoInfo) {
        this.memoInfo = memoInfo;
    }
}
