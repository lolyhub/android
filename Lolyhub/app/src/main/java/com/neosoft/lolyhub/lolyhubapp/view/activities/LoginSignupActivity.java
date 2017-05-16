package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.ApplicationUtil;
import com.neosoft.lolyhub.lolyhubapp.constants.PrefConstant;
import com.neosoft.lolyhub.lolyhubapp.constants.SharedPrefHelper;
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
 * Created by rajendra on 7/5/17.
 */

public class LoginSignupActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    @BindView(R.id.signin_layout)RelativeLayout mSignInButton;
    @BindView(R.id.signup_layout)RelativeLayout mSignUpButton;
    @BindView(R.id.id_loginLayout)View mLoginLayout;
    @BindView(R.id.id_signupLayout)View mSignUpLayout;
    @BindView(R.id.login_progress_id)ProgressBar mProgressBar;
    @BindView(R.id.id_rememberChk)CheckBox rememberChk;
    //login screen
    @BindView(R.id.edit_userMobile)EditText mLoginName;
    @BindView(R.id.edit_userPassword)EditText mLoginPassword;
    @BindView(R.id.btn_login)Button mLoginButton;
    @BindView(R.id.fbLogin_ID)ImageView mFbLoginButton;
    @BindView(R.id.gplusLogin_ID)ImageView mGpLogin;
    @BindView(R.id.txt_pressForgot)TextView mForgotPassword;
    private boolean isSaved=false;
    private ProgressDialog mProgressDialog;
    private static final int RC_SIGN_IN = 9001;
    //sign up screen variables
    @BindView(R.id.edit_firstName)EditText mFirstName;
    @BindView(R.id.edit_lastName)EditText mLastName;
    @BindView(R.id.edit_email)EditText mEmail;
    @BindView(R.id.edit_password)EditText mPassword;
    @BindView(R.id.edit_ReEnterPassword)EditText mConfirmPass;
    @BindView(R.id.btn_Register_signUp)Button mRegisterButton;
    @BindView(R.id.id_fbSignUp)ImageView mFbSignUp;
    @BindView(R.id.id_GpSignUp)ImageView mGpSignUp;
    public static String TAG="LOGINSCREEN";
    //google api client
    private GoogleApiClient mGoogleApiClient;
    //Facebook login button
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Profile profile = Profile.getCurrentProfile();
            Toast.makeText(LoginSignupActivity.this, "current profile name="+profile.getFirstName(), Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCancel() {        }
        @Override
        public void onError(FacebookException e) {      }
    };
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_loginsignup);
        FacebookSdk.sdkInitialize(getApplicationContext());
        ButterKnife.bind(this);
        initValues();
        initFbCallBack();
        mGoogleApiClient=((ApplicationUtil)getApplication()).getGoogleApiClient(LoginSignupActivity.this,this);
    }
    private void initValues(){
        EventBus.getDefault().register(this);
        mSignInButton.setBackgroundColor(ContextCompat.getColor(this,R.color.grey));
        mSignUpButton.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        mLoginLayout.setVisibility(View.VISIBLE);
    }
    private void setLoginValues(){
     isSaved= SharedPrefHelper.getSharedPreferenceBoolean(LoginSignupActivity.this, PrefConstant.saveLogin,false);
        if (isSaved){
            String userName=SharedPrefHelper.getSharedPreferenceString(LoginSignupActivity.this,PrefConstant.USERNAME,"");
            String userPass=SharedPrefHelper.getSharedPreferenceString(LoginSignupActivity.this,PrefConstant.PASSWORD,"");
            mLoginName.setText(userName);
            mPassword.setText(userPass);
            rememberChk.setChecked(true);
        }else{

        }
    }
    /*
     initializing fb values and set up fb login
     */
    private void initFbCallBack() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                // nextActivity(profile);
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();

                String value="facebook_signin";
                SharedPrefHelper.setStringPref(LoginSignupActivity.this, PrefConstant.FACEBOOK_SIGNIN,value);

                if (SharedPrefHelper.getSharedPreferenceString(LoginSignupActivity.this,PrefConstant.FACEBOOK_SIGNIN,"").equalsIgnoreCase(value)){
                    Log.d(TAG,"fblogin="+SharedPrefHelper.getSharedPreferenceString(LoginSignupActivity.this,PrefConstant.FACEBOOK_SIGNIN,""));

                }


                sendToHomeScreen();
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
                if (newProfile != null) {
                    Toast.makeText(LoginSignupActivity.this, "on current profile changed=" + newProfile.getFirstName(), Toast.LENGTH_SHORT).show();

                } else if (newProfile == null) {
                    LoginManager.getInstance().logOut();
                }
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }
    @Override
    public void onStart() {
        super.onStart();

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
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //  mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            Toast.makeText(this, "successfully signed in with G plus"+acct.getDisplayName(), Toast.LENGTH_SHORT).show();
            String value="google_signin";
            SharedPrefHelper.setStringPref(LoginSignupActivity.this, PrefConstant.GOOGLE_SIGNIN,value);
            sendToHomeScreen();
        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this, " user is loged out....", Toast.LENGTH_SHORT).show();
        }
    }
    private void gPlusSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
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
    /**
     * switch to login view
     */
    @OnClick(R.id.signin_layout)void loginView(){
        mLoginLayout.setVisibility(View.VISIBLE);
        mSignUpLayout.setVisibility(View.GONE);
        mSignInButton.setBackgroundColor(ContextCompat.getColor(this,R.color.grey));
        mSignUpButton.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
    }
    /**
     * switch to sign up view
     */
    @OnClick(R.id.signup_layout)void signUpView(){
        mLoginLayout.setVisibility(View.GONE);
        mSignUpLayout.setVisibility(View.VISIBLE);
        mSignInButton.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        mSignUpButton.setBackgroundColor(ContextCompat.getColor(this,R.color.grey));
    }

    /**
     * action used to login user with credetials
     *
     */
  @OnClick(R.id.btn_login)void OnLoginUser(){
      ValidationUtils validationUtils=new ValidationUtils();
      if(validationUtils.validateSignIn(LoginSignupActivity.this,mLoginName,mLoginPassword)){
          mProgressBar.setVisibility(View.VISIBLE);
          mProgressBar.bringToFront();
          NetworkCall networkCall=new NetworkCall(LoginSignupActivity.this);
          networkCall.loginWSCall(new RequestLogin(mLoginName.getText().toString(),mLoginPassword.getText().toString()));
      }
  }
    /**
     * action used to register user with required fields
     *
     */
  @OnClick(R.id.btn_Register_signUp)void OnRegisterUser(){

  }
    /**
     * this action is used to show forgot password activity
     */
    @OnClick(R.id.txt_pressForgot)void showForgotPasswordScreen(){
        startActivity(new Intent(LoginSignupActivity.this,ForgotPasswordActivity.class));
    }
 @OnClick(R.id.fbLogin_ID)void signWithFB(){
     LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));


 }
 @OnClick(R.id.gplusLogin_ID)void signInWithGP(){
     gPlusSignIn();
 }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CommonUtils.removeFocus(LoginSignupActivity.this);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }else
        {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
    /**
     * login response for login webservice call
     * @param loginResponse result
     */
    @Subscribe
    public void OnGetLoginResponse(LoginResponse loginResponse){
        mProgressBar.setVisibility(View.GONE);
        if (loginResponse.getResultMsg().equalsIgnoreCase("Done")){
            sendToHomeScreen();
        }else if (loginResponse.getResultMsg().equalsIgnoreCase("Fail")){
            Toast.makeText(LoginSignupActivity.this, "Something went wrong ...please try again", Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe
    public void OnError(Errors errorMsg){
        Toast.makeText(LoginSignupActivity.this, ""+errorMsg, Toast.LENGTH_SHORT).show();
    }
    /**
     * method to go to home screen if login is successfull
     */
    public void sendToHomeScreen(){
        Intent intent=new Intent(LoginSignupActivity.this,HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(intent);
        this.finish();

    }
    @Override
    protected void onStop() {
        super.onStop();
        //Facebook login
        EventBus.getDefault().unregister(this);
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
