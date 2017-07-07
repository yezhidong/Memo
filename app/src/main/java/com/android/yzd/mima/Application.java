package com.android.yzd.mima;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import com.android.yzd.mima.mvp.model.Constants;
import com.android.yzd.mima.mvp.model.Realm.RealmMigration;
import com.android.yzd.mima.utils.AppUtils;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
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
        initHotFix();
        initMiPush();
    }

    private void initHotFix() {
        SophixManager.getInstance().setContext(this)
                .setAppVersion(AppUtils.getVersionName(this))
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            // SophixManager.getInstance().cleanPatches();
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
        SophixManager.getInstance().queryAndLoadNewPatch();
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
