package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.utilities.Utilities;
import com.neosoft.lolyhub.lolyhubapp.utilities.ValidationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by neosoft on 9/5/17.
 */

public class ForgotPasswordActivity extends AppCompatActivity {
    @BindView(R.id.forgotpass_emailid)EditText forgotEmail;
    @BindView(R.id.id_submit_btn)Button btnSubmitPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        ButterKnife.bind(this);
        initValues();
    }
    private void initValues(){
        Toolbar toolbar= (Toolbar)findViewById(R.id.forgotToobar_toolbar);
        setSupportActionBar(toolbar);
        Utilities utilities=new Utilities(this);
        utilities.setToolbar(this,toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @OnClick(R.id.id_submit_btn)void validateEmail(){
        String name=forgotEmail.getText().toString();
        if (ValidationUtils.isValidEmail(name)){
            // call api
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            ForgotPasswordActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
