package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.TestMethod;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.ApplicationUtil;
import com.neosoft.lolyhub.lolyhubapp.utilities.CommonUtils;
import com.neosoft.lolyhub.lolyhubapp.utilities.Constants;
import com.neosoft.lolyhub.lolyhubapp.utilities.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by neosoft on 9/1/17.
 */

public class LoginActivity extends AppCompatActivity implements  GoogleApiClient.OnConnectionFailedListener{
    //views for login module
    @BindView(R.id.edit_userMobile) EditText mEdit_UserMobuile;
    @BindView(R.id.edit_userPassword)EditText mEdit_UserPassword;
    @BindView(R.id.txt_forgotPass) TextView mTxt_ForgotPassword;
    @BindView(R.id.txt_pressHere) TextView mPressHere;
    @BindView(R.id.btn_login) Button mBtn_Login;
    //Gplus sign in
    private  ProgressDialog mProgressDialog;
    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private GoogleApiAvailability google_api_availability;
    @BindView(R.id.gplus_signout)Button mGplusSignOut;
    @BindView(R.id.gplusLogin_ID)ImageView mGplusSignIn;
    //hhjhh
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mGoogleApiClient=((ApplicationUtil)getApplication()).getGoogleApiClient(LoginActivity.this,this);
        initValues();
    }
    private void initValues(){
        mPressHere.setPaintFlags(mPressHere.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mPressHere.setText(R.string.pressHere);
        Drawable questionMarkDrawable=CommonUtils.resizeDrawable(this,ContextCompat.getDrawable(this,R.drawable.question_mark));
        mEdit_UserPassword.setCompoundDrawablesWithIntrinsicBounds(null,null,questionMarkDrawable,null);
    }
    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d("TAG", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CommonUtils.removeFocus(LoginActivity.this);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
          //  mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            Toast.makeText(this, "successfully signed in with G plus"+acct.getDisplayName(), Toast.LENGTH_SHORT).show();

            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END signOut]

    // [START revokeAccess]
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END revokeAccess]
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }
    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            mGplusSignIn.setVisibility(View.GONE);
            mGplusSignOut.setVisibility(View.VISIBLE);
        } else {
           // Toast.makeText(this, "Gplus sign out", Toast.LENGTH_SHORT).show();

            mGplusSignIn.setVisibility(View.VISIBLE);
            mGplusSignOut.setVisibility(View.GONE);
        }
    }
    /*
     login button fucntionality
     */
    @OnClick(R.id.btn_login)public void submitLogin(){
        ValidationUtils validationUtils=new ValidationUtils();
        if (validationUtils.validateSignIn(LoginActivity.this,mEdit_UserMobuile,mEdit_UserPassword)){
            Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            this.startActivity(intent);
            this.finish();
        }
    }
    /*
    Sign in with google Plus login
     */
    @OnClick(R.id.gplusLogin_ID)void submitGplus(){
        signIn();
    }
    /*
     google sign out
     */
    @OnClick(R.id.gplus_signout)void submitSignOut(){
        signOut();
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
