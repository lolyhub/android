package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.utilities.AlphaTextView;

/**
 * Created by rajendra on 1/3/17.
 */

public class WishlistActivity extends AppCompatActivity {
    TextView mAlphaTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wishlist);
        AlphaTextView alphaTextView=new AlphaTextView(this);

         mAlphaTextView= (TextView)findViewById(R.id.alphtxtView);

        mAlphaTextView.getBackground().setAlpha(255);

    }
}
