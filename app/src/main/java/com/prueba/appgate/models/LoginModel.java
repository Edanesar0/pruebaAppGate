package com.prueba.appgate.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LoginModel extends RealmObject {
    @PrimaryKey
    public String username;
    public String password;
    public RealmList<IntentosModel> intentos = new RealmList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RealmList<IntentosModel> getIntentos() {
        return intentos;
    }

    public void setIntentos(RealmList<IntentosModel> intentos) {
        this.intentos = intentos;
    }
}
