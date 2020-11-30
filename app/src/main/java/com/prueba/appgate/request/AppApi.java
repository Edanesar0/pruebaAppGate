package com.prueba.appgate.request;

import com.prueba.appgate.models.TimezoneJsonModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * interface que contiene las peticiones hacia el
 * servidor el cual retorna
 * el datos puro o una clase POJO GSON
 */
public interface AppApi {

    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("timezoneJSON")
    Observable<TimezoneJsonModel> timezone(@Query("formatted") boolean formatted,
                                           @Query("lat") double lat,
                                           @Query("lng") double lng,
                                           @Query("username") String username,
                                           @Query("style") String style);

}
