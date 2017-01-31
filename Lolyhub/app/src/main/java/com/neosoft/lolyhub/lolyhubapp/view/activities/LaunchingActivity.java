package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lolyhub.lolyhubapp.R;


/**
 * Created by neosoft on 31/1/17.
 */

public class LaunchingActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mSignUpLaunchingButton;
    private Button mSignInLaunchingButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);
        mSignUpLaunchingButton= (Button) findViewById(R.id.btnSignUpLaunch);
        mSignInLaunchingButton= (Button) findViewById(R.id.btnLoginLaunch);
        mSignUpLaunchingButton.setOnClickListener(this);
        mSignInLaunchingButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUpLaunch:
                startActivity(new Intent(LaunchingActivity.this,SignUpActivity.class));

                break;
            case R.id.btnLoginLaunch:
                startActivity(new Intent(LaunchingActivity.this,LoginActivity.class));
                break;
        }

    }
}
