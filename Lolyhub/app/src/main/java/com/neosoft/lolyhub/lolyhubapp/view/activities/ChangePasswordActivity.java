package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.utilities.Utilities;
import com.neosoft.lolyhub.lolyhubapp.utilities.ValidationUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by neosoft on 9/5/17.
 */

public class ChangePasswordActivity extends AppCompatActivity {
    @BindView(R.id.id_saveBtn)Button saveButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_changepassword);
        initValues();
    }
    private void initValues(){
        Toolbar toolbar= (Toolbar) findViewById(R.id.addProgram_toolbar);
        setSupportActionBar(toolbar);
        Utilities utilities=new Utilities(this);
        utilities.setToolbar(this,toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @OnClick(R.id.id_saveBtn)void validatePassword(){
       /* String name=forgotEmail.getText().toString();
        if (ValidationUtils.isValidEmail(name)){
            // call api
        }*/
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            ChangePasswordActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
