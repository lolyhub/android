package com.neosoft.lolyhub.lolyhubapp;

import android.app.Application;

import com.neosoft.lolyhub.lolyhubapp.utilities.TypefaceUtil;

/**
 * Created by neosoft on 29/12/16.
 */

public class ApplicationUtil extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/OpenSans-Light.ttf");
    }
}
