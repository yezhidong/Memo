package com.android.yzd.memo.mvp.model.Realm;

import io.realm.DynamicRealm;
import io.realm.RealmSchema;

/**
 * Created by yezhidong on 2016/2/19.
 */
public class RealmMigration implements io.realm.RealmMigration {

    @Override
    public void migrate(DynamicRealm dynamicRealm, long oldVersion, long newVersion) {
        RealmSchema schema = dynamicRealm.getSchema();

        if (oldVersion == 0) {
            schema.get("God")
                    .addField("memoInfo", String.class);
            oldVersion++;
        }
    }
}
