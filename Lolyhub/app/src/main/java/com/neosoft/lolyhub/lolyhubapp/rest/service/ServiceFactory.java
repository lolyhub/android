package com.neosoft.lolyhub.lolyhubapp.rest.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by neosoft on 29/12/16.
 */

public class ServiceFactory {

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     * @param clazz Java interface of the retrofit service
     * @param BaseURl REST base url
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz, final String BaseURl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURl)
                .addConverterFactory(GsonConverterFactory.create())
                 .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        T service = retrofit.create(clazz);

        return service;
    }
}
