package com.neosoft.lolyhub.lolyhubapp.utilities;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by neosoft on 11/1/17.
 */

public class ValidationUtils {
    private  Pattern usrNamePtrn = Pattern.compile("^[a-z0-9._-]{3,16}$");

    public boolean validateSignIn(Context context, EditText userMob, EditText password){
        ValidationUtils ValidationUtils=new ValidationUtils();
        if(!TextUtils.isEmpty(userMob.getText())&&!TextUtils.isEmpty(password.getText())){
            if (ValidationUtils.isValidPhoneNumber(userMob.getText()) || ValidationUtils.validateUserName(userMob.getText().toString())||ValidationUtils.isValidEmail(userMob.getText().toString())){
                return true;
            }else{
                userMob.setError(context.getResources().getString(R.string.validateMbileNumber));
                userMob.requestFocus();
            }
        }
        if (TextUtils.isEmpty(password.getText())){
            password.setError(context.getResources().getString(R.string.notEmpty));
            password.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(userMob.getText())){
            userMob.setError(context.getResources().getString(R.string.notEmpty));
            userMob.requestFocus();
            return false;
        }
        return  false;
    }
    public boolean validateSignUp(Context context, EditText firstName, EditText lastName, EditText userMail, EditText confirmMail, EditText userName, EditText userPass, EditText confirmPass, TextView birthday,String gender,String country,String city,EditText mobileNumber, boolean isChecked){
        ValidationUtils ValidationUtils=new ValidationUtils();

        if (!TextUtils.isEmpty(firstName.getText()) && !TextUtils.isEmpty(lastName.getText()) && !TextUtils.isEmpty(userMail.getText())
                && !TextUtils.isEmpty(confirmMail.getText()) && !TextUtils.isEmpty(userName.getText()) && !TextUtils.isEmpty(userPass.getText()) && !TextUtils.isEmpty(confirmPass.getText()) && !TextUtils.isEmpty(birthday.toString()) && !TextUtils.isEmpty(gender) && !TextUtils.isEmpty(country) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(mobileNumber.getText())) {




            if (!(userMail.getText().toString()).equals(confirmMail.getText().toString())) {
                confirmMail.setError(context.getResources().getString(R.string.emailConfirmMsg));
                confirmMail.requestFocus();
                return false;
            }
            if (!ValidationUtils.isValidEmail(userMail.getText())) {
                userMail.setError(context.getResources().getString(R.string.valid_mail));
                userMail.requestFocus();
                return false;
            }
            if (!ValidationUtils.isValidEmail(confirmMail.getText())) {
                confirmMail.setError(context.getResources().getString(R.string.valid_mail));
                confirmMail.requestFocus();
                return false;
            }
            if (!isChecked) {
                Toast.makeText(context, "please select terms and condition", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (!(userPass.getText().toString()).equals(confirmPass.getText().toString())) {
                confirmPass.setError(context.getResources().getString(R.string.validate_missmatchpassword));
                confirmPass.requestFocus();
                return false;
            }

            if (!ValidationUtils.isValidPhoneNumber(mobileNumber.getText())) {
                mobileNumber.setError(context.getResources().getString(R.string.validateMbileNumber));
                mobileNumber.requestFocus();
                return false;
            }
            return true;

        }else{
            if (TextUtils.isEmpty(firstName.getText())){
                firstName.setError(context.getResources().getString(R.string.validate_firstname));
                firstName.requestFocus();
                return false;

            }

            if (TextUtils.isEmpty(lastName.getText())){
                lastName.setError(context.getResources().getString(R.string.validate_lastname));
                lastName.requestFocus();
                return false;
            }

            if (TextUtils.isEmpty(userMail.getText())){
                userMail.setError(context.getResources().getString(R.string.validate_mail));
                userMail.requestFocus();
                return false;
            }

            if (TextUtils.isEmpty(confirmMail.getText())){
                confirmMail.setError(context.getResources().getString(R.string.validate_confirmmail));
                confirmMail.requestFocus();
                return false;
            }

            if (TextUtils.isEmpty(userName.getText())){
                userName.setError(context.getResources().getString(R.string.validate_username));
                userName.requestFocus();
                return false;
            }

            if (TextUtils.isEmpty(userPass.getText())){
                userPass.setError(context.getResources().getString(R.string.validate_password));
                userPass.requestFocus();
                return false;
            }
            if (TextUtils.isEmpty(confirmPass.getText())){
                confirmPass.setError(context.getResources().getString(R.string.validate_confirmpassword));
                confirmPass.requestFocus();
                return false;
            }
            if (TextUtils.isEmpty(birthday.toString())){
                birthday.setError(context.getResources().getString(R.string.validate_birthdate));
                birthday.requestFocus();
                return false;
            }
            if (TextUtils.isEmpty(gender)){
                Toast.makeText(context, "please select Gender", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(country)){
                Toast.makeText(context, "please select Country", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(city)){
                Toast.makeText(context, "please select City", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(mobileNumber.getText())){
                mobileNumber.setError(context.getResources().getString(R.string.validate_mobileNumber));
                mobileNumber.requestFocus();
                return false;
            }
            if (!isChecked) {
                Toast.makeText(context, "please select terms and condition", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return  false;
    }
    public boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
        return false;
    }
    public boolean validateUserName(String userName) {

        Matcher mtch = usrNamePtrn.matcher(userName);
        if (mtch.matches()) {
            return true;
        }
        return false;
    }


    public boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }
}
