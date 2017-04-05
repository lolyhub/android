package com.neosoft.lolyhub.lolyhubapp.rest.service;

import com.neosoft.lolyhub.lolyhubapp.rest.model.Countries;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by neosoft on 29/12/16.
 */

public interface RestClient {
    String SERVICE_BASEURL = "http://services.groupkt.com";
    @GET("country/get/all")
    Observable<Countries> getUser();

}
