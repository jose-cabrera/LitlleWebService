package com.das.jospablo.littewebservice.database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by JoséPablo on 16/07/16.
 */
public class OpenRealm {

    public static Realm open(Context context) {
        return Realm.getInstance(new RealmConfiguration.Builder(context).build());
    }

}
