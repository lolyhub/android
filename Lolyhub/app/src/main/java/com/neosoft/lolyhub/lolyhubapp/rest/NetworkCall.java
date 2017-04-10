package com.neosoft.lolyhub.lolyhubapp.rest;

import android.app.Activity;
import android.util.Log;

import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
import com.neosoft.lolyhub.lolyhubapp.constants.URLCONSTANTS;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.NetworkReceiver;
import com.neosoft.lolyhub.lolyhubapp.rest.model.Countries;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.RequestLogin;
import com.neosoft.lolyhub.lolyhubapp.rest.service.RestClient;
import com.neosoft.lolyhub.lolyhubapp.rest.service.ServiceFactory;

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

    public NetworkCall(Activity mActivity, NetworkReceiver mReceiver) {
        this.mReceiver = mReceiver;
        this.mActivity = mActivity;
    }
    public void fetchWSCall(){
        RestClient service = ServiceFactory.createRetrofitService(RestClient.class, URLCONSTANTS.BASE_URL);
        service.getUserLogin()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RequestLogin>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                        Log.d(TAG,"onCompleted");
                    }
                    @Override
                    public final void onError(Throwable e) {
                        Log.d(TAG,"onError"+e.getMessage());
                        mReceiver.onError(e.getMessage());
                    }
                    @Override
                    public final void onNext(RequestLogin response) {
                        Log.d(TAG,"onNext"+response.getUserEmail());

                        postResult(response, CommonConstant.TAG_COUNTRY);

                    }
                });
    }
    public void postResult(RequestLogin countries, int tag){

        switch (tag){
            case CommonConstant.TAG_COUNTRY:
                 mReceiver.onResponse(countries,tag);
                break;
            default:
                Log.d("default","default");
                break;
        }
    }

}
