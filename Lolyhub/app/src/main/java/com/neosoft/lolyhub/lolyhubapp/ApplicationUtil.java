package com.neosoft.lolyhub.lolyhubapp;

import android.app.Application;
import android.provider.ContactsContract;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.neosoft.lolyhub.lolyhubapp.utilities.TypefaceUtil;
import com.neosoft.lolyhub.lolyhubapp.view.activities.LoginActivity;

/**
 * Created by neosoft on 29/12/16.
 */

public class ApplicationUtil extends Application{
    private GoogleSignInOptions mSignInOptions;
    private GoogleApiClient mGoogleApiClient;
    public LoginActivity activity;
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/OpenSans-Light.ttf");
    }
    public GoogleSignInOptions getGoogleSignInOptions(){
        mSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestScopes(new Scope(Scopes.PROFILE))
                .build();

        return mSignInOptions;
    }
    public GoogleApiClient getGoogleApiClient(LoginActivity activity, GoogleApiClient.OnConnectionFailedListener listener){
        this.activity = activity;
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this.activity, listener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, getGoogleSignInOptions())
                .build();
        return mGoogleApiClient;
    }
}
