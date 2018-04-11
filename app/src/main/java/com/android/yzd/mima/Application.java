package com.android.yzd.mima;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import com.android.yzd.mima.mvp.model.Constants;
import com.android.yzd.mima.mvp.model.Realm.RealmMigration;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yezhidong on 2016/2/19.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initMiPush();
    }

    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("memo.realm")
                .migration(new RealmMigration())
                .schemaVersion(Constants.REALM_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    /**
     * 初始化小米推送
     */
    private void initMiPush(){
        if(shouldInit()){
            MiPushClient.registerPush(this, "2882303761517594654", "5871759489654");
        }
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
