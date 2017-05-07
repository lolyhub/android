package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;

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
    //login screen
    @BindView(R.id.edit_userMobile)EditText mLoginName;
    @BindView(R.id.edit_userPassword)EditText mLoginPassword;
    @BindView(R.id.txt_pressHere)TextView mForgotPass;
    @BindView(R.id.btn_login)Button mLoginButton;
    @BindView(R.id.fbLogin_ID)ImageView mFbLoginButton;
    @BindView(R.id.gplusLogin_ID)ImageView mGpLogin;

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
        mSignInButton.setBackgroundColor(ContextCompat.getColor(this,R.color.grey));
        mSignUpButton.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        mLoginLayout.setVisibility(View.VISIBLE);

    }
    @OnClick(R.id.signin_layout)void loginView(){
        mLoginLayout.setVisibility(View.VISIBLE);
        mSignUpLayout.setVisibility(View.GONE);
        mSignInButton.setBackgroundColor(ContextCompat.getColor(this,R.color.grey));
        mSignUpButton.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
    }
    @OnClick(R.id.signup_layout)void signUpView(){
        mLoginLayout.setVisibility(View.GONE);
        mSignUpLayout.setVisibility(View.VISIBLE);
        mSignInButton.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        mSignUpButton.setBackgroundColor(ContextCompat.getColor(this,R.color.grey));
    }
}
