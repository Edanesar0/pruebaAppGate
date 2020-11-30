package com.prueba.appgate.ui.home.main;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.prueba.appgate.BaseApplication;
import com.prueba.appgate.models.IntentosModel;
import com.prueba.appgate.models.LoginModel;
import com.prueba.appgate.models.TimezoneJsonModel;
import com.prueba.appgate.request.AppApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

public class HomeViewModel extends ViewModel {

    public LoginModel loginModel;
    public IntentosModel intentosModel;

    double longitude = 0;
    double latitude = 0;
    private final AppApi appApi;

    @Inject
    public HomeViewModel(AppApi appApi) {
        this.appApi = appApi;
        loginModel = new LoginModel();
        intentosModel = new IntentosModel();
    }

    boolean validateUser() {
        Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);
        RealmResults<LoginModel> query = realm.where(LoginModel.class)
                .equalTo("username", loginModel.username)
                .and().equalTo("password", loginModel.password)
                .findAll();
        return query.size() > 0;

    }

    void saveIntentoRealm() {
        Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);
        realm.executeTransaction(realm1 -> {
            RealmResults<LoginModel> query = realm1.where(LoginModel.class).equalTo("username", loginModel.username).findAll();
            for (LoginModel model : query) {
                model.getIntentos().add(intentosModel);
                model.setIntentos(model.getIntentos());
                realm1.copyToRealmOrUpdate(query);
            }
        });
    }

    Observable<TimezoneJsonModel> dateForServer() {
        return appApi.timezone(true, latitude, longitude, "qa_mobile_easy", "full");
    }

}
