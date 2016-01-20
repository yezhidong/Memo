package com.android.yzd.memo.otto;

/**
 * Created by Administrator on 2016/1/20.
 */
public class Success {
    private boolean isSuccess;
    public Success(){}
    public Success(boolean isSuccess){
        this.isSuccess = isSuccess;
    }
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
