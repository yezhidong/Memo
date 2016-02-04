package com.android.yzd.memo.mvp.model.Realm;

import android.content.Context;

import com.android.yzd.memo.bean.God;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2016/1/26.
 */
public class RealmHelper {
    private static RealmHelper instances;
    private Context mContext;

    private RealmHelper(Context context){
        mContext = context;
    }
    public static RealmHelper getInstances(Context context){
        if (instances == null) {
            instances = new RealmHelper(context);
        }
        return instances;
    }
    private static void closeConnect(Realm realm) {
        if (null != realm) {
            try {
                realm.close();
                realm = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static RealmConfiguration secure(Context context) {
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context)
                .encryptionKey(key)
                .build();

        // Start with a clean slate every time
        Realm.deleteRealm(realmConfiguration);
        return realmConfiguration;
    }
    public static ArrayList<God> selector(Context context, int godType){
        Realm realm = Realm.getInstance(context);
        RealmQuery<God> realmQuery = realm.where(God.class);
        RealmQuery<God> godRealmQuery = realmQuery.equalTo("godType", godType);
        RealmResults<God> realmResults = godRealmQuery.findAll();
        if (realmResults != null && realmResults.size() > 0) {
            ArrayList<God> godList = new ArrayList<>();
            for (God god : realmResults) {
                godList.add(god);
            }
            Collections.reverse(godList);
            return godList;
        }
        return null;
    }

    public static void save(Context context, God god) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        realm.copyToRealm(god);
        realm.commitTransaction();
    }

    public static void update(Context context, God god) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(god);
        realm.commitTransaction();
    }

    public static void delete(Context context, God god, int position) {
        Realm realm = Realm.getInstance(context);
        RealmQuery<God> realmQuery = realm.where(God.class);
        RealmQuery<God> godRealmQuery = realmQuery.equalTo("godType", god.getGodType());
        RealmResults<God> realmResults = godRealmQuery.findAll();
        if (realmResults != null ) {
            int size = realmResults.size() - 1;
            int i = size - position;
            realm.beginTransaction();
            realmResults.remove(i);
            realm.commitTransaction();
        }
    }
}
