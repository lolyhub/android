package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

/**
 * Created by neosoft on 8/3/17.
 */

public class GplusSignInActivity  extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks,ResultCallback {
    private GoogleApiClient mGoogleApiClient;
    GoogleApiAvailability google_api_availability;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // buildGoogleSignIn();

    }

   /* private void buildGoogleSignIn(){
        mGoogleApiClient= new GoogleApiClient.Builder(this)
                          .addConnectionCallbacks(this)
                           .addOnConnectionFailedListener(this)
                           .addApi(Plus)
    }*/

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onResult(@NonNull Result result) {

    }
}
