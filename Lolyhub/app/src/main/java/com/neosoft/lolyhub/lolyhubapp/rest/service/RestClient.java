package com.neosoft.lolyhub.lolyhubapp.rest.service;

import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.CartRequest;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.RegistrationRequest;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.RequestLogin;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.UpdateCartRequest;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.CartResponse;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.LoginResponse;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.RegistrationResponse;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.UpdateCartResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by neosoft on 29/12/16.
 */

public interface RestClient {
   // @Headers("Content-Type: application/json")
    @POST("/Service1.svc/login")
    Observable<LoginResponse> getUserLogin(@Body RequestLogin requestLogin);

  //  @Headers("Content-Type: application/json")
    @POST("/Service1.svc/signup")
    Observable<RegistrationResponse> registerUser(@Body RegistrationRequest registrationRequest);

    //adding data to cart
    //@Headers("Content-Type: application/json")
    @POST("/Service1.svc/AddToCart")
    Observable<CartResponse> addToCart(@Body CartRequest addtoCartRequest);

    //deleting data from cart
   // @Headers("Content-Type: application/json")
    @POST("/Service1.svc/AddToCart")
    Observable<CartResponse> deleteFromCart(@Body CartRequest deleteCartRequest );

    //adding data to cart
   // @Headers("Content-Type: application/json")
    @POST("/Service1.svc/AddToCart")
    Observable<CartResponse> getItemCart(@Body CartRequest addtoCartRequest);


    //adding data to cart
    //@Headers("Content-Type: application/json")
    @POST("/Service1.svc/AddToWish")
    Observable<CartResponse> addToWishlist(@Body CartRequest addtoCartRequest);

    //deleting data from cart
    // @Headers("Content-Type: application/json")
    @POST("/Service1.svc/DeleteFromWishlist")
    Observable<CartResponse> deleteFromWishlist(@Body CartRequest deleteCartRequest );

    //adding data to cart
    // @Headers("Content-Type: application/json")
    @POST("/Service1.svc/getItemWishlist")
    Observable<CartResponse> getItemWishlist(@Body CartRequest addtoCartRequest);

  //updating data to cart
  // @Headers("Content-Type: application/json")
  @POST("/Service1.svc/getItemWishlist")
  Observable<UpdateCartResponse> updateCartData(@Body UpdateCartRequest updateCartRequest);
}
