package com.android.yzd.memo.bean;

import io.realm.RealmObject;

/**
 * Created by Clearlove on 16/1/17.
 */
public class God extends RealmObject{

    private int godType;

    private String company;

    private String userName;

    private String passWord;

    private long time;
    private String url;
    private String imageUrl;

    public God() {}

    public God(int godType, String company, String userName, String passWord, long time) {
        this.godType = godType;
        this.company = company;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
