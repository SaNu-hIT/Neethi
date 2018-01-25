package com.example.work.cartapp.Activitys;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by intellyelabs on 19/01/18.
 */


public class Neethi extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // The default Realm file is "default.realm" in Context.getFilesDir();
        // we'll change it to "myrealm.realm"
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("neethi.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}