package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
import com.neosoft.lolyhub.lolyhubapp.rest.NetworkCall;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.RegistrationRequest;
import com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos.RequestLogin;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.RegistrationResponse;
import com.neosoft.lolyhub.lolyhubapp.utilities.ValidationUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by neosoft on 31/1/17.
 */

public class SignUpActivity extends AppCompatActivity {

    //views for sign up modules
    @BindView(R.id.edit_firstName)
    EditText mFirstName;
    @BindView(R.id.edit_lastName)
    EditText mLastName;
    @BindView(R.id.edit_email)
    EditText mEmailName;
    @BindView(R.id.edit_password)
    EditText mPassword;
    @BindView(R.id.edit_ReEnterPassword)
    EditText mReenterPassword;
    @BindView(R.id.btn_Register_signUp)
    Button mSignUpButton;
    @BindView(R.id.fbLogin_ID)
    ImageView mFbLogin;
    @BindView(R.id.gplusLogin_ID)
    ImageView mGooglePlus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        initViews();
        initValues();
        initListener();
    }

    private void initViews() {
        //sign up values


    }

    private void initValues() {

    }

    private void initListener() {
    }

    @OnClick(R.id.btn_Register_signUp)
    void OnSubmitAction() {
        ValidationUtils validationUtils=new ValidationUtils();
        if (validationUtils.validateSignUp(SignUpActivity.this,mFirstName,mLastName,mEmailName,mPassword,mReenterPassword)){
          /*mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.bringToFront();
            NetworkCall networkCall=new NetworkCall(SignUpActivity.this);
            networkCall.loginWSCall(new RequestLogin(mEdit_UserMobuile.getText().toString(),mEdit_UserPassword.getText().toString()));*/
        }
    }

    @OnClick(R.id.fbLogin_ID)
    void OnFbSignup() {

    }

    @OnClick(R.id.gplusLogin_ID)
    void OnGoogleSignUp() {

    }

    @Subscribe
    public void OnRegistrationEvent(RegistrationResponse registrationResponse) {
        Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show();
        goToHomeScreen();

    }

    public void goToHomeScreen() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
