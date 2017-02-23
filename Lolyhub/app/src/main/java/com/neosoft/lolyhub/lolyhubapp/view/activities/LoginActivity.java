package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
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
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.utilities.CommonUtils;
import com.neosoft.lolyhub.lolyhubapp.utilities.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by neosoft on 9/1/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    //views for login module
    private EditText mEdit_UserMobuile;
    private EditText mEdit_UserPassword;
    private TextView mTxt_ForgotPassword;
    private TextView mPressHere;

    private Button mBtn_Login;
    //hhjhh
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initValues();
        initListener();
    }
    private void initValues(){
        //sign in values
        mEdit_UserMobuile= (EditText) findViewById(R.id.edit_userMobile);
        mEdit_UserPassword= (EditText) findViewById(R.id.edit_userPassword);
        mTxt_ForgotPassword= (TextView) findViewById(R.id.txt_forgotPass);
        mPressHere= (TextView) findViewById(R.id.txt_pressHere);
        mBtn_Login= (Button) findViewById(R.id.btn_login);
        mPressHere.setPaintFlags(mPressHere.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mPressHere.setText(R.string.pressHere);

        Drawable questionMarkDrawable=CommonUtils.resizeDrawable(this,ContextCompat.getDrawable(this,R.drawable.question_mark));
        //  mEdit_UserPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, questionMarkDrawable, 0);
        mEdit_UserPassword.setCompoundDrawablesWithIntrinsicBounds(null,null,questionMarkDrawable,null);

    }
    private void initListener(){
        mBtn_Login.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                ValidationUtils validationUtils=new ValidationUtils();
                if (validationUtils.validateSignIn(LoginActivity.this,mEdit_UserMobuile,mEdit_UserPassword)){
                    Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this,HomeActivity.class);
                    this.startActivity(intent);
                }
        }
    }



}
