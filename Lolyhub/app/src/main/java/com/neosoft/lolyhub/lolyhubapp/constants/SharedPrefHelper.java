package com.neosoft.lolyhub.lolyhubapp.constants;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by neosoft on 10/5/17.
 */

public class SharedPrefHelper {

    /**
     * Set a string shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setStringPref(Context context,String key,String value){
        SharedPreferences setings=context.getSharedPreferences(PrefConstant.LoginPref,0);
        SharedPreferences.Editor editor=setings.edit();
        editor.putString(key,value);
        editor.apply();

    }
    /**
     * Get a string shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static String getSharedPreferenceString(Context context, String key, String defValue){
        SharedPreferences settings = context.getSharedPreferences(PrefConstant.LoginPref, 0);
        return settings.getString(key, defValue);
    }
    /**
     * Set a integer shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setSharedPreferenceInt(Context context, String key, int value){
        SharedPreferences settings = context.getSharedPreferences(PrefConstant.LoginPref, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }
    /**
     * Get a integer shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static int getSharedPreferenceInt(Context context, String key, int defValue){
        SharedPreferences settings = context.getSharedPreferences(PrefConstant.LoginPref, 0);
        return settings.getInt(key, defValue);
    }
    /**
     * Set a Boolean shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setSharedPreferenceBoolean(Context context, String key, boolean value){
        SharedPreferences settings = context.getSharedPreferences(PrefConstant.LoginPref, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Get a boolean shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static boolean getSharedPreferenceBoolean(Context context, String key, boolean defValue){
        SharedPreferences settings = context.getSharedPreferences(PrefConstant.LoginPref, 0);
        return settings.getBoolean(key, defValue);
    }

}
