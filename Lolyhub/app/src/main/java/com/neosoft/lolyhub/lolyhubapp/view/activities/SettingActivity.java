package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.ApplicationUtil;
import com.neosoft.lolyhub.lolyhubapp.constants.PrefConstant;
import com.neosoft.lolyhub.lolyhubapp.constants.SharedPrefHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by neosoft on 3/5/17.
 */

public class SettingActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    @BindView(R.id.homeImage_Id)ImageView mHomeImg;
    @BindView(R.id.homeTxtID)TextView mHomeTxt;
    @BindView(R.id.adProgramImg)ImageView adProgramImg;
    @BindView(R.id.adProgramTxt)TextView adProgramTxt;
    @BindView(R.id.orderhistoryImg)ImageView orderHistoryImg;
    @BindView(R.id.orderhistoryTxt)TextView orderHistoryTxt;
    @BindView(R.id.accountSettingImg)ImageView accountSettingImg;
    @BindView(R.id.acountSettingTxt)TextView accountSettingTxt;
    @BindView(R.id.signOutImg)ImageView mSignOutImg;
    @BindView(R.id.signOutTxt)TextView mSignOutTxt;
    @BindView(R.id.closeImg_Id)ImageView mCloseButton;
    //google api client
    private GoogleSignInOptions mSignInOptions;
    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, getGoogleSignInOptions())
                .build();
    }
    private void logout(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken != null){
            LoginManager.getInstance().logOut();
            Intent intent=new Intent(SettingActivity.this,LoginSignupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        Intent intent=new Intent(SettingActivity.this,LoginSignupActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        // [END_EXCLUDE]
                    }
                });
    }
    public GoogleSignInOptions getGoogleSignInOptions(){
        mSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestScopes(new Scope(Scopes.PROFILE))
                .build();

        return mSignInOptions;
    }
    @OnClick(R.id.homeTxtID)void homeSetting(){

    }
    @OnClick(R.id.adProgramTxt)void addProgramSetting(){
        startActivity(new Intent(SettingActivity.this,AddProgramActivity.class));
    }
    @OnClick(R.id.orderhistoryTxt)void historySetting(){
     startActivity(new Intent(SettingActivity.this,OrderHistoryActivity.class));
    }
    @OnClick(R.id.acountSettingTxt)void accountSettingting(){

    }
    @OnClick(R.id.signOutTxt)void signOutSetting(){

        String value=SharedPrefHelper.getSharedPreferenceString(SettingActivity.this, PrefConstant.FACEBOOK_SIGNIN,"");
        String valueGoole=SharedPrefHelper.getSharedPreferenceString(SettingActivity.this, PrefConstant.GOOGLE_SIGNIN,"");

        if (value.equalsIgnoreCase("facebook_signin")){
            SharedPrefHelper.clearStringPref(SettingActivity.this);
            logout();
        }else if (valueGoole.equalsIgnoreCase("google_signin")){
            SharedPrefHelper.clearStringPref(SettingActivity.this);
            signOut();
        }
    }
    @OnClick(R.id.closeImg_Id)void OnCloseWindow(){
        SettingActivity.this.finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection failed with Google Api client...try again", Toast.LENGTH_SHORT).show();
    }
}
