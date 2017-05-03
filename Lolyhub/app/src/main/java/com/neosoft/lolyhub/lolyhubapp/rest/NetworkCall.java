package com.neosoft.lolyhub.lolyhubapp.rest;

import android.app.Activity;
import android.util.Log;

import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
import com.neosoft.lolyhub.lolyhubapp.constants.URLCONSTANTS;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.NetworkReceiver;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.CartRequest;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.RegistrationRequest;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.RequestLogin;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.UpdateCartRequest;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.CartResponse;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.LoginResponse;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.RegistrationResponse;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.UpdateCartResponse;
import com.neosoft.lolyhub.lolyhubapp.rest.service.RestClient;
import com.neosoft.lolyhub.lolyhubapp.rest.service.ServiceFactory;

import org.greenrobot.eventbus.EventBus;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by neosoft on 30/12/16.
 */

public class NetworkCall {
    private static String TAG="NetworkCall";
    private Activity mActivity;
    private NetworkReceiver mReceiver;

    public NetworkCall(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void loginWSCall(RequestLogin requestLogin){
        RestClient service = ServiceFactory.createRetrofitService(RestClient.class, URLCONSTANTS.BASE_URL);
        service.getUserLogin(requestLogin)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public final void onCompleted() {
                        Log.d(TAG,"onCompleted");
                    }
                    @Override
                    public final void onError(Throwable e) {
                        EventBus.getDefault().post(e.getMessage());
                    }
                    @Override
                    public final void onNext(LoginResponse response) {
                        Log.d(TAG,"onNext="+response.toString());
                        EventBus.getDefault().post(response);
                    }
                });
    }

    public void registerWSCall(RegistrationRequest registrationRequestin){
        RestClient service = ServiceFactory.createRetrofitService(RestClient.class, URLCONSTANTS.BASE_URL);
        service.registerUser(registrationRequestin)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegistrationResponse>() {
                    @Override
                    public final void onCompleted() {
                        Log.d(TAG,"onCompleted");
                    }
                    @Override
                    public final void onError(Throwable e) {
                        EventBus.getDefault().post(e.getMessage());
                    }
                    @Override
                    public final void onNext(RegistrationResponse response) {
                        Log.d(TAG,"onNext="+response.toString());
                        EventBus.getDefault().post(response);
                    }
                });
    }
    /*
    this method can be used by addTocart, delketeFromCart, getItemCart request depending upon request pojo params
     */
    public void cartAndwishlistWSCall(CartRequest cartRequest,String name){
        RestClient service = ServiceFactory.createRetrofitService(RestClient.class, URLCONSTANTS.BASE_URL);
        if (name.equalsIgnoreCase(CommonConstant.ADDTOCART)){
            serviceCallCartWishlist(service,cartRequest,CommonConstant.ADDTOCART);
        }else if (name.equalsIgnoreCase(CommonConstant.DELETEFROMCART)){
            serviceCallCartWishlist(service,cartRequest,CommonConstant.DELETEFROMCART);
        }else if(name.equalsIgnoreCase(CommonConstant.GETITEMCART)){
            serviceCallCartWishlist(service,cartRequest,CommonConstant.GETITEMCART);
        }else if (name.equalsIgnoreCase(CommonConstant.ADDTOWISHLIST)){
            serviceCallCartWishlist(service,cartRequest,CommonConstant.ADDTOWISHLIST);
        }else if(name.equalsIgnoreCase(CommonConstant.DELETEFROMWISHLIST)){
            serviceCallCartWishlist(service,cartRequest,CommonConstant.DELETEFROMWISHLIST);
        }else if (name.equalsIgnoreCase(CommonConstant.GETITEMWISHLIST)){
            serviceCallCartWishlist(service,cartRequest,CommonConstant.GETITEMWISHLIST);
        }

    }
    public void updateCartServiceCall(UpdateCartRequest updateCartRequest){
        RestClient service = ServiceFactory.createRetrofitService(RestClient.class, URLCONSTANTS.BASE_URL);
        service.updateCartData(updateCartRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateCartResponse>() {
                    @Override
                    public final void onCompleted() {
                        Log.d(TAG,"onCompleted");
                    }
                    @Override
                    public final void onError(Throwable e) {
                        EventBus.getDefault().post(e.getMessage());
                    }
                    @Override
                    public final void onNext(UpdateCartResponse response) {
                        Log.d(TAG,"onNext="+response.toString());

                        EventBus.getDefault().postSticky(response);
                    }
                });
    }
public void serviceCallCartWishlist(RestClient service, CartRequest cartRequest, final String name){
            service.addToCart(cartRequest)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<CartResponse>() {
                @Override
                public final void onCompleted() {
                    Log.d(TAG,"onCompleted");
                }
                @Override
                public final void onError(Throwable e) {
                    EventBus.getDefault().post(e.getMessage());
                }
                @Override
                public final void onNext(CartResponse response) {
                    Log.d(TAG,"onNext="+response.toString());
                    response.setRequestType(name);
                    EventBus.getDefault().postSticky(response);
                }
            });
  }
}
