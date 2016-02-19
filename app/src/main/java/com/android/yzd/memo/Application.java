package com.android.yzd.memo;

import com.android.yzd.memo.mvp.model.Constants;
import com.android.yzd.memo.mvp.model.Realm.RealmMigration;

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
    }

    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("memo.realm")
                .migration(new RealmMigration())
                .schemaVersion(Constants.REALM_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
