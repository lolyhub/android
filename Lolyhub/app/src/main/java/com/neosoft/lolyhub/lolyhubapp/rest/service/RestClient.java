package com.neosoft.lolyhub.lolyhubapp.rest.service;

import com.neosoft.lolyhub.lolyhubapp.rest.model.Countries;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.RequestLogin;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.LoginResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by neosoft on 29/12/16.
 */

public interface RestClient {
    @Headers("Content-Type: application/json")
    @POST("/Service1.svc/login")
    Observable<LoginResponse> getUserLogin(@Body RequestLogin requestLogin);

}
