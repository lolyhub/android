package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.TestMethod;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.utilities.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by neosoft on 9/1/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout mLinearLayout_SignIn;
    private LinearLayout mLinearLayout_SignUp;
    private View mLayout_SignIN;
    private View mLayout_SignUP;
    //views for login module
    private EditText mEdit_UserMobuile;
    private EditText mEdit_UserPassword;
    private CheckBox mRememberMe;
    private TextView mTxt_ForgotPassword;
    private Button mBtn_Login;
    //hhjhh

    //views for sign up modules
    private EditText mEdit_FirstName;
    private EditText mEdit_LastName;
    private EditText mEdit_Email;
    private EditText mEdit_ReEnterEmail;
    private EditText mEdit_UserName;
    private EditText mEdit_NewUserPassword;
    private EditText mEdit_NewUserReEnterPassword;
    private EditText mEdit_MobileNumber;
    private Spinner mSpinner_Gender;
    private Spinner mSpinner_Country;
    private Spinner mSpinner_City;
    private Spinner mSpinner_CoutryCode;
    private CheckBox mChk_Terms;
    private CheckBox mChk_Subscribe;
    private TextView mTxt_TermsCondtions;
    private Button mBtn_Registration;

    private  ArrayAdapter<String> spin_adapter;
    private TextView mTxt_BirthDate;
    private DatePickerDialog mDatePicker;
    private SimpleDateFormat dateFormatter;
    private String[] gender = { "Male", "Female" };//array of strings used to populate the spinner

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initValues();
        initListener();
    }
    private void initValues(){
        mLinearLayout_SignIn= (LinearLayout) findViewById(R.id.layout_signIn);
        mLinearLayout_SignUp= (LinearLayout) findViewById(R.id.layout_signUp);
        mLayout_SignIN=findViewById(R.id.layout_signIN);
        mLayout_SignIN.setVisibility(View.VISIBLE);
        mLayout_SignUP=findViewById(R.id.layout_signUP);
        mTxt_BirthDate= (TextView) findViewById(R.id.edit_birthDate);
        //sign in values
        mEdit_UserMobuile= (EditText) findViewById(R.id.edit_userMobile);
        mEdit_UserPassword= (EditText) findViewById(R.id.edit_userPassword);
        mRememberMe= (CheckBox) findViewById(R.id.chk_remember);
        mTxt_ForgotPassword= (TextView) findViewById(R.id.txt_forgotPassword);
        mBtn_Login= (Button) findViewById(R.id.btn_login);

        //sign up values
        mEdit_FirstName= (EditText) findViewById(R.id.edit_firstName);
        mEdit_LastName= (EditText) findViewById(R.id.edit_lastName);
        mEdit_Email= (EditText) findViewById(R.id.edit_email);
        mEdit_ReEnterEmail= (EditText) findViewById(R.id.edit_reEnterEmail);
        mEdit_UserName= (EditText) findViewById(R.id.edit_newUser);
        mEdit_NewUserPassword= (EditText) findViewById(R.id.edit_password);
        mEdit_NewUserReEnterPassword= (EditText) findViewById(R.id.edit_reEnterPassword);
        mEdit_MobileNumber= (EditText) findViewById(R.id.edit_userMobile);
        mSpinner_Gender= (Spinner) findViewById(R.id.select_gender);
        mSpinner_City= (Spinner) findViewById(R.id.select_city);
        mSpinner_Country= (Spinner) findViewById(R.id.select_country);
        mSpinner_CoutryCode= (Spinner) findViewById(R.id.select_codeCountry);
        mChk_Terms= (CheckBox) findViewById(R.id.checkBox_termsCondtions);
        mChk_Subscribe= (CheckBox) findViewById(R.id.checkBox_subscribe);
        mTxt_TermsCondtions= (TextView) findViewById(R.id.txt_termsCondtions);
        mBtn_Registration= (Button) findViewById(R.id.btn_Register_signUp);

    }
    private void initListener(){
        mLinearLayout_SignIn.setOnClickListener(this);
        mLinearLayout_SignUp.setOnClickListener(this);
        mTxt_BirthDate.setOnClickListener(this);
        mBtn_Login.setOnClickListener(this);
        spin_adapter = new ArrayAdapter<String>(LoginActivity.this, android.R.layout.simple_spinner_item, gender);

// setting adapters to spinners
        mSpinner_Gender.setAdapter(spin_adapter);
    }
    private void setDateTimeField() {
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        Calendar newCalendar = Calendar.getInstance();
        mDatePicker = new DatePickerDialog(LoginActivity.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mTxt_BirthDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout_signIn:
                mLayout_SignIN.setVisibility(View.VISIBLE);
                mLayout_SignUP.setVisibility(View.GONE);
                mLinearLayout_SignIn.setBackgroundColor(Color.parseColor("#ffffff"));
                mLinearLayout_SignUp.setBackgroundColor(ContextCompat.getColor(this, R.color.grey));
                break;
            case R.id.layout_signUp:
                mLayout_SignUP.setVisibility(View.VISIBLE);
                mLayout_SignIN.setVisibility(View.GONE);
                mLinearLayout_SignUp.setBackgroundColor(Color.parseColor("#ffffff"));
                mLinearLayout_SignIn.setBackgroundColor(ContextCompat.getColor(this, R.color.grey));
                break;
            case R.id.edit_birthDate:
                setDateTimeField();
                mDatePicker.show();
                break;
            case R.id.btn_login:
                ValidationUtils validationUtils=new ValidationUtils();
                validationUtils.validateSignIn(LoginActivity.this,mEdit_UserMobuile,mEdit_UserPassword);
                break;
        }
    }



}
