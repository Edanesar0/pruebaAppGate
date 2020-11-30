package com.prueba.appgate.ui.home.intentos;

import androidx.lifecycle.ViewModel;

import com.prueba.appgate.models.LoginModel;

import javax.inject.Inject;

public class IntentosViewModel extends ViewModel {
    public LoginModel loginModel;

    @Inject
    public IntentosViewModel() {
    }
}
