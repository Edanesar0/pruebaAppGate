package com.prueba.appgate.models;

import io.realm.RealmObject;

public class IntentosModel extends RealmObject {
    public String fecha;
    public boolean resultado;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }
}
