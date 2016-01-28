package com.android.yzd.memo.bean;

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


    public God() {}

    public God(int godType, String title, String userName, String passWord, long time) {
        this.godType = godType;
        this.title = title;
        this.userName = userName;
        this.passWord = passWord;
        this.time = time;
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
}
