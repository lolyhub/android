package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by neosoft on 3/5/17.
 */

public class SettingActivity extends AppCompatActivity {
    @BindView(R.id.homeImage_Id)ImageView mHomeImg;
    @BindView(R.id.homeTxtID)TextView mHomeTxt;
    @BindView(R.id.adProgramImg)ImageView adProgramImg;
    @BindView(R.id.adProgramTxt)ImageView adProgramTxt;
    @BindView(R.id.orderhistoryImg)ImageView orderHistoryImg;
    @BindView(R.id.orderhistoryTxt)ImageView orderHistoryTxt;
    @BindView(R.id.accountSettingImg)ImageView accountSettingImg;
    @BindView(R.id.acountSettingTxt)ImageView accountSettingTxt;
    @BindView(R.id.signOutImg)ImageView mSignOutImg;
    @BindView(R.id.signOutTxt)TextView mSignOutTxt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }
    @OnClick(R.id.homeTxtID)void homeSetting(){

    }
    @OnClick(R.id.adProgramTxt)void addProgramSetting(){

    }
    @OnClick(R.id.orderhistoryTxt)void historySetting(){

    }
    @OnClick(R.id.acountSettingTxt)void accountSettingting(){

    }
    @OnClick(R.id.signOutTxt)void signOutSetting(){

    }
}
