package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.app.DatePickerDialog;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
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
    private TextView mGenderMale;
    private TextView mGenderFemale;
    private ToggleButton mToggleButton;

    private Button mBtn_Registration;

    private ArrayAdapter<String> spin_adapter;
    private ArrayAdapter<String> mCountryadapter;
    private ArrayAdapter<String> mCityAdapter;
    private TextView mTxt_BirthDate;
    private DatePickerDialog mDatePicker;
    private SimpleDateFormat dateFormatter;
    private LinearLayout mGenderLayout;
    private String mGenderString="";
    private String mCountryString="";
    private String mCityString="";
    private boolean isCheckedValue=false;
    private String[] gender = { "Male", "Female" };//array of strings used to populate the spinner

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initViews();
        initValues();
        initListener();
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
        mEdit_MobileNumber= (EditText) findViewById(R.id.edit_phoneNumber);
        mSpinner_Gender= (Spinner) findViewById(R.id.select_gender);
        mSpinner_City= (Spinner) findViewById(R.id.select_city);
        mSpinner_Country= (Spinner) findViewById(R.id.select_country);
        mSpinner_CoutryCode= (Spinner) findViewById(R.id.select_codeCountry);
        mPrivacyPolicy= (TextView) findViewById(R.id.txt_privacyPolicy);
        mTxt_TermsCondtions= (TextView) findViewById(R.id.txt_termsCondtions);
        mBtn_Registration= (Button) findViewById(R.id.btn_Register_signUp);
        mTxt_BirthDate= (TextView) findViewById(R.id.edit_birthDate);
        mGenderMale= (TextView) findViewById(R.id.male_id);
        mGenderFemale= (TextView) findViewById(R.id.female_id);
        mGenderLayout= (LinearLayout) findViewById(R.id.genderLayout_ID);
        mToggleButton= (ToggleButton) findViewById(R.id.toggle);

    }
    private void initValues(){
        mPrivacyPolicy.setPaintFlags(mPrivacyPolicy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mPrivacyPolicy.setText(R.string.privacyMsg);
        mTxt_TermsCondtions.setPaintFlags(mTxt_TermsCondtions.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mTxt_TermsCondtions.setText(R.string.termsMsg);
    }
    private void initListener(){
        mTxt_BirthDate.setOnClickListener(this);
        mGenderMale.setOnClickListener(this);
        mGenderFemale.setOnClickListener(this);
        mBtn_Registration.setOnClickListener(this);
        // spin_adapter = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_spinner_item, gender);
        mCountryadapter=new ArrayAdapter<String>(SignUpActivity.this,android.R.layout.simple_spinner_item, CommonConstant.COUNTRYLIST);
        mCityAdapter=new ArrayAdapter<String>(SignUpActivity.this,android.R.layout.simple_spinner_item, CommonConstant.CITYLIST);
        mSpinner_Country.setAdapter(mCountryadapter);
        mSpinner_City.setAdapter(mCityAdapter);
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    isCheckedValue=true;
                }
                else
                {
                    isCheckedValue=false;
                }
            }
        });
        mSpinner_Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                mCountryString = (String) parentView.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        mSpinner_City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                mCityString = (String) parentView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
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
            case R.id.male_id:
                mGenderMale.setBackgroundColor(ContextCompat.getColor(this,R.color.grey));
                mGenderMale.setTextColor(Color.WHITE);
                mGenderFemale.setTextColor(ContextCompat.getColor(this,R.color.grey));
                mGenderFemale.setBackgroundColor(Color.WHITE);
                mGenderLayout.setPadding(1,1,1,1);
                mGenderString="Male";

                break;
            case R.id.female_id:
                mGenderFemale.setBackgroundColor(ContextCompat.getColor(this,R.color.grey));
                mGenderFemale.setTextColor(Color.WHITE);
                mGenderMale.setTextColor(ContextCompat.getColor(this,R.color.grey));
                mGenderMale.setBackgroundColor(Color.WHITE);
                mGenderString="Female";
                mGenderLayout.setPadding(1,1,1,1);
                break;
            case R.id.btn_Register_signUp:
                if (validationUtils.validateSignUp(SignUpActivity.this,mEdit_FirstName,mEdit_LastName,mEdit_Email,mEdit_ReEnterEmail,mEdit_UserName,mEdit_NewUserPassword,mEdit_NewUserReEnterPassword,mTxt_BirthDate,mGenderString,mCountryString,mCityString,mEdit_MobileNumber,isCheckedValue)){
                    Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
