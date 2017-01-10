package com.neosoft.onesignal.idealrestformat.rest.service;

import com.neosoft.onesignal.idealrestformat.rest.model.Countries;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by neosoft on 29/12/16.
 */

public interface RestClient {
    String SERVICE_BASEURL = "http://services.groupkt.com";

    @GET("country/get/all")
    Observable<Countries> getUser();
}
