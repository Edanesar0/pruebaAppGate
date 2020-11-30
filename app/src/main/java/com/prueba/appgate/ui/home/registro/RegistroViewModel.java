package com.prueba.appgate.ui.home.registro;

import androidx.lifecycle.ViewModel;

import com.prueba.appgate.BaseApplication;
import com.prueba.appgate.models.LoginModel;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

public class RegistroViewModel extends ViewModel {

    public LoginModel loginModel;

    @Inject
    public RegistroViewModel() {
        loginModel = new LoginModel();
    }

    boolean validateUser() {
        Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);
        // Build the query looking at all users:
        RealmResults<LoginModel> query = realm.where(LoginModel.class)
                .equalTo("username", loginModel.username)
                .findAll();

        return query.size() > 0;

    }
    void saveLoginRealm() {
        Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);
        realm.executeTransaction(realm1 -> {
            realm1.copyToRealmOrUpdate(loginModel);
        });
    }
}
