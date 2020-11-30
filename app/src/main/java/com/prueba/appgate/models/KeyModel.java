package com.prueba.appgate.models;

public class KeyModel {
    public static String EXTRA = "key_model";
    public byte[] key;

    public KeyModel(byte[] key) {
        this.key = key;
    }
}
