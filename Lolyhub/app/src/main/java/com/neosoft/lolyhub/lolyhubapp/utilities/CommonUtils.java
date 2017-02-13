package com.neosoft.lolyhub.lolyhubapp.utilities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.lolyhub.lolyhubapp.R;

/**
 * Created by neosoft on 2/2/17.
 */

public class CommonUtils  {

    public CommonUtils(){

    }
    public Drawable resizeDrawable(Activity activity, Drawable drawable){
        Drawable dr = ContextCompat.getDrawable(activity,R.drawable.question_mark);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(activity.getResources(), Bitmap.createScaledBitmap(bitmap, 35, 35, true));
        return d;
    }
}
