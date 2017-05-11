package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lolyhub.lolyhubapp.R;

/**
 * Created by neosoft on 9/5/17.
 */

public class ContactUsActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_contactus);
        initValues();
    }
    private void initValues(){
        Toolbar toolbar= (Toolbar) findViewById(R.id.addProgram_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Title");
        toolbar.setTitleTextColor(ContextCompat.getColor(this,android.R.color.black));
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId()==android.R.id.home){
            ContactUsActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
