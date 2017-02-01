package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.utilities.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by neosoft on 31/1/17.
 */

public class SignUpActivity extends AppCompatActivity  implements View.OnClickListener{


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
    private TextView mPrivacyPolicy;
    private TextView mTxt_TermsCondtions;
    private Button mBtn_Registration;

    private ArrayAdapter<String> spin_adapter;
    private TextView mTxt_BirthDate;
    private DatePickerDialog mDatePicker;
    private SimpleDateFormat dateFormatter;
    private String[] gender = { "Male", "Female" };//array of strings used to populate the spinner

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initViews();
        initValues();


    }
private void initViews(){
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
    mPrivacyPolicy= (TextView) findViewById(R.id.txt_privacyPolicy);
    mTxt_TermsCondtions= (TextView) findViewById(R.id.txt_termsCondtions);
    mBtn_Registration= (Button) findViewById(R.id.btn_Register_signUp);
    mTxt_BirthDate= (TextView) findViewById(R.id.edit_birthDate);
}
    private void initValues(){
        mPrivacyPolicy.setPaintFlags(mPrivacyPolicy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mPrivacyPolicy.setText(R.string.privacyMsg);
        mTxt_TermsCondtions.setPaintFlags(mTxt_TermsCondtions.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mTxt_TermsCondtions.setText(R.string.termsMsg);
    }
    private void initListener(){
        mTxt_BirthDate.setOnClickListener(this);
        spin_adapter = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_spinner_item, gender);
// setting adapters to spinners
        mSpinner_Gender.setAdapter(spin_adapter);
    }
    private void setDateTimeField() {
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        Calendar newCalendar = Calendar.getInstance();
        mDatePicker = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mTxt_BirthDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public void onClick(View view) {
        ValidationUtils validationUtils=new ValidationUtils();
        switch (view.getId()){
            case R.id.edit_birthDate:
                setDateTimeField();
                mDatePicker.show();
                break;
            case R.id.btn_login:
//                if (validationUtils.validateSignIn(SignUpActivity.this,mEdit_UserMobuile,mEdit_UserPassword)){
//                    Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show();
//                }
                break;
        }
    }
}
