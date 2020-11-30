package com.prueba.appgate.di.home;

import androidx.lifecycle.ViewModel;

import com.prueba.appgate.di.ViewModelKey;
import com.prueba.appgate.ui.home.intentos.IntentosViewModel;
import com.prueba.appgate.ui.home.main.HomeViewModel;
import com.prueba.appgate.ui.home.registro.RegistroViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class HomeViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHome(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(IntentosViewModel.class)
    abstract ViewModel bindIntentos(IntentosViewModel intentosViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RegistroViewModel.class)
    abstract ViewModel bindRegistro(RegistroViewModel registroViewModel);
}
