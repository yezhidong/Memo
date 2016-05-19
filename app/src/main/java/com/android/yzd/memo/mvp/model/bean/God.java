package com.android.yzd.memo.mvp.model.bean;

import com.android.yzd.memo.utils.encrypt.Base64Util;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Clearlove on 16/1/17.
 */
public class God extends RealmObject{

    /**
     * 密码所属的分组
     */
    private int godType;

    /**
     * 标题，注明该账号为哪个网站的账号
     */
    @PrimaryKey
    private String title;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 创建的时间,或者修改时间
     */
    private long time;

    /**
     * 备忘信息
     */
    private String memoInfo;

    public God() {}

    public God(int godType, String title, String userName, String passWord, long time, String memoInfo) {
        this.godType = godType;
        this.title = Base64Util.encryptBASE64(title);
        this.userName = Base64Util.encryptBASE64(userName);
        this.passWord = Base64Util.encryptBASE64(passWord);
        this.time = time;
        this.memoInfo = Base64Util.encryptBASE64(memoInfo);
    }

    public int getGodType() {
        return godType;
    }

    public void setGodType(int godType) {
        this.godType = godType;
    }

    public String getUserName() {
        return Base64Util.decryptBASE64(userName);
    }

    public void setUserName(String userName) {
        this.userName = Base64Util.encryptBASE64(userName);
    }

    public String getPassWord() {
        return Base64Util.decryptBASE64(passWord);
    }

    public void setPassWord(String passWord) {
        this.passWord = Base64Util.encryptBASE64(passWord);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return Base64Util.decryptBASE64(title);
    }

    public void setTitle(String title) {
        this.title = Base64Util.encryptBASE64(title);
    }

    public String getMemoInfo() {
        return Base64Util.decryptBASE64(memoInfo);
    }

    public void setMemoInfo(String memoInfo) {
        this.memoInfo = Base64Util.encryptBASE64(memoInfo);
    }
}
