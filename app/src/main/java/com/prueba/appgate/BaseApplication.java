package com.prueba.appgate;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.prueba.appgate.di.DaggerAppComponent;
import com.prueba.appgate.models.KeyModel;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        realmDb();
        return DaggerAppComponent.builder().application(this).build();
    }

    public static RealmConfiguration realmConfiguration;

    private void realmDb() {
        try {
            Realm.init(this);
            byte[] key = new byte[64];

            SharedPreferences sharedPreferences = this.getSharedPreferences(this.getString(R.string.key_preferences), Context.MODE_PRIVATE);
            String keyString = sharedPreferences.getString(KeyModel.EXTRA, "");

            if (keyString == null || keyString.equals("")) {
                new SecureRandom().nextBytes(key);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String gson = new Gson().toJson(new KeyModel(key));
                editor.putString(KeyModel.EXTRA, AESCrypt.encrypt(KeyModel.class.getName(), gson));
                editor.apply();
            } else {
                KeyModel data;
                if (keyString != null && !keyString.isEmpty()) {
                    String json = AESCrypt.decrypt(KeyModel.class.getName(), keyString);
                    data = new Gson().fromJson(json, KeyModel.class);
                } else {
                    data = new Gson().fromJson("{}", KeyModel.class);
                }
                key = data.key;
            }
            realmConfiguration = new RealmConfiguration.Builder()
                    .name(Realm.DEFAULT_REALM_NAME)
                    .encryptionKey(key)
                    .schemaVersion(0)
                    .deleteRealmIfMigrationNeeded()
                    .allowWritesOnUiThread(true)
                    .build();
            Realm.setDefaultConfiguration(realmConfiguration);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }
}
