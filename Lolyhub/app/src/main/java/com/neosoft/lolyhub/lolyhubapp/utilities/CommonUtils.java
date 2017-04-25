package com.neosoft.lolyhub.lolyhubapp.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;

import java.util.ArrayList;

/**
 * Created by neosoft on 2/2/17.
 */

public class CommonUtils  {
    // step 1. create a MenuCreator


    public static Drawable resizeDrawable(Activity activity, Drawable drawable){
        Drawable dr = ContextCompat.getDrawable(activity,R.drawable.question_mark);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(activity.getResources(), Bitmap.createScaledBitmap(bitmap, 35, 35, true));
        return d;
    }
    public static void makeToast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
    //
    public static void CustomDialog(View dialogbox,Activity activity){

        Dialog dialog = new Dialog(activity);
        // it remove the dialog title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // set the laytout in the dialog
        dialog.setContentView(dialogbox);
        // set the background partial transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams param = window.getAttributes();
        // set the layout at right bottom
        param.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        // it dismiss the dialog when click outside the dialog frame
        dialog.setCanceledOnTouchOutside(true);
        // initialize the item of the dialog box, whose id is demo1
        View demodialog =(View) dialog.findViewById(R.id.cross);
        // it call when click on the item whose id is demo1.
        final Dialog finalDialog = dialog;
        demodialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // diss miss the dialog
                finalDialog.dismiss();
            }
        });
        // it show the dialog box
        dialog.show();
    }
   /* public  static void initDrawer(Activity activity){
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }*/

    public static void gainFocus(Context mContext,EditText mEditText ){
    //  mEditText.requestFocus();
       /* InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);*/
    }
    public static void closeFocus(Context context, EditText editText){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
    public static void removeFocus(Activity activity){
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static void deleteWalletRowDialog(Context context, final ArrayList<Integer> params, final int pos){
        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*0.70);
        int height = (int)(context.getResources().getDisplayMetrics().heightPixels*0.70);


        final AlertDialog builder=new AlertDialog.Builder(context,R.style.CustomAlertDialog)
        .setTitle(context.getResources().getString(R.string.alert_wallet_title))
        .setMessage(context.getResources().getString(R.string.alert_wallet_message))

                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   params.remove(pos);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        builder.show();
        builder.getWindow().setLayout(width, height);

    }

}
