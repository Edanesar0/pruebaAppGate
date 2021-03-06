package com.prueba.appgate.di;

import android.app.Application;

import com.prueba.appgate.BaseApplication;
import com.prueba.appgate.di.home.ActivityBuildersModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {AndroidSupportInjectionModule.class, ActivityBuildersModule.class, AppModule.class, NetworkModule.class}
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
