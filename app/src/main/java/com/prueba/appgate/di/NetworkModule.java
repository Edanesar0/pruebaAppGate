package com.prueba.appgate.di;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prueba.appgate.request.AppApi;
import com.prueba.appgate.util.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kotlin.jvm.JvmStatic;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.prueba.appgate.util.Constants.CONNECTION_TIMEOUT;
import static com.prueba.appgate.util.Constants.READ_TIMEOUT;
import static com.prueba.appgate.util.Constants.WRITE_TIMEOUT;

/**
 * Módulo que proporciona todas las dependencias necesarias sobre la red.
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    @JvmStatic
    AppApi appGateApi(Retrofit retrofit) {
        return retrofit.create(AppApi.class);
    }

    @Singleton
    @Provides
    @JvmStatic
    Retrofit retrofitBuilder(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * Provides custom OkHttpClient
     */

    @Singleton
    @Provides
    @JvmStatic
    OkHttpClient okHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                // establish connection to server
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                // time between each byte read from the server
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                // time between each byte sent to server
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();

    }

    @Provides
    @Singleton
    Gson gson() {
        return new GsonBuilder().serializeNulls().create();
    }
}
