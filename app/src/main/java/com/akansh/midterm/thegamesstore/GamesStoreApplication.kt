package com.akansh.midterm.thegamesstore

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class GamesStoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .name("gamesstore.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.getInstance(realmConfig)
        Realm.setDefaultConfiguration(realmConfig)
    }
}