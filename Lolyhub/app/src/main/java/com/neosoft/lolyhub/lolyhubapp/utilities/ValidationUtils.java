package com.neosoft.lolyhub.lolyhubapp.utilities;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import com.lolyhub.lolyhubapp.R;


/**
 * Created by neosoft on 11/1/17.
 */

public class ValidationUtils {

    public void validateSignIn(Context context, EditText userMob, EditText password){
        ValidationUtils ValidationUtils=new ValidationUtils();
        if(!TextUtils.isEmpty(userMob.getText())&&!TextUtils.isEmpty(password.getText())){
            if (ValidationUtils.isValidPhoneNumber(userMob.getText())){

            }else{
                userMob.setError(context.getResources().getString(R.string.validateMbileNumber));
                userMob.requestFocus();
            }
        }
        if (TextUtils.isEmpty(password.getText())){
            password.setError(context.getResources().getString(R.string.notEmpty));
            password.requestFocus();
        }
        if (TextUtils.isEmpty(userMob.getText())){
            userMob.setError(context.getResources().getString(R.string.notEmpty));
            userMob.requestFocus();
        }
    }

    public boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Patterns.PHONE.matcher(phoneNumber).matches();
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
