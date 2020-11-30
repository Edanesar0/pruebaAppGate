package com.prueba.appgate.di.home;

import com.prueba.appgate.ui.home.intentos.IntentosFragment;
import com.prueba.appgate.ui.home.main.HomeFragment;
import com.prueba.appgate.ui.home.registro.RegistroFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract IntentosFragment contributeIntentosFragment();

    @ContributesAndroidInjector
    abstract RegistroFragment contributeRegistroFragment();
}
