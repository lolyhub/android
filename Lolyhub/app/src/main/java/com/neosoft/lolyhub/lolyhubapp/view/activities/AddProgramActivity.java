package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rajendra on 8/5/17.
 */

public class AddProgramActivity extends AppCompatActivity{
    @BindView(R.id.searchIcon)ImageView mSearchIcon;
    @BindView(R.id.searchTxt)TextView mSerachTxt;
    @BindView(R.id.id_qitaftxt)TextView mProgramQitafTxt;
    @BindView(R.id.id_qitafImg)ImageView mProgramQitafImg;
    @BindView(R.id.id_addProgramButton)Button mProgramAddButton;
    @BindView(R.id.id_registerqitaftxt)TextView mRegisterQitafTxt;
    @BindView(R.id.id_registerqitafImg)ImageView mRegisterQitafImg;
    @BindView(R.id.id_updateInfoButton)Button mUpdateInfoButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprogram);
        ButterKnife.bind(this);
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
            AddProgramActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
