package com.neosoft.onesignal.idealrestformat.view.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neosoft.onesignal.idealrestformat.R;

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

    //views for sign up modules
    private EditText mEdit_FirstName;
    private EditText mEdit_LastName;
    private EditText mEdit_Email;
    private EditText mEdit_ReEnterEmail;
    private EditText mEdit_UserName;
    private EditText mEdit_NewUserPassword;
    private EditText mEdit_NewUserReEnterPassword;
    private TextView mTxt_BirthDate;
    private DatePickerDialog mDatePicker;
    private SimpleDateFormat dateFormatter;

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
    }
    private void initListener(){
        mLinearLayout_SignIn.setOnClickListener(this);
        mLinearLayout_SignUp.setOnClickListener(this);
        mTxt_BirthDate.setOnClickListener(this);
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
        }
    }

}
