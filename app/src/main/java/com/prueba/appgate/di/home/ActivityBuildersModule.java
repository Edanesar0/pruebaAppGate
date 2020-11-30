package com.prueba.appgate.di.home;


import com.prueba.appgate.ui.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {HomeFragmentBuildersModule.class, HomeViewModelsModule.class})
    abstract HomeActivity contributeHomeActivity();

}
