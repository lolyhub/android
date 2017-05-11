package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.ApplicationUtil;
import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.NetworkReceiver;
import com.neosoft.lolyhub.lolyhubapp.rest.NetworkCall;
import com.neosoft.lolyhub.lolyhubapp.rest.model.Errors;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.RequestLogin;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.LoginResponse;
import com.neosoft.lolyhub.lolyhubapp.utilities.CommonUtils;
import com.neosoft.lolyhub.lolyhubapp.utilities.ValidationUtils;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by neosoft on 9/1/17.
 */

public class LoginActivity extends AppCompatActivity implements  GoogleApiClient.OnConnectionFailedListener{
    //views for login module
    @BindView(R.id.edit_userMobile) EditText mEdit_UserMobuile;
    @BindView(R.id.edit_userPassword)EditText mEdit_UserPassword;
    @BindView(R.id.txt_forgotPass) TextView mTxt_ForgotPassword;
    @BindView(R.id.txt_pressHere) TextView mPressHere;
    @BindView(R.id.btn_login) Button mBtn_Login;
    //Gplus sign in
    private  ProgressDialog mProgressDialog;
    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private GoogleApiAvailability google_api_availability;
    @BindView(R.id.gplus_signout)Button mGplusSignOut;
    @BindView(R.id.gplusLogin_ID)ImageView mGplusSignIn;
    private NetworkReceiver mReceiver;
    private ProgressBar mProgressBar;
  //twiiter login
    //facebook login
    @BindView(R.id.fbSignOut_signout) Button mFbSignOut;

    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    @BindView(R.id.fbLogin_ID) ImageView mFbLoginButton;
    //Facebook login button
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Profile profile = Profile.getCurrentProfile();
            Toast.makeText(LoginActivity.this, "current profile name="+profile.getFirstName(), Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCancel() {        }
        @Override
        public void onError(FacebookException e) {      }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initFbCallBack();

        mGoogleApiClient=((ApplicationUtil)getApplication()).getGoogleApiClient(LoginActivity.this,this);
        initValues();
    }
    private void initValues(){
        mProgressBar= (ProgressBar) findViewById(R.id.login_progress_id);
        mPressHere.setPaintFlags(mPressHere.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mPressHere.setText(R.string.pressHere);
        Drawable questionMarkDrawable=CommonUtils.resizeDrawable(this,ContextCompat.getDrawable(this,R.drawable.question_mark));
        mEdit_UserPassword.setCompoundDrawablesWithIntrinsicBounds(null,null,questionMarkDrawable,null);
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d("TAG", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void initFbCallBack(){
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                // nextActivity(profile);
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });


        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
                if (newToken == null) {
                    //write your code here what to do when user logout

                    LoginManager.getInstance().logOut();
                }

            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                // nextActivity(newProfile);
                if (newProfile!=null){
                    Toast.makeText(LoginActivity.this, "on current profile changed="+newProfile.getFirstName(), Toast.LENGTH_SHORT).show();

                }else if (newProfile ==null){
                    LoginManager.getInstance().logOut();
                }

            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }
    // [START onActivityResult]

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CommonUtils.removeFocus(LoginActivity.this);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }else
        {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
          //  mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            Toast.makeText(this, "successfully signed in with G plus"+acct.getDisplayName(), Toast.LENGTH_SHORT).show();

            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END signOut]

    // [START revokeAccess]
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END revokeAccess]
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }
    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            mGplusSignIn.setVisibility(View.GONE);
            mGplusSignOut.setVisibility(View.VISIBLE);
        } else {
           // Toast.makeText(this, "Gplus sign out", Toast.LENGTH_SHORT).show();

            mGplusSignIn.setVisibility(View.VISIBLE);
            mGplusSignOut.setVisibility(View.GONE);
        }
    }
    /*
     login button fucntionality
     */
    @OnClick(R.id.btn_login)public void submitLogin(){
        ValidationUtils validationUtils=new ValidationUtils();
        if (validationUtils.validateSignIn(LoginActivity.this,mEdit_UserMobuile,mEdit_UserPassword)){
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.bringToFront();
            NetworkCall networkCall=new NetworkCall(LoginActivity.this);
            networkCall.loginWSCall(new RequestLogin(mEdit_UserMobuile.getText().toString(),mEdit_UserPassword.getText().toString()));
        }
    }

    /**
     * facebook login button
     */
    @OnClick(R.id.fbLogin_ID)void fbLogin(){
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }
    /*
    Sign in with google Plus login
     */
    @OnClick(R.id.gplusLogin_ID)void submitGplus(){
        signIn();
    }
    /*
     google sign out
     */
    @OnClick(R.id.gplus_signout)void submitSignOut(){
        signOut();
    }
    @OnClick(R.id.fbSignOut_signout)void fbSignOut(){
         logout();
    }
    private void logout(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken != null){
            LoginManager.getInstance().logOut();
        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected void onStop() {
        super.onStop();
        //Facebook login
        EventBus.getDefault().unregister(this);
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }
    @Subscribe
    public void OnGetLoginResponse(LoginResponse loginResponse){
        mProgressBar.setVisibility(View.GONE);
        if (loginResponse.getResultMsg().equalsIgnoreCase("Done")){
            sendToHomeScreen();
        }else if (loginResponse.getResultMsg().equalsIgnoreCase("Fail")){
            Toast.makeText(LoginActivity.this, "Something went wrong ...please try again", Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe
    public void OnError(Errors errorMsg){
        Toast.makeText(LoginActivity.this, ""+errorMsg, Toast.LENGTH_SHORT).show();
    }
    /**
     * method to go to home screen if login is successfull
     */
   public void sendToHomeScreen(){
       Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
       this.startActivity(intent);
       this.finish();
   }
}
