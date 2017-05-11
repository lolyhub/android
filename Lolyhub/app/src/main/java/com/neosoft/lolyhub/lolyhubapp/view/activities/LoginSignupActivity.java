package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.constants.PrefConstant;
import com.neosoft.lolyhub.lolyhubapp.constants.SharedPrefHelper;
import com.neosoft.lolyhub.lolyhubapp.rest.NetworkCall;
import com.neosoft.lolyhub.lolyhubapp.rest.model.Errors;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.RequestLogin;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.LoginResponse;
import com.neosoft.lolyhub.lolyhubapp.utilities.ValidationUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rajendra on 7/5/17.
 */

public class LoginSignupActivity extends AppCompatActivity{
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
    //sign up screen variables
    @BindView(R.id.edit_firstName)EditText mFirstName;
    @BindView(R.id.edit_lastName)EditText mLastName;
    @BindView(R.id.edit_email)EditText mEmail;
    @BindView(R.id.edit_password)EditText mPassword;
    @BindView(R.id.edit_ReEnterPassword)EditText mConfirmPass;
    @BindView(R.id.btn_Register_signUp)Button mRegisterButton;
    @BindView(R.id.id_fbSignUp)ImageView mFbSignUp;
    @BindView(R.id.id_GpSignUp)ImageView mGpSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_loginsignup);
        ButterKnife.bind(this);
       initValues();
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
    }
}
